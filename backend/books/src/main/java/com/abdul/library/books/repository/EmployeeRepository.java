package com.abdul.library.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdul.library.books.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
