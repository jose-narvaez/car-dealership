package cardealerPackage;

import java.io.File;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class StaffDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldColour;
	private JTextField textAddPlate;
	private JTextField textAddModel;
	private JTextField textAddColour;
	private JTextField textAddHistory;
	private JTextField textAddArrival;
	private JTextField textAddSelling;
	private JLabel lblPlate;
	private JLabel lblModel_1;
	private JLabel lblType;
	private JLabel lblSize;
	private JLabel lblColour_1;
	private JLabel lblMileage;
	private JLabel lblAccidentHistory;
	private JLabel lblTransmission_1;
	private JLabel lblPrice;
	private JLabel lblArrivalDate;
	private JLabel lblSelling;
	private JFileChooser fileChooser;
	private JPanel panelSell;
	private JTextField textFieldSell;
	private JButton btnSellCar;
	private JLabel lblEnterPlate;
	private JPanel panelSearchOptions;
	private JPanel panelColour;
	private JPanel panelSeats;
	private JLabel lblSearchBy;
	private JPanel panelAddOptions;
	private JPanel panel_4;
	private JComboBox<?> comboBox_1;
	private JLabel lblAddBy;
	private JPanel panel_5;
	private JButton btnChooseFile_1;
	private JPanel panelAddUsers;
	private JTextField textFieldAddUsername;
	private JTextField textFieldAddPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRole;
	private JButton btnAddUser;
	private JPanel panel;
	private JButton btnPrintCars;
	private JPanel panelModel;
	private JTextField textField;
	private JLabel lblModel_2;
	private JLabel lblTransmission_2;
	private JButton btnSearch_3;
	private JRadioButton rdbtnSmall;
	private JRadioButton rdbtnLarge;
	private JComboBox<?> comboBoxType;
	private JSpinner spinnerMileage;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnA;
	private JSpinner spinnerYear;
	private JLabel lblYear;
	private JSpinner spinnerMonth;
	private JLabel lblMonth;
	private JSpinner spinnerDay;
	private JLabel lblDay;
	private JTextField textFieldResult;
	private JButton btnCalculateByMonth;
	private JRadioButton rdbtnStaff;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnCustomer;
	private JLabel lblRevenue;
	private JLabel lblClickTheButton;
	private JLabel lblEnterThePlate;
	private JLabel lblChooseTheFile;
	private JScrollPane scrollPane_1;
	private JLabel lblAllCarsIn;
	private JLabel lblYyyy;
	private JLabel lblYyyymmdd;
	
	String [][] ArrayListToArray(ArrayList<Car> cars){
		/**
		 * This function converts an ArrayList<Car> to a two dimensional array of type String and returns the array.
		 * */
		
		String[][] arrayCar = new String[cars.size()][11];
		int i =0;
		
		//getting attributes of car and assigning them to 
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
			arrayCar[i][6] = car.getHistory();
			arrayCar[i][7] = car.getTransmission();
			arrayCar[i][8] = Integer.toString(car.getPrice());
			arrayCar[i][9]=car.getArrivalDate();
			arrayCar[i][10] = car.getSellingDate();
			
			i ++;			
		}
		return arrayCar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		Staff myUser = new Staff("blah", "3456");
		Admin myUser = new Admin("blah", "543");
		
		try {
			StaffDialog dialog = new StaffDialog(myUser);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StaffDialog(Staff myStaff) {
		
		setBounds(100, 100, 1112, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 1087, 427);
		contentPanel.add(tabbedPane);
		
		//SEARCH TAB
		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Search", null, panelSearch, null);
		panelSearch.setLayout(null);
		
		panelSearchOptions = new JPanel();
		panelSearchOptions.setBounds(359, 46, 445, 159);
		panelSearch.add(panelSearchOptions);
		CardLayout cardlayout = new CardLayout(0, 0);
		panelSearchOptions.setLayout(cardlayout);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 217, 1039, 158);
		panelSearch.add(scrollPane);
		
		
		//search by
		lblSearchBy = new JLabel("Search By");
		lblSearchBy.setBounds(446, 11, 61, 16);
		panelSearch.add(lblSearchBy);
		
		String[] searchOptions = { "Colour","Model and Transmission", "Seats", "Size"};
		
		JComboBox<?> comboBox = new JComboBox<Object>(searchOptions);
		comboBox.setBounds(519, 7, 109, 27);
		panelSearch.add(comboBox);
		comboBox.setSelectedIndex(0);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String)comboBox.getSelectedItem();
				if (selectedOption.equals("Colour")) {
					cardlayout.show(panelSearchOptions,"ColourOption");				
				} else if (selectedOption.equals("Model and Transmission")) {
					cardlayout.show(panelSearchOptions,"modelOption");
				}else if (selectedOption.equals("Seats")) {
					cardlayout.show(panelSearchOptions,"seatsOption");
				}else if (selectedOption.equals("Size")) {
					cardlayout.show(panelSearchOptions,"sizeOption");
				}
			}
		});
		
		//search by colour
		
		panelColour = new JPanel();
		panelSearchOptions.add(panelColour, "ColourOption");
		panelColour.setLayout(null);
		
		textFieldColour = new JTextField();
		textFieldColour.setBounds(159, 25, 130, 26);
		panelColour.add(textFieldColour);
		textFieldColour.setColumns(10);
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setBounds(98, 30, 61, 16);
		panelColour.add(lblColour);
				
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(159, 100, 117, 29);
		panelColour.add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//search function
				String colour = textFieldColour.getText();
				ArrayList<Car> queryResult = myStaff.searchCar(colour, "cars-database.txt");
				
				//Table
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
				String[][] cars = ArrayListToArray(queryResult);
								
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
		
		
		//search by seats
		panelSeats = new JPanel();
		panelSearchOptions.add(panelSeats, "seatsOption");
		panelSeats.setLayout(null);
		
		
		SpinnerNumberModel modelMin = new SpinnerNumberModel(4,1,10,1);
		SpinnerNumberModel modelMax = new SpinnerNumberModel(5,1,10,1);
		
		JSpinner spinner = new JSpinner(modelMin);
		spinner.setBounds(201, 18, 49, 26);
		panelSeats.add(spinner);
		
		JSpinner spinnerMax = new JSpinner(modelMax);
		spinnerMax.setBounds(201, 73, 49, 26);
		panelSeats.add(spinnerMax);
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setBounds(128, 23, 61, 16);
		panelSeats.add(lblMin);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setBounds(128, 78, 61, 16);
		panelSeats.add(lblMax);
		
		//click on button
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//search function
				int minVal = (Integer)spinner.getValue();
				int maxVal = (Integer)spinnerMax.getValue();
				ArrayList<Car> queryResult =myStaff.searchCar(minVal, maxVal, "cars-database.txt");
				
				//table
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
				String[][] cars = ArrayListToArray(queryResult);
				
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
				
				//error messages
				if (minVal > maxVal) {
					JOptionPane.showMessageDialog(contentPanel, "The min value needs to be smaller than the max value");
					
				}else if (queryResult.size() ==0){
					JOptionPane.showMessageDialog(contentPanel, "No cars with that seats were found");
				}	
			}
		});
		btnSearch_1.setBounds(133, 109, 117, 29);
		panelSeats.add(btnSearch_1);
		
		//search by size
		JPanel panelSize = new JPanel();
		panelSearchOptions.add(panelSize, "sizeOption");
		panelSize.setLayout(null);
		
		rdbtnSmall = new JRadioButton("Small");
		rdbtnSmall.setBounds(137, 23, 141, 23);
		panelSize.add(rdbtnSmall);
		rdbtnSmall.setSelected(true);
		
		rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setBounds(137, 58, 141, 23);
		panelSize.add(rdbtnLarge);
		
		ButtonGroup groupSize = new ButtonGroup();
		groupSize.add(rdbtnSmall);
		groupSize.add(rdbtnLarge);
		
		JButton btnSearch_2 = new JButton("Search");
		btnSearch_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    //search function
			    boolean size;
			    if (rdbtnLarge.isSelected()) size = true;
				else size = false;
			    
			    ArrayList<Car> queryResult = myStaff.searchCar(size, "cars-database.txt");
			    String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
			    //Table
				String[][] cars = ArrayListToArray(queryResult);
				
				JTable table = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane.setViewportView(table);
				
			    //error messages
			    if (queryResult.size() ==0){
					JOptionPane.showMessageDialog(contentPanel, "No Vans with that size were found");
				} 
			}
		});
		btnSearch_2.setBounds(125, 93, 117, 29);
		panelSize.add(btnSearch_2);
		
		//search by model and transmission
		panelModel = new JPanel();
		panelSearchOptions.add(panelModel, "modelOption");
		panelModel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(151, 16, 130, 26);
		panelModel.add(textField);
		textField.setColumns(10);
		
		lblModel_2 = new JLabel("Model");
		lblModel_2.setBounds(78, 21, 61, 16);
		panelModel.add(lblModel_2);
		
		lblTransmission_2 = new JLabel("Transmission");
		lblTransmission_2.setBounds(42, 71, 85, 16);
		panelModel.add(lblTransmission_2);
		
		JRadioButton rdbtnAutomatic = new JRadioButton("Automatic");
		rdbtnAutomatic.setBounds(151, 86, 141, 23);
		panelModel.add(rdbtnAutomatic);
		rdbtnAutomatic.setSelected(true);
		
		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setBounds(151, 54, 141, 23);
		panelModel.add(rdbtnManual);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnManual);
		group.add(rdbtnAutomatic);
		
		btnSearch_3 = new JButton("Search");
		btnSearch_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//search function
				String transmission;
				if (rdbtnManual.isSelected()) transmission = "manual";
				else transmission = "automatic";
				
				String model = textField.getText();
				
				ArrayList<Car> queryResult = myStaff.searchCar(model,transmission, "cars-database.txt");
				
				//table
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
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
		
		btnSearch_3.setBounds(151, 121, 117, 29);
		panelModel.add(btnSearch_3);
		
		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setBounds(6, 189, 120, 16);
		panelSearch.add(lblSearchResults);
		
		
		
		//ADD TAB
		JPanel panelAdd = new JPanel();
		tabbedPane.addTab("Add cars", null, panelAdd, null);
		panelAdd.setLayout(null);
		
		panelAddOptions = new JPanel();
		panelAddOptions.setBounds(292, 49, 653, 277);
		panelAdd.add(panelAddOptions);
		CardLayout cl_panelAddOptions = new CardLayout(0, 0);
		panelAddOptions.setLayout(cl_panelAddOptions);
		
		panel_4 = new JPanel();
		panelAddOptions.add(panel_4, "carOption");
		panel_4.setLayout(null);
		
		panel_5 = new JPanel();
		panelAddOptions.add(panel_5, "fileOption");
		panel_5.setLayout(null);
		
		//add by
		
		lblAddBy = new JLabel("Add By");
		lblAddBy.setBounds(439, 14, 61, 16);
		panelAdd.add(lblAddBy);
		
		String[] addOptions = { "Enter Values", "Add from file"};
		comboBox_1 = new JComboBox<Object>(addOptions);
		comboBox_1.setBounds(504, 10, 168, 27);
		panelAdd.add(comboBox_1);
		comboBox_1.setSelectedIndex(0);
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String)comboBox_1.getSelectedItem();
				if (selectedOption.equals("Enter Values")) {
					cl_panelAddOptions.show(panelAddOptions,"carOption");				
				} else if (selectedOption.equals("Add from file")) {
					cl_panelAddOptions.show(panelAddOptions,"fileOption");
				}
			}
		});
		
		
		//add by car
		textAddPlate = new JTextField();
		textAddPlate.setBounds(145, 21, 130, 26);
		panel_4.add(textAddPlate);
		textAddPlate.setColumns(10);
		
		textAddModel = new JTextField();
		textAddModel.setBounds(145, 55, 130, 26);
		panel_4.add(textAddModel);
		textAddModel.setColumns(10);
		
		textAddColour = new JTextField();
		textAddColour.setBounds(145, 131, 130, 26);
		panel_4.add(textAddColour);
		textAddColour.setColumns(10);
		
		textAddHistory = new JTextField();
		textAddHistory.setBounds(145, 207, 130, 26);
		panel_4.add(textAddHistory);
		textAddHistory.setColumns(10);
		
		textAddArrival = new JTextField();
		textAddArrival.setBounds(434, 105, 130, 26);
		panel_4.add(textAddArrival);
		textAddArrival.setColumns(10);
		
		textAddSelling = new JTextField();
		textAddSelling.setBounds(434, 149, 130, 26);
		panel_4.add(textAddSelling);
		textAddSelling.setColumns(10);

		
		lblSelling = new JLabel("Selling Date");
		lblSelling.setBounds(337, 154, 85, 16);
		panel_4.add(lblSelling);
		
		lblSize = new JLabel("size");
		lblSize.setBounds(378, 207, 32, 16);
		panel_4.add(lblSize);
		
		lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setBounds(337, 110, 85, 16);
		panel_4.add(lblArrivalDate);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(361, 71, 61, 16);
		panel_4.add(lblPrice);
		
		lblTransmission_1 = new JLabel("Transmission");
		lblTransmission_1.setBounds(323, 26, 85, 16);
		panel_4.add(lblTransmission_1);
		
		lblPlate = new JLabel("Plate");
		lblPlate.setBounds(78, 26, 61, 16);
		panel_4.add(lblPlate);
		
		lblMileage = new JLabel("Mileage");
		lblMileage.setBounds(72, 174, 61, 16);
		panel_4.add(lblMileage);
		
		lblModel_1 = new JLabel("Model");
		lblModel_1.setBounds(78, 60, 61, 16);
		panel_4.add(lblModel_1);
		
		lblAccidentHistory = new JLabel("Accident History");
		lblAccidentHistory.setBounds(35, 212, 105, 16);
		panel_4.add(lblAccidentHistory);
		
		lblColour_1 = new JLabel("Colour");
		lblColour_1.setBounds(72, 136, 61, 16);
		panel_4.add(lblColour_1);
		
		lblType = new JLabel("Type");
		lblType.setBounds(78, 98, 61, 16);
		panel_4.add(lblType);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(270, 242, 117, 29);
		panel_4.add(btnAdd);
		SpinnerNumberModel mileageModel = new SpinnerNumberModel(0,0,1000000000,1);
		
		spinnerMileage = new JSpinner(mileageModel);
		spinnerMileage.setBounds(145, 169, 130, 26);
		panel_4.add(spinnerMileage);
		
		rdbtnM = new JRadioButton("Manual");
		rdbtnM.setBounds(434, 6, 141, 23);
		panel_4.add(rdbtnM);
		rdbtnM.setSelected(true);
		
		rdbtnA = new JRadioButton("Automatic");
		rdbtnA.setBounds(434, 31, 141, 23);
		panel_4.add(rdbtnA);
		
		ButtonGroup transmissionGroup = new ButtonGroup();
		transmissionGroup.add(rdbtnM);
		transmissionGroup.add(rdbtnA);
		
		SpinnerNumberModel priceModel = new SpinnerNumberModel(0,0,1000000000,1);
		
		JSpinner spinnerPrice = new JSpinner(priceModel);
		spinnerPrice.setBounds(434, 67, 130, 26);
		panel_4.add(spinnerPrice);
		
		JRadioButton rdbtnLarge_1 = new JRadioButton("Large");
		rdbtnLarge_1.setBounds(434, 187, 141, 23);
		panel_4.add(rdbtnLarge_1);
		rdbtnLarge_1.setSelected(true);
		
		JRadioButton rdbtnSmall_1 = new JRadioButton("Small");
		rdbtnSmall_1.setBounds(434, 212, 141, 23);
		panel_4.add(rdbtnSmall_1);
		
		ButtonGroup sizeGroup = new ButtonGroup();
		sizeGroup.add(rdbtnLarge_1);
		sizeGroup.add(rdbtnSmall_1);
		
		String[] carType = { "SUV", "MPV", "Van","Hatchback","Saloon","coupe"};
		comboBoxType = new JComboBox<Object>(carType);
		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String currentType = (String)comboBoxType.getSelectedItem();
				if (currentType.equals("Van")) {
					lblSize.setVisible(true);
					rdbtnLarge_1.setVisible(true);
					rdbtnSmall_1.setVisible(true);
				}else {
					lblSize.setVisible(false);
					rdbtnLarge_1.setVisible(false);
					rdbtnSmall_1.setVisible(false);
				}
			}
		});
		comboBoxType.setBounds(145, 92, 130, 27);
		panel_4.add(comboBoxType);
		comboBoxType.setSelectedIndex(0);
		
		lblYyyy = new JLabel("yyyy-mm-dd");
		lblYyyy.setBounds(565, 110, 99, 16);
		panel_4.add(lblYyyy);
		
		lblYyyymmdd = new JLabel("yyyy-mm-dd");
		lblYyyymmdd.setBounds(565, 154, 99, 16);
		panel_4.add(lblYyyymmdd);
		
		//click on button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plate = textAddPlate.getText();
				String model  = textAddModel.getText();
				String type = (String)comboBoxType.getSelectedItem();
				String colour = textAddColour.getText();
				int mileage = (Integer)spinnerMileage.getValue();
				String history = textAddHistory.getText();
				String transmission;
				int price = (Integer)spinnerPrice.getValue();
				String arrival = textAddArrival.getText();
				String selling = textAddSelling.getText();
				String size = "";
				
				
				if (rdbtnM.isSelected()) transmission ="manual";
				else transmission ="automatic";
				
				if (rdbtnLarge_1.isSelected() && type.equals("Van")) size="large";
				else if (type.equals("Van")) size="small";
				
				//add function
				ArrayList<Car> carsBefore = myStaff.readCars("cars-database.txt");
				
				myStaff.addCar("cars-database.txt", plate, model, type, size, colour, mileage, history, transmission, price, arrival, selling);
				
				ArrayList<Car> carsAfter = myStaff.readCars("cars-database.txt");
				
				//updating sell car table
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
				ArrayList<Car> arrayListCars = myStaff.readCars("cars-database.txt");
				ArrayList<Car> unsoldCars = new ArrayList<Car>();
				
				for (Car car :arrayListCars) {
					if (car.getStatus()==false) unsoldCars.add(car);
				}
				
				String[][] cars = ArrayListToArray(unsoldCars);
				
				JTable table_1 = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  

				       }
				};
				scrollPane_1.setViewportView(table_1);
				
				//error messages
				if (colour.length()==0 || model.length() ==0 || plate.length()==0 || history.length() ==0) {
					JOptionPane.showMessageDialog(contentPanel, "The car was not added, some fields are empty. The only fields than can be empty are the dates.");
					
				}else if (carsBefore.size() == carsAfter.size()) {
					JOptionPane.showMessageDialog(contentPanel, "The car was not added because there exists a car with that plate already");
				}else {
					JOptionPane.showMessageDialog(contentPanel, "The car was added succesfully");
				}
			}
		});
		
		
		//add by file
		btnChooseFile_1 = new JButton("Choose File");
		btnChooseFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(contentPanel);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					
					//file function
					ArrayList<Car> carsBefore = myStaff.readCars("cars-database.txt");
					myStaff.addCars(selectedFile.getAbsolutePath(), "cars-database.txt");
					ArrayList<Car> carsAfter = myStaff.readCars("cars-database.txt");
					ArrayList<Car> importCars = myStaff.readCars("cars-import.txt");
					
					//updating sell table
					String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
					
					ArrayList<Car> arrayListCars = myStaff.readCars("cars-database.txt");
					ArrayList<Car> unsoldCars = new ArrayList<Car>();
					
					for (Car car :arrayListCars) {
						if (car.getStatus()==false) unsoldCars.add(car);
					}
					
					String[][] cars = ArrayListToArray(unsoldCars);
					
					JTable table_1 = new JTable(cars,heading) {
						
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row,int column){  
					         return false;  

					       }
					};
					scrollPane_1.setViewportView(table_1);
					
					//error messages
					if (carsBefore.size() == carsAfter.size()) {
						JOptionPane.showMessageDialog(contentPanel, "No cars were added, the cars already exist in the database");
						
					}else if (carsBefore.size()+importCars.size()!= carsAfter.size()) {
						JOptionPane.showMessageDialog(contentPanel, "Some cars were not added because there are cars with that plates already in the database");
					}else {
						JOptionPane.showMessageDialog(contentPanel, "The cars were added succesfully");
					}
				}
			}
		});
		btnChooseFile_1.setBounds(261, 88, 117, 29);
		panel_5.add(btnChooseFile_1);
		
		lblChooseTheFile = new JLabel("Choose the file that contains the cars to be added");
		lblChooseTheFile.setBounds(176, 42, 360, 16);
		panel_5.add(lblChooseTheFile);
		
				
		//SELL TAB
		panelSell = new JPanel();
		tabbedPane.addTab("Sell Car", null, panelSell, null);
		panelSell.setLayout(null);
		
		lblEnterPlate = new JLabel("Plate");
		lblEnterPlate.setBounds(389, 74, 61, 16);
		panelSell.add(lblEnterPlate);
		
		textFieldSell = new JTextField();
		textFieldSell.setBounds(455, 69, 130, 26);
		panelSell.add(textFieldSell);
		textFieldSell.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 159, 1012, 197);
		panelSell.add(scrollPane_1);
		
		String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
		
		ArrayList<Car> arrayListCars = myStaff.readCars("cars-database.txt");
		ArrayList<Car> unsoldCars = new ArrayList<Car>();
		
		for (Car car :arrayListCars) {
			if (car.getStatus()==false) unsoldCars.add(car);
		}
		
		String[][] cars = ArrayListToArray(unsoldCars);
		
		JTable table_1 = new JTable(cars,heading) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){  
		         return false;  

		       }
		};
		scrollPane_1.setViewportView(table_1);
		
		
		btnSellCar = new JButton("Sell Car");
		btnSellCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String plate = textFieldSell.getText();
				
				//sell function
				boolean error = myStaff.sellCar("cars-database.txt", plate);
				
				//error messages
				if (error == false) {
					JOptionPane.showMessageDialog(contentPanel, "The car was sold succesfully");
				}else JOptionPane.showMessageDialog(contentPanel, "The cars could not be sold");
				
				//table
				String[] heading = {"Plate","Model", "Type","Size", "Colour", "Mileage", "History","Transmission","Price", "Arrival Date", "Selling Date"};
				
				ArrayList<Car> arrayListCars = myStaff.readCars("cars-database.txt");
				ArrayList<Car> unsoldCars = new ArrayList<Car>();
				
				for (Car car :arrayListCars) {
					if (car.getStatus()==false) unsoldCars.add(car);
				}
				
				String[][] cars = ArrayListToArray(unsoldCars);
				
				JTable table_1 = new JTable(cars,heading) {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row,int column){  
				         return false;  
				       }
				};
				scrollPane_1.setViewportView(table_1);
			}
		});
		btnSellCar.setBounds(455, 107, 117, 29);
		panelSell.add(btnSellCar);
		
		lblEnterThePlate = new JLabel("Enter the plate of the car to be sold");
		lblEnterThePlate.setBounds(403, 26, 238, 16);
		panelSell.add(lblEnterThePlate);
		
		lblAllCarsIn = new JLabel("All cars available in database");
		lblAllCarsIn.setBounds(26, 131, 201, 16);
		panelSell.add(lblAllCarsIn);
		
		
		
		//Print cars
		panel = new JPanel();
		tabbedPane.addTab("Print cars", null, panel, null);
		panel.setLayout(null);
		
		btnPrintCars = new JButton("Print cars");
		btnPrintCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//print function
				boolean error = myStaff.printCars("cars-database.txt", "cars-output.txt");
				//messages
				if (error == false) {
					JOptionPane.showMessageDialog(contentPanel, "The cars were printed correctly, you can find them in text file cars-output.txt");
				}else JOptionPane.showMessageDialog(contentPanel, "The cars could not be printed");
			}
		});
		btnPrintCars.setBounds(486, 137, 117, 29);
		panel.add(btnPrintCars);
		
		lblClickTheButton = new JLabel("Click the button to print cars. The cars will be printed to text file cars-output.txt");
		lblClickTheButton.setBounds(301, 42, 526, 71);
		panel.add(lblClickTheButton);
		
		//calculate revenue
		JPanel PanelRevenue = new JPanel();
		tabbedPane.addTab("Calculate Revenue", null, PanelRevenue, null);
		PanelRevenue.setLayout(null);
		
		SpinnerNumberModel yearSpinner = new SpinnerNumberModel(2018,1960,2019,1);
		SpinnerNumberModel monthSpinner = new SpinnerNumberModel(10,1,12,1);
		SpinnerNumberModel daySpinner = new SpinnerNumberModel(4,1,31,1);
		
		spinnerYear = new JSpinner(yearSpinner);
		spinnerYear.setBounds(416, 111, 70, 26);
		PanelRevenue.add(spinnerYear);
		
		lblYear = new JLabel("Year");
		lblYear.setBounds(383, 116, 33, 16);
		PanelRevenue.add(lblYear);
		
		spinnerMonth = new JSpinner(monthSpinner);
		spinnerMonth.setBounds(561, 111, 50, 26);
		PanelRevenue.add(spinnerMonth);
		
		lblMonth = new JLabel("Month");
		lblMonth.setBounds(520, 116, 40, 16);
		PanelRevenue.add(lblMonth);
		
		spinnerDay = new JSpinner(daySpinner);
		spinnerDay.setBounds(691, 111, 50, 26);
		PanelRevenue.add(spinnerDay);
		
		lblDay = new JLabel("Day");
		lblDay.setBounds(665, 116, 33, 16);
		PanelRevenue.add(lblDay);
		
		textFieldResult = new JTextField();
		textFieldResult.setBounds(507, 230, 130, 26);
		PanelRevenue.add(textFieldResult);
		textFieldResult.setColumns(10);
		textFieldResult.setEditable(false);
		
		//on click
		JButton btnCalculate = new JButton("Calculate by day");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer)spinnerYear.getValue();
				int month = (Integer)spinnerMonth.getValue();
				int day = (Integer)spinnerDay.getValue();
				//calculate revenue function
				int total = myStaff.calculateRevenue(year, month, day, "cars-database.txt");
				String strTotal = Integer.toString(total);
				textFieldResult.setText("");
				textFieldResult.setText(strTotal);
				
			}
		});
		btnCalculate.setBounds(416, 172, 139, 29);
		PanelRevenue.add(btnCalculate);
		
		btnCalculateByMonth = new JButton("Calculate by month");
		btnCalculateByMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer)spinnerYear.getValue();
				int month = (Integer)spinnerMonth.getValue();
				//calculate revenue function
				int total = myStaff.calculateRevenue(year, month, "cars-database.txt");
				String strTotal = Integer.toString(total);
				textFieldResult.setText("");
				textFieldResult.setText(strTotal);	
			}
		});
		btnCalculateByMonth.setBounds(561, 172, 149, 29);
		PanelRevenue.add(btnCalculateByMonth);
		
		lblRevenue = new JLabel("REVENUE:");
		lblRevenue.setBounds(431, 235, 61, 16);
		PanelRevenue.add(lblRevenue);
		
		
		
		
