/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Anuncio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate fechaPublicacion;
	private LocalDate fechaLimitePublicacion;
	private Producto producto;
	private Persona anunciante;

	public Anuncio() {}

	/**
	 * @return the fechaPublicacion
	 */
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/**
	 * @return the fechaLimitePublicacion
	 */
	public LocalDate getFechaLimitePublicacion() {
		return fechaLimitePublicacion;
	}

	/**
	 * @param fechaLimitePublicacion the fechaLimitePublicacion to set
	 */
	public void setFechaLimitePublicacion(LocalDate fechaLimitePublicacion) {
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
