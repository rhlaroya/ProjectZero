package com.shop.service;

import java.util.List;

public interface Payable<P> {
	public abstract void revealPayment();
	List <P> FindAll();
	
	void insert(P p);
	
	void delete(P p);
}
