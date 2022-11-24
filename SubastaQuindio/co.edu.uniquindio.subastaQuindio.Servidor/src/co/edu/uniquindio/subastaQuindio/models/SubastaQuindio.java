/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.models;

/**
 * 
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import co.edu.uniquindio.subastaQuindio.exceptions.AnuncioException;
import co.edu.uniquindio.subastaQuindio.exceptions.ProductoException;
import co.edu.uniquindio.subastaQuindio.exceptions.PujaException;
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
	//ArrayList<Producto> listaProducto = new ArrayList<>();
	ArrayList<Anunciante> listaAnunciante = new ArrayList<>();	
	
	ArrayList<Anuncio> listaAnuncios = new ArrayList<>();
	
	ArrayList<Puja> listaPujas = new ArrayList<>();
	
	ArrayList<Comprador> listaCompradores= new ArrayList<>();	
	

	public SubastaQuindio() {
		
	}
	
	/**
	 * @return the listaCompradores
	 */
	public ArrayList<Comprador> getListaCompradores() {
		return listaCompradores;
	}

	/**
	 * @param listaCompradores the listaCompradores to set
	 */
	public void setListaCompradores(ArrayList<Comprador> listaCompradores) {
		this.listaCompradores = listaCompradores;
	}

	/**
	 * @return the listaPujas
	 */
	public ArrayList<Puja> getListaPujas() {
		return listaPujas;
	}

	/**
	 * @param listaPujas the listaPujas to set
	 */
	public void setListaPujas(ArrayList<Puja> listaPujas) {
		this.listaPujas = listaPujas;
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
	 * @param listaPersonas the listaPersonas to set
	 */
	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}	
	/**
	 * @return the listaAnunciante
	 */
	public ArrayList<Anunciante> getListaAnunciante() {
		return listaAnunciante;
	}

	/**
	 * @param listaAnunciante the listaAnunciante to set
	 */
	public void setListaAnunciante(ArrayList<Anunciante> listaAnunciante) {
		this.listaAnunciante = listaAnunciante;
	}

	/**
	 * @return the listaAnuncios
	 */
	public ArrayList<Anuncio> getListaAnuncios() {
		return listaAnuncios;
	}

	/**
	 * @param listaAnuncios the listaAnuncios to set
	 */
	public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
		this.listaAnuncios = listaAnuncios;
	}	
	public void addNuevoAnuncioListaAnuncios(Anuncio anuncio) {
		this.listaAnuncios.add(anuncio);
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
	public Anuncio crearAnuncio(Date fechaPublicacion,Date fechaFin, Producto producto,Persona usuarioLogueado)
			throws AnuncioException, IOException {
		
		String mensaje = "";
		Anuncio anuncio = null;
		
		anuncio = new Anuncio();
		anuncio.setFechaPublicacion(fechaPublicacion);
		anuncio.setFechaLimitePublicacion(fechaFin);
		anuncio.setProducto(producto);
		anuncio.setAnunciante(usuarioLogueado);
		
		Anunciante anunciante = new Anunciante();						
		//buscar anunciante en lista de subasta quindio y si esta capturar ese anunciante 
		// si no esta se crear nuevo anunciante
		if (buscarAnunciante(usuarioLogueado.getCedula())!= null) {
			anunciante = buscarAnunciante(usuarioLogueado.getCedula());
			if(anunciante.getLista_anuncio()==null)
			{
				anunciante.setLista_anuncio(new ArrayList<>());
			}
			anunciante.getLista_anuncio().add(anuncio);
			// modificar anunciante en lista de anunciantes
			moficiarListaAnunciante(anunciante);
		}
		mensaje = "Se creó el anuncio fecha publicacion "+fechaPublicacion+
					" fecha fin "+fechaFin+ " nombre anunciante "+usuarioLogueado.getNombre()+
					" con el producto "+producto;
		
		Persistencia.guardarRegistroLog(mensaje, 1, "Se creó el anuncio, crear Anuncio - Subasta quindío");
		
		return anuncio;
	}
	@Override
	public Producto crearProducto(String codigo,String nombreProducto,String descripcion,
			double valorInicial,TipoProducto tipoProducto,String foto, Persona usuarioLogueado)throws ProductoException,IOException 
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
			
			Anunciante anunciante = new Anunciante();						
			//buscar anunciante en lista de subasta quindio y si esta capturar ese anunciante 
			// si no esta se crear nuevo anunciante
			if (buscarAnunciante(usuarioLogueado.getCedula())!= null) {
				anunciante = buscarAnunciante(usuarioLogueado.getCedula());
				anunciante.getProductosAnunciar().add(producto);
				// modificar anunciante en lista de anunciantes
				moficiarListaAnunciante(anunciante);
			}else {
				anunciante.setCedula(usuarioLogueado.getCedula());
				anunciante.setContrasenia(usuarioLogueado.getContrasenia());
				anunciante.setNombre(usuarioLogueado.getNombre());
				anunciante.setUsuario(usuarioLogueado.getUsuario());
				anunciante.setEdad(usuarioLogueado.getEdad());
				anunciante.setCantidadAnunciosPermitidos(10);
				anunciante.setTiempoLimitadoPublicacion(3);
				anunciante.setTipoPersona(usuarioLogueado.getTipoPersona());
				anunciante.setUsuarioAsociado(usuarioLogueado);
				anunciante.getProductosAnunciar().add(producto);
				getListaAnunciante().add(anunciante);
			}	
			mensaje = "Se guardó el producto con código: "+producto.getCodigo()+
					" nombre"+producto.getNombreProducto() +" descripcion: "+producto.getDescripcion()+
					"valor inicial "+producto.getValorInicial()+" tipo Producto"+producto.getTipoProducto();
			
			
			Persistencia.guardarRegistroLog(mensaje, 1, "Se creó el producto, crear Producto - Subasta quindío");
			
			//Persistencia.guardarProducto(listaProducto);
		}
		return producto;
	}
	private void moficiarListaAnunciante(Anunciante nuevoAnunciante) {		
		int index=0;
		for (Anunciante anunciante : listaAnunciante) {
			if (anunciante.getCedula().equals(nuevoAnunciante.getCedula())) {
				break;
			}
			index++;
		}	
		listaAnunciante.set(index, nuevoAnunciante);
	}
	private void modificarListaCompradores(Comprador nuevoComprador) {		
		int index=0;
		for (Comprador comprador : listaCompradores) {
			if (comprador.getCedula().equals(nuevoComprador.getCedula())) {
				break;
			}
			index++;
		}	
		listaCompradores.set(index, nuevoComprador);
	}
	
	public Comprador buscarComprador(String cedula) {
		Comprador compradorAux = null;
		for (Comprador comprador : listaCompradores) {
			if (comprador.getCedula().equals(cedula)) {
				compradorAux = comprador;
				break;
			}
		}
		return compradorAux;
	}
	
	
	public Anunciante buscarAnunciante(String cedula) {
		Anunciante anuncianteAux = null;
		for (Anunciante anunciante : listaAnunciante) {
			if (anunciante.getCedula().equals(cedula)) {
				anuncianteAux = anunciante;
				break;
			}
		}
		return anuncianteAux;
	}
	@Override
	public Puja crearPuja(String codigoPuja,String codigoProducto, String nombreProducto, String tipoProducto,
			String valorInicialProducto, String nombreAnunciante, double ofertaInicial, Date fechaPuja,Persona usuarioLogueado)
			throws PujaException, IOException {
		String mensaje = "";
		Puja puja =null;
		boolean pujaExistente = verificarPujaExistente(codigoPuja);
		if(pujaExistente == true)
		{
			throw new PujaException("La puja con código: " +codigoPuja+ " ya existe");
		}
		else {
			puja = new Puja();
			puja.setCodigoPuja(codigoPuja);
			puja.setCodigoProducto(codigoProducto);
			puja.setNombreProducto(nombreProducto);
			puja.setNombreAnunciante(nombreAnunciante);
			puja.setTipoProducto(tipoProducto);
			puja.setValorInicialProducto(valorInicialProducto);
			puja.setOfertaInicial(ofertaInicial);
			puja.setFechaPuja(fechaPuja);
			getListaPujas().add(puja);
			
			
			Comprador comprador = new Comprador();
			
			
			if(buscarComprador(usuarioLogueado.getCedula()) != null) 
			{
				comprador = buscarComprador(usuarioLogueado.getCedula());
				comprador.getPujas().add(puja);
				modificarListaCompradores(comprador);
			}	
			else {
			comprador.setCedula(usuarioLogueado.getCedula());
			comprador.setNombre(usuarioLogueado.getNombre());
			comprador.setTipoPersona(usuarioLogueado.getTipoPersona());
			comprador.setUsuario(usuarioLogueado.getUsuario());
			comprador.setContrasenia(usuarioLogueado.getContrasenia());
			comprador.setMontoDispuestoPagar(ofertaInicial);
			getListaCompradores().add(comprador);
			}
		mensaje = "Se guardó la puja con código "+puja.getCodigoPuja()+"Código Producto"+puja.getCodigoProducto()
						+" nombre producto "+puja.getNombreProducto()
						+"Se guardó el comprador cédula"+comprador.getCedula()+
						" nombre"+comprador.getNombre()+" tipo usuario"+comprador.getTipoPersona()+
						"usuario "+comprador.getUsuario()+" contraseña "+comprador.getContrasenia();
		
			Persistencia.guardarRegistroLog(mensaje, 1, "Se creó la puja, crear Puja - Subasta quindío");
			
		}
		
		return puja;
	}

	public boolean verificarPujaExistente(String codigo) {	
		if(ArchivoUtil.buscarPuja(codigo))
		{
			return true;
		}else {
			return false;
		}	
	}
		
}
