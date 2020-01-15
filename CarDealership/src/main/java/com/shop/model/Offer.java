package com.shop.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The Offer class creates a blueprint for 
 * offers that is to be created by a customer.
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shop.UI.Display;
import com.shop.data.ConnectionUtil;
import com.shop.data.Serialization;
import com.shop.service.Offerable;

public class Offer implements Offerable {
	
	/**
	 * Instance fields
	 */
	private int monthsPay;
	private int loanRate;
	Scanner kbd = new Scanner(System.in);
	int[] key = new int[1];
	
	static ArrayList<String> pay = new ArrayList<>();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());


	
	
	/**
	 * Getters and Setters
	 */
	public int getMonthsPay() {
		return monthsPay;
	}

	public void setMonthsPay(int monthsPay) {
		this.monthsPay = monthsPay;
	}

	public double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(int loanRate) {
		this.loanRate = loanRate;
	}

	
	
	/**
	 * No args constructor
	 */
	public Offer() {
		super();
	}
	
	

	/**
	 * Offer constructor
	 */
	public Offer(int monthsPay, int loanRate, Scanner kbd) {
		super();
		this.monthsPay = monthsPay;
		this.loanRate = loanRate;
		this.kbd = kbd;
	}
	
	public Offer(int offer, String time, int offerorid, int loanRate, int monthsPay, boolean status) {
		super();
		int offr = offer;
		String tm = time;
		int offrrd = offerorid;
		this.loanRate = loanRate;
		this.monthsPay = monthsPay;
		boolean stts = status;
		
	}
	

	/**
	 * This method show the functionality
	 * to create an offer in the customer menu
	 * this method generates a payment based on
	 * the offer of the customer. 
	 */
	public void revealOffer() {
		System.out.println("CREATING OFFER FOR: ");
		Serialization s = new Serialization();
		List<Car> cars = s.readCarList("cars.txt");
		
		for(Car c: cars) {
			System.out.println(c.toString());
		}
		
		String expectedNumber = "";
		String[] ar = cars.toString().split("");
			
		for (int i = 0; i < ar.length;i++) {
			if(Character.isDigit(cars.toString().charAt(i))) {
				expectedNumber += ar[i-0]; 
			} 
		}
		
		StringBuilder sb = new StringBuilder(expectedNumber);
		sb.deleteCharAt(expectedNumber.length() - 1);
		Offer of = new Offer();
		Double price = Double.parseDouble(sb.toString());
		System.out.println("-------------------------------------------------");
		System.out.println("How many months do you intend to pay for the car?");
		of.setMonthsPay(kbd.nextInt());
		System.out.println("What percentage are you paying for the down payment?");
		System.out.println("---------------Minimum of 30 percent---------------");
		of.setLoanRate(kbd.nextInt());
		if(of.getLoanRate() < 30) {
			System.out.println("Sorry you are ineligible of making an offer");
		} else if (of.getLoanRate() >= 30 && of.getLoanRate() <= 100) {
			double down = price*(of.getLoanRate()/100.0f);
			System.out.println("Your down payment is: " + "USD " + down);
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double down = price*(of.getLoanRate()/100.0f);
        double mpay = (price - down) / of.getMonthsPay();
        double fmpay = (mpay * .04)  + mpay;
        pay.add(df.format(fmpay));
        System.out.println("The interest rate is 4%");
        System.out.println("Your monthly payment is: " + "USD " + df.format(fmpay));
		System.out.println("Submit your offer?");
		System.out.println("(1) Yes");
		System.out.println("(2) No");
		int c;
		c = kbd.nextInt();
		if(c == 1) {
			System.out.println("Your offer has been sent to an employee.");
			Object offa = of.toString();
			String file = "offers.txt";
			s.writeOffer(file, offa);
			Display.showCustomerMenu();
		} else if (c==2) {
			Display.showCustomerMenu();
		} else {
			Display.showCustomerMenu();
		}
	}

	int numb;
	boolean estado;
	/**
	 * toString() creates the format for printing
	 * the offer for the employee menu
	 */
	@Override
	public String toString() {
		return "Offer [customerid=" + Arrays.toString(key) + " timestamp=" + timestamp + ", " + "customerid " + numb + " loanRate=" + loanRate + " monthsPay=" + monthsPay + " accepted:" + estado +  "]\n";
	}

	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"offer\" where \"status\" = 'false'";
			List<Offer> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			//int offer, String time, int offerorid, int loanRate, int monthsPay, boolean status
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Offer(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getBoolean(6)));
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
			System.out.println(timestamp);
			Offer ofs = new Offer();
			Connection conn = ConnectionUtil.connect();
			System.out.println("CREATING CAR OFFER:");
			System.out.println("Enter the car id you would like to make an offer to:");
			int cid = kbd.nextInt();
			String sql = "select * from \"car\" where \"carid\" = " +  "" + cid +";";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("Making an offer for:");
			double price = 0;
			while(rs.next()) {
			Car sale =new Car(rs.getDouble(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			price = rs.getDouble(1);
			System.out.println(sale.toString());
			}
			
			System.out.println("-------------------------------------------------");
			System.out.println("How many months do you intend to pay for the car?");
			ofs.setMonthsPay(kbd.nextInt());
			System.out.println("What percentage are you paying for the down payment?");
			System.out.println("---------------Minimum of 30 percent---------------");
			ofs.setLoanRate(kbd.nextInt());
			if(ofs.getLoanRate() < 30) {
				System.out.println("Sorry you are ineligible of making an offer");
			} else if (ofs.getLoanRate() >= 30 && ofs.getLoanRate() <= 100) {
				double down = price*(ofs.getLoanRate()/100.0f);
				System.out.println("Your down payment is: " + "USD " + down);
			}
			DecimalFormat df = new DecimalFormat("0.00");
			double down = price*(ofs.getLoanRate()/100.0f);
	        double mpay = (price - down) / ofs.getMonthsPay();
	        double fmpay = (mpay * .04)  + mpay;
	        pay.add(df.format(fmpay));
	        System.out.println("The interest rate is 4%");
	        System.out.println("Your monthly payment is: " + "USD " + df.format(fmpay));
	        System.out.println("-----------------------------------------------");
			System.out.println("Enter your last name for confirmation:");
			String user = kbd.next();
				try {					
				conn = ConnectionUtil.connect();
				sql = "select \"customerid\" from \"customer\" where \"lastname\" = " +  "'" + user +"';";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					key[0] = rs.getInt(1);
				}
					//
					
				sql = "insert into offer (timestamp, offeror, down, months) values('"+ timestamp + "'," + key[0]  + "," + ofs.getLoanRate() + "," + ofs.getMonthsPay() + ")";					ps = conn.prepareStatement(sql);
				int ins = ps.executeUpdate();
				System.out.println("Your offer has been sent to an employee.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				

			  }catch (SQLException e) {
				e.printStackTrace();
			} 
		
	}

	@Override
	public void delete(Object c) {
		// TODO Auto-generated method stub
		
	}
}

