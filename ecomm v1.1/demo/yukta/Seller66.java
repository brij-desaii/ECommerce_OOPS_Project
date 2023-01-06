package demo.yukta;

import demo.*;
import ecomm.*;
import java.util.*;

public class Seller66 extends Seller {
    private String myID;
    private ArrayList<Product> myBooks;
    private ArrayList<Product> myMobiles;

    public Seller66(String id) {
        super(id);
        this.myBooks = new ArrayList<Product>();
        this.myMobiles = new ArrayList<Product>();
        Book book1 = new Book("66-Book1", "Divergent", 999.99f, 5);
        Book book2 = new Book("66-Book2", "Harry_Potter", 100.99f, 5);
        Book book3 = new Book("66-Book3", "Percy_Jackson", 60.99f, 5);
        myBooks.add(book1);
        myBooks.add(book2);
        myBooks.add(book3);
        Mobile mob1 = new Mobile("66-Mobile1", "iPhone", 99999.99f, 35);
        Mobile mob2 = new Mobile("66-Mobile2", "Samsung", 799.99f, 300);
        Mobile mob3 = new Mobile("66-Mobile3", "Oppo", 1000.77f, 90);
        myMobiles.add(mob1);
        myMobiles.add(mob2);
        myMobiles.add(mob3);
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) 
    {
        if(whichOne.toString().equals("Book"))
            return myBooks;
        else
            return myMobiles;
    }

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < myBooks.size(); i++) {
            if (myBooks.get(i).getProductID() == productID && myBooks.get(i).getQuantity() >= quantity) 
            {
                //myBooks.get(i).decreaseQty(quantity);
                return true;
            }
        }
        for (int i = 0; i < myMobiles.size(); i++) {
            if (myMobiles.get(i).getProductID() == productID && myMobiles.get(i).getQuantity() >= quantity) 
            {
                //myMobiles.get(i).decreaseQty(quantity);
                return true;
            }
        }
        return false;
    }
}