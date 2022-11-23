/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import co.edu.uniquindio.subastaQuindio.exceptions.AnuncioException;
import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.models.Anunciante;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
import co.edu.uniquindio.subastaQuindio.models.Usuario;

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
	
	public Anuncio crearAnuncio(LocalDate fechaPublicacion,LocalDate fechaFin, Producto producto,Persona usuarioLogueado) throws AnuncioException,IOException;

}
