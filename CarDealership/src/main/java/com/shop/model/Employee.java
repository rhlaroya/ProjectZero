/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.model;

/**
 * This class creates an employee which implements methods
 * to view the cars on the lot, add a car on the lot,
 * remove a car from the lot, add an employee and the
 * ability to reject or accept and offer from a customer.
 */

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.data.ConnectionUtil;
import com.shop.service.CarService;
import com.shop.service.Employable;
import com.shop.service.EmployeeService;
import com.shop.service.OfferService;
import com.shop.service.PaymentService;
import com.shop.service.User;

public class Employee extends User implements Serializable, Employable{
	
	/**
	 * Employee instance fields
	 */
	private static final long serialVersionUID = 1L;
	private String user_name;
	private String password;
	private String firstName;
	private String lastName; 
	private int employeeid;
	
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	/**
	 * Initializing Lists to hold cars and the 
	 * employees. Creating a Scanner to acquire
	 * all the input from the users
	 */
	List<Car> cars = new ArrayList<>();
	List<Employee> employees = new ArrayList<>();
	transient Scanner kbd = new Scanner(System.in);
	String filename = "cars.txt";
	
	/**
	 * Getters and Setters
	 */
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * No args constructor
	 */
	public Employee() {
		super();
	}
	
	/**
	 * Employee constructor
	 */
	public Employee(String user_name, String password, String firstName, String lastName, int employeeid) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeid = employeeid;
	}
	
	/**
	 * toString() method used for authentication
	 * @return user_name
	 * @retrun password
	 */
	@Override
	public String toString() {
		return "Employee[" + user_name + " " + password + " " + firstName + " "+ lastName + " ID:" + employeeid + "]\n" ;
	}
	
	/**
	 * This method allows the users to view the
	 * cars on the lot. The cars are read from
	 * the cars.txt
	 */
	@Override
	public void viewCar() throws FileNotFoundException {
		CarService cs = new CarService();
		List<Car> lists = cs.getList();
		String liststrs = lists.toString().replace(",", "");
		System.out.println(liststrs);
	}
	
	/**
	 * Adds a car to the existing lot. Prompts
	 * the employee to enter the make, name,
	 * price, and the price of the car then
	 * stores it into a list.
	 */
	@Override
	public void addCar() {
		Car car = new Car();
		car.insert(car);
	}
	
	/**
	 * removeCar() prints the cars with their index
	 * beside them. The employee then select the 
	 * index of the car to remove from the lot
	 * then it gets deleted.
	 */
	@Override
	public void removeCar() {
		Car c = new Car();
		c.delete(c);
	}
	
	/**
	 * This method shows the offers made by the 
	 * customers after a client submits an offer
	 * The employee can choose to accept it or
	 * reject it. If the offer is accepted the
	 * payment shall be generated and written 
	 * to the payments.txt
	 */
	@Override
	public void viewOffer() {

		System.out.println("OFFERS: ");
		OfferService os = new OfferService();
		Offer ofa = new Offer();
		List<Offer> lists = os.getList();
		String liststrs = lists.toString().replace(",", "");
		System.out.println(liststrs);
		
		System.out.println("Would you like to Accept or Reject Offers?");
		System.out.println("(1) Accept");
		System.out.println("(2) Reject");
		int dec;
		dec = kbd.nextInt();
		if(dec==1) {
			Offer fr = new Offer();
			fr.accept(fr);
		} else if (dec == 2) {
			{
				Offer fr = new Offer();
				fr.delete(fr);
			}
		} else {
			System.out.println("Wrong input");
		}
	}

	/**
	 * The manager has the capability
	 * to add an employee. The existing employee
	 * will be prompted to enter the necessary
	 * information then creates a new employee
	 * which is written to the employees.txt
	 */
	@Override
	public void addEmployee() {
		
		Employee em = new Employee();
		em.insert(em);
		
	}
	
	/**
	 * This allows the manager to view all the payments
	 * generated in the system
	 */
	public void viewAllPayments() {
		PaymentService ps = new PaymentService();
		List<Payment> lists = ps.getList();
		String liststrs = lists.toString().replace(",", "");
		System.out.println(liststrs);
	}
	
	/**
	 * This queries the employees of the database
	 * assigns them as a Employee object then
	 * puts them into an array.
	 */
	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"employee\" order by \"employeeid\" asc";
			List<Employee> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Employee(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
			}
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			return null;
			
			
	}
	
	/**
	 * This method inserts created employee
	 * accounts into the database
	 */
	@Override
	public void insert(Object e) {
	try {
		Employee em = new Employee();
		Connection conn = ConnectionUtil.connect();
		System.out.println("ADDING NEW EMPLOYEE");
		System.out.println("Fill up the necessary information");
		System.out.println("Username: ");
		em.setUser_name(kbd.next());
		System.out.println("Password: ");
		em.setPassword(kbd.next());
		System.out.println("First name: ");
		em.setFirstName(kbd.next());
		System.out.println("Last name: ");
		kbd.nextLine();
		em.setLastName(kbd.nextLine());
		String sql = "insert into \"employee\" values('"+ em.getUser_name() + "','" + em.getPassword() + "','" + em.getFirstName() + "','" + em.getLastName() + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		int ins = ps.executeUpdate();
		System.out.println("A new employee has been added.");
		} catch (SQLException ei) {
			ei.printStackTrace();
		}	
	}
	
	/**
	 * Allows the manager to fire an employee
	 */
	@Override
	public void delete(Object e) {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Enter the ID number to fire an employee");	
			String employeeid = kbd.nextLine();
			String sql = "delete from \"employee\" where employeeid = "+ employeeid +"";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rs = ps.executeUpdate();
			System.out.println("Employee successfully removed");
			} catch (SQLException e1) {
				e1.printStackTrace();
		}
	}
	
	/**
	 * Grabs all employees from the database then
	 * puts them in a list to easily iterate through 
	 * it
	 */
	public void viewAllEmployees() {
		EmployeeService es = new EmployeeService();
		List<Employee> list = es.getList();
		String liststr = list.toString().replace(",", "");
		System.out.println(liststr);
	}


}
