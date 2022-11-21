/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static String NOMBRE_ARCHIVO_GUARDADO_EXTENSION = "archivoUsuarios.txt";
	public static String NOMBRE_ARCHIVO_GUARDADO = "archivoUsuarios";	
	public static String EXTENSION_ARCHIVO_GUARDADO = ".txt";
	
	
	private String usuario;
	private String contrasenia;
	private TipoPersona tipoPersona;
	
	public Usuario() {}
	
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**
	 * @return the tipoPersona
	 */
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}



	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasenia=" + contrasenia + ", tipoPersona=" + tipoPersona + "]";
	}
	
	
	

}
