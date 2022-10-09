/**
 * 
 */
package co.edu.uniquindio.subastaQuindio.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.subastaQuindio.Main;
import javafx.fxml.FXML;

/**
 * @author GonzalezHDanielaA
 *
 */
public class AnunciantesViewController {

	Main aplication;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }
    
    public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion) {
		this.aplication = aplicacion;
	}
}
