/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.service;
/**
 * Employee Service
 *
 * @author Alias King - Younes OUFRID
 */
import com.lillygourmet.cash.register.model.Employee;
import com.lillygourmet.cash.register.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository EmployeeRepository;


	public List<Employee> retrieveAllEmployees() {
		return EmployeeRepository.findAll();
	}

	public Employee getEmployee(Long id) {
		return EmployeeRepository.getOne(id);
	}

	public Employee createEmployee(Employee Employee) {
		return EmployeeRepository.save(Employee);
	}
	
	@Transactional
	public void deleteEmployee(Long id) throws Exception {

		Employee Employee = EmployeeRepository.getOne(id);
		if (Employee != null) {
			EmployeeRepository.deleteById(id);
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	
	public Employee updateEmployee(Employee Employee) {
		Employee editedEmployee = EmployeeRepository.getOne(Employee.getId());
		editedEmployee.setEmail(Employee.getEmail());
		editedEmployee.setFirstName(Employee.getFirstName());
		editedEmployee.setLastName(Employee.getLastName());
		editedEmployee.setCin(Employee.getCin());
		editedEmployee.setSalaire(Employee.getSalaire());
		editedEmployee.setRoles(Employee.getRoles());
		editedEmployee.setAdress(Employee.getAdress());
		editedEmployee.setBdate(Employee.getBdate());
		editedEmployee.setSexe(Employee.getSexe());
		editedEmployee.setPhone(Employee.getPhone());
		editedEmployee.setPassword(Employee.getPassword());
		return EmployeeRepository.save(editedEmployee);
	}
}
