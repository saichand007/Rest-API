package com.restservices.dao.interfaces;

import java.util.List;

import com.restservices.model.Department;

public interface DepartmentDAO {
	public List<Department> getDepartments(Department dept);
	public Department addDepartment(Department Department);
	public Department deleteDepartment(Integer idnum);
	public Department updateDepartment(Department Department);
}
