/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import co.edu.uniquindio.subastaQuindio.models.Persona;
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
	
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto, Persona usuariologueado)
	{
		return modelFactoryController.crearProducto(codigo, nombreProducto, descripcion, valorInicial, tipoProducto, foto, usuariologueado);
	}
	
	

}
