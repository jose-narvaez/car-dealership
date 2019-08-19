package cardealerPackage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class CustomerTest {
	
	/*
	 * This class tests the methods under Customer class and the methods defined under User.
	 * These are: readCars, the overloaded method searchCars and encodePassword.
	 * */

	@Test
	public void testReadCars() {
		//this method should read 16 cars
		Customer myCustomer = new Customer("testCustomer", "1");
		ArrayList<Car> carsDatabase = myCustomer.readCars("cars-database.txt");
		int size = carsDatabase.size();
		
		//test
		assertEquals(16, size);
	}
	
	@Test
	public void testSearchCarsColour() {
		//function should find 3 cars
		Customer myCustomer = new Customer("testCustomer", "1");
		ArrayList<Car> cars = myCustomer.searchCar("red", "cars-database.txt");
		int size = cars.size();
		
		assertEquals(3, size);
	}
	
	@Test
	public void testSearchCarsModel() {
		//function should find one car
		Customer myCustomer = new Customer("testCustomer", "1");
		ArrayList<Car> cars = myCustomer.searchCar("Mercedes Citan","manual", "cars-database.txt");
		int size = cars.size();
		
		assertEquals(1, size);
	}
	
	@Test
	public void testSearchCarsSeats() {
		
		//function should find 4 cars
		Customer myCustomer = new Customer("testCustomer", "1");
		ArrayList<Car> cars = myCustomer.searchCar(3,4, "cars-database.txt");
		int size = cars.size();
		
		assertEquals(4, size);
	}
	
	@Test
	public void testSearchCarsSize() {
		//function should find 1
		Customer myCustomer = new Customer("testCustomer", "1");
		ArrayList<Car> cars = myCustomer.searchCar(true, "cars-database.txt");//large cars
		int size = cars.size();
		
		assertEquals(1, size);
	}
	
	@Test
	public void testEncodePassword() {
		Customer myCustomer = new Customer("testCustomer", "1");
		String encodedPassword = myCustomer.encodePassword("123abc");
		
		assertEquals("49-50-51-97-98-99", encodedPassword);
		
	}
	
}


