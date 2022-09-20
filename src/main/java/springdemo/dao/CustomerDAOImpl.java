package springdemo.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import springdemo.entity.Customer;


// @Repository Annotation is a specialization of @Component annotation
// which is used to indicate that the class provides the mechanism for
// storage, retrieval, update, delete and search operation on objects.
// ......
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by first name
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer order by firstName",
											Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now read from database using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		
		return theCustomer;
	}

	
}
