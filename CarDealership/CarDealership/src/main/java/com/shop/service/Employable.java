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
	
	
	List <E> FindAll();
	
	E findById(int id);
	
	List<E> findAllByTitle();
	
	void insert(E e);
	
	void delete(E e);
}
