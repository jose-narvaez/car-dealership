package cardealerPackage;

import java.io.*;
import java.util.ArrayList;

/** 
 * This class inherits all methods and attributes from the User and Staff classes. It also implements the readUser
 * and addUser method. 
 * @author Jose Narvaez Paliza
 * @version 10/05/2019
 * */

public class Admin extends Staff {
		
	Admin(String username, String password){
		super(username, password);
	}
	
	protected ArrayList<User> readUsers(String fileName) {
		/** 
		 * This method takes as parameter the name of the file where the users are stored, it reads the file
		 * and converts each line into a User object and stores it into an ArrayList of Users.
		 * @param fileName name of the users database
		 * @return returns an ArrayList of type User with all users that are read from the file */
		
		BufferedReader br = null;
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = br.readLine()) != null){
				String[] parts = line.split(",",3);
				String username = parts[0].trim();
				String password = parts[1].trim();//encoded password
				String role = parts[2].trim().toLowerCase();
				
		        //adding User object
				if (role.equals("staff")) users.add(new Staff(username, password));
				else if (role.equals("admin")) users.add(new Admin(username,password));
				else if (role.equals("customer")) users.add(new Customer(username,password));
			}
		} catch(FileNotFoundException e) {
		System.err.println(e.getMessage());
		} catch(IOException e) {
		System.err.println(e.getMessage());
	}  finally {
		try {
			if(br != null) br.close();
			
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
		return users;
	}
		
	protected boolean addUser(String username, String inputPassword,String role, String fileName){
		/** 
		 * This method takes as parameters a user name, a password, a role and a file name and it writes the 
		 * given fields into the file. It only writes the user to the file if none of the parameters is left blank
		 * ,if the password is a string representation of an int,if the password is less than 10 digits and if 
		 * there is no user in the database with that same user name.
		 * @param username
		 * @param inputPassword needs to be a number
		 * @param role can be staff, admin or customer
		 * @param fileName is the users database  */
		
		boolean error = false;
		
		//checking that no field is left blank
		if (username.length()==0 || inputPassword.length() ==0 || role.length()==0 || fileName.length()==0) {
			error=true;
		}
		
		//checking that a user with given user name does not exist already
		ArrayList<User> users = readUsers(fileName);

		for (User user : users) {
			String currentUsername = user.getUsername();
			if (username.equals(currentUsername)) {
				error=true;
			}
		}
		
		if (error == false) { // if no error then add user to the database
			//encoding password			
			String encodedPassword = encodePassword(inputPassword);
		
			BufferedWriter bw = null;
			//writing user fields into file
		    try {
		    	bw = new BufferedWriter(new FileWriter(fileName,true));
		        bw.write(username+","+encodedPassword+","+role);
		        bw.newLine();
		    
		    } catch(FileNotFoundException e) {
		        System.err.println(e.getMessage());
		    } catch(IOException e) {
		        System.err.println(e.getMessage());
		    } finally {
		        try {
		            if(bw != null) {
		                bw.close();
		            }
		        } catch(IOException e) {
		            System.err.println(e.getMessage());
		        }
		    }
		}
		return error;
	}
}

