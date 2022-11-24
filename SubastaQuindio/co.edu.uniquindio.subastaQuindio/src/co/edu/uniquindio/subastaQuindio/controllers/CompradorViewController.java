package co.edu.uniquindio.subastaQuindio.controllers;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.subastaQuindio.Main;
import co.edu.uniquindio.subastaQuindio.dto.InformacionAnuncioDto;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CompradorViewController {
	Main aplication;
	
	Persona usuarioLogueado;
	
	CrudProductoController crudProductoController;
	
	CrudAnuncioController crudAnuncioController;
	
	ModelFactoryController modelFactoryController;
	
	List<InformacionAnuncioDto> listaInformacionAnuncios = new ArrayList<>();	
	ObservableList<InformacionAnuncioDto> listaInformacionAnunciosData = FXCollections.observableArrayList();
	
	InformacionAnuncioDto informacionAnuncioDtoSeleccionado;
	
	//campos 
	@FXML
    private Button btnGuardarPuja;

	@FXML
    private TableView<InformacionAnuncioDto> tblListaProductosPujar;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnDescripcionAnuncio;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnFechaLimite;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnFechaPublicacion;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnNombreAnunciante;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnNombreProducto;
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnValorInicial;    
    @FXML
    private TableColumn<InformacionAnuncioDto, String> columnTipoProducto;

    @FXML
    private AnchorPane tblListaAnunciosParaPujar;
    

    @FXML
    private TabPane tblListaSolicitudAnuncios;

    @FXML
    private TextField txtMontoPuja;

    @FXML
    private TextField txtNobreProductoSeleccionPuja;

    @FXML
    private TextField txtTipoProducto;

    @FXML
    private TextField txtValorInicialProductoSeleccionPuja;
	    
	
	@FXML
    void initialize() {
		this.columnDescripcionAnuncio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.columnValorInicial.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
		this.columnNombreAnunciante.setCellValueFactory(new PropertyValueFactory<>("nombreAnunciante"));
		this.columnFechaPublicacion.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
		this.columnFechaLimite.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));
		this.columnNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
		this.columnTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
		
		
		tblListaProductosPujar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			informacionAnuncioDtoSeleccionado = newSelection;

			mostrarInformacionAnuncioSeleccionado(informacionAnuncioDtoSeleccionado);

		});
		
		modelFactoryController = ModelFactoryController.getInstance();
    	crudProductoController = new CrudProductoController(modelFactoryController);
    	crudAnuncioController = new CrudAnuncioController(modelFactoryController);
	}
	
	private void mostrarInformacionAnuncioSeleccionado(InformacionAnuncioDto informacionAnuncioDtoSeleccionado2) {
		if(informacionAnuncioDtoSeleccionado2 != null) {
			this.txtNobreProductoSeleccionPuja.setText(informacionAnuncioDtoSeleccionado2.getNombreProducto());
			this.txtValorInicialProductoSeleccionPuja.setText(informacionAnuncioDtoSeleccionado2.getValorInicial());
			this.txtTipoProducto.setText(informacionAnuncioDtoSeleccionado2.getTipoProducto());
		}
	}

	@FXML
    void guardarPujaAction(ActionEvent event) {
		guardarPuja();
    }
	
	private void guardarPuja() {
		double valorPagarPuja = Double.parseDouble(this.txtMontoPuja.getText());
		String codigoProducto ="";
		
		
		
	}

	public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion, Persona usuarioLogueado) {
		this.aplication = aplicacion;
		this.usuarioLogueado = usuarioLogueado;
		//llenarComboProductos();
		llenarInformacionAnunciosTabla();	
	}
	
	private void llenarInformacionAnunciosTabla() {
		if (modelFactoryController.getSubastaQuindio().getListaAnuncios() != null && modelFactoryController.getSubastaQuindio().getListaAnuncios().size() > 0 ) {
	    	InformacionAnuncioDto informacionAnuncioDto = null;					
			for (Anuncio anuncio : modelFactoryController.getSubastaQuindio().getListaAnuncios()) {
				informacionAnuncioDto = new InformacionAnuncioDto();
				informacionAnuncioDto.setNombreAnunciante(usuarioLogueado.getNombre());
				informacionAnuncioDto.setNombreProducto(anuncio.getProducto().getNombreProducto());
				informacionAnuncioDto.setFechaLimite(anuncio.getFechaLimitePublicacion().toString());
				informacionAnuncioDto.setFechaPublicacion(anuncio.getFechaPublicacion().toString());
				informacionAnuncioDto.setValorInicial(""+ anuncio.getProducto().getValorInicial());
				informacionAnuncioDto.setDescripcion(anuncio.getProducto().getDescripcion());
				informacionAnuncioDto.setTipoProducto(anuncio.getProducto().getTipoProducto().toString());
				listaInformacionAnuncios.add(informacionAnuncioDto);            
			}
			listaInformacionAnunciosData.addAll(listaInformacionAnuncios);
			tblListaProductosPujar.setItems(listaInformacionAnunciosData);						
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
		//this.txtTipoProducto.setItems(tipoProducto);	
	}
	
	private void showMessage(String titulo, String header, String contenido, AlertType alertType){
		 Alert alert = new Alert(alertType);
		 alert.setTitle(titulo);
		 alert.setHeaderText(header);
		 alert.setContentText(contenido);
		 alert.showAndWait();	 
	 }
	
}
