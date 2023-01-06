package demo.brij;//induvisual sub-package per seller


import demo.*;
import ecomm.*;
import java.util.*;

public class Seller67 extends Seller {
    private String myID;
    private ArrayList<myProduct> booksList; //list of categories which will contain different unique products
    private ArrayList<myProduct> mobilesList;

    public Seller67(String id) {
        super(id);
        
        this.booksList = new ArrayList<myProduct>();
        this.mobilesList = new ArrayList<myProduct>();
        //hardcoding products for each seller
        Book book1 = new Book("67-Book1", "Book1", (float)Math.random()*100, 5);    //random generator for price
        Book book2 = new Book("67-Book2", "Book2", (float)Math.random()*100, 8);
        Mobile mobile1 = new Mobile("67-Mobile1", "Mobile1", (float)Math.random()*100, 9);
        Mobile mobile2 = new Mobile("67-Mobile2", "Mobile2", (float)Math.random()*100, 3);
        Mobile mobile3 = new Mobile("67-Mobile3", "Mobile3", (float)Math.random()*100, 4);
        Mobile mobile4 = new Mobile("67-Mobile4", "Mobile4", (float)Math.random()*100, 2);

        booksList.add(book1);   //adding products to list
        booksList.add(book2);
        mobilesList.add(mobile1);
        mobilesList.add(mobile2);
        mobilesList.add(mobile3);
        mobilesList.add(mobile4);
    }

    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);    //adding platform to seller
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) 
    {
        ArrayList<Product> temp = new ArrayList<Product>(); //dummy array to return fixed type
        if(whichOne.toString().equals("Book")){
            temp.addAll(booksList); //adding all products to dummy array type and returning
            return temp;
        }
        else{
            temp.addAll(mobilesList);
            return temp;
        }
    }

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getProductID().equals(productID) && booksList.get(i).getQuantity() >= quantity) //linear search to check if product exists with valid quanitity to buy
            {
                booksList.get(i).decreaseQty(quantity); //decrease quantity after valid purchase
                return true;
            }
        }
        for (int i = 0; i < mobilesList.size(); i++) {
            if (mobilesList.get(i).getProductID().equals(productID) && mobilesList.get(i).getQuantity() >= quantity) 
            {
                mobilesList.get(i).decreaseQty(quantity);   //decrease quantity after valid purchase
                return true;
            }
        }
        return false;    //if not found, invalid purchase
    }
}