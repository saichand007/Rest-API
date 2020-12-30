package com.restservices.dao.impl;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restservices.dao.interfaces.DepartmentDAO;
import com.restservices.model.Department;
import com.restservices.util.DBConnections;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class DepartmentDAOImpl implements DepartmentDAO {

	private static final Logger LOGGER = LogManager.getLogger(DepartmentDAOImpl.class);
	
	@Override
	public List<Department> getDepartments(Department bean) {

		Connection conn=null;
		List<Department> list= new ArrayList<Department>();
		try {
			Integer id=bean.getDeptId();
			conn= DBConnections.getConnection();
			CallableStatement stmt=conn.prepareCall("{call PKG_EMPLYS.SP_GET_DepartmentS(?,?)}");
			
			if(bean.getDeptId() !=null)
				stmt.setInt(1, bean.getDeptId()) ;
			else 
				stmt.setNull(1,OracleTypes.NUMBER );
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			stmt.execute();
			ResultSet rs=(ResultSet) stmt.getObject(2);
			
			while(rs.next())
			{
				Department emp=new Department();
				emp.setDeptId(rs.getInt(1));
				emp.setDeptName(rs.getString(2));
				emp.setDeptLocation(rs.getInt(3));
				list.add(emp);
			}
			LOGGER.info("size of the Departments-list is="+list.size());
			LOGGER.debug("debug msg");
			
		} catch (SQLException e) {
			LOGGER.error("ERROR Occured in Fetching the Departments");
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
	public Department addDepartment(Department bean) {
		
		Connection conn=null;
		Department demp=null;
		try {
			conn= DBConnections.getConnection();
			CallableStatement stmt=conn.prepareCall("{call PKG_EMPLYS.SP_ADD_Department(?,?,?)}");
			
			StructDescriptor StructDesc = StructDescriptor.createDescriptor("DEPARTMENT_TYPE", conn);
			
			Object[] obj   = new Object[4];
			obj[0]= bean.getDeptId();
			obj[1]=bean.getDeptName();
			obj[2]=null;
			obj[3]=null;
			
			STRUCT deptobj = new STRUCT(StructDesc,conn,obj);

			stmt.setObject(1, deptobj);
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			
			stmt.execute();
			int code= stmt.getInt(2);
			ResultSet rs=(ResultSet) stmt.getObject(3);
			
			if(code==0) {
				while(rs.next())
				{
					demp=new Department();
					demp.setDeptId(rs.getInt(1));
					demp.setDeptName(rs.getString(2));
					demp.setDeptLocation(rs.getInt(3));
					demp.setMsg(rs.getString(4));
						
				}
				LOGGER.debug("Insertion SUCCESS");
			}
			else if(code==1)
			{
				demp=new Department();
				demp.setCode(code);
				demp.setMsg("Insertion Failed");
				LOGGER.debug("Insertion Failed");
			}
	
		} catch (SQLException e) {
			LOGGER.error("ERROR Occured in adding the Department");
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
		
		return demp;
	}

	@Override
	public Department deleteDepartment(Integer idnum) {
		
		Connection conn=null;;
		Department dept= null;
		try {
			conn= DBConnections.getConnection();
			CallableStatement stmt=conn.prepareCall("{call PKG_EMPLYS.SP_DELETE_Department(?,?,?)}");
			
			stmt.setInt(1, idnum );
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			
			stmt.execute();
			int code= stmt.getInt(2);
			String msg= stmt.getString(3);
			
			if(code == 0)
			{
				dept=new Department();
				dept.setMsg(msg);
				dept.setCode(code);
				LOGGER.info("Deletion Successfull");
			}
			else if(code==1)
			{
				dept=new Department();
				dept.setMsg(msg);
				dept.setCode(code);
				LOGGER.info("Deletion Failed");
			}
			
		} catch (SQLException e) {
			LOGGER.error("ERROR Occured in Deleting the Departments");
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
		
		return dept;
	}
	

	@Override
	public Department updateDepartment(Department bean) {
		Connection conn=null;
		Department demp=null;
		try {
			conn= DBConnections.getConnection();
			CallableStatement stmt=conn.prepareCall("{call PKG_EMPLYS.SP_update_Department(?,?,?)}");
			
			StructDescriptor StructDesc = StructDescriptor.createDescriptor("DEPARTMENT_TYPE", conn);
			
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("DEPARTMENT_ARRAY", conn);
			
			Object[] obj   = new Object[4];
			obj[0]= bean.getDeptId();
			obj[1]=bean.getDeptName();
			obj[2]=null;
			obj[3]=bean.getDeptLocation();
			
			STRUCT deptobj = new STRUCT(StructDesc,conn,obj);
			
			STRUCT[] structs = new STRUCT[1];
			
			structs[0]=deptobj;
			
			Array array = new ARRAY(arrDesc, conn, structs);

			stmt.setObject(1, array);
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			
			stmt.execute();
			int code= stmt.getInt(2);
			ResultSet rs=(ResultSet) stmt.getObject(3);
			
			if(code==0) {
				while(rs.next())
				{
					demp=new Department();
					demp.setDeptId(rs.getInt(1));
					demp.setDeptName(rs.getString(2));
					demp.setDeptLocation(rs.getInt(3));
					demp.setMsg(rs.getString(4));
						
				}
				LOGGER.debug("updation SUCCESS");
			}
			else if(code==1)
			{
				demp=new Department();
				demp.setDeptId(rs.getInt(1));
				demp.setDeptName(rs.getString(2));
				demp.setDeptLocation(rs.getInt(3));
				demp.setMsg(rs.getString(4));
				LOGGER.debug("No data to update");
			}
			else if(code==-1)
			{
				demp=new Department();
				demp.setDeptId(rs.getInt(1));
				demp.setDeptName(rs.getString(2));
				demp.setDeptLocation(rs.getInt(3));
				demp.setMsg(rs.getString(4));
				LOGGER.debug("updation failed");
			}
	
		} catch (SQLException e) {
			LOGGER.error("ERROR Occured in updating the Department");
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
		
		return demp;

	}

}
