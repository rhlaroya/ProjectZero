/**
 * @author Regae Laroya
 * Project 0
 */
package com.shop.data;

/**
 * Serialization class provides an IOStream
 * to enable the object classes to read and
 * write objects into and from a text file.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.shop.model.Car;
import com.shop.model.Customer;
import com.shop.model.Employee;

public class Serialization {
	
	public void writeCustomerList(String file, List<Customer> customers) {
		try(ObjectOutputStream os  = new ObjectOutputStream(
				new FileOutputStream(file))) {
			
			os.writeObject(customers);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public  List<Customer> readObjectList(String file) {
		try(ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file))){
			
			return (List<Customer>)ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void writeEmployeeList(String file, List<Employee> employees) {
		try(ObjectOutputStream os  = new ObjectOutputStream(
				new FileOutputStream(file))) {
			
			os.writeObject(employees);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public  List<Employee> readEmployeeList(String file) {
		try(ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file))){
			
			return (List<Employee>)ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void writeCarList(String file, List<Car> cars) {
		try(ObjectOutputStream os  = new ObjectOutputStream(
				new FileOutputStream(file))) {
			
			os.writeObject(cars);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> readCarList(String file) {
		
		try(ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file))){
			
			return (List<Car>)ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void writeOffer(String file, Object o) {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(o);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object readOffer(String file) {
	try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))){
		
		return is.readObject();
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return null;
}
	
	public void writePayment(String file, Object o) {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(o);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object readPayment(String file) {
	try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))){
		
		return is.readObject();
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return null;
}
	
}
