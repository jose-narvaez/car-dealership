package cardealerPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AdminTest {
	
	/*
	 * This class tests all methods under class Admin which are: addUser and Read Users.
	 * */
	
	@Test
	public void testAddUser() {
		Admin myAdmin = new Admin("testAdmin", "1");
		boolean error = myAdmin.addUser("Carl", "carlspassword", "staff", "cars-users.txt"); //no error
		boolean error2 = myAdmin.addUser("staff1", "password123", "customer", "cars-users.txt"); //user already exists
		
		//test
		assertEquals(false, error);
		assertEquals(true, error2);
		
	}

	@Test
	public void testReadUsers() {
		//testing how many users it reads from the database, and it should be 4 because it adds carl from above.
		Admin myAdmin = new Admin("testAdmin", "1");
		ArrayList<User> carsDatabase = myAdmin.readUsers("cars-users.txt");
		int size = carsDatabase.size();
		
		//test
		assertEquals(4, size);
	}
}

