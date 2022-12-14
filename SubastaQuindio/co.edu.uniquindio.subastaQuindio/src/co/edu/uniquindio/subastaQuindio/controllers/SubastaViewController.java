/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;


import co.edu.uniquindio.subastaQuindio.Main;
import co.edu.uniquindio.subastaQuindio.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import co.edu.uniquindio.subastaQuindio.models.TipoProducto;
import co.edu.uniquindio.subastaQuindio.models.Usuario;
import co.edu.uniquindio.subastaQuindio.persistence.Persistencia;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaViewController {
	
	
	Main aplication;
	
	ModelFactoryController modelFactoryController;
	
	CrudRegistroViewController crudRegistroViewController;
	
	CrudProductoController crudProductoController;
	
	
	@FXML 
	private ResourceBundle resources;
	
	  @FXML
	    private Button btningresar;

	    @FXML
	    private Button btnregistrar;

	    @FXML
	    private PasswordField txtcontrasena;

	    @FXML
	    private PasswordField txtcontrasenaautenticarse;

	    @FXML
	    private TextField txtedad;

	    @FXML
	    private TextField txtnombre;

	    @FXML
	    private ComboBox<TipoPersona> txtrol;

	    @FXML
	    private TextField txtusuario;

	    @FXML
	    private TextField txtusuarioautenticarse;
	    
	    @FXML
	    private TextField txtcedula;
	    
	    @FXML
	    private RadioButton rdbAnunciante;
	    
	    @FXML
	    private RadioButton rdbComprador;
	
	    ToggleGroup tg = new ToggleGroup();
	    
	    

	    
	
	@FXML
	void initialize()
	{
		modelFactoryController = ModelFactoryController.getInstance();
		crudRegistroViewController = new CrudRegistroViewController(modelFactoryController);
		crudProductoController = new CrudProductoController(modelFactoryController);
		llenarComboRol();
		
		
		rdbAnunciante.setToggleGroup(this.tg);
		rdbComprador.setToggleGroup(this.tg);
		
		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ob, Toggle oldSelection, Toggle newSelection)
			{
				RadioButton rb = (RadioButton)tg.getSelectedToggle();

//				if (rb != null) {
//					String s = rb.getText();
//					JOptionPane.showMessageDialog(null,"Radio buton seleccionado: --> "+s);
//				}
			}
		});
	}
	
	public void llenarComboRol()
	{
		ObservableList<TipoPersona> tipoPers = FXCollections.observableArrayList();
		TipoPersona t = TipoPersona.USUARIOANUNCIANTES;
		TipoPersona t2 = TipoPersona.USUARIOCOMPRADOR;
		tipoPers.addAll(t,t2);
		this.txtrol.setItems(tipoPers);
	}
	
	
	
	public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion) {
		this.aplication = aplicacion;
	}
	
	 @FXML
    void registrarPerson(ActionEvent event) {
		 registerPerson();	 
    }
	 
	 @FXML 
    void ingresar(ActionEvent event) {
		 ingresarLogin();
    }
	 

	 private void ingresarLogin() {
		 
		//capturar usuario y contrase?a
		 String usuarioIngresado =  txtusuarioautenticarse.getText();
		 String contrase?aIngresada =  txtcontrasenaautenticarse.getText();
		// buscar usuario y conse?a en archivo binario
		try {
			modelFactoryController.getFlujoSalidaComunicacion().writeInt(3);
			modelFactoryController.getFlujoSalidaComunicacion().writeUTF(usuarioIngresado);
			modelFactoryController.getFlujoSalidaComunicacion().writeUTF(contrase?aIngresada);
			boolean respuesta =  modelFactoryController.getFlujoEntradaComunicacion().readBoolean();
			if (respuesta) {
				if(rdbAnunciante.isSelected()){	
					Persona usuarioLogueado = Persistencia.obtenerUsuario(usuarioIngresado, contrase?aIngresada);
					getAplicacion().mostrarVentanaAnunciante(usuarioLogueado);
			    }
			    else{
			    	Persona usuarioLogueado = Persistencia.obtenerUsuario(usuarioIngresado, contrase?aIngresada);
			    	getAplicacion().mostrarVentanaComprador(usuarioLogueado);
			    }	
			}
			Persistencia.guardarRegistroLog("Usuario Logueado", 1, "Ingreso al sistema  "+usuarioIngresado);
		} catch (IOException e) {
			Persistencia.guardarRegistroLog("SubastaViewController - ingresarLogin", 3, e.getMessage());
			e.printStackTrace();
		}
		
			
	}

	private void registerPerson()
	 {
		 //1. Capturar los datos
		 String cedula = txtcedula.getText(); 
		 String nombre = txtnombre.getText();
		 String edad = txtedad.getText();
		 TipoPersona rol = txtrol.getValue();
		 String usuario = txtusuario.getText();
		 String contrasenia = txtcontrasena.getText();
		 
		 //2. Validar la informaci?n 
		 
		 if(datosValidos(cedula, nombre, edad, usuario, contrasenia, rol)==true) {
			 Persona persona = null;
			 persona = crudRegistroViewController.registerPerson(cedula, nombre, Integer.parseInt(edad), usuario, contrasenia, rol);
			 
			 if(persona != null)
			 {
				 showMessage("Notificaci?n registro", "Usuario creado", "El usuario se ha creado con ?xito", AlertType.INFORMATION);
				 clearFields();
			 }else {
				 showMessage("Notificaci?n registro", "Usuario no creado", "El usuario no se ha creado con ?xito", AlertType.WARNING);
			 }
		 
		 }else {
			 showMessage("Notificaci?n registro", "Usuario no creado", "Los datos ingresados son inv?lidos", AlertType.ERROR);
			 
		 }		 
	 }
	 
	 
	 private void clearFields()
	 {
		 
		 txtcedula.setText("");
		 txtnombre.setText("");
		 txtedad.setText("");
		 //txtrol.setValue(null);
		 txtusuario.setText("");
		 txtcontrasena.setText("");
		 
	 }
	 
	 
	 
	 private void showMessage(String titulo, String header, String contenido, AlertType alertType)
	 {
		 Alert alert = new Alert(alertType);
		 alert.setTitle(titulo);
		 alert.setHeaderText(header);
		 alert.setContentText(contenido);
		 alert.showAndWait();	 
	 }
	 
	 private boolean datosValidos(String cedula, String nombre, String edad, String usuario, String contrasenia, TipoPersona rol) {
		 
		 String mensaje="";
		 
		 if(cedula == null || cedula.equals(""))
			 mensaje += "La cedula es inv?lida \n";
		 
		 if(nombre == null || nombre.equals(""))
			 mensaje += "El nombre es invalido \n";
		 
		 if(usuario == null || usuario.equals(""))
			 mensaje += "El usuario es invalido \n";
		 
		 if(contrasenia == null || contrasenia.equals(""))
			 mensaje += "La contrasena es invalida \n";
		 
		 if(rol == null || rol.equals(""))
			 mensaje += "El rol es invalido \n";
		 
		 if(edad  == null || edad.equals("") )
			 mensaje += "edad invalidad \n";
		 
		 if(mensaje.equals(""))
		 {
			 return true;
		 }else {
			 showMessage("Notificaci?n registro ", "Datos inv?lidos", mensaje, AlertType.WARNING);
			 return false;
		 }	 
	 }
	 
	 
	 

}
