package demo;

import ecomm.*;
import ecomm.Globals.Category;

public class Book extends Product {
    private String name;
    private int quantity;
    private float price;
    private String ProductID;

    public Book(String ProductID, String name, float price, int quantity) 
    {
        this.ProductID = ProductID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Globals.Category getCategory() {
        return Category.Book;
    }

    public String getName() {
        return name;
    }

    public String getProductID() {
        return ProductID;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQty(int qty){
        this.quantity -= qty;
    }
}