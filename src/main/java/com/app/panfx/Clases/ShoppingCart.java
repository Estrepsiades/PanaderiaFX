package com.app.panfx.Clases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ShoppingCart {
    private ObservableList<PanSC> breadsSC;
    public ShoppingCart(){
        this.breadsSC = FXCollections.observableArrayList();
    }
    public ObservableList<PanSC> getBreadsSC() {
        return breadsSC;
    }
    public void addBread( PanSC panSC ){
        this.getBreadsSC().add( panSC );
    }
    public double getTotalPrices(){
        double total = 0.00;
        for (PanSC panSC : getBreadsSC() ){
            total += panSC.getPrice() * panSC.getSelectedUnits();
        }
        return total;
    }
}
