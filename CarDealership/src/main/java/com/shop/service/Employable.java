/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.service;

import java.util.List;

/**
 * Interface for the Employee class
 * creates methods addCar(), 
 * removeCar(), viewOffer(),
 * and addEmployee()
 * @author rhlar
 *
 */

public interface Employable <E> {
	
	//add a car to the lot
	public abstract void addCar();
	//remove a car from the lot
	public abstract void removeCar();
	//view offers made by customers
	public abstract void viewOffer();
	//add Employee
	public abstract void addEmployee();
	//view payments for manager
	public abstract void viewAllPayments();
	//view all employees for manager
	public abstract void viewAllEmployees();
	
	List <E> FindAll();
	
	void insert(E e);
	
	void delete(E e);
}
