package com.mak.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	public static final List<Employee> EMPLOYEES;
	static {
		EMPLOYEES = new ArrayList<Employee>();
		Employee employee = new Employee();
		employee.setAddress("new heena colony");
		employee.setName("Anas");
		employee.setId(1);
		System.err.println(employee.toString());
		Employee employee2 = new Employee();
		employee2.setAddress("new heena colony");
		employee2.setName("Kaif");
		employee2.setId(2);
		System.err.println(employee.toString());
		EMPLOYEES.add(employee);
		EMPLOYEES.add(employee2);
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Welcome";
	}

	@GetMapping("/employee")
	public List<Employee> getEmployee() {

		return EMPLOYEES;
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		for (Employee e : EMPLOYEES) {
			if (e.getId() == id) {
				return e;
			}

		}
		return null;
	}

	@PostMapping("/employee")
	public String createEmployee(@RequestBody Employee emp) {
		EMPLOYEES.add(emp);
		return "successfully created";
	}

	@PutMapping("/employee/{id}")
	public String updateEmployee(@RequestBody Employee emp, @PathVariable("id") int id) {
		for (Employee e : EMPLOYEES) {
			if (e.getId() == id) {
				e.setAddress(emp.getAddress());
				e.setName(emp.getName());
				return "updated Successfully";
			}

		}
		return "not updated employee may not exist";
	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		Employee employeeToBeDeleted = null;
		for (Employee e : EMPLOYEES) {
			if (e.getId() == id) {
				employeeToBeDeleted = e;
			}
		}
		if (employeeToBeDeleted != null) {
			EMPLOYEES.remove(employeeToBeDeleted);
			return " deleted employee successfully";
		}
		return "not deleted employee may not exist";
	}

}
