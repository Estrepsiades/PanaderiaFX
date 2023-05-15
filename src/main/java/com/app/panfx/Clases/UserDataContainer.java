package com.app.panfx.Clases;

public class UserDataContainer {
    private  InventarioPan inventarioPan;
    private ShoppingCart shoppingCart;
    public UserDataContainer( InventarioPan inventarioPan, ShoppingCart shoppingCart ){
        this.inventarioPan = inventarioPan;
        this.shoppingCart = shoppingCart;
    }

    public InventarioPan getInventarioPan() {
        return inventarioPan;
    }

    public void setInventarioPan(InventarioPan inventarioPan) {
        this.inventarioPan = inventarioPan;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
