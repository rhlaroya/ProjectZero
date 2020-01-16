package com.shop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.UI.Display;
import com.shop.data.Serialization;
import com.shop.model.Car;
import com.shop.model.Customer;
import com.shop.model.Payment;

public class CarDealershipTest {
	
	private final static Logger LOGGER = Logger.getLogger(Display.class.getName());
	
	static Payment p;
	static Serialization s;
	static Customer c;
	static Display d;
	
	@BeforeClass
	public static void beforeAll() {
		p = new Payment();
		s = new Serialization();
		c = new Customer();
		d = new Display();
		LOGGER.info("Initialized Object");
	}
	
//	@Test
//	public void testViewCar() throws FileNotFoundException {
//		Car car = new Car();
//		car.setMake("BMW");
//		car.setName("i9");
//		car.setPrice(120000.00);
//		car.setSpecs("Dream car");
//		
//		List<Car> cars = new ArrayList<>();
//		cars.add(car);
//		assertNotNull(cars);
//		LOGGER.info("FileNotFoundException");
//	}
//	
//	@Test
//	public void testRegistration() {
//		
//		c.setUser_name("admin");
//		c.setPassword("password");
//		c.setFirstName("Arthur");
//		c.setLastName("Fleck");
//		c.setMoney_balance(1000000.00);
//		List<Object> customers = new ArrayList<>();
//		customers.add(c);
//	}
	
	
	@Test
	public void testViewPayments() {

		p.revealPayment();
		assertNotNull("USD" + s.readPayment("payments.txt"));
	}
	
	@Test
	public void testEmployeeExist() {
		assertNotNull(s.readEmployeeList("employees.txt"));
	}
	
	@Test
	public void testCustomerExist() {
		assertNotNull(s.readObjectList("customers.txt"));
	}
	
	@Test
	public void testOffersExist() {
		assertNotNull(s.readOffer("offers.txt"));
	}
	
	@Test
	public void deleteCar() {
		File file = new File("C:\\Users\\rhlar\\Desktop\\git repos\\1912dec16java\\regae_laroya_code\\CarDealership\\cars.txt");
		file.delete();
		assertNull(s.readCarList("cars.txt"));
		LOGGER.info("Exception because file is deleted");
	}
	
	
}
