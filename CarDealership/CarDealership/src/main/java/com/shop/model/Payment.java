package com.shop.model;

/**
 * This class serves as the blueprint 
 * for reading and writing the payment
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
	
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
}
