package com.mastercard.dao;

import java.util.List;

import com.mastercard.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomers(int theId);
	
}
