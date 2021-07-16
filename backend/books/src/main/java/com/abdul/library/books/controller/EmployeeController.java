package com.abdul.library.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdul.library.books.entity.Employee;
import com.abdul.library.books.security.AuthenticationDto;
import com.abdul.library.books.service.EmployeeService;

@RestController
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public AuthenticationDto authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationDto authenticationDto = new AuthenticationDto();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            authenticationDto.setLogin(Boolean.TRUE);
            authenticationDto.setUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        }
        return authenticationDto;
    }
	
	@GetMapping(path="employees")
    @PreAuthorize("isAuthenticated()")
	public List<Employee> getEmployees() {
		return employeeService.getAllEmployee();
		
	}
	
	@GetMapping(path="employee")
    @PreAuthorize("isAuthenticated()")
	public Employee getEmployee(@RequestParam(name = "id", required = true) String Id) {
		return employeeService.getEmployee(Id);
	}
	
	@PostMapping(path="employee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee postEmployee(@RequestBody Employee employee) {
		return employeeService.postEmployee(employee);
	}
	
	@PostMapping(path="delete/employee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee deleteEmployee(@RequestBody Employee employee) {
		return employeeService.deleteEmployee(employee);
	}

}
