package cardealerPackage;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/** 
 * This class is an abstract class and has as attributes the information every car can have (size for example is left to
 * the Van class), that includes, the plate, model,type,colour, mileage, accidentHistory, transmission, price, number of doors
 * and seats, arrival and selling date and the status. This class has as getter methods to get all the attributes. This class 
 * also implements the Comparable interface and creates the compareTo method for the object Car in order to compare cars 
 * by their status, selling and arrival date. 
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 * */

public abstract class Car implements Comparable<Car> {
	
	private String plate;
	private String model;
	private String colour;
	private int mileage;
	private String accidentHistory;
	private boolean transmission;//true is manual, false if automatic
	private int price;
	private Date arrivalDate;
	private Date sellingDate;
	private Boolean status;// true is sold false if available
	
	Car(String plate,String model, String colour, int mileage,String accidentHistory,boolean transmission, int price, String arrivalDate, String sellingDate){
		/** 
		 * This constructor sets all the attributes above except for numberOfDoors and numberOfSeats. If the arrival date is left blank
		 * or it is not a valid date then it creates a current date and assigns it to the arrival date. If the selling date is
		 * not a valid date then it sets the selling date to null.
		 * */
		this.plate=plate;
		this.model=model;
		this.colour=colour;
		this.mileage=mileage;
		this.accidentHistory=accidentHistory;
		this.transmission=transmission;
		this.price=price;
				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//Setting arrival date. If date entered is blank or not a valid date, it creates a new current date
		try {
			Date dateS = formatter.parse(arrivalDate);
			this.arrivalDate = dateS;
		} catch (ParseException e) {
			Date currentDate = new Date();
			this.arrivalDate = currentDate;
		}
				
		//setting selling date. If date is not in correct format it sets date to null
		try {
			Date dateA = formatter.parse(sellingDate);
			this.sellingDate = dateA;
			this.status=true;
		} catch (ParseException e) {
			this.sellingDate=null;
			this.status=false;
		}
	}
	
	//abstract classes
	abstract protected String getType();
	abstract protected int getSeats();
	abstract protected int getDoors();
		
	//SETTERS
	protected void setSellingDate() {
		/** 
		 * This method creates a date of type Date with the current date and sets the selling date to this current date.*/
		Date currentDate = new Date();
		this.sellingDate = currentDate;
}
	protected void setStatus(boolean status) {
		/**
		 * This method takes as parameter the status of the car as a boolean. It sets the status to the value entered. */
		this.status = status;
	}
	//GETTERS
	protected boolean getStatus() {
		/** 
		 * This method gets the status of the car and returns a boolean. */
		return this.status;
	}
	
	protected String getArrivalDate() {
		/** 
		 * This method gets the arrival date of the car of type Date and converts the Date object into String.
		 * If the car does not have an arrival date then it creates a new date of type Date with the current date
		 * and then converts that date into String.
		 * @return String with date*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate;
		
		if (this.arrivalDate == null) {
			Date currentDate = new Date();
			strDate = formatter.format(currentDate);
		}else {
			strDate= formatter.format(this.arrivalDate);
		}
		return strDate;
	}
		
	protected String getSellingDate() {
		/** 
		 * This method gets the selling date of the car of type Date and converts the Date object into String.
		 * If the car does not have a selling date then it returns an empty String.
		 * @return String with the date*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate;
		if (this.sellingDate == null) {
			strDate="";
		}else {
			strDate= formatter.format(this.sellingDate);
		}
		return strDate;
	}
	
	protected String getPlate() {
		/**
		 * This method returns the plate of the car in upper case.
		 * @return String with plate */
		return this.plate.toUpperCase();
	}
	
	protected String getModel() {
		/**
		 * This method returns the model of the car.
		 * @return String with model */
		return this.model;
	}
		
	protected String getColour() {
		/**
		 * This method returns the colour of the car.
		 * @return String with colour */
		return this.colour;
	}
	
	protected String getMileage() {
		/**
		 * This method returns the mileage of the car.
		 * @return String with mileage */
		return Integer.toString(this.mileage);
	}
	
	protected String getHistory() {
		/**
		 * This method returns the history of the car.
		 * @return String with history */
		return this.accidentHistory;
	}
	
	protected String getTransmission() {
		/**
		 * This method returns the transmission of the car. It is manual if true and automatic if false.
		 * @return String with transmission */
		if(this.transmission) {
			return "manual";
		} else {
			return "automatic";
		}
	}
		
	protected int getPrice() {
		/**
		 * This method returns the model of the car.
		 * @return int with price */
		return this.price;
	}
			
	public int compareTo(Car car) {
		/**
		 * This method takes as parameter an object of type Car. It implements the compareTo method of the 
		 * comparable interface for the case of Car type. This method first checks if the car has been sold or not.
		 * if the car has not been sold it compares by it arrival date otherwise by its selling date. 
		 * @param car of type Car
		 * @return  an int*/
		int cmpResult = status.compareTo(car.status);
		if (cmpResult == 0 && car.status==false) {
			cmpResult = arrivalDate.compareTo(car.arrivalDate);
		}else if (cmpResult == 0 && car.status==true) {
			cmpResult = sellingDate.compareTo(car.sellingDate);
		}
		return cmpResult;
	}
}


