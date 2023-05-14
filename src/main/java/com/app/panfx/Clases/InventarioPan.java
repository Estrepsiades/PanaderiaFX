package com.app.panfx.Clases;

import java.util.ArrayList;

public class InventarioPan {
    private ArrayList<Pan> breads;
    public InventarioPan(){
        this.breads = new ArrayList<Pan>();
    }

    public ArrayList<Pan> getBreads() {
        return breads;
    }
    public void addBread( Pan pan ){
        this.breads.add( pan );
    }
}
