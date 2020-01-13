/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.service;

import java.io.FileNotFoundException;

public abstract class User {
	
	String user_name;
	transient String password;
	String firstName;
	String lastName; 
	
	/*
	 * Employee methods
	 */
//	public abstract void addCar();
//	public abstract void removeCar();
//	public abstract void viewOffer();
	
	//both
	public abstract void viewCar() throws FileNotFoundException;
	
	/*
	 * Customer methods
	 */
//	public abstract void makeOffer();
//	public abstract void viewPayments();
//	public abstract void register();

}
