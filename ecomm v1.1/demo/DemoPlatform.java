package demo;

import java.io.*;
import ecomm.*;
import java.util.*;

public class DemoPlatform extends Platform {

	private ArrayList<Seller> sellerList;

	public DemoPlatform(){
		this.sellerList = new ArrayList<Seller>();
	}

	@Override
	public boolean addSeller(Seller aSeller) {
		// TODO Auto-generated method stub
		this.sellerList.add(aSeller);
		return false;
	}

	@Override
	public void processRequests() {
		// TODO Auto-generated method stub
		try
		{
		File obj = new File(Globals.toPlatform);
		File outputFile = new File(Globals.fromPlatform);
		Scanner reader = new Scanner(obj);
	

		ArrayList<ArrayList<String>> dataLines = new ArrayList<ArrayList<String>>();

		while (reader.hasNextLine())
		{
			String data = reader.nextLine();

			String[] tmp = data.split(" ");

			ArrayList<String> dataArr = new ArrayList<String>(Arrays.asList(tmp));
			dataLines.add(dataArr);
		}
		//clear the file after reading
		
		//System.out.println(dataLines);
		
		reader.close();
		try 
		{
			FileWriter myWriter = new FileWriter(obj, false);	//switch file names w dumb global
			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		} 
		catch (IOException e) 
		{
			System.out.println("An error occurred.");
		}
		//new FileWriter(outputFile, false).close();

		for(int i = 0; i < dataLines.size(); i++)
		{
			String portalID, requestID;
			portalID = dataLines.get(i).get(0);
			requestID = dataLines.get(i).get(1);

			if(dataLines.get(i).get(2).equals("Start"))
			{
				try 
				{
					FileWriter myWriter = new FileWriter(outputFile, true);	//switch file names w dumb global
					myWriter.write(portalID + " " + requestID + " " + Globals.Category.Book.toString() + " " + Globals.Category.Mobile.toString() + "\n");
					myWriter.close();
					System.out.println("Successfully wrote to the file.");
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
					System.out.println("Successfully wrote to the file.");
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
				boolean valid;
				
				for(int s = 0; s < sellerList.size(); s++)
				{
					valid = sellerList.get(s).buyProduct(UniqueProductName, quantity);
					if(valid == true)
					{
						myWriter.write(portalID + " " + requestID + " " + "Success" + "\n");
						return;
					}
				}
				myWriter.write(portalID + " " + requestID + " " + "Failure" + "\n");
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
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
