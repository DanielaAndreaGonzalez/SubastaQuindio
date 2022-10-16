/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.persistence.ArchivoUtil;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import co.edu.uniquindio.subastaQuindio.services.ISubastaQuindioService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaQuindio implements  Serializable, ISubastaQuindioService{

	private static final long serialVersionUID = 1L;
	private Archivos archivo = new Archivos("Usuarios");
	//private static Persistencia persistencia =null; no es necesario declararla debido a que se puede acceder a la clase porque el metodo es estatico
	
	
	ArrayList<Persona> listaPersonas = new ArrayList<>();
	
	
	
	public SubastaQuindio() {
		
	}
	

	
	
	/**
	 * @return the archivo
	 */
	public Archivos getArchivo() {
		return archivo;
	}




	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the listaPersonas
	 */
	public ArrayList<Persona> getListaPersonas() {
		return listaPersonas;
	}

	/**
	 * @param listaPersonas the listaPersonas to set
	 */
	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol) throws RegistroException,IOException {

		Persona personaNueva = null;
		boolean personaExist = verifyPersonExists(cedula);
		String mensaje = "";
		if (personaExist == true) {
			throw new RegistroException("El usuario con cédula: " + cedula + " ya existe");

		} else {
			personaNueva = new Persona();
			personaNueva.setCedula(cedula);
			personaNueva.setNombre(nombre);
			personaNueva.setEdad(edad);
			personaNueva.setUsuario(usuario);
			personaNueva.setContrasenia(contrasenia);
			personaNueva.setTipoPersona(rol);
			getListaPersonas().add(personaNueva);
			Persistencia.hacerBackupArchivo(Persona.NOMBRE_ARCHIVO_GUARDADO_EXTENSION, Persona.NOMBRE_ARCHIVO_GUARDADO);
			Persistencia.guardarUsuarios(listaPersonas);
			
			mensaje = "Se guardó el usuario con cédula "+personaNueva.getCedula()+
					  "nombre: "+personaNueva.getNombre()+",edad: "+personaNueva.getEdad()+
					  ",Usuario: "+personaNueva.getUsuario()+",contrasenia "+personaNueva.getContrasenia();
					  
			Persistencia.guardarRegistroLog(mensaje,1, "Información Registrar Usuario");
		}
		return personaNueva;
	}

@Override
	public boolean verifyPersonExists(String cedula) {
		if(ArchivoUtil.searchPerson(cedula)) 
			return true;
		else
			return false;
	}


	@Override
	public Persona obtenerPerson(String cedula) {

		Persona personaEncontrada = null;
		for (Persona persona : getListaPersonas()) {
			if (persona.getCedula().equalsIgnoreCase(cedula)) {
				personaEncontrada = persona;
				break;
			}
		}
		return personaEncontrada;
	}




	@Override
	public String toString() {
		return "SubastaQuindio [listaPersonas=" + listaPersonas + "]";
	}
	

		
	
	
	
}
