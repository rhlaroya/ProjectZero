/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.model;

/**
 * This class implements serializable to serialize the data 
 * into a text file called txt.cars. This class serves as 
 * the blueprint for the car object.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.data.ConnectionUtil;
import com.shop.service.Automotive;

public class Car implements Automotive, Serializable {
	
	/**
	 * Instance fields
	 */
	private static final long serialVersionUID = 1L;
	private double price;
	private String make;
	private String name;
	private String specs;
	private int carid;
	private boolean status;
	Scanner kbd = new Scanner(System.in);
	
	/**
	 * Getters and Setters
	 */
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * No args constructor
	 */
	public Car() {
		super();
	}
	
	/**
	 * Car constructor
	 */
	public Car(double price, String make, String name, String specs, int carid,boolean status) {
		super();
		this.price = price;
		this.make = make;
		this.name = name;
		this.specs = specs;
		this.carid = carid;
		this.status = status;
	}
	
	/**
	 * Car insertion constructor
	 */
	public Car(double price, String make, String name, String specs, int carid) {
		super();
		this.price = price;
		this.make = make;
		this.name = name;
		this.specs = specs;
		this.carid = carid;
	}
	
	/**
	 * toString() to print the car format
	 */
	@Override
	public String toString() {
		return "USD " + price + " [make=" + make + ", name=" + name + ", specs=" + specs + ", Serial No:" + carid + ", owned="+status+"]\n";
	}
	
	/**
	 * Grabs all the cars and puts them into a list
	 */
	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"car\" order by \"carid\" asc";
			List<Car> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Car(rs.getDouble(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getBoolean(6)));
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Finds the cars owned by a customer
	 * then adds it into a list
	 */
	public void FindMine() {
		try {
			System.out.print("Enter your username for confirmation: ");
			String cmtn = kbd.next();
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"car\" where ownerid = (select customerid from customer where user_name = '"+ cmtn +"') and sold = true;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			Car akin = new Car();
			while(rs.next()) {
			akin =new Car(rs.getDouble(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getBoolean(6));
			}
			System.out.println(akin);	
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method permits adding a car to the 
	 * lot by getting the values and inserting
	 * into the database
	 */
	@Override
	public void insert(Object c) {
		try {
		Car ca = new Car();
		Connection conn = ConnectionUtil.connect();
		System.out.println("Enter the car make");
		ca.setMake(kbd.next());
		System.out.println("Enter the model");
		ca.setName(kbd.next());
		System.out.println("Enter the price of the car");
		ca.setPrice(kbd.nextDouble());
		System.out.println("Enter the specs");
		kbd.nextLine();
		ca.setSpecs(kbd.nextLine());
		String sql = "insert into \"car\" values("+ ca.getPrice() + ",'" + ca.getMake() + "','" + ca.getName() + "','" + ca.getSpecs() + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		int ins = ps.executeUpdate();
		System.out.println("Car successfully placed on the lot");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This delete method deletes cars from
	 * the database by using the carid
	 */
	@Override
	public void delete(Object c) {
		try {
		Connection conn = ConnectionUtil.connect();
		System.out.println("Enter the car serial no. to remove car from lot");	
		String carno = kbd.nextLine();
		String sql = "delete from \"car\" where carid = "+ carno +"";
		PreparedStatement ps = conn.prepareStatement(sql);
		int rs = ps.executeUpdate();
		System.out.println("Car successfully removed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
