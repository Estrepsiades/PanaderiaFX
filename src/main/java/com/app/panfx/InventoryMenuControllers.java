package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryMenuControllers implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //InventarioPan inventarioPan = (InventarioPan) scene.getUserData();
    }

    public void switchToSell(ActionEvent event ) throws Exception{
        try {
            root = FXMLLoader.load(getClass().getResource("ventaMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            String css = this.getClass().getResource("/css/app.css").toExternalForm();
            scene = new Scene( root );
            scene.getStylesheets().add( css );
            stage.setScene( scene );
            stage.show();
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
    public void addItemButton(ActionEvent event){
        System.out.println("Agregando Item");
    }
}
