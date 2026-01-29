package org.openjfx.wortratespiel_fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

   

    @Override
    public void start(Stage stage) throws Exception {
    	
        Parent root = FXMLLoader.load(getClass().getResource("/org/openjfx/wortratespiel_fxml/Wortratespiel.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
        	    getClass().getResource("/org/openjfx/wortratespiel_fxml/style.css").toExternalForm()
        	);
        stage.setScene(scene);
        stage.setTitle("Wortratespiel");
        
        stage.show();
        }

    public static void main(String[] args) {
        launch();
    }

}