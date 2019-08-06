package cardealerPackage;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	User validateUser(String username, String inputPassword, String fileName) {
		/** 
		 * This method gets as parameters a username a password and the name of a file of the users database.
		 * This method checks if there exists a user in the database with the given username and password, 
		 * if it exists it returns a User object of the user and if not it returns null.
		 * @param username
		 * @param inputPassword it has to be at most a 9 digit number
		 * @param fileName is the name of the database file
		 * @returns User object*/
		ArrayList<User> users =(new Admin("admin","1")).readUsers(fileName);

		String encodedPassword = (new Admin("admin","1")).encodePassword(inputPassword);
					
		for (User user : users) {
			String currentUsername = user.getUsername();
			String currentPassword = user.getPassword();
			
			if (currentUsername.equals(username) && currentPassword.equals(encodedPassword)) {
				return user;
			}			
		}
		return null; //if not found
	}
	
	//GUI

	private JPanel contentPanel;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(149, 94, 130, 26);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 144, 130, 26);
		contentPanel.add(passwordField);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(71, 99, 77, 16);
		contentPanel.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(76, 149, 61, 16);
		contentPanel.add(lblPassword);
		
		lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(149, 20, 130, 36);
		contentPanel.add(lblNewLabel);
		
		//Action listener
		JButton btnSubmit = new JButton("Log in");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username =textFieldUsername.getText();
				char[] charPassword =passwordField.getPassword();
				String password = String.valueOf(charPassword);
				User myUser = validateUser(username, password, "cars-users.txt");
				
				//opening dialogs
				if (myUser instanceof Admin) {
					new StaffDialog((Admin)myUser);
				} else if (myUser instanceof Staff) {
					new StaffDialog((Staff)myUser);					
				}else if (myUser instanceof Customer) {
					new CustomerDialog((Customer)myUser);
				}else {//the user was not found
					JOptionPane.showMessageDialog(contentPanel, "The username or password are not correct");
				}	
			}
		});
		btnSubmit.setBounds(162, 182, 117, 29);
		contentPanel.add(btnSubmit);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

