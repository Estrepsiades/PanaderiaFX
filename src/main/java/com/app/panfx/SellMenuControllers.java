package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SellMenuControllers {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToInventory( ActionEvent event ) throws Exception{
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
}
