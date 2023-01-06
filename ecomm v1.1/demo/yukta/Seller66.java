package demo.yukta; //induvisual sub-package per seller

import demo.*;
import ecomm.*;
import java.util.*;

public class Seller66 extends Seller {
    private String myID;
    private ArrayList<myProduct> myBooks;   //list of categories which will contain different unique products
    private ArrayList<myProduct> myMobiles;

    public Seller66(String id) {
        super(id);
        this.myBooks = new ArrayList<myProduct>();
        this.myMobiles = new ArrayList<myProduct>();

        //hardcoding products for each seller
        Book book1 = new Book("66-Book1", "Divergent", 999.99f, 5); 
        Book book2 = new Book("66-Book2", "Harry_Potter", 100.99f, 5);
        Book book3 = new Book("66-Book3", "Percy_Jackson", 60.99f, 5);
        myBooks.add(book1); //adding to list
        myBooks.add(book2);
        myBooks.add(book3);
        Mobile mob1 = new Mobile("66-Mobile1", "iPhone", 99999.99f, 35);
        Mobile mob2 = new Mobile("66-Mobile2", "Samsung", 799.99f, 300);
        Mobile mob3 = new Mobile("66-Mobile3", "Oppo", 1000.77f, 90);
        myMobiles.add(mob1);    //adding to list
        myMobiles.add(mob2);
        myMobiles.add(mob3);
    }

    public void addPlatform(Platform thePlatform) { //adding platform to seller
        thePlatform.addSeller(this);
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) //returns list of user-asked category list
    {
        ArrayList<Product> temp = new ArrayList<Product>(); //dummy array to return fixed type
        if(whichOne.toString().equals("Book")){
            temp.addAll(myBooks);   //adding all products to dummy array type and returning
            return temp;
        }
        else{
            temp.addAll(myMobiles);
            return temp;
        }
    }

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < myBooks.size(); i++) {
            if (myBooks.get(i).getProductID().equals(productID) && myBooks.get(i).getQuantity() >= quantity) 
            {
                myBooks.get(i).decreaseQty(quantity);   //linear search to search if product exists and enough quantity is there to purchase
                return true;                            //decrease quantity after purchase
            }
        }
        for (int i = 0; i < myMobiles.size(); i++) {
            if (myMobiles.get(i).getProductID().equals(productID) && myMobiles.get(i).getQuantity() >= quantity) 
            {
                myMobiles.get(i).decreaseQty(quantity); //linear search for valid purchase and decrease amt after purchase
                return true;
            }
        }
        return false;    //if not found, invalid purchase
    }
}