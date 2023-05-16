package com.app.panfx;

import com.app.panfx.Clases.PanSC;
import com.app.panfx.Clases.ShoppingCart;
import com.app.panfx.InventoryMenuControllers;
import com.app.panfx.ShoppingCartControllers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketShoppingCartController implements Initializable {
    private ShoppingCartControllers parentController;
    private ShoppingCart shoppingCart;
    private Stage stage;
    @FXML
    private Label totalPriceItemsTicket;
    private double totalPrices;
    @FXML
    ListView<String> itemsToTicket;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->{
            stage = (Stage) totalPriceItemsTicket.getScene().getWindow();
            shoppingCart = (ShoppingCart) stage.getUserData();
            totalPrices = shoppingCart.getTotalPrices();
            totalPriceItemsTicket.setText(String.valueOf(totalPrices));
            List<String> dataBread = new ArrayList<>();
            for ( PanSC panSC : shoppingCart.getBreadsSC() ){
                String data = panSC.getNameOfBread() + " - Unidades: " + panSC.getSelectedUnits() + " - Precio: " + panSC.getBreadTotalPrice();
                dataBread.add( data );
            }
            ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll( dataBread );
            itemsToTicket.setItems( items );
        });
    }

    public void acceptBtn(ActionEvent event ){
        stage = (Stage) totalPriceItemsTicket.getScene().getWindow();
        if ( parentController != null ){
            parentController.sellBtnAction();
        }else {
            System.out.println("Error en ticket");
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
    public void setParentController( ShoppingCartControllers parentController ){
        this.parentController = parentController;
    }
}
