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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingCartControllers implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserDataContainer userDataContainer;
    private InventarioPan inventarioPan;
    private ShoppingCart shoppingCart;
    @FXML
    private ListView<PanSC> shoppingCartBreads;
    @FXML
    private Spinner<Integer> unitsSpinnerCart;
    @FXML
    private Label nameOfBreadCart;
    @FXML
    private Label priceOfBreadCart;
    @FXML
    private Label unitsOfBreadCart;
    @FXML
    private Label totalPrice;
    String currentNameOfBread;
    double currentPriceOfBread;
    double currentTotalPrice;
    int currentUnitsOfBread;
    int selectedIndex = -1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            stage = (Stage) shoppingCartBreads.getScene().getWindow();
            userDataContainer = (UserDataContainer) stage.getUserData();
            inventarioPan = userDataContainer.getInventarioPan();
            shoppingCart = userDataContainer.getShoppingCart();
            currentTotalPrice = shoppingCart.getTotalPrices();
            totalPrice.setText(String.valueOf( currentTotalPrice ));
            ObservableList<PanSC> breadsSC = FXCollections.observableArrayList();
            breadsSC.addAll( shoppingCart.getBreadsSC() );
            shoppingCartBreads.setItems( breadsSC );
            shoppingCartBreads.setCellFactory(param -> new ListCell<PanSC>(){
                @Override
                protected void updateItem(PanSC panSC, boolean empty) {
                    super.updateItem(panSC, empty);
                    if (empty || panSC == null || panSC.getNameOfBread() == null ){
                        setText(null);
                    }else {
                        setText(panSC.getNameOfBread());
                    }
                }
            });
            shoppingCartBreads.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PanSC>() {
                @Override
                public void changed(ObservableValue<? extends PanSC> observableValue, PanSC oldValue, PanSC newValue) {
                    selectedIndex = shoppingCartBreads.getSelectionModel().getSelectedIndex();
                    if (newValue != null){
                        currentNameOfBread = newValue.getNameOfBread();
                        currentPriceOfBread = newValue.getPrice();
                        currentUnitsOfBread = newValue.getSelectedUnits();
                        nameOfBreadCart.setText( currentNameOfBread );
                        priceOfBreadCart.setText(String.valueOf(currentPriceOfBread));
                        unitsOfBreadCart.setText(String.valueOf(currentUnitsOfBread));
                        SpinnerValueFactory<Integer> valueFactory =
                                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, newValue.getSelectedUnits() );
                        valueFactory.setValue(1);
                        unitsSpinnerCart.setValueFactory(valueFactory);
                    }
                }
            });

        });
    }
    public void deleteAUnitFromShoppingCar( ActionEvent event){
        if ( selectedIndex != -1 ){
            int unitsToDelete = unitsSpinnerCart.getValue();
            if ( unitsToDelete == shoppingCart.getBreadsSC().get(selectedIndex).getSelectedUnits() ){
                if ( shoppingCart.getBreadsSC().get(selectedIndex).getNameOfBread().equals(inventarioPan.getBreads().get(shoppingCart.getBreadsSC().get(selectedIndex).getIndexFromInventory()).getNameOfBread())){
                    inventarioPan.getBreads().get( shoppingCart.getBreadsSC().get(selectedIndex).getIndexFromInventory()).addUnits( unitsToDelete );
                    shoppingCart.getBreadsSC().get(selectedIndex).removeSelectedUnits( unitsToDelete );
                    shoppingCart.getBreadsSC().remove( selectedIndex );
                    shoppingCartBreads.getItems().remove( selectedIndex );
                    currentTotalPrice = shoppingCart.getTotalPrices();
                    totalPrice.setText(String.valueOf(currentTotalPrice));
                } else {
                    String nameOfBread = shoppingCart.getBreadsSC().get(selectedIndex).getNameOfBread();
                    double price = shoppingCart.getBreadsSC().get(selectedIndex).getPrice();
                    int units = shoppingCart.getBreadsSC().get(selectedIndex).getSelectedUnits();
                    inventarioPan.addBread(new Pan(nameOfBread, price, units));
                    shoppingCart.getBreadsSC().remove( selectedIndex );
                    shoppingCartBreads.getItems().remove( selectedIndex );
                    currentTotalPrice = shoppingCart.getTotalPrices();
                    totalPrice.setText(String.valueOf(currentTotalPrice));
                }
            }else {
                inventarioPan.getBreads().get( shoppingCart.getBreadsSC().get(selectedIndex).getIndexFromInventory()).addUnits( unitsToDelete );
                shoppingCart.getBreadsSC().get(selectedIndex).removeSelectedUnits( unitsToDelete );
                currentTotalPrice = shoppingCart.getTotalPrices();
                totalPrice.setText(String.valueOf(currentTotalPrice));
                ObservableList<PanSC> breadsSC = FXCollections.observableArrayList();
                breadsSC.addAll( shoppingCart.getBreadsSC() );
                shoppingCartBreads.setItems( breadsSC );
            }
        }
    }
    public void deleteBreadFromShoppingCar(ActionEvent event) {
        selectedIndex = shoppingCartBreads.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String nameOfBread = shoppingCart.getBreadsSC().get(selectedIndex).getNameOfBread();

            int indexFromInventory = -1;
            for (int i = 0; i < inventarioPan.getBreads().size(); i++) {
                if (inventarioPan.getBreads().get(i).getNameOfBread().equals(nameOfBread)) {
                    indexFromInventory = i;
                    break;
                }
            }

            if (indexFromInventory != -1) {
                inventarioPan.getBreads().get(indexFromInventory).addUnits(shoppingCart.getBreadsSC().get(selectedIndex).getSelectedUnits());
            } else {
                double price = shoppingCart.getBreadsSC().get(selectedIndex).getPrice();
                int units = shoppingCart.getBreadsSC().get(selectedIndex).getSelectedUnits();
                inventarioPan.addBread(new Pan(nameOfBread, price, units));
            }

            shoppingCart.getBreadsSC().remove(selectedIndex);
            shoppingCartBreads.getItems().remove(selectedIndex);

            currentTotalPrice = shoppingCart.getTotalPrices();
            totalPrice.setText(String.valueOf(currentTotalPrice));
        }
    }


    public void cleanShoppingCar(ActionEvent event) {
        for (PanSC panSC : shoppingCart.getBreadsSC()) {
            String nameOfBread = panSC.getNameOfBread();
            double price = panSC.getPrice();
            int units = panSC.getSelectedUnits();

            Pan existingPan = inventarioPan.getPanByName(nameOfBread);
            if (existingPan != null) {
                existingPan.addUnits(units);
            } else {
                inventarioPan.addBread(new Pan(nameOfBread, price, units));
            }
        }

        shoppingCart.getBreadsSC().clear();
        shoppingCartBreads.getItems().clear();
        currentTotalPrice = shoppingCart.getTotalPrices();
        totalPrice.setText(String.valueOf(currentTotalPrice));
    }

    public void sellBtn( ActionEvent event ) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketShoppingCart.fxml"));
            Parent root = loader.load();
            TicketShoppingCartController ticketShoppingCartController = loader.getController();
            ticketShoppingCartController.setParentController( this );
            Stage stage = new Stage();
            stage.setUserData( shoppingCart );
            stage.setScene(new Scene( root ));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch ( IOException e ){
            e.printStackTrace();
        }

    }
    public void sellBtnAction(){
        shoppingCart.getBreadsSC().clear();
        shoppingCartBreads.getItems().clear();
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
    public void backToVenta(ActionEvent event ) throws Exception{
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
}
