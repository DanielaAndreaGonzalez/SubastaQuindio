package co.edu.uniquindio.subastaQuinidio;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.subastaQuindio.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;

public class HiloDesdeClienteAlServidor  extends Thread{
	private ObjectInputStream flujoEntradaObjeto;
	private ObjectOutputStream flujoSalidaObjeto;
	private DataInputStream flujoEntradaComunicacion;
	private DataOutputStream flujoSalidaComunicacion;
	private DataInputStream flujoEntradaLoginComunicacion;
	private DataOutputStream flujoSalidaLoginComunicacion;
	private Appservidor servidor;
	private int opcion;
	private SubastaQuindio subastaQuindio;
	
	
	public void inicializar(DataInputStream flujoEntradaComunicacion, DataOutputStream flujoSalidaComunicacion,
			ObjectInputStream flujoEntradaObjeto, ObjectOutputStream flujoSalidaObjeto, /*DataInputStream flujoEntradaLoginComunicacion,DataOutputStream flujoSalidaLoginComunicacion,*/Appservidor servidor) {
		// TODO Auto-generated method stub 
		this.flujoEntradaComunicacion = flujoEntradaComunicacion;
		this.flujoSalidaComunicacion = flujoSalidaComunicacion;
		this.flujoEntradaObjeto = flujoEntradaObjeto;
		this.flujoSalidaObjeto = flujoSalidaObjeto;
		
//		this.flujoEntradaLoginComunicacion =flujoEntradaLoginComunicacion; 
//		this.flujoSalidaLoginComunicacion =flujoSalidaLoginComunicacion; 
		
		this.servidor = servidor;
		
		
	}
	
	public void run() {
		try {
			opcion = flujoEntradaComunicacion.readInt();		
			System.out.println("La opcion enviada por cliente: " + opcion);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch (opcion) {
		case 1:
			try {
				enviarInformacionPersistencia();
				System.out.println("informacion envidada al cliente");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
//			try {
//				Persistencia.cargarAutores(aplicacion);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			break;
		case 2:
			try { 
				guardarInformacionPersistencia();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			try {
//				//Persistencia.cargarAutores(aplicacion);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		    break;
		}	
		try {
			int option = flujoEntradaComunicacion.readInt();
			String usuario = flujoEntradaComunicacion.readUTF();
			String contrasena = flujoEntradaComunicacion.readUTF();
			System.out.println("Solicitó el login opcion: " + option);
			if (option == 3) {				
				if (Persistencia.iniciarSesion(usuario, contrasena)) {
					flujoSalidaComunicacion.writeBoolean(true);
				}else {
					flujoSalidaComunicacion.writeBoolean(false);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void guardarInformacionPersistencia() throws Exception {
		// TODO Auto-generated method stub
		
		subastaQuindio = (SubastaQuindio) flujoEntradaObjeto.readObject();
		Persistencia.guardarResourceSubastaXML(subastaQuindio);	
	}
	private void enviarInformacionPersistencia() throws Exception {
		// TODO Auto-generated method stub
		//aplicacion = Persistencia.guardarRecursivoXMLBlockBuster(aplicacion);
		subastaQuindio = Persistencia.cargarRecursoSubastaQuindioXML();
		flujoSalidaObjeto.writeObject(subastaQuindio);
		
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
	 * @return the flujoEntradaLoginComunicacion
	 */
	public DataInputStream getFlujoEntradaLoginComunicacion() {
		return flujoEntradaLoginComunicacion;
	}

	/**
	 * @param flujoEntradaLoginComunicacion the flujoEntradaLoginComunicacion to set
	 */
	public void setFlujoEntradaLoginComunicacion(DataInputStream flujoEntradaLoginComunicacion) {
		this.flujoEntradaLoginComunicacion = flujoEntradaLoginComunicacion;
	}

	/**
	 * @return the flujoSalidaLoginComunicacion
	 */
	public DataOutputStream getFlujoSalidaLoginComunicacion() {
		return flujoSalidaLoginComunicacion;
	}

	/**
	 * @param flujoSalidaLoginComunicacion the flujoSalidaLoginComunicacion to set
	 */
	public void setFlujoSalidaLoginComunicacion(DataOutputStream flujoSalidaLoginComunicacion) {
		this.flujoSalidaLoginComunicacion = flujoSalidaLoginComunicacion;
	}

	/**
	 * @return the servidor
	 */
	public Appservidor getServidor() {
		return servidor;
	}

	/**
	 * @param servidor the servidor to set
	 */
	public void setServidor(Appservidor servidor) {
		this.servidor = servidor;
	}

	/**
	 * @return the opcion
	 */
	public int getOpcion() {
		return opcion;
	}

	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	/**
	 * @return the subastaQuindio
	 */
	public SubastaQuindio getSubastaQuindio() {
		return subastaQuindio;
	}

	/**
	 * @param subastaQuindio the subastaQuindio to set
	 */
	public void setSubastaQuindio(SubastaQuindio subastaQuindio) {
		this.subastaQuindio = subastaQuindio;
	}
	
	
	
}
