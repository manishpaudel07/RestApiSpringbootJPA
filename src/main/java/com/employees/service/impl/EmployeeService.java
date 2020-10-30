package com.employees.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employees.model.Employee;
import com.employees.model.User;


public interface EmployeeService {

	public void saveEmployee(Employee employee);
	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(long id);
	public void deleteEmployeeById(long id);
	public Employee updateEmployeeById(long id, Employee employee);
	public void signUpUser(User user);
}
