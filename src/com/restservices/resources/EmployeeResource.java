package com.restservices.resources;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.restservices.model.Employee;
import com.restservices.service.EmployeeService;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	
	private EmployeeService service=new EmployeeService();
	
	@GET
	public List<Employee> fetchEmployeesList()
	{
		return service.getEmployees();
	}
	
	
	@GET
	@Path("/{id}")
	public Employee fetchEmployee(@PathParam(value = "id")  int idnum)
	{
		return  service.getEmployee(idnum);
	}
	
	@POST
	public Employee addNewEmployee(Employee employee)
	{
		return service.addEmployee(employee);
	}
	
	@DELETE
	@Path("/{id}")
	public Employee removeEmployee(@PathParam(value="id") int idnum)
	{
		return service.deleteEmployee(idnum);
	}
	
	@PUT
	@Path("/{id}")
	public Employee modifyEmployee(@PathParam(value="id") int idnum,Employee employee)
	{
		return service.updateEmployee(employee);
	}

}
