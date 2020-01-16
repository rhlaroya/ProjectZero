/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.service;

import java.util.List;

public interface Serviceable <C> {
	
	//make an offer to purchase a car
	public abstract void makeOffer();
	//view payments for a car
	public abstract void viewPayments();
	//registration for customer account by user
	public abstract void register();
	//allows user to view cars
	public abstract <C> void viewOwnedCars();
	
	List <C> FindAll();
	
	void insert(C c);
	
	void delete(C c);
}
