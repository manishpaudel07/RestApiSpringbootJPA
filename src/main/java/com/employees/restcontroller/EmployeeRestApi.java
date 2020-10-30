package com.employees.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.model.Employee;
import com.employees.model.User;
import com.employees.repository.EmployeeRepository;
import com.employees.restcontroller.exceptions.EmployeeNotFoundException;
import com.employees.service.impl.EmployeeService;

@RestController
public class EmployeeRestApi {

	@Autowired
	private EmployeeService employeeService;
	
	

	// create an employee
	@PostMapping("/employees")
	public ApplicationResponseVO createEmployee(@Valid @RequestBody Employee employee) {
		ApplicationResponseVO vo = new ApplicationResponseVO();
		employeeService.saveEmployee(employee);
		vo.setCode(200);
		vo.setStatus("Success");
		vo.setMessage("employee has been added");
		return vo;
	}
	
	@PostMapping("/student")
	public ApplicationResponseVO signUpStudent(@RequestBody User user)
	{
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		employeeService.signUpUser(user);
		applicationResponseVO.setCode(200);
		applicationResponseVO.setStatus("Success");
		applicationResponseVO.setMessage("student is signed up successfully");
		return applicationResponseVO;
		
	}

	// get list of employees
	@GetMapping("/employees")
	public List<Employee> getEmployeesList() {
		List<Employee> employees = employeeService.getEmployees();
		return employees;
	}

	// find by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id)
			throws EmployeeNotFoundException {
		Employee employee = employeeService.getEmployeeById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("employee has not found" + id));
		return ResponseEntity.ok().body(employee);
	}

	// delete by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable(value = "id") long id) throws EmployeeNotFoundException {
		employeeService.getEmployeeById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("employee is not present" + id));
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok().build();
	}
	// update by id

	@PutMapping("/employees/{id}")
	public Employee updateById(@PathVariable(value = "id") long id, @RequestBody Employee employee) {
		Employee employee2 = employeeService.updateEmployeeById(id, employee);
		return employee2;
	}
}
