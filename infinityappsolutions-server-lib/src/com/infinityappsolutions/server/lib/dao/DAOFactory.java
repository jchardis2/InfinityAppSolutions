package com.infinityappsolutions.server.lib.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.infinityappsolutions.server.lib.dao.IConnectionDriver;
import com.infinityappsolutions.server.lib.dao.ProductionConnectionDriver;

/**
 * The central mediator for all Database Access Objects. The production instance
 * uses the database connection pool provided by Tomcat (so use the production
 * instance when doing stuff from JSPs in the "real code"). Both the production
 * and the test instance parses the context.xml file to get the JDBC connection.
 * 
 * Also, @see {@link EvilDAOFactory} and @see {@link TestDAOFactory}.
 * 
 * Any DAO that is added to the system should be added in this class, in the
 * same way that all other DAOs are.
 * 
 * 
 * 
 */
public class DAOFactory {
	private static DAOFactory productionInstance = null;
	private IConnectionDriver driver;
	private String PROPERTY_DATABASE_NAME = "com.infinityappsolutions.server.lib.dao.DATABASE.NAME";
	private String PROPERTY_DATABASE_PASSWORD = "com.infinityappsolutions.server.lib.dao.DATABASE.PASSWORD";

	/**
	 * 
	 * @return A production instance of the DAOFactory, to be used in deployment
	 *         (by Tomcat).
	 */
	public static DAOFactory getProductionInstance(String databaseName) {
		productionInstance = new DAOFactory(databaseName);
		return productionInstance;
	}

	/**
	 * Protected constructor. Call getProductionInstance to get an instance of
	 * the DAOFactory
	 */
	protected DAOFactory(String databaseName) {
		this.driver = (IConnectionDriver) new ProductionConnectionDriver(
				databaseName);
	}

	/**
	 * 
	 * @return this DAOFactory's Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		try {
			return driver.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
