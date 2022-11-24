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
	protected String valorInicialProductoPuja;
	protected double ofertaInicial;
	protected Date fechaPuja;
	protected String tipoProducto;
	protected String nombreAnunciante;
	
	
	
	public Puja() {
		
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
	
	public String getValorInicialProducto() {
		return valorInicialProductoPuja;
	}
	/**
	 * @param valorInicialProducto the valorInicialProducto to set
	 */
	public void setValorInicialProducto(String valorInicialProducto) {
		this.valorInicialProductoPuja = valorInicialProducto;
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
	 * 
	 * @return
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

	@Override
	public String toString() {
		return "Puja [codigoPuja=" + codigoPuja + ", codigoProducto=" + codigoProducto + ", nombreProducto="
				+ nombreProducto + ", valorInicialProducto=" + valorInicialProductoPuja + ", ofertaInicial=" + ofertaInicial
				+ ", fechaPuja=" + fechaPuja + ", tipoProducto=" + tipoProducto + ", nombreAnunciante="
				+ nombreAnunciante + "]";
	}
	
	
	
	
	

}
