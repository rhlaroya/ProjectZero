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
	private int offerid;
	private int monthsPay;
	private int loanRate;
	private Timestamp times;
	private int offerorid;
	private boolean status;
	
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
	
	public int getOfferid() {
		return offerid;
	}

	public void setOfferid(int offerid) {
		this.offerid = offerid;
	}
	
	public void setTimes(Timestamp times) {
		this.times = times;
	}
	
	public int getOfferorid() {
		return offerorid;
	}

	public void setOfferorid(int offerorid) {
		this.offerorid = offerorid;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Timestamp getTimes() {
		return times;
	}
	
	public void setTimes1(Timestamp times) {
		this.times = times;
	}

	Scanner kbd = new Scanner(System.in);
	int[] key = new int[1];
	static ArrayList<String> pay = new ArrayList<>();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	/**
	 * No args constructor
	 */
	public Offer() {
		super();
	}	

	/**
	 * Offer constructor
	 */
	public Offer(int offerid, Timestamp times, int offerorid, int loanRate, int monthsPay, boolean status) {
		super();
		this.offerid = offerid;
		this.times = times;
		this.offerorid = offerorid;
		this.loanRate = loanRate;
		this.monthsPay = monthsPay;
		this.status = status;		
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
		return "Offer [offerid=" + offerid + " timestamp=" + timestamp + ", " + "customerid=" + offerorid + " loanRate=" + loanRate + " monthsPay=" + monthsPay + " accepted:" + estado + "]\n";
	}

	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"offer\" where \"sold\" = 'false'";
			List<Offer> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Offer(rs.getInt(1), rs.getTimestamp(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getBoolean(6)));
			}
			return list;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	/**
	 * This method allows offer creation 
	 * by creating a car object 
	 */
	@Override
	public void insert(Object c) {
		try {
			System.out.println(timestamp);
			Offer ofs = new Offer();
			ofs.setTimes1(timestamp);
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
			System.out.println("Enter your username for confirmation:");
			String user = kbd.next();
				try {					
				conn = ConnectionUtil.connect();
				sql = "select \"customerid\" from \"customer\" where \"user_name\" = " +  "'" + user +"';";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					key[0] = rs.getInt(1);
					ofs.setOfferorid(key[0]);
				}	
				sql = "insert into offer (timestamp, offeror, down, months, carid) values('"+ ofs.getTimes() + "'," + ofs.getOfferorid()  + "," + ofs.getLoanRate() + "," + ofs.getMonthsPay() + ","+ cid + ")";					
				ps = conn.prepareStatement(sql);
				int ins = ps.executeUpdate();
				System.out.println("Your offer has been sent to an employee.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				double remaining = price - down;
				Payment pp = new Payment();
				pp.pGeneration(fmpay,times,down,remaining,ofs.getOfferid(),cid);
				conn = ConnectionUtil.connect();
				sql = "insert into \"payment\" values("+ df.format(fmpay) + ",'" + ofs.getTimes() + "'," + down + ", " + remaining + "," + ofs.getOfferorid() + "," + cid + ")";
				ps = conn.prepareStatement(sql);
				int ins = ps.executeUpdate();
			  }catch (SQLException e) {
				e.printStackTrace();
			} 
	}
	
	/**
	 * Deletes an offer this is called
	 * when an employee rejects an offer
	 */
	@Override
	public void delete(Object c) {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Enter the offer Id to reject an offer");	
			String rem = kbd.nextLine();
			String sql = "delete from \"offer\" where offerid = "+ rem +"";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rs = ps.executeUpdate();
			System.out.println("Offer rejected successfully");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
	
	/**
	 * Updates the values of the car and the 
	 * offer when an offer get accepted by
	 * the employee
	 */
	public void accept(Object c) {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Enter the offer Id to accept an offer");	
			String rem = kbd.nextLine();
			String sql = "delete from \"offer\" where offerid != "+ rem +"AND offeror != (Select offeror from offer where offerid = "+rem+") "
					+ "and carid = (select carid from offer where offerid= "+rem+")";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rs = ps.executeUpdate();
			sql = "update car set ownerid = (select offeror from offer where carid = "+rem+") where carid = "+rem;
			ps = conn.prepareStatement(sql);
			rs = ps.executeUpdate();
			sql = "update offer set sold = true where carid = "+rem;
			ps = conn.prepareStatement(sql);
			rs = ps.executeUpdate();
			System.out.println("Offer accepted successfully other offers for the car have been rejected");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
}

