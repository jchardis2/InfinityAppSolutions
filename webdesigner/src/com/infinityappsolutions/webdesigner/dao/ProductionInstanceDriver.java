package com.infinityappsolutions.webdesigner.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductionInstanceDriver {
	@Resource(name = "jdbc/webdesigner")
	private DataSource ds_webdesigner;

	public Connection getWebDesignerConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		// System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdesigner", "root", "mytestpassword");
		return connection;
	}

	public Connection getDatabaseConnection(String dbName) throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/" + dbName);
		return ds.getConnection();
	}

	public boolean dataBaseExists(String dbName) {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/" + dbName);
			Connection conn = ds.getConnection();
			conn.close();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
