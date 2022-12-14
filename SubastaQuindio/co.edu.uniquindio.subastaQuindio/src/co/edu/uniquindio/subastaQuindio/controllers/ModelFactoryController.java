/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import co.edu.uniquindio.subastaQuindio.exceptions.AnuncioException;
import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.PujaException;
import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Anunciante;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Archivos;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.Puja;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import co.edu.uniquindio.subastaQuindio.services.IModelFactoryService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ModelFactoryController implements IModelFactoryService,Runnable{

	Archivos archivo = new Archivos("Usuarios");
	SubastaQuindio subastaQuindio;
	Thread hiloServicio1GuardarXML;
	Thread hiloServicio2GuardarBinario;
	Thread hiloSerivcio3GuardarRegistroLog;
	Thread hiloServicio4GuardarPersona;
	boolean run;
	
	
	String host = "localhost";
	//int puerto = 8081;
	ServerSocket server;
	
	Socket socketComunicacion;
	Socket socketTranferenciaObjeto;
	
	DataOutputStream flujoSalidaComunicacion;
	DataInputStream flujoEntradaComunicacion;
	
	ObjectInputStream flujoEntradaObjeto;
	ObjectOutputStream flujoSalidaObjeto;
	
	BufferedReader entrada;
	boolean is;
	

	//***********************************Singleton***********************************************
	//Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder{
		//El constructor de Singleton puede ser llamado desde aqu? al ser protected 
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}
	
	
	//Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance()
	{
		return SingletonHolder.eINSTANCE;
	}
	public ModelFactoryController()
	{
		System.out.println("Invocaci?n clase singleton");
		System.out.println("Llamado... ");
		//inicializarDatos();
		try {
			crearConexion();
			solicitarInformacionPersistencia();			
			leerObjetoPersistenciaTransferido();
			Persistencia.guardarRegistroLog("Inicio sesion del usuario",1, "Inicio sesion");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}	
	private void cargarDatos()
	{
		ArrayList<Persona> listaPersonas = null;
		try {
			
			listaPersonas = Persistencia.cargarUsuarios();
			if (listaPersonas !=null && listaPersonas.size()>0) {
				subastaQuindio.setListaPersonas(listaPersonas);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void inicializarDatos()
	{		
		Persistencia.hacerBackupArchivosSerializados("usuarioBin.dat", "usuarioBin", ".dat");
		Persistencia.hacerBackupArchivosSerializados("model.xml", "model", ".xml");
		subastaQuindio= new SubastaQuindio();  
		archivo.crearArchivo();
		cargarDatos();
	}
	
	
	private void cargarResourceBinario()
	{
		subastaQuindio = Persistencia.cargarRecursoSubastaQuindioBinario();
	}
	
	private void cargarResourceXML() {
		subastaQuindio = Persistencia.cargarRecursoSubastaQuindioXML();
	}
	/**
	 * Metodo que guarda en un archivo el texto en binario
	 */
	private void guardarResourceBinario() {
		hiloServicio2GuardarBinario = new Thread(this);
		run=true;
		hiloServicio2GuardarBinario.start();
		//Persistencia.guardarRecursoSubastaBinario(subastaQuindio);			
	}
	
	private void guardarPersona()
	{
		hiloServicio4GuardarPersona = new Thread(this);
		run=true;
		hiloServicio4GuardarPersona.start();
	}
	/**
	 * 
	 */
	private void guardarResourceXML()
	{
		hiloServicio1GuardarXML = new Thread(this);
		run=true;
		hiloServicio1GuardarXML.start();
		//Persistencia.guardarResourceSubastaXML(subastaQuindio);
	}
	
	private void guardarRecursoLog(String mensaje,int nivel, String accion)
	{
		hiloSerivcio3GuardarRegistroLog = new Thread(this);
		run=true;
		hiloSerivcio3GuardarRegistroLog.start();
	}
	/**
	 * 
	 * @return
	 */
	public SubastaQuindio getSubastaQuindio()
	{
		return this.subastaQuindio;
	}
	
	public void setSubastaQuindio(SubastaQuindio subasta)
	{
		this.subastaQuindio = subasta;
	}

	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol){
		Persona persona = null;
		try {			
			persona = getSubastaQuindio().registerPerson(cedula, nombre, edad, usuario, contrasenia, rol);

			if(persona != null)
				guardarResourceXML();
				guardarResourceBinario();
				guardarRecursoLog("Se registr? el usuario",1,"register person-Model Factory");
			//guardar en archivo plano
			archivo.saveperson(persona);
			//guardar en binario
			//Persistencia.guardarRecursoSubastaBinario(subastaQuindio);
			//guardar en xml
			//Persistencia.guardarResourceSubastaXML(subastaQuindio);
			
			
		} catch (RegistroException e) {
			Persistencia.guardarRegistroLog(e.getMessage(),3, "ModelFactoryController - registerPerson ");
		}catch(IOException e)
		{
			Persistencia.guardarRegistroLog(e.getMessage(),3, "ModelFactoryController - registerPerson ");
		}
		return persona;
	}

	@Override
	public ArrayList<Persona> obtenerPerson(String cedula) {
		return getSubastaQuindio().getListaPersonas();
	}

	@Override
	public void run() {
		Thread hiloEjecucion = Thread.currentThread();
		System.out.println("Entro al run ");
		if(hiloServicio1GuardarXML == hiloEjecucion)
		{
			try {
				crearConexion();
				solicitarGuardarInformacionPersistencia();
				//enviarObjetoPersistenciaTransferido();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Persistencia.guardarResourceSubastaXML(subastaQuindio);
		}
		if(hiloServicio2GuardarBinario == hiloEjecucion)
		{
			try {
				crearConexion();
				solicitarGuardarInformacionPersistenciaBinaria();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Persistencia.guardarRecursoSubastaBinario(subastaQuindio);
		}
		if(hiloSerivcio3GuardarRegistroLog== hiloEjecucion)
		{
			try {
				crearConexion();	
				guardarRecursoLog("Informaci?n", 1, "acci?n");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Persistencia.guardarRecursoSubastaBinario(subastaQuindio);
		}	
	}
	
	@Override
	public Anuncio crearAnuncio(Date fechaPublicacion, Date fechaFin,Producto producto,Persona anunciante) {
		
		Anuncio anuncio = null;
		try {
			anuncio = getSubastaQuindio().crearAnuncio(fechaPublicacion, fechaFin, producto, anunciante);
			getSubastaQuindio().getListaAnuncios().add(anuncio);
			//guardar en binario}
			if(anuncio != null)
				guardarResourceXML();
				guardarResourceBinario();
			//Persistencia.guardarRecursoSubastaBinario(subastaQuindio);
			//guardar en xml
			//Persistencia.guardarResourceSubastaXML(subastaQuindio);
		} catch (AnuncioException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anuncio;
	}
	
	@Override
	public Puja crearPuja(String codigoPuja,String codigoProducto, String nombreProducto, String tipoProducto,
			String valorInicialProducto, String nombreAnunciante, double ofertaInicial, Date fechaPuja,Persona usuarioLogueado) {
		Puja puja=null;
		try {
			puja =getSubastaQuindio().crearPuja(codigoPuja,codigoProducto, nombreProducto, tipoProducto, valorInicialProducto, nombreAnunciante, ofertaInicial, fechaPuja,usuarioLogueado);
			
			if(puja != null)
				guardarResourceXML();
				guardarResourceBinario();
		} catch (PujaException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return puja;
	}

	@Override
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto, Persona usuarioLogueado)
	{
		Producto producto = null; 
		try {			
			
			producto = getSubastaQuindio().crearProducto(codigo, nombreProducto, descripcion,valorInicial, tipoProducto, foto, usuarioLogueado);
			
			//guardar en binario
			if(producto != null)
				guardarResourceXML();
				guardarResourceBinario();
				
			//Persistencia.guardarRecursoSubastaBinario(subastaQuindio);
			//guardar en xml
			//Persistencia.guardarResourceSubastaXML(subastaQuindio);
			
		} catch (ProductoException | IOException e) {
			// TODO Auto-generated catch block
			Persistencia.guardarRegistroLog(e.getMessage(),3, "ModelFactoryController - crearProducto ");
		}
		return producto;
	}
	//Verificar persona Existe
	
	
	private void crearConexion() throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		 socketComunicacion = new Socket("localhost", 9998);
		 socketTranferenciaObjeto = new Socket("localhost", 9999);

		 flujoEntradaComunicacion = new DataInputStream(socketComunicacion.getInputStream());
		 flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());


		 flujoEntradaObjeto = new ObjectInputStream(socketTranferenciaObjeto.getInputStream());
		 flujoSalidaObjeto = new ObjectOutputStream(socketTranferenciaObjeto.getOutputStream());

	}
		
	private void leerObjetoPersistenciaTransferido() throws IOException, ClassNotFoundException
	{
		subastaQuindio =  (SubastaQuindio) flujoEntradaObjeto.readObject();
		System.out.println("Objeto recibido");
		flujoEntradaObjeto.close();	
	}
	
	private void solicitarInformacionPersistencia() throws IOException
	{
		// TODO Auto-generated method stub
		try {
			flujoSalidaComunicacion.writeInt(1);
			//flujoSalidaComunicacion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void solicitarGuardarInformacionPersistencia() throws IOException
	{
		// TODO Auto-generated method stub
		try {
			flujoSalidaComunicacion.writeInt(2);
			flujoSalidaObjeto.writeObject(subastaQuindio);
			//flujoSalidaComunicacion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void solicitarGuardarInformacionPersistenciaBinaria() throws IOException
	{
		// TODO Auto-generated method stub
		try {
			flujoSalidaComunicacion.writeInt(4);
			flujoSalidaObjeto.writeObject(subastaQuindio);
			//flujoSalidaComunicacion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void solicitarGuardarRegistroLog() throws IOException
	{
		// TODO Auto-generated method stub
		try {
			flujoSalidaComunicacion.writeInt(5);
			flujoSalidaObjeto.writeObject(subastaQuindio);
			flujoSalidaComunicacion.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void enviarObjetoPersistenciaTransferido() throws IOException
	{
		flujoSalidaObjeto.writeObject(subastaQuindio);
		System.out.println("Objeto enviado");
		flujoSalidaObjeto.close();
	}
	
	
	
	
	/**
	 * @return the flujoSalidaComunicacion
	 */
	public DataOutputStream getFlujoSalidaComunicacion() {
		return flujoSalidaComunicacion;
	}

	/**
	 * @param flujoSalidaComunicacion the flujoSalidaComunicacion to set
	 */
	public void setFlujoSalidaComunicacion(DataOutputStream flujoSalidaComunicacion) {
		this.flujoSalidaComunicacion = flujoSalidaComunicacion;
	}

	/**
	 * @return the flujoEntradaComunicacion
	 */
	public DataInputStream getFlujoEntradaComunicacion() {
		return flujoEntradaComunicacion;
	}

	/**
	 * @param flujoEntradaComunicacion the flujoEntradaComunicacion to set
	 */
	public void setFlujoEntradaComunicacion(DataInputStream flujoEntradaComunicacion) {
		this.flujoEntradaComunicacion = flujoEntradaComunicacion;
	}

	/**
	 * @return the flujoEntradaObjeto
	 */
	public ObjectInputStream getFlujoEntradaObjeto() {
		return flujoEntradaObjeto;
	}

	/**
	 * @param flujoEntradaObjeto the flujoEntradaObjeto to set
	 */
	public void setFlujoEntradaObjeto(ObjectInputStream flujoEntradaObjeto) {
		this.flujoEntradaObjeto = flujoEntradaObjeto;
	}

	/**
	 * @return the flujoSalidaObjeto
	 */
	public ObjectOutputStream getFlujoSalidaObjeto() {
		return flujoSalidaObjeto;
	}

	/**
	 * @param flujoSalidaObjeto the flujoSalidaObjeto to set
	 */
	public void setFlujoSalidaObjeto(ObjectOutputStream flujoSalidaObjeto) {
		this.flujoSalidaObjeto = flujoSalidaObjeto;
	}

	

	
	
	
}