//		Add users if staff is also Admin
		if (myStaff instanceof Admin) {
	
			panelAddUsers = new JPanel();
			tabbedPane.addTab("Create User", null, panelAddUsers, null);
			panelAddUsers.setLayout(null);
			
			textFieldAddUsername = new JTextField();
			textFieldAddUsername.setBounds(554, 62, 130, 26);
			panelAddUsers.add(textFieldAddUsername);
			textFieldAddUsername.setColumns(9);
			
			textFieldAddPassword = new JTextField();
			textFieldAddPassword.setBounds(554, 116, 130, 26);
			panelAddUsers.add(textFieldAddPassword);
			textFieldAddPassword.setColumns(10);
			
			lblUsername = new JLabel("Username");
			lblUsername.setBounds(471, 67, 77, 16);
			panelAddUsers.add(lblUsername);
			
			lblPassword = new JLabel("Password");
			lblPassword.setBounds(471, 121, 61, 16);
			panelAddUsers.add(lblPassword);
			
			lblRole = new JLabel("Role");
			lblRole.setBounds(490, 204, 38, 16);
			panelAddUsers.add(lblRole);
			
			rdbtnStaff = new JRadioButton("staff");
			rdbtnStaff.setBounds(543, 181, 141, 23);
			panelAddUsers.add(rdbtnStaff);
			rdbtnStaff.setSelected(true);
			
			rdbtnAdmin = new JRadioButton("admin");
			rdbtnAdmin.setBounds(543, 200, 141, 23);
			panelAddUsers.add(rdbtnAdmin);
			
			rdbtnCustomer = new JRadioButton("customer");
			rdbtnCustomer.setBounds(543, 223, 141, 23);
			panelAddUsers.add(rdbtnCustomer);
			
			ButtonGroup roleGroup = new ButtonGroup();
			roleGroup.add(rdbtnStaff);
			roleGroup.add(rdbtnAdmin);
			roleGroup.add(rdbtnCustomer);
			
			//on click
			btnAddUser = new JButton("Add User");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String username = textFieldAddUsername.getText();
					String password = textFieldAddPassword.getText();
					String role="";
					if (rdbtnStaff.isSelected()) role = "staff";
					else if (rdbtnAdmin.isSelected()) role ="admin";
					else if (rdbtnCustomer.isSelected()) role = "customer";
					//add user function
					boolean error =((Admin)myStaff).addUser(username, password, role, "cars-users.txt");
					
					//error messages
					if (error == false) {
						JOptionPane.showMessageDialog(contentPanel, "The user was added succesfully");
					}else {
						JOptionPane.showMessageDialog(contentPanel, "The user was not added succesfully");
					}
				}
			});
			btnAddUser.setBounds(567, 287, 117, 29);
			panelAddUsers.add(btnAddUser);
						
		}
		setVisible(true);
	}
}