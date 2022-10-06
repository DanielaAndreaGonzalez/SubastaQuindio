/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.io.IOException;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface ISubastaQuindioService {

	
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia, TipoPersona rol) throws RegistroException,IOException;

	public boolean verifyPersonExists(String cedula);
	
	public Persona obtenerPerson(String cedula);

}
