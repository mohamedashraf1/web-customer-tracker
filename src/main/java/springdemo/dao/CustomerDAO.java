package springdemo.dao;

import java.util.List;

import springdemo.entity.Customer;

// DAO is data access object
public interface CustomerDAO {
	
	public List<Customer> getCustomers();
}
