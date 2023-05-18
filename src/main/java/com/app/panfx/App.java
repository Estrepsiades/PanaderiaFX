//Importamos nuestros paquetes iniciales y JavaFx
package com.app.panfx;
import com.app.panfx.Clases.InventarioPan;
import com.app.panfx.Clases.Pan;
import com.app.panfx.Clases.ShoppingCart;
import com.app.panfx.Clases.UserDataContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
public class App extends Application {
    //Necesario tener JavaJDK 20 y JavaFx20, para configurar JavaFX en NetBeans revisar el siguiente tutorial: "https://www.youtube.com/watch?v=6E4IkTuvUCI&t=331s"
    //Universidad Nacional Autonoma De Mexico
    //Facultad De Estudios Superiores Aragon
    //Ingenieria Electrica Electronica
    //Programacion Aplicada
    //Hernandez Reyna Samuel Alejandro
    //Ahuatzi Calderon Ezequiel Santiago
    //Santiago Ramirez Moraled
    //Jose Antonio Carranza Trejo
    //Carol Alondra Perez Gomez
    //Kevin Manuel Silva Ramirez
    @Override
    //Metodo start, encargado de renderizar nuestra aplicacion en pantalla
    public void start(Stage stage) throws IOException {
        //Hacemos el root, donde estara montada nuestras ventanas
        FXMLLoader root = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(root.load(), 720, 420);
        InventarioPan inventarioPan = new InventarioPan();
        ShoppingCart shoppingCart = new ShoppingCart();
        inventarioPan.addBread(new Pan("Bolillo", 2.5, 50));
        inventarioPan.addBread(new Pan("Dona", 5, 40));
        UserDataContainer userDataContainer = new UserDataContainer( inventarioPan, shoppingCart );
        stage.setUserData( userDataContainer );
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