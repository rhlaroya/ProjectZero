/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.UI;

/**
 * The Driver class simply runs the 
 * showMenu() method with initializes
 * the entire program.
 */

import org.apache.log4j.Logger;

public class Driver  {
	
	final static Logger logger = Logger.getLogger("log: ");
	
	public static void main(String[] args) {
		
		try {
		Display.showMenu();
		System.exit(0);
		} catch(Exception e) {
			logger.log(null, "Exception happen", e);
		}
	}
	

}

