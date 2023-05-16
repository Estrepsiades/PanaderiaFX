package com.app.panfx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class AddUnitsInventoryController {
    private InventoryMenuControllers parentController;
    private Stage stage;
    private int units;
    @FXML
    TextField unitsTextField;
    public void addUnitsButton(ActionEvent event ){
        stage = (Stage) unitsTextField.getScene().getWindow();
        units = Integer.parseInt( unitsTextField.getText());
        if ( parentController != null ){
            parentController.addUnitsAction( units );
        }else {
            System.out.println("Error en AddUnitsInventroyController ");
        }
        stage.close();
    }
    @FXML
    private void cancel(ActionEvent event ){
        closeWindow(event);
    }
    private void closeWindow(ActionEvent event ){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    public void setParentController( InventoryMenuControllers parentController ){
        this.parentController = parentController;
    }
}