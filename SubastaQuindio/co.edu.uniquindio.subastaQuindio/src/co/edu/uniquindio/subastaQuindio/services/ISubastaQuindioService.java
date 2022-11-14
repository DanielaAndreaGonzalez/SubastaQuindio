/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.io.IOException;
import java.util.Calendar;

import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;

/**
 * @author GonzalezHDanielaA
 *
 */
public interface ISubastaQuindioService {

	
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia, TipoPersona rol) throws RegistroException,IOException;

	public boolean verifyPersonExists(String cedula);
	
	public Persona obtenerPerson(String cedula);
	
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,String nombreAnunciante,
			Calendar fechaPublicacion,Calendar fechaFinPublicacion,double valorInicial,
			TipoProducto tipoProducto,String foto) throws ProductoException,IOException;
	
	public boolean verificarProductoExistente(String codigo);
	
	

}
