/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.util.Date;

import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudAnuncioController {
	
	ModelFactoryController modelFactoryController;
	SubastaQuindio subastaQuindio;
	
	public CrudAnuncioController(ModelFactoryController modelFactoryController)
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
	
	
	
	public Anuncio crearAnuncio(Date fechaPublicacion,Date fechaFin,Producto producto,Persona anunciante)
	{
		return modelFactoryController.crearAnuncio(fechaPublicacion, fechaFin, producto, anunciante);
		
	}
	
	

}
