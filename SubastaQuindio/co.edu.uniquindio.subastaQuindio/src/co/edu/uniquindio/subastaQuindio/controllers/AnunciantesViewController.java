/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.subastaQuindio.Main;
import co.edu.uniquindio.subastaQuindio.dto.InformacionAnuncioDto;
import co.edu.uniquindio.subastaQuindio.models.Anunciante;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
import co.edu.uniquindio.subastaQuindio.models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author GonzalezHDanielaA
 *
 */
public class AnunciantesViewController {

	Main aplication;
	
	Persona usuarioLogueado;
	
	CrudProductoController crudProductoController;
	
	CrudAnuncioController crudAnuncioController;
	
	ModelFactoryController modelFactoryController;
	
	File fotoProductoCreado;
	
	List<InformacionAnuncioDto> listaInformacionAnuncios = new ArrayList<>();	
	ObservableList<InformacionAnuncioDto> listaInformacionAnunciosData = FXCollections.observableArrayList();
	List<Producto> listaInformaProducto = new ArrayList<>();
	ObservableList<Producto> listaInformacionProductoData = FXCollections.observableArrayList();
	
	ObservableList<Producto> productos = FXCollections.observableArrayList();
	//Producto
    @FXML
    private TextField txtCodigoProductoAnuncio;
    
    @FXML
    private TextField txtNombreProductoAnuncio;
    
    @FXML
    private TextField txtDescripcionProductoAnuncio;
    
    @FXML
    private TextField txtValorInicialProductoAnuncio;
    
    @FXML
    private Button txtFotoProductoAnuncio;
    
    @FXML
    private ComboBox<TipoProducto> txtTipoProducto;
    
    @FXML
    private ComboBox<Producto> cboProducto;
    
    @FXML
    private Button btnCrearProducto;
    
    
    @FXML
    private Button btnActualizarAnuncio;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnCrearAnuncio;


    @FXML
    private Button btnElejirPujas;

    @FXML
    private Button btnEliminarProducto;   

    @FXML
    private TableView<InformacionAnuncioDto> tableListaAnunciosRealizados;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnNombreProductoAnuncio;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnNombreAnuncianteAnuncio;    
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnFechaLimiteAnuncio;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnFechaPublicacionAnuncio;    
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnValorInicialAnuncio;
    
    @FXML
    private TableView<Producto> tblTablaProductos;
    @FXML
    private TableColumn<Producto, String> columnCodigoProducto;
    @FXML
    private TableColumn<Producto, String>columnNombreProducto;
    @FXML
    private TableColumn<Producto, String>columnDescripcionProducto;
    @FXML
    private TableColumn<Producto, String>colunmValorInicalProducto;
    @FXML
    private TableColumn<Producto, String>columnTipoProductoProducto;
    
    @FXML
    private AnchorPane tblListaAnunciosParaPujar;
    @FXML
    private TabPane tblListaSolicitudAnuncios;    
    @FXML
    private DatePicker txtFechaLimite;

    @FXML
    private DatePicker txtFechaPublicacion;

    @FXML
    private TextField txtNombreAnunciante;
	
	@FXML
	private ImageView imagenProducto;
	
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btnDescargarAnuncios;

