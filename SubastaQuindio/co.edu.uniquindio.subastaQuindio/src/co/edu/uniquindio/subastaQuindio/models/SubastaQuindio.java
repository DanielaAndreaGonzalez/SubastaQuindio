/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.services.ISubastaQuindioService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaQuindio implements ISubastaQuindioService{

	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Persona> listaPersonas = new ArrayList<>();
	
	
	
	public ArrayList<Persona> getListaPersona()
	{
		return listaPersonas;
	}
	
	

	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol) throws RegistroException {

		Persona personaNueva = null;
		boolean personaExist = verifyPersonExists(cedula);
		if (personaExist == true) {
			throw new RegistroException("El usuario con cédula: " + cedula + "ya existe");

		} else {
			personaNueva = new Persona();
			personaNueva.setCedula(cedula);
			personaNueva.setNombre(nombre);
			personaNueva.setEdad(edad);
			personaNueva.setUsuario(usuario);
			personaNueva.setContrasenia(contrasenia);
			personaNueva.setTipoPersona(rol);
			getListaPersona().add(personaNueva);
		}
		return personaNueva;
	}

@Override
	public boolean verifyPersonExists(String cedula) {
		Persona persona= null;
		persona = obtenerPerson(cedula);
		if(persona==null)
			return false;
		else
			return true;
		
	}


	@Override
	public Persona obtenerPerson(String cedula) {

		Persona personaEncontrada = null;
		for (Persona persona : getListaPersona()) {
			if (persona.getCedula().equalsIgnoreCase(cedula)) {
				personaEncontrada = persona;
				break;
			}
		}
		return personaEncontrada;
	}
	

	
	
	
	
	
}
