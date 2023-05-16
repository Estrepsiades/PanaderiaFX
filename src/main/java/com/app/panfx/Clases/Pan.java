package com.app.panfx.Clases;
public class Pan {
    private String nameOfBread;
    private double price;
    private int units;
    public Pan( String nameOfBread, double price, int units ){
        this.nameOfBread = nameOfBread;
        this.price = price;
        this.units = units;
    }
    public String getNameOfBread() {
        return nameOfBread;
    }
    public double getPrice() {
        return price;
    }
    public int getUnits() {
        return units;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    public void deleteUnits( int unitsToDelete ){
        this.units = this.units - unitsToDelete;
    }
    public void addUnits( int unitsToAdd ){
        this.units = this.units + unitsToAdd;
    }
}