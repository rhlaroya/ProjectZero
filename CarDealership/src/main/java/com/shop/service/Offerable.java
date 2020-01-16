package com.shop.service;

import java.util.List;

public interface Offerable <O> {
	public void revealOffer();
	List <O> FindAll();
	
	void insert(O c);
	
	void delete(O c);
}
