package com.app.panfx;

import com.app.panfx.Clases.InventarioPan;
import com.app.panfx.Clases.Pan;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryMenuControllers implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private InventarioPan inventarioPan;
    private Pan currentPan;
    @FXML
    private ListView<Pan> inventoryBreads;
    @FXML
    private Label nameOfBread;
    @FXML
    private Label priceOfBread;
    @FXML
    private Label unitsOfBread;
    String currentNameOfBread;
    double currentPriceOfBread;
    int currentUnitsOfBread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->{
            stage = (Stage) inventoryBreads.getScene().getWindow();
            inventarioPan = (InventarioPan) stage.getUserData();
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
                    if (newValue != null){
                        currentPan = newValue;
                        currentNameOfBread = newValue.getNameOfBread();
                        currentPriceOfBread = newValue.getPrice();
                        currentUnitsOfBread = newValue.getUnits();
                        nameOfBread.setText( currentNameOfBread );
                        priceOfBread.setText(String.valueOf(currentPriceOfBread));
                        unitsOfBread.setText(String.valueOf(currentUnitsOfBread));
                    }
                }
            });
        });
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
    public void changePriceBread(ActionEvent event ){
        //System.out.println( "Test"+currentPan.getNameOfBread() );
    }
    public void testButton( ActionEvent event ){
    }
}
