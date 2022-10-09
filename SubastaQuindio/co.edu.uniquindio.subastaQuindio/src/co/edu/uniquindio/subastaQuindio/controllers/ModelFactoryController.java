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
	}
	
	private void inicializarDatos()
	{		
		subastaQuindio= new SubastaQuindio();  
		archivo.crearArchivo();
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
			archivo.saveperson(persona);	
			
		} catch (RegistroException e) {		
			System.out.println(e.getMessage());
		}catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		return persona;
	}

	@Override
	public ArrayList<Persona> obtenerPerson(String cedula) {
		return getSubastaQuindio().getListaPersona();
	}

	
	
	
	
	
	
	
	//Verificar persona Existe
	
	
	
	

}
