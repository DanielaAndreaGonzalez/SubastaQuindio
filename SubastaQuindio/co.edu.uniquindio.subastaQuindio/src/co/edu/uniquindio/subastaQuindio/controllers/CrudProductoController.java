/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.util.Calendar;

import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudProductoController {
	
	ModelFactoryController modelFactoryController;
	SubastaQuindio subastaQuindio;
	
	public CrudProductoController(ModelFactoryController modelFactoryController)
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
	
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,String nombreAnunciante,
			Calendar fechaPublicacion,Calendar fechaFinPublicacion,double valorInicial,
			TipoProducto tipoProducto,String foto)
	{
		
		
		return modelFactoryController.crearProducto(codigo, nombreProducto, descripcion, nombreAnunciante, fechaPublicacion, fechaFinPublicacion, valorInicial, tipoProducto, foto);
	}
	

}