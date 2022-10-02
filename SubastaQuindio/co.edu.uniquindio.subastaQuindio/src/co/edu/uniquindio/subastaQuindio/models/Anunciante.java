/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.util.ArrayList;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Anunciante extends Persona{
	
	
	private int tiempoLimitadoPublicacion;
	private ArrayList<Producto> tipoProductoAnunciar;
	private int cantidadAnunciosPermitidos;
	
	
	public Anunciante(){
		this.tipoProductoAnunciar=new ArrayList<>();
	}
	/**
	 * @return the cantidadAnunciosPermitidos
	 */
	public int getCantidadAnunciosPermitidos() {
		return cantidadAnunciosPermitidos;
	}

	/**
	 * @param cantidadAnunciosPermitidos the cantidadAnunciosPermitidos to set
	 */
	public void setCantidadAnunciosPermitidos(int cantidadAnunciosPermitidos) {
		this.cantidadAnunciosPermitidos = cantidadAnunciosPermitidos;
	}
	/**
	 * @return the tiempoLimitadoPublicacion
	 */
	public int getTiempoLimitadoPublicacion() {
		return tiempoLimitadoPublicacion;
	}
	/**
	 * @param tiempoLimitadoPublicacion the tiempoLimitadoPublicacion to set
	 */
	public void setTiempoLimitadoPublicacion(int tiempoLimitadoPublicacion) {
		this.tiempoLimitadoPublicacion = tiempoLimitadoPublicacion;
	}
	/**
	 * @return the tipoProductoAnunciar
	 */
	public ArrayList<Producto> getTipoProductoAnunciar() {
		return tipoProductoAnunciar;
	}
	/**
	 * @param tipoProductoAnunciar the tipoProductoAnunciar to set
	 */
	public void addTipoProductoAnunciar(Producto tipoProductoAnunciar) {
		this.tipoProductoAnunciar.add(tipoProductoAnunciar);
	}
	
	
	
	

}
