package com.shop.service;

import java.util.List;

public interface Automotive<C> {
	
List <C> FindAll();
	
	void insert(C c);
	
	void delete(C c);
}
