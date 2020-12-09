package com.example.jpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dal.EmployeeDalImpl;
import com.example.jpa.entity.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDalImpl employeeDalImpl;

	@PostMapping("/save")
	public void saveEmployee(@RequestBody Employee employee) {
		employeeDalImpl.createEmployee(employee);
	}

	@PutMapping("/update")
	public void updateEmployee(@RequestBody Employee employee, @RequestParam int id) {
		employeeDalImpl.updateEmployee(employee, id);
	}

	@DeleteMapping("/delete")
	public void deleteEmployee(@RequestParam int id) {
		employeeDalImpl.deleteEmployee(id);
	}

	@GetMapping()
	public List<Employee> getAllEmployees() {
		return employeeDalImpl.getAllEmployees();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return employeeDalImpl.getEmployeeById(id);
	}

	@GetMapping("/year-experience")
	public List<Employee> getEmployeeByYearsOfExperience(@RequestParam int years) {
		return employeeDalImpl.getEmployeeByYearsOfExperience(years);
	}

	@GetMapping("/month-experience")
	public List<Employee> getEmployeeByMonthsOfExperience(@RequestParam int months) {
		return employeeDalImpl.getEmployeeByMonthsOfExperience(months);
	}

	@GetMapping("/day-experience")
	public List<Employee> getEmployeeByDaysOfExperience(@RequestParam int days) {
		return employeeDalImpl.getEmployeeByDaysOfExperience(days);
	}

	@GetMapping("/between-year")
	public List<Employee> getEmployeeBetweenYearsOfExperience(@RequestParam int start, @RequestParam int end) {
		return employeeDalImpl.getEmployeeBetweenYearsOfExperience(start, end);
	}

	@GetMapping("/employee-stats")
	public Map<String, Object> getEmployeeStats() {
		return employeeDalImpl.getEmployeeStats();
	}

	@GetMapping("year-sorted")
	public List<Employee> getEmployeeSortedByYearExperience() {
		return employeeDalImpl.getEmployeeSortedByYearExperience();
	}

}
