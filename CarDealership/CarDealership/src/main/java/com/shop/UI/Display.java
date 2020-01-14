/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.UI;

/**
 * The display class creates menus for the
 * User and specific one for either an employee
 * or a customer
 */

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.shop.data.Serialization;
import com.shop.model.Car;
import com.shop.model.Customer;
import com.shop.model.Employee;

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
				Serialization s = new Serialization();
				List<Car> ems = s.readCarList("employees.txt");
				Object ploy = ems;
				String work = String.valueOf(ploy);
				String[] ar = work.split("[^a-zA-Z']+");
				String uname = ar[2];
				String pword = ar[3];
//				LOGGER.info("Starting scanner inputs");
				System.out.println("Enter your user name");
				String user = kbd.next();
				System.out.println("Enter your password");
				String pass = kbd.next();
				
					if(user.equals(uname) && pass.equals(pword)) {
						showEmployeeMenu();
					} else {
						System.out.println("Incorrect Password");
						showMenu();
					}				
				break;
				
			case 2:
				Serialization c = new Serialization();

				List<Car> client = c.readCarList("customers.txt");
				Object buyer = client;
				work = String.valueOf(buyer);
				String[] cl = work.split("[^a-zA-Z']+");
				uname = cl[1];
			    pword = cl[2];

				System.out.println("Enter your user name");
				String usa = kbd.next();
				System.out.println("Enter your password");
				String pas = kbd.next();
				
				if(usa.equals(uname) && pas.equals(pword)) {
					showCustomerMenu();
					
				} else {
					System.out.println("Incorrect Password");
					showMenu();
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
	 * Shows the employee menu
	 */
	public static void showEmployeeMenu() {
		
		System.out.println("\n" + "------Enter Number of Choice------");
		System.out.println("(1) View cars on the lot");
		System.out.println("(2) View car offers");
		System.out.println("(3) Add a car to the lot");
		System.out.println("(4) Remove a car from the lot");
		System.out.println("(5) Add an employee");
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
			} else if (emch == 5) {
				Employee e = new Employee();
				e.addEmployee();
				showEmployeeMenu();
			} else if (emch == 6) {
				showMenu();
			} else {
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
		System.out.println("(4) Go Back to previous");
		
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
			Customer c =  new Customer();
			c.viewPayments();
			showCustomerMenu();
		} else if (csch == 4) {
			showMenu();
		} else {
			System.out.println("Invalid Number");
			showCustomerMenu();
		}

	}
}
