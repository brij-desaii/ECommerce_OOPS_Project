package demo.brij;

import demo.*;
import ecomm.*;
import java.util.*;

public class Seller67 extends Seller {
    private String myID;
    private ArrayList<Product> booksList;
    private ArrayList<Product> mobilesList;

    public Seller67(String id) {
        super(id);
        
        this.booksList = new ArrayList<Product>();
        this.mobilesList = new ArrayList<Product>();
        
        Book book1 = new Book("67-Book1", "Book1", (float)Math.random()*100, 5);
        Book book2 = new Book("67-Book2", "Book2", (float)Math.random()*100, 8);
        Mobile mobile1 = new Mobile("67-Mobile1", "Mobile1", (float)Math.random()*100, 9);
        Mobile mobile2 = new Mobile("67-Mobile2", "Mobile2", (float)Math.random()*100, 3);
        Mobile mobile3 = new Mobile("67-Mobile3", "Mobile3", (float)Math.random()*100, 4);
        Mobile mobile4 = new Mobile("67-Mobile4", "Mobile4", (float)Math.random()*100, 2);

        booksList.add(book1);
        booksList.add(book2);
        mobilesList.add(mobile1);
        mobilesList.add(mobile2);
        mobilesList.add(mobile3);
        mobilesList.add(mobile4);
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