package cardealerPackage;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTable;

public class CustomerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldColour;
	private JTextField textField;
	private JTable table;
	
	String [][] ArrayListToArray(ArrayList<Car> cars){
		/**
		 * This function converts an ArrayList<Car> to a two dimensional array of type String and returns the array.
		 * */
		
		String[][] arrayCar = new String[cars.size()][10];
		int i =0;
		
		for (Car car : cars) {
			arrayCar[i][0] = car.getPlate();
			arrayCar[i][1] = car.getModel();
			arrayCar[i][2] = car.getType();
			if (car instanceof Van) {
				arrayCar[i][3]=((Van) car).getSize();
			}
			else {
				arrayCar[i][3]="";
			}
			arrayCar[i][4] = car.getColour();
			arrayCar[i][5] = car.getMileage();
			arrayCar[i][6] = car.getTransmission();
			arrayCar[i][7] = Integer.toString(car.getPrice());
			arrayCar[i][8]=car.getArrivalDate();
			arrayCar[i][9] = car.getSellingDate();
			
			i ++;			
		}
		return arrayCar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Customer mycustomer = new Customer("d","1234");
		try {
			CustomerDialog dialog = new CustomerDialog(mycustomer);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerDialog(Customer myCustomer) {
		//setting main display
		setBounds(100, 100, 1193, 504);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel searchOptions = new JPanel();
		searchOptions.setBounds(464, 121, 441, 164);
		contentPanel.add(searchOptions);
		CardLayout cardlayout = new CardLayout(0, 0);
		searchOptions.setLayout(cardlayout);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 315, 1153, 145);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//comboBox to set panels
		String[] options = { "Colour","Model and transmission", "Seats", "Size"};
		
		JComboBox<?> comboBox = new JComboBox<Object>(options);
		comboBox.setBounds(619, 81, 117, 27);
		contentPanel.add(comboBox);
		comboBox.setSelectedIndex(0);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String)comboBox.getSelectedItem();
				if (selectedOption.equals("Colour")) {
					cardlayout.show(searchOptions,"ColourOption");				
				} else if (selectedOption.equals("Seats")) {
					cardlayout.show(searchOptions,"seatsOption");
				}else if (selectedOption.equals("Size")) {
					cardlayout.show(searchOptions,"sizeOption");
				}else if (selectedOption.equals("Model and transmission")) {
					cardlayout.show(searchOptions, "modelOption");
				}
			}
		});
		
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setBounds(546, 85, 61, 16);
		contentPanel.add(lblSearchBy);
		
		
		//Search by colour
		JPanel panelcolour = new JPanel();
		searchOptions.add(panelcolour, "ColourOption");
		panelcolour.setLayout(null);
		
		textFieldColour = new JTextField();
		textFieldColour.setBounds(153, 19, 130, 26);
		panelcolour.add(textFieldColour);
		textFieldColour.setColumns(10);
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setBounds(85, 24, 61, 16);
		panelcolour.add(lblColour);
				
		JButton btnSearch_2 = new JButton("Search");
		btnSearch_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//search function
				String colour = textFieldColour.getText();
				ArrayList<Car> queryResult = myCustomer.searchCar(colour, "cars-database.txt");
				
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage","Transmission","Price", "Arrival Date", "Selling Date"};
				
				String[][] cars = ArrayListToArray(queryResult);
				
				//table
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
			    
				//error message
			    if (queryResult.size() ==0) {
			    	JOptionPane.showMessageDialog(contentPanel, "No cars with that colour were found");
			    }
			}
		});
		btnSearch_2.setBounds(166, 77, 117, 29);
		panelcolour.add(btnSearch_2);
		
		//search by seats
		JPanel panelSeats = new JPanel();
		searchOptions.add(panelSeats, "seatsOption");
		panelSeats.setLayout(null);
		
		SpinnerNumberModel modelMin = new SpinnerNumberModel(4,1,10,1);
		SpinnerNumberModel modelMax = new SpinnerNumberModel(5,1,10,1);
		
		JLabel lblNewLabel = new JLabel("Min");
		lblNewLabel.setBounds(151, 17, 61, 16);
		panelSeats.add(lblNewLabel);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setBounds(151, 66, 61, 16);
		panelSeats.add(lblMax);
		
		JSpinner spinnerMin = new JSpinner(modelMin);
		spinnerMin.setBounds(211, 12, 33, 26);
		panelSeats.add(spinnerMin);
		
		JSpinner spinnerMax = new JSpinner(modelMax);
		spinnerMax.setBounds(211, 61, 33, 26);
		panelSeats.add(spinnerMax);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int minVal = (Integer)spinnerMin.getValue();
				int maxVal = (Integer)spinnerMax.getValue();
				
				//search function
				ArrayList<Car> queryResult =myCustomer.searchCar(minVal, maxVal, "cars-database.txt");
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage","Transmission","Price", "Arrival Date", "Selling Date"};
				
				//table
				String[][] cars = ArrayListToArray(queryResult);
								
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
			    
				//error message
				if (minVal > maxVal) {
					JOptionPane.showMessageDialog(contentPanel, "The min value needs to be smaller than the max value");
					
				}else if (queryResult.size() ==0){
					JOptionPane.showMessageDialog(contentPanel, "No cars with that seats were found");
				}
				
			}
		});
		btnSearch.setBounds(136, 118, 117, 29);
		panelSeats.add(btnSearch);
		
		//search by size
		JPanel panelSize = new JPanel();
		searchOptions.add(panelSize, "sizeOption");
		panelSize.setLayout(null);
		
		JRadioButton rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setBounds(141, 22, 141, 23);
		panelSize.add(rdbtnLarge);
		rdbtnLarge.setSelected(true);
		
		JRadioButton rdbtnSmall = new JRadioButton("Small");
		rdbtnSmall.setBounds(141, 57, 141, 23);
		panelSize.add(rdbtnSmall);
		
		ButtonGroup sizeGroup = new ButtonGroup();
		sizeGroup.add(rdbtnLarge);
		sizeGroup.add(rdbtnSmall);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    boolean size;
			    if (rdbtnLarge.isSelected()) size = true;
				else size = false;
			    
			    //search function
			    ArrayList<Car> queryResult = myCustomer.searchCar(size, "cars-database.txt");
			    
			    //table
			    String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage","Transmission","Price", "Arrival Date", "Selling Date"};
				
				String[][] cars = ArrayListToArray(queryResult);
								
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
			    
				//error message
			    if (queryResult.size() ==0){
					JOptionPane.showMessageDialog(contentPanel, "No Vans with that size were found");
				}
			}
		});
		btnSearch_1.setBounds(136, 106, 117, 29);
		panelSize.add(btnSearch_1);
		
		//search by model and transmission
		JPanel panelModel = new JPanel();
		searchOptions.add(panelModel, "modelOption");
		panelModel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(156, 17, 130, 26);
		panelModel.add(textField);
		textField.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(91, 22, 61, 16);
		panelModel.add(lblModel);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(156, 55, 141, 23);
		panelModel.add(rdbtnManual);
		rdbtnManual.setSelected(true);
		
		JRadioButton rdbtnAutomatic = new JRadioButton("Automatic");
		rdbtnAutomatic.setBounds(156, 90, 141, 23);
		panelModel.add(rdbtnAutomatic);
		
		ButtonGroup transmissionGroup = new ButtonGroup();
		transmissionGroup.add(rdbtnManual);
		transmissionGroup.add(rdbtnAutomatic);
		
		
		JLabel lblTransmission = new JLabel("Transmission");
		lblTransmission.setBounds(58, 73, 85, 16);
		panelModel.add(lblTransmission);
		
		JButton btnSearch_3 = new JButton("Search");
		btnSearch_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String transmission;
				if (rdbtnManual.isSelected()) transmission = "manual";
				else transmission = "automatic";
				String model = textField.getText();
				
				//search function
				ArrayList<Car> queryResult = myCustomer.searchCar(model,transmission, "cars-database.txt");
				
				//table 
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage","Transmission","Price", "Arrival Date", "Selling Date"};
				
				String[][] cars = ArrayListToArray(queryResult);
								
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
			    
				//error messages
			    if (queryResult.size() == 0){
					JOptionPane.showMessageDialog(contentPanel, "No cars with that model and transmission were found");
				}
			}
		});
		btnSearch_3.setBounds(156, 125, 117, 29);
		panelModel.add(btnSearch_3);
		
		JLabel lblSearchCars = new JLabel("SEARCH CARS");
		lblSearchCars.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblSearchCars.setBounds(535, 17, 177, 27);
		contentPanel.add(lblSearchCars);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setBounds(19, 281, 61, 16);
		contentPanel.add(lblResults);
		
		setVisible(true);	
	}
}
