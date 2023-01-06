import java.util.*;
import ecomm.*;
import demo.*;
import demo.yukta.*;
import demo.brij.*;
import java.io.*;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform();  // replace with appropriate derived class
		
		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.
		
		// replace each of the XYZ below with the derived class name of one of the
		// team members.
		
		
		Seller s1 = new Seller66("yuks"); 
		s1.addPlatform(pf);

		
		Seller s2 = new Seller67("brij");
		s2.addPlatform(pf);
		
		/*
		Seller s3 = new SellerXYZ2("Seller3");
		s1.addPlatform(pf);
 		*/
		
		/*
		// keep reading from System.in
		// If "Check" typed in
		// invoke 
			pf.processRequests();
		*/
		Scanner scanner = new Scanner(System.in);
		String input;
		while (true)
		{
			input = scanner.nextLine();
			if (input.equals("Check"))
			{
				pf.processRequests();
			}
		}	
	}

}
