/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Customer Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Customer;
import com.lillygourmet.cash.register.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerController {

	private static Logger _log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService CustomerService;

	@GetMapping("api/Customers")
	public ResponseEntity<List<Customer>> retrieveAllCustomers() {
		_log.info("retrieve all Customers controller...!");
		List<Customer> Customers = CustomerService.retrieveAllCustomers();
		return new ResponseEntity<List<Customer>>(Customers, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("api/Customers/{id}")
	public ResponseEntity<Customer> retrieveCustomerById(@PathVariable Long id) {

		_log.info("retrieve Customer controller...!");
		Customer Customer = CustomerService.getCustomer(id);
		return new ResponseEntity<Customer>(Customer, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {

		_log.info("create Customer controller...!");
		Customer savedCustomer = CustomerService.createCustomer(Customer);
		return new ResponseEntity<Customer>(savedCustomer, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Customers/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteCustomer(@PathVariable Long id) throws Exception {

		_log.info("delete Customer controller...!");
		CustomerService.deleteCustomer(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Customers")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer Customer) {

		_log.info("update Customer controller...!");
		Customer updatedCustomer= CustomerService.updateCustomer(Customer);
		return new ResponseEntity<Customer>(updatedCustomer, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
