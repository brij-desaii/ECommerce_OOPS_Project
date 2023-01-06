package demo;

import java.io.*;
import ecomm.*;
import java.util.*;

public class DemoPlatform extends Platform {

	//ArrayList that keeps track of all the sellers on the platform
	private ArrayList<Seller> sellerList;

	//Constructor
	public DemoPlatform(){
		this.sellerList = new ArrayList<Seller>();
	}

	//method that adds a seller to the sellerList
	@Override
	public boolean addSeller(Seller aSeller) {
		this.sellerList.add(aSeller);
		return false;
	}

	@Override
	public void processRequests() {
		try
		{
			File inputFile = new File(Globals.toPlatform);
			File outputFile = new File(Globals.fromPlatform);
			Scanner reader = new Scanner(inputFile);
		
			//2-D ArrayList that will store the contents of PortalToPlatform.txt
			ArrayList<ArrayList<String>> dataLines = new ArrayList<ArrayList<String>>();

			//Reading PortalToPlatform.txt and adding its contents to dataLines
			while (reader.hasNextLine())
			{
				String data = reader.nextLine();
				String[] tmp = data.split(" ");
				ArrayList<String> dataArr = new ArrayList<String>(Arrays.asList(tmp));
				dataLines.add(dataArr);
			}
			reader.close();

			//clear the file after reading from it
			try 
			{
				FileWriter myWriter = new FileWriter(inputFile, false);	
				myWriter.close();
			} 
			catch (IOException e) 
			{
				System.out.println("An error occurred.");
			}
			
			//Processing the input
			for(int i = 0; i < dataLines.size(); i++)
			{
				String portalID, requestID;
				portalID = dataLines.get(i).get(0);
				requestID = dataLines.get(i).get(1);

				//If request is "Start", write the categories to PlatformToPortal.txt
				if(dataLines.get(i).get(2).equals("Start"))
				{
					try 
					{
						FileWriter myWriter = new FileWriter(outputFile, true);	
						myWriter.write(portalID + " " + requestID + " " + Globals.Category.Book.toString() + " " + Globals.Category.Mobile.toString() + "\n");
						myWriter.close();
					} 
					catch (IOException e) 
					{
						System.out.println("An error occurred.");
					}
				}

				
				if(dataLines.get(i).get(2).equals("List"))
				{
					String category = dataLines.get(i).get(3);
					try 
					{
						// Check for the category first, then iterate through the sellersList and write the products of that category to the output file
						FileWriter myWriter = new FileWriter(outputFile, true);
						if(category.equals("Book"))
						{
							for(int s = 0; s < sellerList.size(); s++)
							{
								ArrayList<Product> DatafromSellers;
								DatafromSellers = sellerList.get(s).findProducts(Globals.Category.Book);	//get list from each seller

								for(int prod = 0; prod < DatafromSellers.size(); prod++)	//write list to file for each seller
								{
									myWriter.write(portalID + " " + requestID + " " + DatafromSellers.get(prod).getName() + " " + DatafromSellers.get(prod).getProductID() + " " + Float.toString(DatafromSellers.get(prod).getPrice()) + " "+ Integer.toString(DatafromSellers.get(prod).getQuantity())  + "\n");
								}
							} 
						}
						if(category.equals("Mobile"))
						{

							for(int s = 0; s < sellerList.size(); s++)
							{
								ArrayList<Product> DatafromSellers;
								DatafromSellers = sellerList.get(s).findProducts(Globals.Category.Mobile);

								for(int prod = 0; prod < DatafromSellers.size(); prod++)
								{
									myWriter.write(portalID + " " + requestID + " " + DatafromSellers.get(prod).getName() + " " + DatafromSellers.get(prod).getProductID() + " " + Float.toString(DatafromSellers.get(prod).getPrice()) + " "+ Integer.toString(DatafromSellers.get(prod).getQuantity())  + "\n");
								}
							}
								
						}
						myWriter.close();
					} 
					catch(IOException e) 
					{
						System.out.println("An error occurred.");
					}
				}
				if(dataLines.get(i).get(2).equals("Buy"))
				{
					try{
					FileWriter myWriter = new FileWriter(outputFile, true);

					String UniqueProductName = dataLines.get(i).get(3);
					Integer quantity = Integer.parseInt(dataLines.get(i).get(4));
					boolean valid = false;
					
					//Iterate through all the sellers, call buy product, and return success or failure
					for(int s = 0; s < sellerList.size(); s++)
					{
						valid = sellerList.get(s).buyProduct(UniqueProductName, quantity);
						if(valid)
						{
							myWriter.write(portalID + " " + requestID + " " + "Success" + "\n");
							break;
						}
					}
					if(!valid){
						myWriter.write(portalID + " " + requestID + " " + "Failure" + "\n");
					}
					myWriter.close();
					}
					catch(IOException ex)
					{
						System.out.println("IOException occured");
					}
				}
			}
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("FileNotFoundException occured");
		}
	}
}
