package com.app.panfx;

import com.app.panfx.Clases.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SellMenuControllers implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserDataContainer userDataContainer;
    private InventarioPan inventarioPan;
    private ShoppingCart shoppingCart;
    @FXML
    private ListView<Pan> inventoryBreads;
    @FXML
    private Spinner<Integer> unitsSpinner;
    @FXML
    private Label nameOfBread;
    @FXML
    private Label priceOfBread;
    @FXML
    private Label unitsOfBread;
    String currentNameOfBread;
    double currentPriceOfBread;
    int currentUnitsOfBread;
    int selectedIndex = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->{
            stage = (Stage) inventoryBreads.getScene().getWindow();
            userDataContainer = (UserDataContainer) stage.getUserData();
            inventarioPan = userDataContainer.getInventarioPan();
            shoppingCart = userDataContainer.getShoppingCart();
            ObservableList<Pan> breads = FXCollections.observableArrayList();
            breads.addAll( inventarioPan.getBreads() );
            inventoryBreads.setItems( breads );
            inventoryBreads.setCellFactory(param -> new ListCell<Pan>(){
                @Override
                protected void updateItem(Pan pan, boolean empty) {
                    super.updateItem(pan, empty);
                    if (empty || pan == null || pan.getNameOfBread() == null ){
                        setText(null);
                    }else {
                        setText(pan.getNameOfBread());
                    }
                }
            });
            inventoryBreads.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pan>() {
                @Override
                public void changed(ObservableValue<? extends Pan> observableValue, Pan oldValue, Pan newValue) {
                    selectedIndex = inventoryBreads.getSelectionModel().getSelectedIndex();
                    if (newValue != null){
                        currentNameOfBread = newValue.getNameOfBread();
                        currentPriceOfBread = newValue.getPrice();
                        currentUnitsOfBread = newValue.getUnits();
                        nameOfBread.setText( currentNameOfBread );
                        priceOfBread.setText(String.valueOf(currentPriceOfBread));
                        unitsOfBread.setText(String.valueOf(currentUnitsOfBread));
                        SpinnerValueFactory<Integer> valueFactory =
                                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, newValue.getUnits() );
                        valueFactory.setValue(1);
                        unitsSpinner.setValueFactory(valueFactory);
                    }
                }
            });
        });
    }
    public void addToShoppingCart( ActionEvent event ){
        if ( selectedIndex != -1 ){
            if ( unitsSpinner.getValue() == inventarioPan.getBreads().get(selectedIndex).getUnits() ){
                String nameOfBread = inventarioPan.getBreads().get(selectedIndex).getNameOfBread();
                double price = inventarioPan.getBreads().get(selectedIndex).getPrice();
                int unitsSelected = unitsSpinner.getValue();
                shoppingCart.addBread(new PanSC( nameOfBread, price, unitsSelected, selectedIndex ));
                inventarioPan.getBreads().remove( selectedIndex );
                inventoryBreads.getItems().remove( selectedIndex );
            }else {
                String nameOfBread = inventarioPan.getBreads().get(selectedIndex).getNameOfBread();
                int index = -1;
                for (int i = 0; i < shoppingCart.getBreadsSC().size(); i++) {
                    PanSC panSC = shoppingCart.getBreadsSC().get(i);
                    if (panSC.getNameOfBread().equals(nameOfBread)) {
                        index = i;
                        break;
                    }
                }
                if ( index != -1 ){
                    shoppingCart.getBreadsSC().get(index).addSelectedUnits( unitsSpinner.getValue() );
                    inventarioPan.getBreads().get(selectedIndex).deleteUnits( unitsSpinner.getValue() );
                }else {
                    inventarioPan.getBreads().get(selectedIndex).deleteUnits( unitsSpinner.getValue() );
                    double price = inventarioPan.getBreads().get(selectedIndex).getPrice();
                    int unitsSelected = unitsSpinner.getValue();
                    shoppingCart.addBread(new PanSC( nameOfBread, price, unitsSelected, selectedIndex ));
                }
            }
        }
    }
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
    public void switchToShoppingCart( ActionEvent event ) throws Exception {
        try {
            root = FXMLLoader.load(getClass().getResource("shoppingCart.fxml"));
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
