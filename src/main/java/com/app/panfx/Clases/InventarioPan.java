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
    public void deleteBread( Pan pan ){
        System.out.println("Tiene que borrar algo");
    }
    public ListView<Pan> createListBreadView(){
        ListView<Pan> listBreadView = new ListView<>();
        listBreadView.setItems( breads );
        return listBreadView;
    }
    public void shownBreads(){
        int index = 0;
        String msg;
        for ( Pan pan : breads ){
                msg = String.format("*****\n%d.Pan Dulce \nNombre: %s\nPrecio: %.2f \n*****", index+1, pan.getNameOfBread(), pan.getPrice() );
                System.out.println( msg );
                index++;
        }
    }
}
