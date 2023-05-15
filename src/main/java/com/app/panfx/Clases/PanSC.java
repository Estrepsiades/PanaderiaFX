package com.app.panfx.Clases;

public class PanSC {
    private String nameOfBread;
    private double price;
    private int selectedUnits;
    private int indexFromInventory;
    public PanSC( String nameOfBread, double price, int selectedUnits, int indexFromInventory){
        this.nameOfBread = nameOfBread;
        this.price = price;
        this.selectedUnits = selectedUnits;
        this.indexFromInventory = indexFromInventory;
    }
    public String getNameOfBread() {
        return nameOfBread;
    }
    public double getPrice() {
        return price;
    }
    public int getIndexFromInventory() {
        return indexFromInventory;
    }
    public int getSelectedUnits() {
        return selectedUnits;
    }
    public void addSelectedUnits( int unitsToAdd ){
        this.selectedUnits = this.selectedUnits + unitsToAdd;
    }
    public void removeSelectedUnits( int unitsToRemove ){
        this.selectedUnits = this.selectedUnits - unitsToRemove;
    }
}
