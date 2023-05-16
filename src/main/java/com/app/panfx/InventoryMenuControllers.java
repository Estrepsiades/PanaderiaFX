package com.app.panfx;
import com.app.panfx.Clases.InventarioPan;
import com.app.panfx.Clases.Pan;
import com.app.panfx.Clases.UserDataContainer;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class InventoryMenuControllers implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserDataContainer userDataContainer;
    private InventarioPan inventarioPan;
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
    int selectedIndex = -1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() ->{
            stage = (Stage) inventoryBreads.getScene().getWindow();
            userDataContainer = (UserDataContainer) stage.getUserData();
            inventarioPan = userDataContainer.getInventarioPan();
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
    public void changePriceBread(ActionEvent event ) throws IOException {
        if ( selectedIndex != -1 ){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPriceInventory.fxml"));
            Parent root = loader.load();
            ModifyPriceInventoryController modifyPriceInventoryController = loader.getController();
            modifyPriceInventoryController.setParentController( this );
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
    }
    public void changePriceBreadAction( double newPrice ){
        inventarioPan.getBreads().get( selectedIndex ).setPrice( newPrice );
        ObservableList<Pan> breads = FXCollections.observableArrayList();
        breads.addAll( inventarioPan.getBreads() );
        inventoryBreads.setItems( breads );
    }
    public void addUnits(ActionEvent event) throws IOException {
        if ( selectedIndex != -1 ){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addUnitsInventory.fxml"));
            Parent root = loader.load();
            AddUnitsInventoryController addUnitsInventoryController = loader.getController();
            addUnitsInventoryController.setParentController( this );
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
    }
    public void addUnitsAction( int unitsToAdd ){
        inventarioPan.getBreads().get( selectedIndex ).setUnits( inventarioPan.getBreads().get( selectedIndex ).getUnits() + unitsToAdd );
        ObservableList<Pan> breads = FXCollections.observableArrayList();
        breads.addAll( inventarioPan.getBreads() );
        inventoryBreads.setItems( breads );
    }
    public void addBread(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addBreadInventory.fxml"));
        Parent root = loader.load();
        AddBreadInventoryController addBreadInventoryController = loader.getController();
        addBreadInventoryController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void addBreadAction( String nameOfBread, double price, int units ){
        inventarioPan.addBread(new Pan(nameOfBread, price, units ));
        ObservableList<Pan> breads = FXCollections.observableArrayList();
        breads.addAll( inventarioPan.getBreads() );
        inventoryBreads.setItems( breads );
    }
    public void deleteBread(ActionEvent event ){
        selectedIndex = inventoryBreads.getSelectionModel().getSelectedIndex();
        if ( selectedIndex != -1 ){
            inventarioPan.getBreads().remove( selectedIndex );
            inventoryBreads.getItems().remove( selectedIndex );
        }
    }
}