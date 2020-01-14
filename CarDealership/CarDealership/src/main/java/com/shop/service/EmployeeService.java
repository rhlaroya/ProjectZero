package com.shop.service;

import java.util.List;

import com.shop.model.Employee;

public class EmployeeService {
	
private Employee em;
	
	public EmployeeService() {
		em = new Employee();
	}
	
	public List<Employee> getList(){
		return em.FindAll();
	}
}
