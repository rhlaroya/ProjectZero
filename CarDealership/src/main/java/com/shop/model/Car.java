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
	
	/**
	 * No args constructor
	 */
	public Car() {
		super();
	}
	
	/**
	 * Car consructor
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
		return "USD " + price + " [make=" + make + ", name=" + name + ", specs=" + specs + " Serial No:" + carid +"]\n";
	}
	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"car\" order by \"carid\" asc";
			List<Car> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Car(rs.getDouble(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
			}
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findAllByTitle() {
		// TODO Auto-generated method stub
		return null;
	}
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
	@Override
	public void delete(Object c) {
		try {
		Connection conn = ConnectionUtil.connect();
		System.out.println("Enter the car id to remove car from lot");	
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
