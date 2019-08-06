package cardealerPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * This class inherits the attributes from the abstract class User. It implements abstract method from the User class: readCars 
 * method which gets all the information of a car excluding the accident history.
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 * */

public class Customer extends User {
	
	Customer(String username, String password){
		super(username, password);
	}
	
	protected ArrayList<Car> readCars(String fileName) {
		/** This function takes as parameter a file name which is the name of the file where the information of 
		 * the cars is stored at and it returns an ArrayList of object Car containing the cars. This function
		 * uses BufferReader to read the file line by line where every line is a different car, it converts every
		 * line into a specific Car object depending on the type of the car and adds it into the ArrayList.
		 * The file needs to have 11 fields(car properties) separated with commas in order for this function to work.
		 * @param fileName name of the cars database
		 * @returns an ArrayList<Car> with all cars in the database  */
		BufferedReader br = null;
		ArrayList<Car> cars = new ArrayList<Car>();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = br.readLine()) != null){
				String[] parts = line.split(",",11);//creates a list of length 11 with all properties of the car
				String plate = parts[0].trim();
				String model = parts[1].trim();
				String type = parts[2].trim().toLowerCase();
				String size = parts[3].trim().toLowerCase();
				String colour = parts[4].trim();
				String mileage = parts[5].trim();
				String history = "";//to hide history from customer
				String transmission = parts[7].trim().toLowerCase();
				String price = parts[8].trim();
				String arrivalDate=parts[9].trim();
				String sellingDate = parts[10].trim();
				
				//converts size (String) into boolean data type, true is large and false is small
				boolean boolSize;
				if (size.equals("small")) boolSize = false;
				else boolSize = true;
				
				//converts mileage (String) into int data type
				int intMileage = Integer.parseInt(mileage);
				
				//converts transmission into boolean data type, true if manual and false is automatic
				boolean boolTransmission;
				if (transmission.equals("automatic")) boolTransmission = false;
				else boolTransmission = true;
				
				//converts price(String) into int data type
				int intPrice = Integer.parseInt(price);
				
				//switch statement to decide which constructor to use depending on type
				switch(type) {
					case "suv": 
						cars.add(new Suv(plate,model,colour,intMileage,history,boolTransmission,intPrice, arrivalDate,sellingDate));
						break;
					case "mpv":
						cars.add(new Mpv(plate,model,colour,intMileage,history,boolTransmission,intPrice, arrivalDate,sellingDate));
						break;
					case "van":
						cars.add(new Van(plate,model,colour,intMileage,history,boolTransmission,intPrice,arrivalDate,sellingDate,boolSize));
						break;
					case"hatchback":
						cars.add(new Hatchback(plate,model,colour,intMileage,history,boolTransmission,intPrice, arrivalDate,sellingDate));
						break;
					case "saloon":
						cars.add(new Saloon(plate,model,colour,intMileage,history,boolTransmission,intPrice, arrivalDate,sellingDate));
						break;
					case "coupe":
						cars.add(new Coupe(plate,model,colour,intMileage,history,boolTransmission,intPrice, arrivalDate,sellingDate));
						break;	
				}
			}
		} catch(FileNotFoundException e) {
		System.err.println(e.getMessage());
		} catch(IOException e) {
		System.err.println(e.getMessage());
		} finally {
			try {
				if(br != null) br.close();
				
			} catch(IOException e) {
				System.err.println(e.getMessage());
			  }
		}
		return cars;
	}
}


