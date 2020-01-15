package com.shop.service;

import java.util.List;

public interface Offerable <O> {
	public void revealOffer();
	List <O> FindAll();
	
	O findById(int id);
	
	List<O> findAllByTitle();
	
	void insert(O c);
	
	void delete(O c);
}
