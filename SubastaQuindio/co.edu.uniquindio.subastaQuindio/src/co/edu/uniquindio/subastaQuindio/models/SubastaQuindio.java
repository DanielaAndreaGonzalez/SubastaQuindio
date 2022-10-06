/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.persistence.ArchivoUtil;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import co.edu.uniquindio.subastaQuindio.services.ISubastaQuindioService;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaQuindio implements ISubastaQuindioService{

	private Archivos archivo = new Archivos("Usuarios");
	private static final long serialVersionUID = 1L;
	private static Persistencia persistencia =null;
	
	
	ArrayList<Persona> listaPersonas = new ArrayList<>();
	
	
	
	public ArrayList<Persona> getListaPersona()
	{
		return listaPersonas;
	}
	
	
	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol) throws RegistroException,IOException {

		Persona personaNueva = null;
		boolean personaExist = verifyPersonExists(cedula);
		String mensaje = "";
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
			persistencia.guardarUsuarios(listaPersonas);
			
			mensaje = "Se guardó el usuario con cédula "+personaNueva.getCedula()+
					  "nombre: "+personaNueva.getNombre()+" edad: "+personaNueva.getEdad()+
					  "Usuario: "+personaNueva.getUsuario()+" contrasenia "+personaNueva.getContrasenia();
					  
			persistencia.guardarRegistroLog(mensaje,1, "Información Registrar Usuario");
		}
		return personaNueva;
	}

@Override
	public boolean verifyPersonExists(String cedula) {
		if(archivo.searchPerson(cedula)) 
			return true;
		else
			return false;
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
