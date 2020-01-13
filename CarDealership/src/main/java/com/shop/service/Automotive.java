package com.shop.service;

import java.util.List;

public interface Automotive<C> {
	
List <C> FindAll();
	
	C findById(int id);
	
	List<C> findAllByTitle();
	
	void insert(C c);
	
	void delete(C c);
}
