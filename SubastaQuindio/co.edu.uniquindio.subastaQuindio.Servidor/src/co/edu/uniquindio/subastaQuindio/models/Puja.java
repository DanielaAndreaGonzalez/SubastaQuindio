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
public class Puja implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String codigoPuja;
	protected String codigoProducto;
	protected String nombreProducto;
	protected String tipoProducto;
	protected String valorInicialProducto;
	protected String nombreAnunciante;
	protected double ofertaInicial;
	protected Date fechaPuja;
	
	public Puja() {
		
	}
	public Puja(String codigoProducto, String nombreProducto, String tipoProducto, String valorInicialProducto,
			String nombreAnunciante, double ofertaInicial, Date fechaPuja) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.valorInicialProducto = valorInicialProducto;
		this.nombreAnunciante = nombreAnunciante;
		this.ofertaInicial = ofertaInicial;
		this.fechaPuja = fechaPuja;
	}
	/**
	 * @return the codigoPuja
	 */
	public String getCodigoPuja() {
		return codigoPuja;
	}
	/**
	 * @param codigoPuja the codigoPuja to set
	 */
	public void setCodigoPuja(String codigoPuja) {
		this.codigoPuja = codigoPuja;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the nombreProducto
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}
	/**
	 * @param nombreProducto the nombreProducto to set
	 */
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	/**
	 * @return the tipoProducto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}
	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	/**
	 * @return the valorInicialProducto
	 */
	public String getValorInicialProducto() {
		return valorInicialProducto;
	}
	/**
	 * @param valorInicialProducto the valorInicialProducto to set
	 */
	public void setValorInicialProducto(String valorInicialProducto) {
		this.valorInicialProducto = valorInicialProducto;
	}
	/**
	 * @return the nombreAnunciante
	 */
	public String getNombreAnunciante() {
		return nombreAnunciante;
	}
	/**
	 * @param nombreAnunciante the nombreAnunciante to set
	 */
	public void setNombreAnunciante(String nombreAnunciante) {
		this.nombreAnunciante = nombreAnunciante;
	}
	/**
	 * @return the ofertaInicial
	 */
	public double getOfertaInicial() {
		return ofertaInicial;
	}
	/**
	 * @param ofertaInicial the ofertaInicial to set
	 */
	public void setOfertaInicial(double ofertaInicial) {
		this.ofertaInicial = ofertaInicial;
	}
	/**
	 * @return the fechaPuja
	 */
	public Date getFechaPuja() {
		return fechaPuja;
	}
	/**
	 * @param fechaPuja the fechaPuja to set
	 */
	public void setFechaPuja(Date fechaPuja) {
		this.fechaPuja = fechaPuja;
	}
	
	@Override
	public String toString() {
		return "Puja [codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", tipoProducto="
				+ tipoProducto + ", valorInicialProducto=" + valorInicialProducto + ", nombreAnunciante="
				+ nombreAnunciante + ", ofertaInicial=" + ofertaInicial + ", fechaPuja=" + fechaPuja + "]";
	};
	
	

}
