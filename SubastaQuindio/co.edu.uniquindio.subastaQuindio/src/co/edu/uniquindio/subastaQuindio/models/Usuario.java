/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Usuario {
	
	
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
	
	
	

}
