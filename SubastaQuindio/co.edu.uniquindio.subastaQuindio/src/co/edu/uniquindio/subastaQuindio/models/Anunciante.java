/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Anunciante extends Persona implements Serializable{
	
	
	private int tiempoLimitadoPublicacion;
	private ArrayList<Producto> productosAnunciar;
	/**
	 * @return the productosAnunciar
	 */
	public ArrayList<Producto> getProductosAnunciar() {
		return productosAnunciar;
	}
	/**
	 * @param productosAnunciar the productosAnunciar to set
	 */
	public void setProductosAnunciar(ArrayList<Producto> productosAnunciar) {
		this.productosAnunciar = productosAnunciar;
	}
	private ArrayList<Anuncio> lista_anuncio;	
	private int cantidadAnunciosPermitidos;
	private Usuario usuarioAsociado; 
	/**
	 * @return the usuarioAsociado
	 */
	public Usuario getUsuarioAsociado() {
		return usuarioAsociado;
	}
	/**
	 * @param usuarioAsociado the usuarioAsociado to set
	 */
	public void setUsuarioAsociado(Usuario usuarioAsociado) {
		this.usuarioAsociado = usuarioAsociado;
	}
	private static final long serialVersionUID = 1L;
	
	
	public Anunciante(){
		this.productosAnunciar=new ArrayList<>();
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
	 * @return the lista_anuncio
	 */
	public ArrayList<Anuncio> getLista_anuncio() {
		return lista_anuncio;
	}
	/**
	 * @param lista_anuncio the lista_anuncio to set
	 */
	public void setLista_anuncio(ArrayList<Anuncio> lista_anuncio) {
		this.lista_anuncio = lista_anuncio;
	}
}
