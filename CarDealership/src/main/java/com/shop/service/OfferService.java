package com.shop.service;

import java.util.List;

import com.shop.model.Offer;

public class OfferService {
private Offer ofr;
	
	public OfferService() {
		ofr = new Offer();
	}
	
	public List<Offer> getList(){
		return ofr.FindAll();
	}
}
