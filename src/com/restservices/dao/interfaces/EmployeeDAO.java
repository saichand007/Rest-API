package com.restservices.dao.interfaces;

import java.util.List;

import com.restservices.model.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployees();
	public Employee getEmployee(int idnum);
	public Employee addEmployee(Employee employee);
	public Employee deleteEmployee(int idnum);
	public Employee updateEmployee(Employee employee);
}
