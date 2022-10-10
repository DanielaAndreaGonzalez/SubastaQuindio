/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Archivos;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.persistence.FechaUtil;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import co.edu.uniquindio.subastaQuindio.services.IModelFactoryService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ModelFactoryController implements IModelFactoryService{

	Archivos archivo = new Archivos("Usuarios");
	SubastaQuindio subastaQuindio;
	private static Persistencia persistencia =null;
	
	
	//***********************************Singleton***********************************************
	//Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder{
		//El constructor de Singleton puede ser llamado desde aquí al ser protected 
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}
	
	
	//Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance()
	{
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController()
	{
		System.out.println("Invocación clase singleton");
		inicializarDatos();
		//OJO NOTA: ACÁ SE INICIALIZAN LOS DATOS
		
		//Guardar un registro serializado binario
		guardarResourceXML();
		guardarResourceBinario();
		
		
		
	}
	
	private void inicializarSalvarDatos() {
		inicializarDatos();
		try {
			Persistencia.guardarUsuarios(getSubastaQuindio().getListaPersona());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void inicializarDatos()
	{		
		Persistencia.hacerBackupArchivosSerializados("model.xml", "model", ".xml");
		Persistencia.hacerBackupArchivosSerializados("usuarioBin.dat", "usuarioBin", ".dat");
		subastaQuindio= new SubastaQuindio();  
		archivo.crearArchivo();
	}
	
	
	/**
	 * Metodo que guarda en un archivo el texto en binario
	 */
	private void guardarResourceBinario() {
		Persistencia.guardarRecursoSubastaBinario(subastaQuindio);			
	}
	
	/**
	 * Metodo que guarda en un archivo el texto en binario
	 */
	private void guardarResourceXML() {
		Persistencia.guardarRecursoBancoXML(subastaQuindio);	
	}
	
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
			//guardar en archivo plano
			archivo.saveperson(persona);
			//guardar en binario
			
			//guardar en xml
			
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
		return getSubastaQuindio().getListaPersona();
	}

	
	
	
	
	
	
	
	//Verificar persona Existe
	
	
	
	

}
