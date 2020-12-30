package com.restservices.service;

import java.util.List;

import com.restservices.dao.impl.EmployeeDAOImpl;
import com.restservices.dao.interfaces.EmployeeDAO;
import com.restservices.model.Employee;

public class EmployeeService {
	
	public List<Employee> getEmployees()
	{
		EmployeeDAO dao=new EmployeeDAOImpl();
      return dao.getEmployees();
	}

	public Employee getEmployee(int idnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee deleteEmployee(int idnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
