package com.shop.service;

import java.util.List;

import com.shop.model.Payment;

public class PaymentService {
private Payment pt;
	
	public PaymentService() {
		pt = new Payment();
	}
	
	public List<Payment> getList(){
		return pt.FindAll();
	}
}
