/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Comprador extends Persona implements Serializable{
	
	private double montoDispuestoPagar;
	private int[] ofertas = new int[3];
	private static final long serialVersionUID = 1L;
	private ArrayList<Puja> pujas;
	
	public Comprador(){
		
		this.pujas = new ArrayList<Puja>();
	}
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
	/**
	 * @return the pujas
	 */
	public ArrayList<Puja> getPujas() {
		return pujas;
	}
	/**
	 * @param pujas the pujas to set
	 */
	public void setPujas(ArrayList<Puja> pujas) {
		this.pujas = pujas;
	}
	@Override
	public String toString() {
		return "Comprador [montoDispuestoPagar=" + montoDispuestoPagar + ", ofertas=" + Arrays.toString(ofertas)
				+ ", pujas=" + pujas + "]";
	}
	
	
	
}
