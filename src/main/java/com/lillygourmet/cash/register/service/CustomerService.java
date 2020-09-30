/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Customer Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Customer;
import com.lillygourmet.cash.register.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository CustomerRepository;


	public List<Customer> retrieveAllCustomers() {
		return CustomerRepository.findAll();
	}

	public Customer getCustomer(Long id) {
		return CustomerRepository.getOne(id);
	}

	public Customer createCustomer(Customer Customer) {
		return CustomerRepository.save(Customer);
	}
	
	@Transactional
	public void deleteCustomer(Long id) throws Exception {

		Customer Customer = CustomerRepository.getOne(id);
		if (Customer != null) {
			CustomerRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Customer updateCustomer(Customer Customer) {
		Customer editedCustomer = CustomerRepository.getOne(Customer.getId());
		editedCustomer.setEmail(Customer.getEmail());
		editedCustomer.setFirstName(Customer.getFirstName());
		editedCustomer.setLastName(Customer.getLastName());
		editedCustomer.setRoles(Customer.getRoles());
		editedCustomer.setAdress(Customer.getAdress());
		editedCustomer.setBdate(Customer.getBdate());
		editedCustomer.setSexe(Customer.getSexe());
		editedCustomer.setPhone(Customer.getPhone());
		editedCustomer.setPassword(Customer.getPassword());
		editedCustomer.setCode(Customer.getCode());
		return CustomerRepository.save(editedCustomer);
	}
}
