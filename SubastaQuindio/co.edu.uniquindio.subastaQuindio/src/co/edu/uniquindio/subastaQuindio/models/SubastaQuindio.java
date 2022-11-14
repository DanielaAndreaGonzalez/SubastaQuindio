/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.RegistroException;
import co.edu.uniquindio.subastaQuindio.persistence.ArchivoUtil;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import co.edu.uniquindio.subastaQuindio.services.ISubastaQuindioService;

/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaQuindio implements  Serializable, ISubastaQuindioService{

	private static final long serialVersionUID = 1L;
	private Archivos archivo = new Archivos("Usuarios");
	//private static Persistencia persistencia =null; no es necesario declararla debido a que se puede acceder a la clase porque el metodo es estatico
	
	
	ArrayList<Persona> listaPersonas = new ArrayList<>();
	ArrayList<Producto> listaProducto = new ArrayList<>();
	
	
	public SubastaQuindio() {
		
	}
	
	/**
	 * @return the archivo
	 */
	public Archivos getArchivo() {
		return archivo;
	}




	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivos archivo) {
		this.archivo = archivo;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the listaPersonas
	 */
	public ArrayList<Persona> getListaPersonas() {
		return listaPersonas;
	}
	/**
	 * @return the listaProducto
	 */
	public ArrayList<Producto> getListaProductos() {
		return listaProducto;
	}
	
	/**
	 * @param listaPersonas the listaPersonas to set
	 */
	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	/**
	 * @param listaProducto the listaProducto to set
	 */
	public void setistaProductos(ArrayList<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	@Override
	public Persona registerPerson(String cedula, String nombre, int edad, String usuario, String contrasenia,TipoPersona rol) throws RegistroException,IOException {

		Persona personaNueva = null;
		boolean personaExist = verifyPersonExists(cedula);
		String mensaje = "";
		if (personaExist == true) {
			throw new RegistroException("El usuario con cédula: " + cedula + " ya existe");

		} else {
			personaNueva = new Persona();
			personaNueva.setCedula(cedula);
			personaNueva.setNombre(nombre);
			personaNueva.setEdad(edad);
			personaNueva.setUsuario(usuario);
			personaNueva.setContrasenia(contrasenia);
			personaNueva.setTipoPersona(rol);
			getListaPersonas().add(personaNueva);
			Persistencia.hacerBackupArchivo(Persona.NOMBRE_ARCHIVO_GUARDADO_EXTENSION, Persona.NOMBRE_ARCHIVO_GUARDADO);
			Persistencia.guardarUsuarios(listaPersonas);
			
			mensaje = "Se guardó el usuario con cédula "+personaNueva.getCedula()+
					  "nombre: "+personaNueva.getNombre()+",edad: "+personaNueva.getEdad()+
					  ",Usuario: "+personaNueva.getUsuario()+",contrasenia "+personaNueva.getContrasenia();
					  
			Persistencia.guardarRegistroLog(mensaje,1, "Información Registrar Usuario");
		}
		return personaNueva;
	}

	@Override
	public boolean verifyPersonExists(String cedula) {
		if(ArchivoUtil.searchPerson(cedula)) 
			return true;
		else
			return false;
	}
	
	

	@Override
	public boolean verificarProductoExistente(String codigo) {	
		if(ArchivoUtil.buscarProducto(codigo))
		{
			return true;
		}else {
			return false;
		}	
	}

	@Override
	public Persona obtenerPerson(String cedula) {

		Persona personaEncontrada = null;
		for (Persona persona : getListaPersonas()) {
			if (persona.getCedula().equalsIgnoreCase(cedula)) {
				personaEncontrada = persona;
				break;
			}
		}
		return personaEncontrada;
	}

	@Override
	public String toString() {
		return "SubastaQuindio [listaPersonas=" + listaPersonas + "]";
	}

	@Override
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto)throws ProductoException,IOException 
	{
		
		Producto producto = null; 
		boolean productoExistente = verificarProductoExistente(codigo);
		String mensaje = "";
		if(productoExistente == true)
		{
			throw new ProductoException("El producto con código: " +codigo+ " ya existe");

		}else {
			
			producto = new Producto();
			producto.setCodigo("SUB"+codigo);
			producto.setNombreProducto(nombreProducto);
			producto.setDescripcion(descripcion);
			producto.setValorInicial(valorInicial);
			producto.setTipoProducto(tipoProducto);
			producto.setFoto(foto);
			getListaProductos().add(producto);
			
			mensaje = "Se guardó el producto con código: "+producto.getCodigo()+
					" nombre"+producto.getNombreProducto() +" descripcion: "+producto.getDescripcion()+
					"valor inicial "+producto.getValorInicial()+" tipo Producto"+producto.getTipoProducto();
					
			Persistencia.guardarRegistroLog(mensaje, 1, "Se creó el producto, crear Producto - Subasta quindío");	
		}
		return producto;
	}

	
	

		
	
	
	
}
