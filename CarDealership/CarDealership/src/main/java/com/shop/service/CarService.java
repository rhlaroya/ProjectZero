package com.shop.service;

import java.util.List;

import com.shop.model.Car;

public class CarService {
	
private Car ca;
	
	public CarService() {
		ca = new Car();
	}
	
	public List<Car> getList(){
		return ca.FindAll();
	}
}
