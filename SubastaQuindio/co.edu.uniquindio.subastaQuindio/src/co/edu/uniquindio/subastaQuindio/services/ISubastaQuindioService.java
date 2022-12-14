/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.io.IOException;
import java.util.Date;

import co.edu.uniquindio.subastaQuindio.exceptions.AnuncioException;
import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.PujaException;
import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.Puja;
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
	
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto, Persona usuarioLogueado) throws ProductoException,IOException;
	
	public boolean verificarProductoExistente(String codigo);
	
	public Anuncio crearAnuncio(Date fechaPublicacion,Date fechaFin, Producto producto,Persona usuarioLogueado) throws AnuncioException,IOException;

	public Puja crearPuja(String codigoPuja,String codigoProducto, String nombreProducto, String tipoProducto, String valorInicialProducto,
			String nombreAnunciante, double ofertaInicial, Date fechaPuja,Persona usuarioLogueado)throws PujaException,IOException;
	
	
	
}
