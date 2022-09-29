/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudRegistroViewController {

	
	
	ModelFactoryController modelFactoryController;
	SubastaQuindio subastaQuindio;
	
	
	public CrudRegistroViewController(ModelFactoryController modelFactoryController)
	{
		this.modelFactoryController = modelFactoryController;
		subastaQuindio = modelFactoryController.getSubastaQuindio();
	}
	
	public SubastaQuindio getSubastaQuindio()
	{
		return subastaQuindio;
	}
	
	public void setSubastaQuindio(SubastaQuindio subasta)
	{
		this.subastaQuindio = subasta;
	}
	
	
	public Persona  registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol)
	{
		return modelFactoryController.registerPerson(cedula, nombre, edad, usuario, contrasenia, rol);
	}
	
	
	
	
}
