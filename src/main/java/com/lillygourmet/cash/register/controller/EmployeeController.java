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
import com.lillygourmet.cash.register.repository.EmployeeRepository;
import com.lillygourmet.cash.register.service.EmployeeService;
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
public class EmployeeController {

	private static Logger _log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService EmployeeService;

	@Autowired
	private EmployeeRepository employeeRepository;

	// get users with role employee using JPA Query Jointure
	@RequestMapping(value="api/Employees", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String getAll() {
		List<Object[]> entityList = employeeRepository.findUsersbyRole("ROLE_EMPLOYEE");
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
