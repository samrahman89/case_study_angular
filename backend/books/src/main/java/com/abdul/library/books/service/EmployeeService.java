package com.abdul.library.books.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abdul.library.books.entity.Employee;
import com.abdul.library.books.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public Employee postEmployee(Employee employee) {
		if(employee.getId().isEmpty()) {
			employee.setId(UUID.randomUUID().toString());
		}
		return employeeRepository.save(employee);
	}

	public Employee deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
		return employee;
	}

	public Employee getEmployee(String Id) {
		return employeeRepository.findById(Id).get();
	}

}
