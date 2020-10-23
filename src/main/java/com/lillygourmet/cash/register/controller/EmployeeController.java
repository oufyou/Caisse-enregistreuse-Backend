/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.controller;
/**
 * Employee Controller
 *
 * @author Alias King - Younes OUFRID
 */

import com.lillygourmet.cash.register.model.Employee;
import com.lillygourmet.cash.register.model.User;
import com.lillygourmet.cash.register.service.EmployeeService;
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
public class EmployeeController {

	private static Logger _log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService EmployeeService;


	@GetMapping("api/Employees")
	public ResponseEntity<List<Employee>> retrieveAllEmployees() {
		_log.info("retrieve all Employees controller...!");
		List<Employee> Employees = EmployeeService.retrieveAllEmployees();
		return new ResponseEntity<List<Employee>>(Employees, new HttpHeaders(), HttpStatus.OK);
	}


	@GetMapping("api/Employees/{id}")
	public ResponseEntity<Employee> retrieveEmployeeById(@PathVariable Long id) {

		_log.info("retrieve Employee controller...!");
		Employee Employee = EmployeeService.getEmployee(id);
		return new ResponseEntity<Employee>(Employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("api/Employees")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee Employee) {

		_log.info("create Employee controller...!");
		Employee savedEmployee = EmployeeService.createEmployee(Employee);
		return new ResponseEntity<Employee>(savedEmployee, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("api/Employees/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteEmployee(@PathVariable Long id) throws Exception {

		_log.info("delete Employee controller...!");
		EmployeeService.deleteEmployee(id);
		return HttpStatus.ACCEPTED;
	}

	@PutMapping("api/Employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee Employee) {

		_log.info("update Employee controller...!");
		Employee updatedEmployee= EmployeeService.updateEmployee(Employee);
		return new ResponseEntity<Employee>(updatedEmployee, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
}
