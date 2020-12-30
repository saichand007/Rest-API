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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restservices.dao.impl.DepartmentDAOImpl;
import com.restservices.model.Department;
import com.restservices.service.DepartmentService;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {
	
	private DepartmentService service=new DepartmentService();
	private static final Logger LOGGER = LogManager.getLogger(DepartmentResource.class);
	
	@GET
	public List<Department> fetchDepartmentsList()
	{
		LOGGER.info("fetch ---");
		return service.getDepartments();
	}
	
	
	@GET
	@Path("/{id}")
	public List<Department> fetchDepartment(@PathParam(value = "id")  Integer idnum)
	{
		LOGGER.info("fetch-1 ---"+idnum);
		return  service.getDepartment(idnum);
	}
	
	@POST
	public Department addNewDepartment(Department Department)
	{
		return service.addDepartment(Department);
	}
	
	@DELETE
	@Path("/{id}")
	public Department removeDepartment(@PathParam(value="id") Integer idnum)
	{
		LOGGER.info("Deletion ---"+idnum);
		 return service.deleteDepartment(idnum);
	}
	
	@PUT
	@Path("/{id}")
	public Department modifyDepartment(@PathParam(value="id") Integer idnum,Department Department)
	{
		LOGGER.info("updation ---"+idnum);
		return service.updateDepartment(Department);
	}

}
