/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.model;

/**
 * Customer class to create a 
 * customer object. Allows a customer to 
 * make offers, view cars from the lot, 
 * register for a new account, and view
 * the payments for an accepted offer.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.shop.data.ConnectionUtil;
import com.shop.service.CarService;
import com.shop.service.PaymentService;
import com.shop.service.Serviceable;
import com.shop.service.User;

public class Customer extends User implements Serializable, Serviceable {
	
	/**
	 * Instance fields
	 */
	private static final long serialVersionUID = 1L;
	private String user_name;
	private String password;
	private String firstName;
	private String lastName;
	private double money_balance;
	Scanner kbd = new Scanner(System.in);
	
	/**
	 * Getters and Setters
	 * @return
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
	public double getMoney_balance() {
		return money_balance;
	}
	public void setMoney_balance(double money_balance) {
		this.money_balance = money_balance;
	}
	
	/**
	 * No args constructor
	 */
	public Customer() {
		super();
	}
	
	/**
	 * 
	 * @param user_name
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param money_balance
	 */
	public Customer(String user_name, String password, String firstName, String lastName, double money_balance) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.money_balance = money_balance;
	}
	
	/**
	 * toString print the customer information
	 */
	@Override
	public String toString() {
		return user_name + " " + password + " " + firstName + " " + lastName + " " +money_balance;
	}
	
	/**
	 * Allows the customer to view cars
	 * from the lot as much as an
	 * employee can
	 */
	@Override
	public void viewCar() {
		CarService cs = new CarService();
		List<Car> lists = cs.getList();
		String liststrs = lists.toString().replace(",", "");
		System.out.println(liststrs);
		
	}
	
	/**
	 * Calls the revealOffer() method
	 * from the offer class to make an 
	 * offer that will be sent to the 
	 * employee class
	 */
	@Override
	public void makeOffer() {
		Offer off = new Offer();
		off.insert(off);
	}
	
	/**
	 * The customer will be able to view
	 * the payments after a created offer
	 * is accepted by an employee
	 */
	@Override
	public void viewPayments() {
		PaymentService ps = new PaymentService();
		List<Payment> mypay = ps.getList();
		String liststrs = mypay.toString().replace(",", "");
		System.out.println(liststrs);
	}
	
	/**
	 * Allows a user to register for a customer
	 * account
	 */
	@Override
	public void register() {
		try {
			Customer cu = new Customer();
			Connection conn = ConnectionUtil.connect();
			System.out.println("CUSTOMER REGISTRATION");
			System.out.println("Please enter the following information: ");
			System.out.println("Username: ");
			cu.setUser_name(kbd.next());
			System.out.println("Password: ");
			cu.setPassword(kbd.next());
			System.out.println("First name: ");
			cu.setFirstName(kbd.next());
			System.out.println("Last name: ");
			kbd.nextLine();
			cu.setLastName(kbd.nextLine());
			System.out.println("Money Balance: ");
			cu.setMoney_balance(kbd.nextDouble());
			String sql = "insert into \"customer\" values('"+ cu.getUser_name() + "','" + cu.getPassword() + "','" + cu.getFirstName() + "','" + cu.getLastName() + "'," + cu.getMoney_balance() + ")";
			PreparedStatement ps = conn.prepareStatement(sql);
			int ins = ps.executeUpdate();
			System.out.println("Thank you! Customer Account Created");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Puts all customers into a list
	 */
	public List<Customer> FindAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Allows the adding of a new 
	 * customer account
	 */
	@Override
	public void insert(Object c) {
		try {
			Customer cu = new Customer();
			Connection conn = ConnectionUtil.connect();
			System.out.println("CUSTOMER REGISTRATION");
			System.out.println("Please enter the following information: ");
			System.out.println("Username: ");
			cu.setUser_name(kbd.next());
			System.out.println("Password: ");
			cu.setPassword(kbd.next());
			System.out.println("First name: ");
			cu.setFirstName(kbd.next());
			System.out.println("Last name: ");
			kbd.nextLine();
			cu.setLastName(kbd.nextLine());
			System.out.println("Money Balance: ");
			cu.setMoney_balance(kbd.nextDouble());
			String sql = "insert into \"customer\" values("+ cu.getUser_name() + ",'" + cu.getPassword() + "','" + cu.getFirstName() + "','" + cu.getLastName() + "," + cu.getMoney_balance() + "')";
			PreparedStatement ps = conn.prepareStatement(sql);
			int ins = ps.executeUpdate();
			System.out.println("Thank you! Customer Account Created");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Deletes a customer
	 */
	@Override
	public void delete(Object c) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Finds a car owned by a customer
	 */
	@Override
	public void viewOwnedCars() {
		CarService cs = new CarService();
		List<Car> mycar = cs.getList();
		String liststrs = mycar.toString().replace(",", "");
		System.out.println(liststrs);	
	}
	

	
}
