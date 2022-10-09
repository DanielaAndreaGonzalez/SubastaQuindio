package co.edu.uniquindio.subastaQuindio;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("views/SubastaView.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("SampleController.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void mostrarVentanaAnunciante() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/SubastaActorView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			//SubastaActorViewController subastaActorViewController = loader.getController();
//			subastaActorViewController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
		}
		
	}	

	public void mostrarVentanaComprador() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/SubastaWorkerView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			//SubastaWorkerViewController subastaWorkerViewController = loader.getController();
//			subastaWorkerViewController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
}
