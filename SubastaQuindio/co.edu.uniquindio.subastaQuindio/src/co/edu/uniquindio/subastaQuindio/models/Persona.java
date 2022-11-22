/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Persona extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private int edad;
	
	
	public Persona() {}
	
	
	
	
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}




	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}




	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}




	@Override
	public String toString() {
		return cedula + "-"+nombre ;
	}
	
	
	

}
