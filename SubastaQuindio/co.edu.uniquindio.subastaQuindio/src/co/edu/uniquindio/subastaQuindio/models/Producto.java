/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Producto implements Serializable{
	
	private String codigo;
	private String nombreProducto;
	private String descripcion;
	private String nombreAnunciante;
	private Calendar fechaPublicacion;
	private Calendar fechaFinPublicacion;
	private double valorInicial;
	private TipoProducto tipoProducto;
	private String foto;
	private static final long serialVersionUID = 1L;
	
	
	public Producto(){}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreAnunciante() {
		return nombreAnunciante;
	}
	public void setNombreAnunciante(String nombreAnunciante) {
		this.nombreAnunciante = nombreAnunciante;
	}
	public Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Calendar getFechaFinPublicacion() {
		return fechaFinPublicacion;
	}
	public void setFechaFinPublicacion(Calendar fechaFinPublicacion) {
		this.fechaFinPublicacion = fechaFinPublicacion;
	}
	public double getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}
	

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the tipoProducto
	 */
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", descripcion=" + descripcion + ", nombreAnunciante="
				+ nombreAnunciante + ", fechaPublicacion=" + fechaPublicacion + ", fechaFinPublicacion="
				+ fechaFinPublicacion + ", valorInicial=" + valorInicial + "]";
	}
	
	
	
	
	
	
	
	

}
