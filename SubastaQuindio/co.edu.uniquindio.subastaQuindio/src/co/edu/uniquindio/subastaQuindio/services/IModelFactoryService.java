/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.Usuario;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface IModelFactoryService {
	
	
	public Persona registerPerson(String cedula,String nombre, int edad, String usuario, String contrasenia, TipoPersona rol);
	public ArrayList<Persona> obtenerPerson(String cedula);
	
	
	

}
