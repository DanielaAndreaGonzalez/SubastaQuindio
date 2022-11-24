/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.util.Date;

import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Puja;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudPujaController {
	
	ModelFactoryController modelFactoryController;
	SubastaQuindio subastaQuindio;
	
	
	public CrudPujaController(ModelFactoryController modelFactoryController)
	{
		this.modelFactoryController = modelFactoryController;
		subastaQuindio = modelFactoryController.getSubastaQuindio();
	}
	
	public SubastaQuindio getSubastaQuindio()
	{
		return subastaQuindio;
	}
	
	public void setSubastaQuindio(SubastaQuindio subasta)
	{
		this.subastaQuindio = subasta;
	}
	
	public Puja crearPuja(String codigo,String codigoProducto, String nombreProducto, String tipoProducto, String valorInicialProducto,
			String nombreAnunciante, double ofertaInicial, Date fechaPuja,Persona usuarioLogueado)
	{
		return modelFactoryController.crearPuja(codigo,codigoProducto, nombreProducto, tipoProducto, valorInicialProducto, nombreAnunciante, ofertaInicial, fechaPuja,usuarioLogueado);
	}
	
	
	

}
