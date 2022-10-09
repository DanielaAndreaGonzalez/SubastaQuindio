/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.SubastaQuindio;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.Usuario;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Persistencia {
	
	//public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "C://td//persistencia//Archivos//"+ Usuario.NOMBRE_ARCHIVO_GUARDADO_EXTENCION;
	public static final String RUTA_ARCHIVO_LOG = "C://td//persistencia//log//SubastaQuindioLog.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_BANCO_BINARIO = "src/resources/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_BANCO_XML = "C://td//persistencia//model.xml";
	
	public static final String RUTA_ARCHIVO_COPIA_ORIGEN_GENERAL = "C://td//persistencia//Archivos//";
	public static final String RUTA_ARCHIVO_COPIA_DESTINO_GENERAL = "C://td//persistencia//respaldo//";
	
	
	public static void cargarDatosArchivos(SubastaQuindio subastaQuindio) throws FileNotFoundException,  IOException
	{
		
		//Cargar Archivo de usuarios
		ArrayList<Persona> usuarioCargados = cargarUSuarios();
		
		if(usuarioCargados.size() >0)
			subastaQuindio.getListaPersona().addAll(usuarioCargados);
		
		//Se carga el resto de archivos
		//Cargar archivo anuncios
		//Cargar archivos pujas 
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
			contenido+= persona.getCedula()+ ","+persona.getNombre()+","+persona.getEdad()+","
					 +persona.getTipoPersona()+","+persona.getUsuario()+","+persona.getContrasenia()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
	}
	
	/**
	 * 
	 * @return un arrayList de personas con los datos obtenidos del archivo de
	 * texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Persona> cargarUSuarios() throws FileNotFoundException, IOException
	{
		
		ArrayList<Persona> personas = new ArrayList<Persona>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea = "";
		TipoPersona t = null;
		String tipoPersona = linea.split(",")[3];
		switch(t) {
			case USUARIOANUNCIANTES:
				tipoPersona = t.name();
				break;
			case USUARIOCOMPRADOR: 
				tipoPersona = t.name();
				break;
		}
		for(int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Persona persona = new Persona();
			persona.setCedula(linea.split(",")[0]);
			persona.setNombre(linea.split(",")[1]);
			persona.setEdad(Integer.parseInt(linea.split(",")[2]));
			persona.setTipoPersona(t);
			persona.setUsuario(linea.split(",")[4]);
			persona.setContrasenia(linea.split(",")[5]);
			personas.add(persona);
		}
		return personas;
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

}
