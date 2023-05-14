package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(root.load(), 720, 420);
        InventarioPan inventarioPan = new InventarioPan();
        scene.setUserData( inventarioPan );
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