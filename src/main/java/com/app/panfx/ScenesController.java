package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScenesController {
    private InventarioPan inventarioPan;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToSellMain( ActionEvent event ) throws Exception{
        root = FXMLLoader.load(getClass().getResource("ventaMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("/css/app.css").toExternalForm();
        scene = new Scene( root );
        scene.getStylesheets().add( css );
        stage.setScene( scene );
        stage.show();
    }
    public void switchToInventoryMain( ActionEvent event) throws Exception{
        try {
            root = FXMLLoader.load(getClass().getResource("inventoryMenu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            String css = this.getClass().getResource("/css/app.css").toExternalForm();
            scene = new Scene( root );
            scene.getStylesheets().add( css );
            stage.setScene( scene );
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void testButton( ActionEvent event ){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        inventarioPan = (InventarioPan) stage.getUserData();
        inventarioPan.shownBreads();
    }

}