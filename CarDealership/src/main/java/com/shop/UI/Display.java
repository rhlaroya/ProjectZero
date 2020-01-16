/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.shop.data.ConnectionUtil;
import com.shop.model.Car;
import com.shop.model.Customer;
import com.shop.model.Employee;
import com.shop.model.Payment;

public class Display  {
	
	/**
	 * Instance fields
	 */
	private final static Logger LOGGER = Logger.getLogger(Display.class.getName());
	
	static Scanner kbd = new Scanner(System.in);
	String work;
	String uname;
	String pword;
	
	/**
	 * Shows the general menu
	 */
	public static void showMenu()  {
		System.out.println("\n" + "Welcome to January Car Dealership!");
		System.out.println("----------------------------------");
		System.out.println("Are you an Employee or a Client?");
		System.out.println("----------------------------------");
		System.out.println("(1) Employee");
		System.out.println("(2) Client");
		System.out.println("(3) Sign up for a client account");
		System.out.println("(4) Exit program");
		System.out.println("----------------------------------");
		System.out.println("------Enter Number of Choice------");
		
		try {
		switch(kbd.nextInt()) {
		
			case 1:
				try {
					Connection conn = ConnectionUtil.connect();
					System.out.println("Enter your user name");
					String user = kbd.next();
					if(user.equals("manager")) {
						managerTrigger();
					} else {
					System.out.println("Enter your password");
					}
					String pass = kbd.next();
					String sql = "select \"password\" from \"employee\" where \"user_name\" = " +  "'" + user +"';";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(pass.equals(rs.getString(1))) {
							showEmployeeMenu();
						} else {
							System.out.println("Incorrect Password");
							showMenu();
						}	
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
				break;
				
			case 2:
				try {
					Connection conn = ConnectionUtil.connect();
					System.out.println("Enter your user name");
					String user = kbd.next();
					System.out.println("Enter your password");
					String pass = kbd.next();
					String sql = "select \"password\" from \"customer\" where \"user_name\" = " +  "'" + user +"';";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						if(pass.equals(rs.getString(1))) {
							showCustomerMenu();
						} else {
							System.out.println("Incorrect Password");
							showMenu();
						}	
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				Customer ca = new Customer();
				ca.register();
				showMenu();
				break;
			case 4:
				System.out.println("Thank you we appreciate your business.");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid number from the menu");
				showMenu();
		}
		
		}catch(Exception e) {
			
				System.out.println("Please enter a valid number from the menu\n");
				LOGGER.log(null, "Exceptions happen!", e);
			
		}
	}
	
	/**
	 * Shows the manager menu. The
	 * manager menu has a little more
	 * functionalities compared to a
	 * regular employee account
	 */
	public static void showManagerMenu() {
		
		System.out.println("\n" + "------Enter Number of Choice------");
		System.out.println("(1) View cars on the lot");
		System.out.println("(2) View car offers");
		System.out.println("(3) Add a car to the lot");
		System.out.println("(4) Remove a car from the lot");
		System.out.println("(5) View all car payments");
		System.out.println("(6) View list of employees");
		System.out.println("(7) Add an employee");
		System.out.println("(8) Fire an employee");
		System.out.println("(9) Go Back to previous");
		
		try {
			int emch = kbd.nextInt();
			if(emch == 1) {
				Employee e = new Employee();
				e.viewCar();
				showManagerMenu();
			} else if (emch == 2) {
				Employee e = new Employee();
				e.viewOffer();
				showManagerMenu();
			} else if (emch == 3) {
				Employee e = new Employee();
				e.addCar();
				showManagerMenu();
			} else if (emch == 4) {
				Employee e = new Employee();
				e.removeCar();
				showManagerMenu();
			} else if(emch == 5) {
				Employee e = new Employee();
				e.viewAllPayments();
				showManagerMenu();
			}	else if(emch == 6)  {
				Employee e = new Employee();
				e.viewAllEmployees();
				showManagerMenu();
			} else  if (emch == 7) {
				Employee e = new Employee();
				e.addEmployee();
				showManagerMenu();
			} else if (emch == 8 ) {
				Employee e = new Employee();
				e.delete(e);
				showManagerMenu();
			} else if (emch == 9) {
				showMenu();
			} else {
				System.out.println("Invalid Number");
				showManagerMenu();
			}
		}catch(Exception e1) {
			LOGGER.log(null, "Exception happens", e1);
	
		}
	}
	
	/**
	 * Shows the manager menu
	 */
	public static void managerTrigger() {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Hello Manager!");
			System.out.println("Enter your password");
			String pass = kbd.next();
			String sql = "select \"password\" from \"employee\" where \"user_name\" = " +  "'" + "manager" +"';";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(pass.equals(rs.getString(1))) {
					showManagerMenu();
				} else {
					System.out.println("Incorrect Password");
					showMenu();
				}	
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the employee menu
	 */
	public static void showEmployeeMenu() {
		
		System.out.println("\n" + "------Enter Number of Choice------");
		System.out.println("(1) View cars on the lot");
		System.out.println("(2) View car offers");
		System.out.println("(3) Add a car to the lot");
		System.out.println("(4) Remove a car from the lot");
		System.out.println("(5) View all car payments");
		System.out.println("(6) Go Back to previous");
		
		try {
			int emch = kbd.nextInt();
			if(emch == 1) {
				Employee e = new Employee();
				e.viewCar();
				showEmployeeMenu();
			} else if (emch == 2) {
				Employee e = new Employee();
				e.viewOffer();
				showEmployeeMenu();
			} else if (emch == 3) {
				Employee e = new Employee();
				e.addCar();
				showEmployeeMenu();
			} else if (emch == 4) {
				Employee e = new Employee();
				e.removeCar();
				showEmployeeMenu();
			}  else if (emch == 5) {
				Employee e = new Employee();
				e.viewAllPayments();
				showEmployeeMenu();
			}
				else if (emch == 6) {
					showMenu();
			}  else {
				System.out.println("Invalid Number");
				showEmployeeMenu();
			}
		}catch(Exception e1) {
			LOGGER.log(null, "Exception happens", e1);
	
		}
	}
	
	/**
	 * Shows the customer menu
	 */
	public static void showCustomerMenu() {
		System.out.println("\n" + "------Enter Number of Choice------");
		System.out.println("(1) View cars on the lot");
		System.out.println("(2) Make an offer ");
		System.out.println("(3) View payments");
		System.out.println("(4) View cars that I own");
		System.out.println("(5) Go Back to previous");
		
		int csch = kbd.nextInt();
		if(csch == 1) {
			Customer c =  new Customer();
			c.viewCar();
			showCustomerMenu();
		} else if (csch == 2) {
			Customer c =  new Customer();
			c.makeOffer();
			showCustomerMenu();
		} else if (csch == 3) {
			Payment pnt = new Payment();
			pnt.FindOwned();;
			showCustomerMenu();
		} else if (csch == 4) {
			Car carr = new Car();
			carr.FindMine();
			showCustomerMenu();
		} else if (csch == 5) {
			showMenu();
		} else {
			System.out.println("Invalid Number");
			showCustomerMenu();
		}

	}
}
