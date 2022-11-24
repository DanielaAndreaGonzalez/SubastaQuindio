/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Anuncio implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Date fechaPublicacion;
	private Date fechaLimitePublicacion;
	private Producto producto;
	private Persona anunciante;

	public Anuncio() {}

	/**
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the fechaLimitePublicacion
	 */
	public Date getFechaLimitePublicacion() {
		return fechaLimitePublicacion;
	}

	/**
	 * @param fechaLimitePublicacion the fechaLimitePublicacion to set
	 */
	public void setFechaLimitePublicacion(Date fechaLimitePublicacion) {
		this.fechaLimitePublicacion = fechaLimitePublicacion;
	}
	
	

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the anunciante
	 */
	public Persona getAnunciante() {
		return anunciante;
	}

	/**
	 * @param anunciante the anunciante to set
	 */
	public void setAnunciante(Persona anunciante) {
		this.anunciante = anunciante;
	}

	@Override
	public String toString() {
		return "Anuncio [fechaPublicacion=" + fechaPublicacion + ", fechaLimitePublicacion=" + fechaLimitePublicacion
				+ ", producto=" + producto + ", anunciante=" + anunciante + "]";
	}

}
