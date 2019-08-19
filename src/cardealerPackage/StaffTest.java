package cardealerPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StaffTest {
	/*
	 * This class tests the following methods from the Staff class: carToString, calculateRevenue,printCars,sellCar,
	 * addCar and addCars. 
	 * */

	@Test
	public void testCarToString() {
		Staff myStaff = new Staff("staffName","1");
		//creating two Car objects
		Car myHatchback = new Hatchback("AAAEEE","Honda Jazz", "grey", 2000, "none", true, 7300, "2018-09-01", "2018-10-04");
		Car myVan = new Van("MX67TCP","Mercedes Citan","white",35000,"front damage",true,9500, "2018-11-04","",true);
		//converting objects to string 
		String strHatchback = myStaff.carToString(myHatchback);
		String strVan = myStaff.carToString(myVan);	
		
		//testing
		assertEquals("AAAEEE,Honda Jazz,Hatchback,,grey,2000,none,manual,7300,2018-09-01,2018-10-04", strHatchback);
		assertEquals("MX67TCP,Mercedes Citan,Van,large,white,35000,front damage,manual,9500,2018-11-04,", strVan);
	}
	
	@Test
	public void testCalculateRevenue() {
		Staff myStaff = new Staff("staffName","1");
		//function calculate revenue by day
		int totalRevenueDay = myStaff.calculateRevenue(2018, 10, 4, "cars-database.txt");
		//function calculate revenue by month
		int totalRevenueMonth = myStaff.calculateRevenue(2018, 10, "cars-database.txt");
		//function calculate revenue by non existing date
		int totalRevenueMonthInvalidDate = myStaff.calculateRevenue(20199, 10, "cars-database.txt");
		//testing
		assertEquals(8000,totalRevenueDay);
		assertEquals(34100, totalRevenueMonth);
		assertEquals(0, totalRevenueMonthInvalidDate);
	}
	
	@Test
	public void testPrintCars() {
		Staff myStaff = new Staff("staffName","1");
		boolean error = myStaff.printCars("cars-database.txt", "cars-output.txt");//correct
		boolean error2 = myStaff.printCars("cars-notexisting.txt", "cars-output.txt");//non-existing name for database
		
		//test
		assertEquals(false, error);//no error
		assertEquals(true,error2);//error
	}
	
	@Test
	public void testSellCar() {
		Staff myStaff = new Staff("staffName","1");
		boolean error = myStaff.sellCar("cars-database.txt", "AA13QWE"); //existing database and available car
		boolean error2 = myStaff.sellCar("cars-notexisting.txt", "AA13QWE"); //not existing database
		boolean error3 = myStaff.sellCar("cars-database.txt", "YB18NBA"); //existing database and sold car
		
		//test
		assertEquals(false, error);// only works for first run because you can't sell a not available car 
		assertEquals(true,error2);
		assertEquals(true,error3);
	}
	
	@Test
	public void testAddCars() {
		Staff myStaff = new Staff("staffName","1");
		boolean error = myStaff.addCars("cars-import.txt", "cars-database.txt");//no error
		boolean error2 = myStaff.addCars("non-existingImport.txt", "cars-database.txt");//non existing import file
		boolean error3 = myStaff.addCars("cars-import.txt", "non-existingDatabase.txt");//non existing database
		
		//test
		assertEquals(false, error);
		assertEquals(true, error2);
		assertEquals(true, error3);
	}
	
	@Test
	public void testAddCar() {
		Staff myStaff = new Staff("staffName","1");
		boolean error = myStaff.addCar("cars-database.txt", "QQWWEER", "Mercedes Citan", "Van", "small", "red", 123, "none", "manual", 1233, "2018-02-02", "");
		boolean error2 = myStaff.addCar("cars-database.txt", "CCVV12E", "Mercedes Citan", "Van", "small", "red", 123, "none", "manual", 1233, "2018-02-6655", "");//non-valid date
		boolean error3 = myStaff.addCar("cars-database.txt", "NB17GFG", "Mercedes Citan", "Van", "small", "red", 123, "none", "manual", 1233, "2018-02-02", "");//car already exists on file

		assertEquals(false, error);//change to false, error when this is ran twice
		assertEquals(false, error2); //change to false
		assertEquals(true, error3);
	}
}
