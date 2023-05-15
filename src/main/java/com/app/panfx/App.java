//Importamos nuestros paquetes iniciales y JavaFx
package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import com.app.panfx.Clases.Pan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    //Metodo start, encargado de renderizar nuestra aplicacion en pantalla
    public void start(Stage stage) throws IOException {
        //Hacemos el root, donde estara montada nuestras ventanas
        FXMLLoader root = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(root.load(), 720, 420);
        InventarioPan inventarioPan = new InventarioPan();
        inventarioPan.addBread(new Pan("Bolillo", 2.5, 50));
        inventarioPan.addBread(new Pan("Dona", 5, 40));
        stage.setUserData( inventarioPan );
        inventarioPan.shownBreads();
        Image icon = new Image(getClass().getResourceAsStream("/images/bread.png"));
        String css = this.getClass().getResource("/css/app.css").toExternalForm();
        scene.getStylesheets().add( css );
        stage.getIcons().add( icon );
        stage.setTitle("PanaderiaFX");
        stage.setResizable( false );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}