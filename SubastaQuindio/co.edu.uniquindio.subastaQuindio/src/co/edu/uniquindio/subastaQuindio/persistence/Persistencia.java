/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.Usuario;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Persistencia {
	
	//public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "C://td//persistencia//Archivos//"+ Usuario.NOMBRE_ARCHIVO_GUARDADO_EXTENSION;
	public static final String RUTA_ARCHIVO_LOG = "C://td//persistencia//log//SubastaQuindioLog.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO = "C://td//persistencia//usuarioBin.dat";
	public static final String RUTA_ARCHIVO_MODELO_SUBASTAQUINDIO_XML = "C://td//persistencia//model.xml";
	
	public static final String RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL = "C://td//persistencia//Archivos//";
	public static final String RUTA_ARCHIVO_COPIA_DESTINO_GENERAL = "C://td//persistencia//respaldo//";
	
	public static final String RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL_SERIAL = "C://td//persistencia//";	
	
	public static final String SEPARADOR = "@@";
	
	public static final String RUTA_ARCHIVO_PRODUCTOS = "C://td//persistencia//Archivos//archivoProducto.txt";
	
	

	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new UsuarioExcepcion("Usuario no existe");
		}	
	}
	
	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
	{
		SubastaQuindio subastaQuindio=  Persistencia.cargarRecursoSubastaQuindioBinario();
		
		ArrayList<Persona> usuarios = subastaQuindio.getListaPersonas();
		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			Usuario usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}
	
	public static Persona obtenerUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
	{
		SubastaQuindio subastaQuindio=  Persistencia.cargarRecursoSubastaQuindioBinario();
		
		ArrayList<Persona> usuarios = subastaQuindio.getListaPersonas();
		Persona usuarioAux = null;
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return usuarioAux;
			}
		}
		return usuarioAux;
	}
	
	
	/**
	 * Guarda en un archivo de texto todos la información de las personas
	 * almacenadas en el ArrayList
	 * @param listaUsuario
	 * @throws IOException
	 */
	public static void guardarUsuarios(ArrayList<Persona> listaUsuario ) throws IOException{
		
		String contenido = "";
		
		for(Persona persona: listaUsuario)
		{
			contenido+= persona.getCedula()+SEPARADOR+persona.getNombre()+SEPARADOR+persona.getEdad()+SEPARADOR
					 +persona.getTipoPersona()+SEPARADOR+persona.getUsuario()+SEPARADOR+persona.getContrasenia()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
	}
	
	public static void guardarProducto(ArrayList<Producto> listaProducto)throws IOException{
		
		String contenido = "";
		
		for(Producto producto:listaProducto )
		{
			contenido += producto.getCodigo()+SEPARADOR+producto.getNombreProducto()+SEPARADOR+
					producto.getDescripcion()+SEPARADOR+
					producto.getValorInicial()+SEPARADOR+producto.getTipoProducto()+SEPARADOR+
					producto.getFoto();		
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, true);	
	}
	
	/**
	 * 
	 * @return un arrayList de personas con los datos obtenidos del archivo de
	 * texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Persona> cargarUsuarios() throws FileNotFoundException, IOException
	{
		ArrayList<Persona> listaPersonas = new ArrayList<>();
		try {
			ArrayList<String> contenidoArchivo = ArchivoUtil.leerArchivo(Persistencia.RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL+Usuario.NOMBRE_ARCHIVO_GUARDADO_EXTENSION);
			Persona persona = null;
			String separador =  Persistencia.SEPARADOR;
			TipoPersona t=null;
			if (contenidoArchivo.size() > 0) {
				for (String string : contenidoArchivo) {
					persona = new Persona();
					persona.setCedula(string.split(separador)[0]);
					persona.setNombre(string.split(separador)[1]);
					persona.setEdad(Integer.parseInt(string.split(separador)[2]));
					
					switch(string.split(separador)[3]) {
						case "USUARIOCOMPRADOR":
							t= TipoPersona.USUARIOCOMPRADOR;
							break;
						case "USUARIOANUNCIANTES":
							t= TipoPersona.USUARIOANUNCIANTES;
							break;
						default:
							t=TipoPersona.USUARIOANUNCIANTES;
							
					}
					persona.setTipoPersona(t);
					persona.setUsuario(string.split(separador)[4]);
					persona.setContrasenia(string.split(separador)[5]);
					listaPersonas.add(persona);
					//subasta.setListaPersonas(listaPersonas);
				}				
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return listaPersonas;
	}
	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion)
	{
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}
	/**
	 * 
	 * @param nombreArchivoOrigen nombre del archivo destino con extencion
	 * @param nombreArchivoDestino nombre del archivo destino sin extencion
	 */
	public static void hacerBackupArchivo(String nombreArchivoOrigen, String nombreArchivoDestino) {			
		String rutaOrigen = RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL + nombreArchivoOrigen;
		String rutaOrigenDestino = RUTA_ARCHIVO_COPIA_DESTINO_GENERAL + nombreArchivoDestino+FechaUtil.fechaUtilPersisteciaBackup() + ".txt";
		ArchivoUtil.hacerBackupArchivo(rutaOrigen,rutaOrigenDestino);
	}
	/**
	 * 
	 * @param nombreArchivoOrigen nombre del archivo destino con extencion
	 * @param nombreArchivoDestino nombre del archivo destino sin extencion
	 */
	public static void hacerBackupArchivosSerializados(String nombreArchivoOrigen, String nombreArchivoDestino, String extension) {			
		String rutaOrigen = RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL_SERIAL + nombreArchivoOrigen;
		String rutaOrigenDestino = RUTA_ARCHIVO_COPIA_DESTINO_GENERAL + nombreArchivoDestino + FechaUtil.fechaUtilPersisteciaBackup() + extension;
		ArchivoUtil.hacerBackupArchivo(rutaOrigen,rutaOrigenDestino);
	}
	/**
	 * 
	 * @param subasta
	 */
	public static void guardarResourceSubastaXML(SubastaQuindio subasta)
	{
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTAQUINDIO_XML, subasta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param subasta
	 */
	public static void guardarRecursoSubastaBinario(SubastaQuindio subasta) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO, subasta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return
	 */
	public static SubastaQuindio cargarRecursoSubastaQuindioBinario() {
		
		SubastaQuindio subastaQuindio = null;
		
		try {
			subastaQuindio = (SubastaQuindio)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_SUBASTA_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subastaQuindio;
	}
	/**
	 * 
	 * @return
	 */
	public static SubastaQuindio cargarRecursoSubastaQuindioXML() {

		SubastaQuindio subasta = null;

		try {
			subasta = (SubastaQuindio) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_SUBASTAQUINDIO_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subasta;

	}
	
	
	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public static boolean buscarPersona(String cedula)
	{
		boolean bandera=false;
		try {
			if(ArchivoUtil.searchPerson(cedula)) {
				bandera= true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bandera;
	}

}
