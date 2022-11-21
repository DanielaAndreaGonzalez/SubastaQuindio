/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Comprador extends Persona implements Serializable{
	
	private double montoDispuestoPagar;
	private int[] ofertas = new int[3];
	private static final long serialVersionUID = 1L;
	
	
	public Comprador(){}
	/**
	 * @return the montoDispuestoPagar
	 */
	public double getMontoDispuestoPagar() {
		return montoDispuestoPagar;
	}
	/**
	 * @param montoDispuestoPagar the montoDispuestoPagar to set
	 */
	public void setMontoDispuestoPagar(double montoDispuestoPagar) {
		this.montoDispuestoPagar = montoDispuestoPagar;
	}
	/**
	 * @return the ofertas
	 */
	public int[] getOfertas() {
		return ofertas;
	}
	/**
	 * @param ofertas the ofertas to set
	 */
	public void setOfertas(int[] ofertas) {
		this.ofertas = ofertas;
	}
	
	
	
	
	
	

}
