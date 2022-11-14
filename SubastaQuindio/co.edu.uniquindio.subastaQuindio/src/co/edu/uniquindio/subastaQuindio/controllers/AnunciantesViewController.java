/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subastaQuindio.Main;
import co.edu.uniquindio.subastaQuindio.models.Producto;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 * @author GonzalezHDanielaA
 *
 */
public class AnunciantesViewController {

	Main aplication;
	
	CrudProductoController crudProductoController;
	
	ModelFactoryController modelFactoryController;
	
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
    private TextField txtFotoProductoAnuncio;
    
    @FXML
    private ComboBox<TipoProducto> txtTipoProducto;
    
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
    private TableColumn<?, ?> columnDescripcion1;

    @FXML
    private TableColumn<?, ?> columnDescripcion11;

    @FXML
    private TableColumn<?, ?> columnFechaLimite1;

    @FXML
    private TableColumn<?, ?> columnFechaLimite11;

    @FXML
    private TableColumn<?, ?> columnFechaPublicacion1;

    @FXML
    private TableColumn<?, ?> columnFechaPublicacion11;

    @FXML
    private TableColumn<?, ?> columnNombreAnunciante1;

    @FXML
    private TableColumn<?, ?> columnNombreAnunciante11;

    @FXML
    private TableColumn<?, ?> columnPersona1;

    @FXML
    private TableColumn<?, ?> columnPrecio;

    @FXML
    private TableColumn<?, ?> columnPrecio1;

    @FXML
    private TableColumn<?, ?> columnProducto;

    @FXML
    private TableColumn<?, ?> columnProducto1;

    @FXML
    private TableColumn<?, ?> columnValorInicial1;

    @FXML
    private TableColumn<?, ?> columnValorInicial11;

    @FXML
    private TableColumn<?, ?> columnnombreanuncio;

    @FXML
    private TableView<?> tableListaAnunciosRealizados;

    @FXML
    private TableView<?> tableListaProductos;

    @FXML
    private AnchorPane tblListaAnunciosParaPujar;

    @FXML
    private TableView<?> tblListaPujasRealizadas;

    @FXML
    private TabPane tblListaSolicitudAnuncios;

    @FXML
    private TableView<?> tbllistaAnuncios;


    @FXML
    private DatePicker txtFechaLimite;

    @FXML
    private DatePicker txtFechaPublicacion;

    @FXML
    private TextField txtNombreAnunciante;
	
	
	
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudProductoController = new CrudProductoController(modelFactoryController);
    	
    	llenarComboTipoProducto();
    }
    
    public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion) {
		this.aplication = aplicacion;
	}
	
	 @FXML
	 void crearProductoAction(ActionEvent event) {
		 	 crearProducto();
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
	private void crearProducto()
	 {
		 String codigo = txtCodigoProductoAnuncio.getText();
		 String nombre = txtNombreProductoAnuncio.getText();
		 String descripcion = txtDescripcionProductoAnuncio.getText();
		 double valorInicial = Double.parseDouble(txtValorInicialProductoAnuncio.getText());
		 String foto = txtFotoProductoAnuncio.getText();
		 TipoProducto tipo = txtTipoProducto.getValue();
		 
		 
		 if(datosValidos(codigo, nombre, descripcion, valorInicial, foto, tipo)) {
			 Producto producto = null;
			 producto = crudProductoController.crearProducto(codigo, nombre, descripcion, valorInicial, tipo, foto);
			 
			 if(producto!=null)
			 {
				 showMessage("Notificación registro", "Producto creado", "El producto se ha creado con éxito", AlertType.INFORMATION);
			 }
			 else {
				 showMessage("Notificación registro", "Producto no creado", "Los datos ingresados son inválidos", AlertType.ERROR);
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
	
	private void showMessage(String titulo, String header, String contenido, AlertType alertType)
	 {
		 Alert alert = new Alert(alertType);
		 alert.setTitle(titulo);
		 alert.setHeaderText(header);
		 alert.setContentText(contenido);
		 alert.showAndWait();	 
	 }
	
}
