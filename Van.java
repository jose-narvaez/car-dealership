package cardealerPackage;

/** 
 * This class inherits all the attributes and methods from the Car class, and additionally, this class has a size attribute. 
 * Its constructor user the Car constructor to set all the attributes and it sets the number of doors to 5, number of seats to 3, 
 * the type to "Van" and additionally it sets the size of the van. This class implements the method getSize.
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 * */

public class Van extends Car{
	
	private boolean size; //true if large and false if small
	private static final int DOORS=5;
	private static final int SEATS=3;
	private static final String TYPE= "Van";
	
	Van(String plate,String model, String colour, int milage,String accidentHistory,boolean transmission, int price, String arrivalDate, String sellingDate, boolean size ){
		/** This constructor uses Car constructor and sets number of doors to 5 and number of seats to 3, the type to "Van" and the size of the van*/
		super(plate,model,colour,milage,accidentHistory,transmission,price, arrivalDate, sellingDate);
		this.size = size;
	}
	
	 String getSize() {
		 /**
		  * This method gets the size of the van. If the size is true then it returns the string "large" and if false it returns "small".
		  * @returns String large or small
		  * */
		 if (this.size) {
			 return "large";
		 }else {
			 return "small";
		 }	
	}
	 
	 protected String getType() {
			/**
			 * This method returns the type of the car.
			 * @return String with type */
			return TYPE;
		}
		
		protected int getSeats(){
			/**
			 * This method returns the number of seats of the car.
			 * @return int with number of seats */
			return SEATS;		
		}
		
		protected int getDoors(){
			/**
			 * This method returns the number of seats of the car.
			 * @return int with number of seats */
			return DOORS;		
		}
}

