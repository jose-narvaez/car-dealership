package cardealerPackage;

/**
 * This class inherits everything from the Car class. Its constructor sets the number of doors to 5, the number of seats to 7 and 
 * the type to "MPV".
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 *  */

public class Mpv extends Car{
	
	private static final int DOORS=5;
	private static final int SEATS=7;
	private static final String TYPE= "MPV";
	
	
	Mpv(String plate,String model, String colour, int milage,String accidentHistory,boolean transmission, int price, String arrivalDate, String sellingDate){
		super(plate,model,colour,milage,accidentHistory,transmission,price,arrivalDate, sellingDate);
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

