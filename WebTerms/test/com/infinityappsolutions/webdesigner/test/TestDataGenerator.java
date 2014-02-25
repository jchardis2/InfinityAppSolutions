package com.infinityappsolutions.webdesigner.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;

public class TestDataGenerator {
	public static void main(String[] args) throws IOException, SQLException,
			NamingException {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();

	}

	private String DIR = "sql/data";

	private AbstractDAOFactory factory;

	public TestDataGenerator() {
		this.factory = TestDAOFactory.getTestInstance();
	}

	public TestDataGenerator(String projectHome, AbstractDAOFactory factory) {
		this.DIR = projectHome + "src/main/resources/sql/data";
		this.factory = factory;
	}

	public void clearAllTables() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/deleteFromAllTables.sql");
	}

	public void standardData() throws FileNotFoundException, IOException,
			SQLException, NamingException {
		standardUsers();
		standardRoles();
		standardUserRoles();
		standardOrgs();
		standardOrgUsers();
	}

	public void standardUsers() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardUsers.sql");
	}

	public void standardRoles() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardRoles.sql");
	}

	public void standardUserRoles() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardUserRoles.sql");
	}

	public void standardOrgs() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardOrgs.sql");
	}

	public void standardOrgUsers() throws SQLException, FileNotFoundException,
			IOException, NamingException {
		new DBBuilder(factory).executeSQLFile(DIR + "/standardOrgUsers.sql");
	}

}