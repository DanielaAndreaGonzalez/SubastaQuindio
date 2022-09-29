/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.services.IModelFactoryService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ModelFactoryController implements IModelFactoryService{

	
	SubastaQuindio subasta = null;
	
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
		
		//OJO NOTA: ACÁ SE INICIALIZAN LOS DATOS
	}
	
	
	
	public SubastaQuindio getSubastaQuindio()
	{
		return subasta;
	}
	
	public void setSubastaQuindio(SubastaQuindio subasta)
	{
		this.subasta = subasta;
	}

	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol){
		Persona persona = null;
		try {
			persona = getSubastaQuindio().registerPerson(cedula, nombre, edad, usuario, contrasenia, rol);
		} catch (RegistroException e) {
		
			e.printStackTrace();
		}
		return persona;
	}

	@Override
	public ArrayList<Persona> obtenerPerson(String cedula) {
		return getSubastaQuindio().getListaPersona();
	}

	
	
	
	
	
	
	
	//Verificar persona Existe
	
	
	
	

}
