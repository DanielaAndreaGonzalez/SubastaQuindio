/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.services;

import java.util.ArrayList;
import java.util.Date;
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
public interface IModelFactoryService {
	
	
	public Persona registerPerson(String cedula,String nombre, int edad, String usuario, String contrasenia, TipoPersona rol);
	public ArrayList<Persona> obtenerPerson(String cedula);
	
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto, Persona usuarioLogeado);
	
	public Anuncio crearAnuncio(Date fechaPublicacion,Date fechaFin,Producto producto, Persona anunciante);
	
	public Puja crearPuja(String codigoPuja,String codigoProducto, String nombreProducto, String tipoProducto, String valorInicialProducto,
			String nombreAnunciante, double ofertaInicial, Date fechaPuja,Persona usuarioLogueado);

}
