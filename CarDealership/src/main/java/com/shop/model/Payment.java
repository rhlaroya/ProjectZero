package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * This class serves as the blueprint 
 * for reading and writing the payment
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.shop.data.ConnectionUtil;
import com.shop.service.Payable;

public class Payment implements Payable {
	
	/**
	 * Instance fields
	 */
	private Timestamp timepay;
	private double monthly;
	private double paid;
	private double remaining;
	private int payerid;
	private int carid;
	Scanner kbd = new Scanner(System.in);
	
	/**
	 * toString creates a format for printing 
	 * the payments
	 */
	@Override
	public String toString() {
		return "Payment [monthly=" + monthly + ", timepay=" + timepay + ", paid=" + paid + ", remaining=" + remaining
				+ ", payerid=" + payerid + ", carid=" + carid + "]\n";
	}

	/**
	 * No args constructor
	 */
	public Payment() {
		super();
	}
	
	/**
	 * @param monthly
	 * @param timepay
	 * @param paid
	 * @param remaining
	 * @param payerid
	 * @param carid
	 */
	public Payment(double monthly, Timestamp timepay, double paid, double remaining, int payerid, int carid) {
		super();
		this.monthly = monthly;
		this.timepay = timepay;
		this.paid = paid;
		this.remaining = remaining;
		this.payerid = payerid;
		this.carid = carid;
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public Timestamp getTimepay() {
		return timepay;
	}

	public void setTimepay(Timestamp timepay) {
		this.timepay = timepay;
	}

	public double getMonthly() {
		return monthly;
	}

	public void setMonthly(double monthly) {
		this.monthly = monthly;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getRemaining() {
		return remaining;
	}

	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}
	
	public int getPayerid() {
		return payerid;
	}

	public void setPayerid(int payerid) {
		this.payerid = payerid;
	}
	
	/**
	 * This method prints the payment generated
	 * in the revealOffer() method. 
	 */
	public void revealPayment(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date date = new Date();
		System.out.print("Your payment due as of:");
		System.out.println(dateFormat.format(date) + " is: "); //2016/11/16 12:08:43
		
	}
	
	/**
	 *DELETE THIS CONSTRUCTOR IT IS USELESS!
	 */
	public void pGeneration(double monthly, Timestamp timepay, double paid, double remaining, int payerid,int carid) {
		Payment pp = new Payment();
		pp.setMonthly(monthly);
		pp.setTimepay(timepay);
		pp.setPaid(paid);
		pp.setRemaining(remaining);
		pp.setPayerid(payerid);
		pp.setCarid(carid);
		
	}

	/**
	 * Gets the payments from the db
	 * then add them into a list for
	 * easy iteration
	 */
	@Override
	public List FindAll() {
		try {
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"payment\" order by \"payerid\" asc";
			List<Payment> list = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Payment(rs.getDouble(1),rs.getTimestamp(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6)));
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Prints the payments of a customer
	 * @return
	 */
	public void FindOwned() {
		try {
			
			System.out.print("Enter your username for confirmation: ");
			String pe = kbd.next();
			Connection conn = ConnectionUtil.connect();
			String sql = "select * from \"payment\" where payerid = " + "(select customerid from customer where user_name = ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pe);
			ResultSet rs = ps.executeQuery();
			
			Payment pymt = null;
			while(rs.next()) {
			pymt = new Payment(rs.getDouble(1),rs.getTimestamp(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6));
			}
			System.out.println(pymt.toString());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		//		try {
//			List<Payment> mypays = new ArrayList<>();
//			
//			System.out.print("Enter your username for confirmation: ");
//			String pe = kbd.next();
//			Connection conn = ConnectionUtil.connect();
//			String sql = "select * from \"payment\" where payerid = " + "(select customerid from customer where user_name = ?) and sold = true";
//
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, pe);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				mypays.add(new Payment(rs.getDouble("monthly"),rs.getTimestamp("timepay"),rs.getDouble("paid"),rs.getDouble("remaining"),rs.getInt("payerid"),rs.getInt("carid")));
//			}
//			return mypays;		
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
	}
	
	/**
	 * Inserts a payment into the db
	 */
	@Override
	public void insert(Object p) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Deletes a payment that has been
	 * generated along with the offer
	 */
	@Override
	public void delete(Object p) {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Enter the offer id to reject");	
			String carno = kbd.nextLine();
			String sql = "delete from \"offer\" where offerid = "+ carno +"";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rs = ps.executeUpdate();
			System.out.println("Offer successfully rejected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Generates payment when an offer has been
	 * accepted by an employee
	 * @param p
	 */
	public void accept(Object p) {
		try {
			Connection conn = ConnectionUtil.connect();
			System.out.println("Enter the offer id to accept");	
			String carno = kbd.nextLine();
			String sql = "delete from \"payment\" where payerid != "+ carno +" AND carrid = (SELECT carid where )";
			PreparedStatement ps = conn.prepareStatement(sql);
			int rs = ps.executeUpdate();
			System.out.println("Offer successfully accepted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
