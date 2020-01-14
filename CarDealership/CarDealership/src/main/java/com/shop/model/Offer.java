package com.shop.model;

/**
 * The Offer class creates a blueprint for 
 * offers that is to be created by a customer.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shop.UI.Display;
import com.shop.data.Serialization;

public class Offer {
	
	/**
	 * Instance fields
	 */
	private int monthsPay;
	private int loanRate;
	static ArrayList<String> pay = new ArrayList<>();
	
	
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

	
	Scanner kbd = new Scanner(System.in);
	
	/**
	 * No args constructor
	 */
	public Offer() {
		super();
	}
	
	/**
	 * toString() creates the format for printing
	 * the offer for the employee menu
	 */
	@Override
	public String toString() {
		Serialization s = new Serialization();
		List<Car> client = s.readCarList("customers.txt");
		Object buyer = client;
		String work = String.valueOf(buyer);
		String[] ar = work.split("[^a-zA-Z']+");
		String first = ar[3];
		String last = ar[4];
		return first + " " + last + "[Months to pay=" + monthsPay + ", Loan=" + loanRate + "%]";
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
}

