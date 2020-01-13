/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.UI;

import java.util.List;

/**
 * The Driver class simply runs the 
 * showMenu() method with initializes
 * the entire program.
 */

import org.apache.log4j.Logger;

import com.shop.model.Car;
import com.shop.model.Employee;
import com.shop.service.CarService;
import com.shop.service.EmployeeService;

public class Driver  {
	
	final static Logger logger = Logger.getLogger("log: ");
	
	public static void main(String[] args) {
		System.out.println("insert into \"car\" values ("+ "12" + "," + "23" + "," + "adfad" + "," + "adfasdf" + ")");
		//shows all employees
		EmployeeService es = new EmployeeService();
		List<Employee> list = es.getList();
		String liststr = list.toString().replace(",", "");
		System.out.println(liststr);
		
		//shows all cars
		
		
		//
		
		//Shows the main menu
		try {
		Display.showMenu();
		System.exit(0);
		} catch(Exception e) {
			logger.log(null, "Exception happen", e);
		}
	}
	

}

