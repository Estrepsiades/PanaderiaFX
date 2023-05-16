package com.app.panfx.Clases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
public class InventarioPan {
    private ObservableList<Pan> breads;
    public InventarioPan(){
        this.breads = FXCollections.observableArrayList();
    }
    public ObservableList<Pan> getBreads() {
        return breads;
    }
    public void addBread( Pan pan ){
        this.breads.add( pan );
    }
    public Pan getPanByName(String breadToSearch) {
        for (Pan pan : getBreads()) {
            if (breadToSearch.equals(pan.getNameOfBread())) {
                return pan;
            }
        }
        return null;
    }
}