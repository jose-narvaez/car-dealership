package cardealerPackage;

import java.util.ArrayList;
/**
 * This class is an abstract class and it has three attributes: a username, a password and a role, all of them of String data type.
 * This class has three get methods in order to get the attributes. It also implements the encodePassword method and the overloaded method searchCar, which
 * allows all users to search by colour of the car, by car model and transmission type, number of seats and size of van. This class
 * also states two abstract methods that need to be implemented: carToString and readCars. This methods are abstract because different type 
 * of users have different access to the car information; customers cannot access the accident history of cars for instance.
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 * */

public abstract class User {
	
	//attributes
	private String username;
	private String password;
	
	User(String username, String password){
		/** 
		 * Constructor that sets the username and password*/
		this.password = password;
		this.username = username;		
	}
	
	//abstract methods
	abstract ArrayList<Car> readCars(String fileName);
	
	//GETTERS
	String getUsername() {
		/** 
		 * This method returns the username as a string
		 * @return the username as string*/
		return this.username;
	}
	
	String getPassword() {
		/** 
		 * This method returns the encoded password as a string
		 * @return the password as string*/
		return this.password;
	}
		
	//FUNCTIONS that Staff, Admin and customer classes are able to use
	
	//searchCar methods using method overloading	
	ArrayList<Car> searchCar(String colour, String fileName) {
		/** This method gets as parameters a colour as a String and the file name which is the name of the cars database.
		 * It uses the readCars method to read the database and uses the result to see what cars have the given colour.
		 * If a car matches the given colour, then it adds the car to a new ArrayList.
		 * @param colour
		 * @param fileName name of the cars database
		 * @returns an ArrayList containing all cars as Car object that match the search */
		ArrayList<Car> result = new ArrayList<Car>();
		ArrayList<Car> cars = readCars(fileName);
		colour = colour.toLowerCase();
		
		for(Car car : cars){
			String currentColour = car.getColour().toLowerCase();
			boolean status = car.getStatus();
		    if (currentColour.equals(colour) && status==false){
		    	result.add(car);
		    }
		}
		return result;	
	}
	
	ArrayList<Car> searchCar(String model, String transmission,String fileName) {
		/**This function gets as parameter the model, transmission type and file name of the cars database.
		 * It uses readCars method and then uses the result of it to see if any car has the given model and
		 * transmission type and if that is true then it adds the given car to another ArrayList of type Car.
		 * The functions returns the ArrayList of type Car with all cars that match the query.
		 * @param model
		 * @param transmission, manual or automatic
		 * @param fileName name of the cars database
		 * @returns an ArrayList containing all cars as Car object that match the search
		 * */
		ArrayList<Car> result = new ArrayList<Car>();
		ArrayList<Car> cars = readCars(fileName);
		model= model.toLowerCase();
		
		for(Car car : cars){
			String currentModel = car.getModel().toLowerCase();
			String currentTransmission = car.getTransmission().toLowerCase();
			boolean status = car.getStatus();
		    if (currentModel.equals(model) && currentTransmission.equals(transmission) && status==false){
		    	result.add(car);
		    }
		}
		return result;	
	}
	
	ArrayList<Car> searchCar(int min, int max,String fileName) {
		/**This function gets as parameter the minimum seats, maximum seats, and file name of the cars database.
		 * It uses readCars method and then uses the result of it to see if any car in the database has number of seats
		 * within the given range. If there is any car with that characteristics then it  adds the given car to another ArrayList of type Car.
		 * The functions returns the ArrayList of type Car with all cars that match the query.
		 * @param min minimum number of seats
		 * @param max the maximum number of seats
		 * @param fileName name of the cars database
		 * @returns an ArrayList containing all cars as Car object that match the search */
		ArrayList<Car> result = new ArrayList<Car>();
		ArrayList<Car> cars = readCars(fileName);
		
		for(Car currentCar : cars){
			int currentSeats = currentCar.getSeats();
			boolean status = currentCar.getStatus();

		    if (currentSeats>=min && currentSeats<=max && status==false){
		    	result.add(currentCar);
		    }
		}
		return result;	
	}
	
	ArrayList<Car> searchCar(boolean boolSize, String fileName) {
		/**This function gets as parameter size of the car and file name of the cars database.
		 * It uses readCars method to store the cars in an ArrayList of type Car. If a car is an instance of Van then
		 * it checks if the given Van has the given size, if that is true then it adds the given car to another ArrayList of type Car.
		 * The functions returns the ArrayList of type Car with all cars that match the query.
		 * @param boolSize size of the car, true if large and false if small
		 * @param fileName name of the cars database
		 * @returns an ArrayList containing all cars as Car object that match the search*/
		
		ArrayList<Car> result = new ArrayList<Car>();
		ArrayList<Car> cars = readCars(fileName);
		
		//converts the boolean size to String, large if true and small if false
		String size;
		if (boolSize == true ) size= "large";
		else size="small";
		
		for(Car currentCar : cars){
			if (currentCar instanceof Van) { //otherwise size is null
				String currentSize=((Van)currentCar).getSize();
				boolean status = currentCar.getStatus();
				
				if (currentSize.equals(size) && status==false) {
					result.add(currentCar);
				}
			}
		}
		return result;	
	}
	
	String encodePassword(String password) {
		/** 
		 * This methods takes as parameter a password and it encodes the password by taking every digit and
		 * converting it into its ascii value. It returns the encoded password as a string where every ascii
		 * value is separated with a dash. e.g 97-97-97 would be the encoded password of aaa
		 * @param password
		 * @return encoded password as a String*/
		int length = password.length();
		
		String encodedPassword="";
		
		for (int i=0;i<length;i++) {
			int ascii= password.charAt(i);
			if (i==length-1) {//doesn't add a dash at the end
				encodedPassword+=ascii;
			}else {//adding a dash
				encodedPassword+=ascii+"-";
			}
		}
		return encodedPassword;
	}
}
