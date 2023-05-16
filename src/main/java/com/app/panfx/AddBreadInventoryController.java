package com.app.panfx;
import com.app.panfx.Clases.InventarioPan;
import com.app.panfx.Clases.Pan;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class AddBreadInventoryController{
    private InventoryMenuControllers parentController;
    private InventarioPan inventarioPan;
    private Stage stage;
    @FXML
    TextField nameTextField;
    @FXML
    TextField priceTextField;
    @FXML
    TextField unitsTextField;
    public void newBreadButton(ActionEvent event ){
        stage = (Stage) nameTextField.getScene().getWindow();
        String nameOfBread = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int units = Integer.parseInt(unitsTextField.getText());
        if (parentController != null) {
            if ( nameOfBread.isBlank() ){
                System.out.println("Ingresa un caracter");
            }else {
                parentController.addBreadAction( nameOfBread, price, units );
            }
        } else {
            System.out.println("El controlador de InventoryMenuControllers es nulo");
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