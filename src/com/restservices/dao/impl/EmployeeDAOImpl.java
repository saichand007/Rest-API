package com.restservices.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restservices.dao.interfaces.EmployeeDAO;
import com.restservices.model.Employee;
import com.restservices.util.DBConnections;

import oracle.jdbc.OracleTypes;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	static final Logger LOGGER = LogManager.getLogger(EmployeeDAOImpl.class);

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		Connection conn=null;
		List<Employee> list= new ArrayList<Employee>();
		try {
			conn= DBConnections.getConnection();
			CallableStatement stmt=conn.prepareCall("{call PKG_EMPLYS.SP_GET_EMPLOYEES(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.execute();
			ResultSet rs=(ResultSet) stmt.getObject(1);
			
			while(rs.next())
			{
				Employee emp=new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDepartment(rs.getString(3));
				list.add(emp);
			}
			
			LOGGER.info("size of the Employee-list is="+list.size());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
        	try
        	{
        	 if(!conn.isClosed() && conn !=null)	
        	 {
        		 conn.close();
        	 }
        	}
        	 catch (SQLException ex) {
                 ex.printStackTrace();
             }
        	
        }
		return list;
	}

	@Override
	public Employee getEmployee(int idnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee deleteEmployee(int idnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
