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
import com.lillygourmet.cash.register.repository.CustomerRepository;
import com.lillygourmet.cash.register.service.CustomerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CustomerController {

	private static Logger _log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService CustomerService;

	@Autowired
	private CustomerRepository customerRepository;


	@RequestMapping(value="api/Customers", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getAll() {
		List<Object[]> entityList = customerRepository.findUsersbyRole("ROLE_CUSTOMER");
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for(int i=0;i<entityList.size();i++){
			JSONObject entity = new JSONObject();
			entity.put("id", entityList.get(i)[0]);
			entity.put("adress",entityList.get(i)[1]);
			entity.put("bdate",entityList.get(i)[2]);
			entity.put("createdAt",entityList.get(i)[3]);
			entity.put("createdBy",entityList.get(i)[4]);
			entity.put("email",entityList.get(i)[5]);
			entity.put("firstName",entityList.get(i)[6]);
			entity.put("lastName",entityList.get(i)[7]);
			entity.put("password",entityList.get(i)[8]);
			entity.put("phone",entityList.get(i)[9]);
			entity.put("sexe",entityList.get(i)[10]);
			entity.put("updatedAt",entityList.get(i)[11]);
			entity.put("updatedBy",entityList.get(i)[12]);
			entity.put("username",entityList.get(i)[13]);
			entities.add(entity);
		}
		return  entities.toString();
	}
/*
	// get users with role cutomers using JPA Query Jointure
	@GetMapping("api/Customers")
	public ResponseEntity<JSONObject> retrieveCaissierByRole() {
		_log.info("retrieve Users with role cashier controller...!");
		List<Object[]> users = customerRepository.findUsersbyRole("ROLE_CUSTOMER");
		/////////////////////////////////
		/*
		 [
		1,
				adress "Rabat",
				bdate "1989-11-16T00:00:00.000+00:00",
				created at "2020-10-25T22:41:48.000+00:00",
				created by "Younes",
				email "younes@gmail.com",
				first name "Younes",
				last name "OUFRID",
				password "$2a$10$nLeatHo757ksh0BFwl0wo.KqHssEveDIKNR1yf0a97Gi4FGB9TrIe",
				phone "0621553770",
				sexe "Masculin",
				updated at "2020-10-25T22:41:48.000+00:00",
				updated by "Younes",
				username "younes"
    ]
       */
/*
		Object[] u ;
		JSONObject json = new JSONObject();

		JSONArray array = new JSONArray();
		JSONObject user = new JSONObject();
		for(int i=0;i<users.size();i++){
			 u = users.get(i);
			user.put("id",u[0]);
			user.put("adress",u[1]);
			user.put("bdate",u[2]);
			user.put("createdAt",u[3]);
			user.put("createdBy",u[4]);
			array.put(user);
		}
		json.put("users", array);
		List<JSONObject> list = new ArrayList<JSONObject>();
		list.add(json);
		return new ResponseEntity<JSONObject>(user, new HttpHeaders(), HttpStatus.OK);
	}
*/
	@GetMapping("api/Customerss")
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