    @FXML
    void initialize() {
    	// para la tabla de anuncios
    	this.columnNombreProductoAnuncio.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	this.columnNombreAnuncianteAnuncio.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
    	this.columnFechaLimiteAnuncio.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));
    	this.columnFechaPublicacionAnuncio.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
    	this.columnValorInicialAnuncio.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
    	//para la tabla de producto
    	this.columnCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	this.columnNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	this.columnDescripcionProducto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	this.colunmValorInicalProducto.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
    	this.columnTipoProductoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    	
    	
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudProductoController = new CrudProductoController(modelFactoryController);
    	crudAnuncioController = new CrudAnuncioController(modelFactoryController);
    	llenarComboTipoProducto();
    }
    public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion, Persona usuarioLogueado) {
		this.aplication = aplicacion;
		this.usuarioLogueado = usuarioLogueado;
		this.txtNombreAnunciante.setText(this.usuarioLogueado.getNombre());
		llenarComboProductos();
		llenarInformacionAnunciosTabla();
		llenarInformacionProductoTabla();
	}
	
	 @FXML
	 void crearProductoAction(ActionEvent event) {
		 	 crearProducto();
	 }
	 
	 @FXML
    void crearAnuncioAction(ActionEvent event) {
		 crearAnuncio();
    }
	 
	 @FXML
    void descargarAnunciosReporte(ActionEvent event) {
		 crearReporteAnuncios();
    }

	@FXML
    void seleccionFotoProducto(ActionEvent event) {
		 List<String> listaExtensiones = new ArrayList<>();
		 listaExtensiones.add("*.png");
		 listaExtensiones.add("*.jpg");
		 
		 FileChooser fc = new FileChooser();
		 fc.getExtensionFilters().add(new ExtensionFilter("Archivos", listaExtensiones));
		 fotoProductoCreado = fc.showOpenDialog(null);
		 if (fotoProductoCreado != null) {
			 System.out.println("Seleccion archivo: " + fotoProductoCreado.getAbsolutePath());
			 InputStream stream;
			try {
				stream = new FileInputStream(fotoProductoCreado.getAbsolutePath());
				Image image = new Image(stream);
				imagenProducto.setImage(image);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
    }
	 public void llenarComboTipoProducto()
		{
			ObservableList<TipoProducto> tipoProducto = FXCollections.observableArrayList();
			TipoProducto t1 = TipoProducto.HOGAR;
			TipoProducto t2 = TipoProducto.TECNOLOGIA;
			TipoProducto t3 = TipoProducto.BIEN_RAIZ;
			TipoProducto t4 = TipoProducto.VEHICULOS;
			TipoProducto t5 = TipoProducto.DEPORTES;			
			tipoProducto.addAll(t1,t2,t3,t4,t5);
			this.txtTipoProducto.setItems(tipoProducto);
			
		}
	 public void llenarComboProductos() {
		 if (modelFactoryController.getSubastaQuindio().getListaAnunciante() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().size() > 0 ) {
			 int indexAnunciante = 0;
			 for (int i = 0; i < modelFactoryController.getSubastaQuindio().getListaAnunciante().size(); i++) {
				if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(i).getCedula().equals(this.usuarioLogueado.getCedula())) {
					indexAnunciante = i;
					break;
				}
			 }
			 productos.addAll(modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getProductosAnunciar());
			 this.cboProducto.setItems(productos);
		 }		 
	 }
	private void crearProducto()
	 {
		 String codigo = txtCodigoProductoAnuncio.getText();
		 String nombre = txtNombreProductoAnuncio.getText();
		 String descripcion = txtDescripcionProductoAnuncio.getText();
		 double valorInicial = Double.parseDouble(txtValorInicialProductoAnuncio.getText());
		 String foto = fotoProductoCreado.getAbsolutePath();
		 TipoProducto tipo = txtTipoProducto.getValue();
		 
		 
		 if(datosValidos(codigo, nombre, descripcion, valorInicial, foto, tipo)) {
			 Producto producto = null;			 
			 producto = crudProductoController.crearProducto(codigo, nombre, descripcion, valorInicial, tipo, foto, this.usuarioLogueado);
			 			
			 if(producto!=null)
			 {
				productos.add(producto);
				this.cboProducto.setItems(productos);
				
			 	listaInformaProducto.add(producto);
				listaInformacionProductoData.clear();
				listaInformacionProductoData.addAll(listaInformaProducto);
				tblTablaProductos.setItems(listaInformacionProductoData);
				showMessage("Notificación registro", "Producto creado", "El producto se ha creado con éxito", AlertType.INFORMATION);
			 }
			 else {
				 showMessage("Notificación registro", "Producto no creado", "Los datos ingresados son inválidos", AlertType.ERROR);
			 }	 
		 }
	 }
	private void crearAnuncio()
	{
		//txtNombreAnunciante.setText(this.usuarioLogueado.getNombre());
		Instant instant = null;
		LocalDate fechaPu = txtFechaPublicacion.getValue();
		instant = Instant.from(fechaPu.atStartOfDay(ZoneId.systemDefault()));		
		Date fechaPubli= Date.from(instant);
		
		LocalDate fechaLimite = txtFechaLimite.getValue();
		instant = Instant.from(fechaLimite.atStartOfDay(ZoneId.systemDefault()));
		Date fechaLimiteDate= Date.from(instant);
		
		Producto producto = cboProducto.getValue();
		
		
		if(datosValidosAnuncio(fechaPubli, fechaLimiteDate, producto))
		{
			Anuncio anuncio = null;
			
			anuncio = crudAnuncioController.crearAnuncio(fechaPubli, fechaLimiteDate, producto,this.usuarioLogueado);
			
			
			if(anuncio != null)
			{
				InformacionAnuncioDto informacionAnuncioDto = null;
				informacionAnuncioDto = new InformacionAnuncioDto();
				informacionAnuncioDto.setNombreAnunciante(usuarioLogueado.getNombre());
				informacionAnuncioDto.setNombreProducto(anuncio.getProducto().getNombreProducto());
				informacionAnuncioDto.setFechaLimite(anuncio.getFechaLimitePublicacion().toString());
				informacionAnuncioDto.setFechaPublicacion(anuncio.getFechaLimitePublicacion().toString());
				informacionAnuncioDto.setValorInicial(""+ anuncio.getProducto().getValorInicial());
				listaInformacionAnuncios.add(informacionAnuncioDto);
				listaInformacionAnunciosData.clear();
				listaInformacionAnunciosData.addAll(listaInformacionAnuncios);
				tableListaAnunciosRealizados.setItems(listaInformacionAnunciosData);
				
				showMessage("Notificación registro anuncio ", "Anuncio creado", "El anuncio se ha creado con éxito", AlertType.INFORMATION);
			}else {
				 showMessage("Notificación registro anuncio", "Anuncio no creado", "Los datos ingresados no son válidos", AlertType.ERROR);
			 }
		}
		
		
		
	}

	private boolean datosValidos(String codigo, String nombre,String descripcion, double valorInicial,
			String foto, TipoProducto tipo)
	{
		
		String mensaje = " ";
		
		if(codigo.equals(" ") || codigo == null)
			mensaje += "el codigo no es válido";
		if(nombre.equals("") || nombre == null)
			mensaje += "El nombre no es válido";
		if(descripcion.equals("") || descripcion == null)
			mensaje += "la descripcion no es valida";
		if(foto.equals("") || foto == null)
			mensaje += "La foto es inválida";
		if(tipo == null)
			mensaje += "el tipo es inválido";
		if(mensaje.equals(" "))
			return true;
		else {
			showMessage("Notificación registro ", "Datos inválidos", mensaje, AlertType.WARNING);
			 return false;
		}
	}
	
	
	
	private boolean datosValidosAnuncio(Date fechaPublicacion, Date fechaFin,Producto producto)
	{
		String mensaje = " ";
		if( fechaPublicacion == null)
			mensaje += "fecha publicacion no es válida";
		if(fechaFin == null)
			mensaje += "fecha fin no es válida";
		if(producto == null)
			mensaje += "producto no es válido";
		if(mensaje.equals(" "))
			return true;
		else {
			showMessage("Notificación registro", "Datos inválidos", mensaje, AlertType.WARNING);
			 return false;
		}
		
	}
	
	
	
	
	private void showMessage(String titulo, String header, String contenido, AlertType alertType)
	 {
		 Alert alert = new Alert(alertType);
		 alert.setTitle(titulo);
		 alert.setHeaderText(header);
		 alert.setContentText(contenido);
		 alert.showAndWait();	 
	 }
	
	private void llenarInformacionAnunciosTabla() {
		if (modelFactoryController.getSubastaQuindio().getListaAnunciante() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().size() > 0 ) {
	    	int indexAnunciante = 0;
			for (int i = 0; i < modelFactoryController.getSubastaQuindio().getListaAnunciante().size(); i++) {
				if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(i).getCedula().equals(this.usuarioLogueado.getCedula())) {
					indexAnunciante = i;
					break;
				}
			}				
			InformacionAnuncioDto informacionAnuncioDto = null;
			if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getLista_anuncio() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getLista_anuncio().size()>0) {				
				for (Anuncio anuncio : modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getLista_anuncio()) {
					informacionAnuncioDto = new InformacionAnuncioDto();
					informacionAnuncioDto.setNombreAnunciante(usuarioLogueado.getNombre());
					informacionAnuncioDto.setNombreProducto(anuncio.getProducto().getNombreProducto());
					informacionAnuncioDto.setFechaLimite(anuncio.getFechaLimitePublicacion().toString());
					informacionAnuncioDto.setFechaPublicacion(anuncio.getFechaPublicacion().toString());
					informacionAnuncioDto.setValorInicial(""+ anuncio.getProducto().getValorInicial());
					informacionAnuncioDto.setCodigoProducto(anuncio.getProducto().getCodigo());
					listaInformacionAnuncios.add(informacionAnuncioDto);            
				}
				listaInformacionAnunciosData.addAll(listaInformacionAnuncios);
				tableListaAnunciosRealizados.setItems(listaInformacionAnunciosData);			
			}
		}			
	}
	
	private void llenarInformacionProductoTabla() {
		if (modelFactoryController.getSubastaQuindio().getListaAnunciante() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().size() > 0 ) {
	    	int indexAnunciante = 0;
			for (int i = 0; i < modelFactoryController.getSubastaQuindio().getListaAnunciante().size(); i++) {
				if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(i).getCedula().equals(this.usuarioLogueado.getCedula())) {
					indexAnunciante = i;
					break;
				}
			}				
			
			if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getLista_anuncio() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getProductosAnunciar().size()>0) {				
				for (Producto producto : modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getProductosAnunciar()) {
					listaInformaProducto.add(producto);            
				}
				
				listaInformacionProductoData.addAll(listaInformaProducto);
				tblTablaProductos.setItems(listaInformacionProductoData);			
			}
		}			
	}
	
	private void crearReporteAnuncios() {
		FileChooser fileChooser = new FileChooser();		 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        Writer writer = null;
        if (file != null) {
       	 try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write("");
				if (modelFactoryController.getSubastaQuindio().getListaAnunciante() != null && modelFactoryController.getSubastaQuindio().getListaAnunciante().size() > 0 ) {
					int indexAnunciante = 0;
					
					for (int i = 0; i < modelFactoryController.getSubastaQuindio().getListaAnunciante().size(); i++) {
						if (modelFactoryController.getSubastaQuindio().getListaAnunciante().get(i).getCedula().equals(this.usuarioLogueado.getCedula())) {
							indexAnunciante = i;
							break;
						}
					}	
					writer.write("NOMBRE PRODUCTO;NOMBRE ANUNCIANTE;FECHA LIMITE; FECHA PUBLICACION; VALOR INICAL \n");
					for (Anuncio anuncio : modelFactoryController.getSubastaQuindio().getListaAnunciante().get(indexAnunciante).getLista_anuncio()) {
						 String text = anuncio.getProducto().getNombreProducto() + ";" + this.usuarioLogueado.getNombre() + ";" + anuncio.getFechaLimitePublicacion() + ";" + anuncio.getFechaPublicacion() +";" + anuncio.getProducto().getValorInicial() +"\n";
		                 writer.write(text);
		             }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	            
			}
        }
	}
	
}
