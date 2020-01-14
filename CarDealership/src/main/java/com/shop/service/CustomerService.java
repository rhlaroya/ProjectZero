package com.shop.service;

import java.util.List;

import com.shop.model.Customer;

public class CustomerService {
private Customer cu;
	
	public CustomerService() {
		cu = new Customer();
	}
	
	public List<Customer> getList(){
		return cu.FindAll();
	}
}
