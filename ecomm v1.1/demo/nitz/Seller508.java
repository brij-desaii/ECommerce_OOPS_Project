package demo.nitz;//induvisual sub-package per seller


import demo.*;
import ecomm.*;
import java.util.*;

public class Seller508 extends Seller {
    private String myID;
    private ArrayList<myProduct> ListOfBooks;   //list of categories which will contain different unique products
    private ArrayList<myProduct> ListOfMobiles;

    public Seller508(String id) {
        super(id);
        
        this.ListOfBooks = new ArrayList<myProduct>();
        this.ListOfMobiles = new ArrayList<myProduct>();
        //hardcoding products for each seller
        Book book1 = new Book("508-Book1", "CLRS", (float)Math.random()*100, 5);    //random generator for price
        Book book2 = new Book("508-Book2", "SNS", (float)Math.random()*100f, 8);
        Book book3 = new Book("508-Book3", "Oppenheim", (float)Math.random()*100f, 6);
        Book book4 = new Book("508-Book4", "Pride and Prejudice", (float)Math.random()*100f, 11);
        Mobile mobile1 = new Mobile("508-Mobile1", "Redmi", (float)Math.random()*100f, 4);
        Mobile mobile2 = new Mobile("508-Mobile2", "Vivo", (float)Math.random()*100f, 2);
        Mobile mobile3 = new Mobile("508-Mobile3", "RealMe", (float)Math.random()*100f, 15);

        ListOfBooks.add(book1); //adding products to list
        ListOfBooks.add(book2);
        ListOfBooks.add(book3);
        ListOfBooks.add(book4);
        ListOfMobiles.add(mobile1);
        ListOfMobiles.add(mobile2);
        ListOfMobiles.add(mobile3);
    }

    public void addPlatform(Platform thePlatform) {//adding platform to seller
        thePlatform.addSeller(this);
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) 
    {
        ArrayList<Product> tmp = new ArrayList<Product>(); //dummy array to return fixed type
        if(whichOne.toString().equals("Book"))
        {   
            tmp.addAll(ListOfBooks);    //adding all products to dummy array type and returning
            return tmp;
        }  
        else
        {
            tmp.addAll(ListOfMobiles);  //adding all products to dummy array type and returning
            return tmp;
        }
    }

    public boolean buyProduct(String productID, int quantity) {
        for (int i = 0; i < ListOfBooks.size(); i++) {
            if (ListOfBooks.get(i).getProductID().equals(productID) && ListOfBooks.get(i).getQuantity() >= quantity)//linear search to check if product exists with valid quanitity to buy
            {
                ListOfBooks.get(i).decreaseQty(quantity);   //decreasing quantity after valid purchase
                return true;
            }
        }
        for (int i = 0; i < ListOfMobiles.size(); i++) {
            if (ListOfMobiles.get(i).getProductID().equals(productID) && ListOfMobiles.get(i).getQuantity() >= quantity) 
            {
                ListOfMobiles.get(i).decreaseQty(quantity); //decreasing quantity after valid purchase
                return true;
            }
        }
        return false;   //if not found, invalid purchase
    }
}