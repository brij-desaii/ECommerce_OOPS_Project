package demo;

import ecomm.*;
import ecomm.Globals.Category;

public abstract class myProduct extends Product{

	// common queries to get category, unique name, price, and number available
	public abstract Globals.Category getCategory();
	public abstract String getName();
	public abstract String getProductID();
	public abstract float getPrice();
	public abstract int getQuantity();
	public abstract void decreaseQty(int qty);	//introduced a method to decrease quantity after purchase
}


