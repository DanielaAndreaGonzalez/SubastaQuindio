/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;


import java.util.ResourceBundle;


import co.edu.uniquindio.subastaQuindio.Main;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.TipoPersona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
/**
 * @author GonzalezHDanielaA
 *
 */
public class SubastaViewController {
	
	
	Main aplication;
	
	ModelFactoryController modelFactoryController;
	
	CrudRegistroViewController crudRegistroViewController;
	
	
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
	void initialize()
	{
		modelFactoryController = ModelFactoryController.getInstance();
		crudRegistroViewController = new CrudRegistroViewController(modelFactoryController);
		llenarComboRol();
		
	}
	
	public void llenarComboRol()
	{
		ObservableList<TipoPersona> tipoPers = FXCollections.observableArrayList();
		TipoPersona t = TipoPersona.USUARIOANUNCIANTES;
		TipoPersona t2 = TipoPersona.USUARIOCOMPRADOR;
		tipoPers.addAll(t,t2);
		this.txtrol.setItems(tipoPers);
	}
	
	
	 @FXML
    void registrarPerson(ActionEvent event) {
		 registerPerson();	 
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
		 
		 //2. Validar la información 
		 
		 if(datosValidos(cedula, nombre, edad, usuario, contrasenia, rol)==true) {
			 Persona persona = null;
			 persona = crudRegistroViewController.registerPerson(cedula, nombre, Integer.parseInt(edad), usuario, contrasenia, rol);
			 
			 if(persona != null)
			 {
				 showMessage("Notificación registro", "Usuario creado", "El usuario se ha creado con éxito", AlertType.INFORMATION);
				 clearFields();
			 }else {
				 showMessage("Notificación registro", "Usuario no creado", "El usuario no se ha creado con éxito", AlertType.WARNING);
			 }
		 
		 }else {
			 showMessage("Notificación registro", "Usuario no creado", "Los datos ingresados son inválidos", AlertType.ERROR);
			 
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
			 mensaje += "La cedula es inválida \n";
		 
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
			 showMessage("Notificación registro ", "Datos inválidos", mensaje, AlertType.WARNING);
			 return false;
		 }	 
	 }
	 
	 
	 

}
