package com.mastercard.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mastercard.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO{

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query...sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", 
											Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();		
		
		//return the results
		return customers;		
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomers(int theId) {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

}
