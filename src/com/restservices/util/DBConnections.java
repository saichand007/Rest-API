package com.restservices.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnections {

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("config.properties").getFile());
	
	
	public static Connection getConnection()
	{
		Properties props = new Properties();
		InputStream fis = null;
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			fis=DBConnections.class.getResourceAsStream("db.properties");
			props.load(fis);
			String connectionURL = props.getProperty("connectionUrl");
			String username= props.getProperty("username");
			String password=props.getProperty("password");
			System.out.println("username:"+username+" password:"+password);
			
			conn=DriverManager.getConnection(connectionURL,username,password);
		}
	       catch (IOException ex) {
	            ex.printStackTrace();
	        } 
       catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
	} 

		
		return conn;

	}
}
