package co.edu.uniquindio.subastaQuindio;
	
import java.io.IOException;

import co.edu.uniquindio.subastaQuindio.controllers.AnunciantesViewController;
import co.edu.uniquindio.subastaQuindio.controllers.SubastaViewController;
import co.edu.uniquindio.subastaQuindio.models.Anuncio;
import co.edu.uniquindio.subastaQuindio.models.Persona;
import co.edu.uniquindio.subastaQuindio.models.Usuario;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Subastas UQ");
		mostrarVentanaPrincipal();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	private void mostrarVentanaPrincipal(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/SubastaView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			//	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("views/SubastaView.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("SampleController.fxml"));
			SubastaViewController subastaViewController = loader.getController();
			subastaViewController.setAplicacion(this);
			Scene scene = new Scene(root,880,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarVentanaAnunciante(Persona usuariosLogueado) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/AnuncianteView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			AnunciantesViewController anunciantesViewController = loader.getController();
			
			anunciantesViewController.setAplicacion(this,usuariosLogueado);
			Scene scene = new Scene(rootLayout, 900,650);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}	

	public void mostrarVentanaComprador() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/CompradorView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			//SubastaWorkerViewController subastaWorkerViewController = loader.getController();
//			subastaWorkerViewController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
