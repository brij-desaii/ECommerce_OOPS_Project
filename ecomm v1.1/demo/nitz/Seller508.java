package demo.nitz;

import demo.*;
import ecomm.*;
import java.util.*;

public class Seller508 extends Seller {
    private String myID;
    private ArrayList<Product> booksList;
    private ArrayList<Product> mobilesList;

    public Seller508(String id) {
        super(id);
        
        this.booksList = new ArrayList<Product>();
        this.mobilesList = new ArrayList<Product>();
        
        Book book1 = new Book("508-Book1", "CLRS", (float)Math.random()*100, 5);
        Book book2 = new Book("508-Book2", "SNS", Math.random()*100f, 8);
        Book book3 = new Book("508-Book3", "Oppenheim", Math.random()*100f, 6);
        Book book4 = new Book("508-Book4", "Pride and Prejudice", Math.random()*100f, 11);
        Mobile mobile1 = new Mobile("508-Mobile1", "Redmi", Math.random()*100f, 4);
        Mobile mobile2 = new Mobile("508-Mobile2", "Vivo", Math.random()*100f, 2);
        Mobile mobile3 = new Mobile("508-Mobile3", "RealMe", Math.random()*100f, 15);

        booksList.add(book1);
        booksList.add(book2);
        booksList.add(book3);
        booksList.add(book4);
        mobilesList.add(mobile1);
        mobilesList.add(mobile2);
        mobilesList.add(mobile3);
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) 
    {
        if(whichOne.toString().equals("Book")){
            return booksList;
        }  
        else{
            return mobilesList;
        }
    }

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getProductID() == productID && booksList.get(i).getQuantity() >= quantity) 
            {
                //booksList.get(i).decreaseQty(quantity);
                return true;
            }
        }
        for (int i = 0; i < mobilesList.size(); i++) {
            if (mobilesList.get(i).getProductID() == productID && mobilesList.get(i).getQuantity() >= quantity) 
            {
                //mobilesList.get(i).decreaseQty(quantity);
                return true;
            }
        }
        return false;
    }
}