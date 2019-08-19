package cardealerPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**This class inherits all methods and attributes from the abstract class User. It implements abstract methods from the User class: readCars 
 * method which gets all the information of a car including the accident history. 
 * This class also implements the following methods that will be inherited by the Admin Class: carToString,
 * addCar, addCars, sellCar, printCars, and the overloaded method calculateRevenue which allows the staff to 
 * calculate the revenue by day or month.
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 *    */

public class Staff extends User {
	
	
	Staff(String username, String password){
		/**
		 * This constructor uses the constructor from the User class to set the username and password and 
		 * it sets the role attribute also from the User class to "staff" */
		super(username, password);
	}
	
	
	protected ArrayList<Car> readCars(String fileName) {
		/** This function takes as parameter a file name which is the name of the file where the information of 
		 * the cars is stored at and it returns an ArrayList of object Car containing the cars. This function
		 * uses BufferReader to read the file line by line where every line is a different car, it converts every
		 * line into a specific Car object depending on the type of the car and adds it into the ArrayList.
		 * The file needs to have 11 fields(car properties) separated with commas in order for this function to work  */
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
				String history = parts[6].trim();
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
	
	String carToString(Car myCar) {
		/**
		 * This methods gets as parameter a car of type Car and return the car into a string representation.
		 * @param myCar : car of type Car 
		 * @return String with all fields of the car*/
		String car;
		if (myCar instanceof Van) {//in order to add size
			car = myCar.getPlate() + ","+ myCar.getModel()+","+myCar.getType()+","+ ((Van)myCar).getSize()+","+myCar.getColour()+","+myCar.getMileage()+","+myCar.getHistory()+","+myCar.getTransmission()+","+myCar.getPrice()+","+myCar.getArrivalDate()+","+myCar.getSellingDate();
		}else { //no size
			car = myCar.getPlate() + ","+ myCar.getModel()+","+myCar.getType()+",,"+myCar.getColour()+","+myCar.getMileage()+","+myCar.getHistory()+","+myCar.getTransmission()+","+myCar.getPrice()+","+myCar.getArrivalDate()+","+myCar.getSellingDate();			
		}
		return car;
	}
	 
	protected boolean addCar(String fileName,String plate, String model, String type,String size, String colour, int mileage, String accidentHistory, String transmission, int price, String arrivalDate, String sellingDate ){
		/**
		 * This method is passed a file name, plate, model, type, size , colour, mileage, accident history, price, arrival date and selling date of a car.
		 * It reads that already contains other cars if the plate entered is different than all the plates of the cars that are in the file then this 
		 * method writes  new line into the file with all the entered fields of the car. It does not add anything to the file if the plate already exists.
		 * If the arrival date entered is blank or not a valid date it creates the current day. If the selling date is not in correct format is leaves it blank.
		 * @param fileName : database where the car is going to be added to.*/
		
		boolean error = false; //true if exists and false if it doesn't
		ArrayList<Car> cars = readCars(fileName);
		
		//if arrivalDate is empty or it is not in a correct format it sets arrivalDate to the current date
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	formatter.setLenient(false);
    	try {
    		formatter.parse(arrivalDate.trim());
    	} catch (ParseException pe) {
    		Date currentDate = new Date();
			arrivalDate = formatter.format(currentDate);
    	}
    	
    	//if sellingDate is not in correct format it sets selling date to the current date
    	if (sellingDate.trim().equals("")) {
    		sellingDate ="";
    	}else {
    		try {
        		formatter.parse(sellingDate.trim());
    		} catch (ParseException e) {
				Date currentDate = new Date();
				sellingDate = formatter.format(currentDate);
    		}
    	}
    	
    	//checking that car that wants to be added is not already in the file
    	for (Car car : cars) {
    		String inputPlate = plate.toLowerCase();
    		String currentPlate = car.getPlate().toLowerCase();
    		if (currentPlate.equals(inputPlate)) {
    			error = true;
    		}
    	}
    	
    	//checking that all fields have been entered (except selling date and arrival date)
    	if (colour.length()==0 || model.length() ==0 || plate.length()==0 || accidentHistory.length() ==0 || fileName.length() ==0) {
    		error = false;
	    }
    		
    	if (error == false) {//all fields have bee entered and the car does not exist already
			BufferedWriter bw = null;
		    try {
		    	bw = new BufferedWriter(new FileWriter(fileName,true));
	    		bw.write(plate+","+model+","+type+","+size+","+colour+","+mileage+","+accidentHistory+","+transmission+","+price+","+arrivalDate+","+sellingDate);
		        bw.newLine();
		    	
		    } catch(FileNotFoundException e) {
		    	error = true;
		        System.err.println(e.getMessage());
		    } catch(IOException e) {
		    	error = true;
		        System.err.println(e.getMessage());
		    } finally {
		        try {
		            if(bw != null) {
		                bw.close();    
		            }
		        } catch(IOException e) {
		        	error = true;
		            System.err.println(e.getMessage());
		        }
		    }
    	}
    	return error;
	}
	
	protected boolean addCars(String importFileName, String databaseFileName) {
		/**
		 * This method gets as input the name of a file with the cars to import and the name of a file
		 * of the car database. This method converts the import file into Car objects and writes cars from the import file to the database file if and only if 
		 * the cars from the import file are not already in the database file.
		 * @param importFileName name of file with cars to import
		 * @param databaseFileName name of file of database, cars will be written into this file */
		boolean error = false;
		ArrayList<Car> importCars = readCars(importFileName);
		ArrayList<Car> databaseCars = readCars(databaseFileName);
		
		//if no cars to import or database used is empty
		if (importCars.size() == 0) error = true;
		if (databaseCars.size() ==0) error = true;
		
		if (error == false) { //no error
			BufferedWriter bw = null;
		    try {
		    	bw = new BufferedWriter(new FileWriter(databaseFileName,true));
		    	
		    	//checking if cars are not in database already (using boolean carExistance)
		    	for (Car importCar : importCars) {
		    		boolean carExistance = false;
		    		String importCarPlate = importCar.getPlate();
		    		for (Car databaseCar : databaseCars) {
		    			String databaseCarPlate = databaseCar.getPlate();
		    			if (databaseCarPlate.equals(importCarPlate)) carExistance = true;
		    		}
		    		
		    		//Writing into file only if given car is not in database already
		    		if (carExistance == false) {
		    			bw.write(carToString(importCar));
						bw.newLine();
		    		}
				}
		    } catch(FileNotFoundException e) {
		    	error = true;
		        System.err.println(e.getMessage());
		    } catch(IOException e) {
		    	error = true;
		        System.err.println(e.getMessage());
		    } finally {
		        try {
		            if(bw != null) {
		                bw.close();
		        }
		        } catch(IOException e) {
		        	error = true;
		            System.err.println(e.getMessage());
		        }
		    }
		}
	    return error;
	}
	
	protected boolean sellCar(String databaseFileName, String plate) {
		/**
		 * This method gets as parameter the name of a file and the plate of a car. The method checks if the car has not
		 * been sold already and if that is the case it updates the status of the car to sold (false) and the selling date to the 
		 * current day.
		 * @param databaseFileName : the name of the database where the car that is going to be sold is at.
		 * @param plate : the plate of the car that the user wants to sell
		 * @return Returns true is there was an error and the car could not be sold and false if there was no error and the car was sold. */
		
		boolean error = false;
		
		//checking for errors (if the plate entered is empty (""))
		if (plate.length()==0 || databaseFileName.length()==0) {
			error = true; 
		}
		
		ArrayList<Car> cars = readCars(databaseFileName);
		plate = plate.toLowerCase();
		
		if (error == false) { //if no error with input then change the car selling date in file
			BufferedWriter bw = null;
		    try {
		    	bw = new BufferedWriter(new FileWriter(databaseFileName));
		    	error=true;
		    	for (Car car : cars) {
		    		String currentPlate = car.getPlate().toLowerCase();
		    		//checking that car is in file and it has not been sold already
					if (currentPlate.equals(plate) && car.getStatus() == false) {
						car.setSellingDate();//to current day
						car.setStatus(false);
						error = false;
						
					}
					String strCar = carToString(car);
					bw.write(strCar);
					bw.newLine();
				}
					  
		    } catch(FileNotFoundException e) {
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    } catch(IOException e) {
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    } finally {
		        try {
		            if(bw != null) {
		                bw.close();
		            }
		        } catch(IOException e) {
		            System.err.println(e.getMessage());
		            e.printStackTrace();
		        }
		    }	
		}
		return error;
	}
	
	protected boolean printCars(String databaseFileName, String outputFileName) {
		/**
		 * This method gets as parameter the name of the database file and the name of the file where the cars are going to 
		 * be printed to. This method gets the cars of the database and first sorts them by their status and then by 
		 * their arrival date or selling date depending if they have been sold already or not. After sorting the cars,
		 * the method prints the sorted cars into the output file.
		 * @param databaseFileName : text file with the car information
		 * @param outputFileName : text file where sorted cars are printed to
		 * @return true if there was an error and cars were not printed, false if there were no errors */
		boolean error =false;
		
		//checking error (if file names are empty (""))
		if (databaseFileName.length()==0 || outputFileName.length()==0) {
			error = true;
		}
		
		ArrayList<Car> cars = readCars(databaseFileName);
    	if (cars.size()==0) error = true;//nothing will be written into file
    	
		if (error == false) {
			
			BufferedWriter bw = null;
		    try {
		    	//sorting cars with implementation of Comparable<Car> interface in Car class
		    	Collections.sort(cars);
		    	bw = new BufferedWriter(new FileWriter(outputFileName));
		    	for (Car car : cars) {
					bw.write(carToString(car));
					bw.newLine();
				}
		    } catch(FileNotFoundException e) {
		    	error=true;
		        System.err.println(e.getMessage());
		    } catch(IOException e) {
		    	error=true;
		        System.err.println(e.getMessage());
		    } finally {
		        try {
		            if(bw != null) {
		                bw.close();
		            }
		        } catch(IOException e) {
		        	error=true;
		            System.err.println(e.getMessage());
		        }
		    }
		}
		return error;
	}
	
	protected int calculateRevenue(int year, int month, int day, String fileName){
		/**
		 * This method takes as parameters a year, month, day and a database file name. It reads the database with the cars information 
		 * and it checks if the selling date of cars is the same as the one entered by the user, if it is the same then it adds the price of the given cars
		 * to the total revenue.
		 * @param year
		 * @param month
		 * @param day
		 * @param fileName is the name of the text file with the cars information
		 * @return returns the total revenue as an int  */
		
		ArrayList<Car> cars = readCars(fileName);
		int totalRevenue=0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate;
		String strMonth;
		String strDay;
		
		//Adding a leading zero to the month and day if the user enters a single digit
		if (String.valueOf(month).length()==1) {
			strMonth = "0"+month;	
		} else  strMonth = String.valueOf(month);
		if (String.valueOf(day).length()==1) {
			strDay = "0"+day;	
		}else  strDay = String.valueOf(day);
		
		
		String date=year+"-"+strMonth+"-"+strDay;//date into correct format
		
		//checking if input date is in the correct format and converting string to date
		try {
    		formatter.parse(date.trim());
    		inputDate = formatter.parse(date);
    	} catch (ParseException pe) {
    		inputDate=null;
    	}
				
		for (Car car : cars) {
			String strCarDate= car.getSellingDate();
			if (car.getStatus()==true) {//car that has been sold
				Date carDate;
				
				//checking if car date is in correct format and converting string to date
				try {
		    		formatter.parse(strCarDate.trim());
		    		carDate = formatter.parse(strCarDate);
		    	} catch (ParseException pe) {
		    		carDate =null;
		    	}
								
				int comparison = carDate.compareTo(inputDate);//-1,0 or 1
				
				if (comparison == 0) {//0 if the dates are the same
					int price = car.getPrice();
					totalRevenue += price;	
				}
			}
		}
		return totalRevenue;
	}
	
	
	protected int calculateRevenue(int year, int month, String fileName){
		/**
		 * This function calculates the Revenue of all the cars that have been sold in the same month as the 
		 * month that the user has specified using the GUI. Function has as parameters the year and month and 
		 * it returns an an int which is totalRevenue. The function converts the input date into a Date format
		 * and then gets all cars from the database and for all of the cars that have been sold, uses the implemented
		 * compareTo method to see if both dates are equal in order to add the price into the totalRevenue variable.
		 * @param year
		 * @param month
		 * @param fileName is the name of the text file that contains the car information*/
		
		ArrayList<Car> cars = readCars(fileName);
		int totalRevenue=0;
		String strInputDate;
		Date inputDate;
		String correctedMonth;
		
		//adding a 0 if the user inputs a single digit for month in order to have the correct format MM
		if (String.valueOf(month).length()==1) {
			correctedMonth = "0"+month;	
		} else  correctedMonth = String.valueOf(month);
		
		strInputDate=year+"-"+correctedMonth;		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		
		//checking if input date is in the correct format and converting string to date
		try {
    		formatter.parse(strInputDate.trim());
    		inputDate = formatter.parse(strInputDate);
    	} catch (ParseException pe) {
    		inputDate=null;
    	}
		
		for (Car car : cars) {
						
			String sellingDate = car.getSellingDate();
			
			if (car.getStatus()==true) { //checks if given car has been sold
				String strCarDate = sellingDate.substring(0,7);//gets year and month yyyy-MM
				Date carDate;
				
				//Converting sellingDate into Date type
				try {
					carDate = formatter.parse(strCarDate);
					} catch (ParseException e) {
					carDate=null;
					System.out.println("date is not in correct format");
				} 
				
				//carDate and inputDate are both Date type in format yyyy-MM
				int comparison = carDate.compareTo(inputDate);
				
				if (comparison == 0) { //when month are year are the same
					totalRevenue += car.getPrice();	
				}
			}
		}
		return totalRevenue;
	}
}


