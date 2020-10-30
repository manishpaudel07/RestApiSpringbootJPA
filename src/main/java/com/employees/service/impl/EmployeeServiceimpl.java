package com.employees.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employees.model.Employee;
import com.employees.model.User;
import com.employees.repository.EmployeeRepository;
import com.employees.repository.StudentLoginRepository;
@Service
@Transactional
public class EmployeeServiceimpl implements EmployeeService {

	@Autowired
	 private EmployeeRepository employeeRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StudentLoginRepository studentLoginRepository;
	
	@Override
	public void saveEmployee(Employee employee) {
		 employeeRepository.save(employee);
		
	}
	
	

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees=employeeRepository.findAll();
		
		return employees;
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {

		Optional<Employee> employee=employeeRepository.findById(id);
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee updateEmployeeById(long id, Employee employee) {

		Employee employee2=employeeRepository.findById(id).get();
		employee2.setName(employee.getName());
		employee2.setAddress(employee.getAddress());
		employeeRepository.save(employee2);
		return employee2;
	}



	@Override
	public void signUpUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		studentLoginRepository.save(user);
		
	}

}
