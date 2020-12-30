package com.restservices.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restservices.dao.impl.DepartmentDAOImpl;
import com.restservices.dao.interfaces.DepartmentDAO;
import com.restservices.model.Department;

public class DepartmentService {

	private static final Logger LOGGER = LogManager.getLogger(DepartmentService.class);
	
	public List<Department> getDepartments()
	{
	    Department dept= new Department();
		DepartmentDAO dao=new DepartmentDAOImpl();
      return dao.getDepartments(dept);
	}

	public List<Department> getDepartment(Integer idnum) {
		Department dept= new Department();
		DepartmentDAO dao=new DepartmentDAOImpl();
		dept.setDeptId(idnum);	
	    return dao.getDepartments(dept);
	}

	public Department addDepartment(Department department) {
		DepartmentDAO dao=new DepartmentDAOImpl();
		return dao.addDepartment(department);
	}

	public Department deleteDepartment(Integer idnum) {
		DepartmentDAO dao=new DepartmentDAOImpl();
		return dao.deleteDepartment(idnum);
	}

	public Department updateDepartment(Department department) {
		DepartmentDAO dao=new DepartmentDAOImpl();
		return dao.updateDepartment(department);
	}
	
	
}
