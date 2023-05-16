package com.app.panfx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class ModifyPriceInventoryController {
    private  InventoryMenuControllers parentController;
    private Stage stage;
    private double newPrice;
    @FXML
    TextField priceTextField;
    public void newPriceButton(ActionEvent event ){
        stage = (Stage) priceTextField.getScene().getWindow();
        newPrice = Double.parseDouble(priceTextField.getText());
        if ( parentController != null ){
            parentController.changePriceBreadAction( newPrice );
        }else {
            System.out.println("Error en ModifyPriceInventoryController ");
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
