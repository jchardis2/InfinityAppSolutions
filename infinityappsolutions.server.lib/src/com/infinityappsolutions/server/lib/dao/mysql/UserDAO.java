package com.infinityappsolutions.server.lib.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.infinityappsolutions.lib.beans.UserBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.loaders.UserLoader;

/**
 * Used for managing all static information related to a patient. For other
 * information related to all aspects of patient care, see the other DAOs.
 * 
 * DAO stands for Database Access Object. All DAOs are intended to be
 * reflections of the database, that is, one DAO per table in the database (most
 * of the time). For more complex sets of queries, extra DAOs are added. DAOs
 * can assume that all data has been validated and is correct.
 * 
 * DAOs should never have setters or any other parameter to the constructor than
 * a factory. All DAOs should be accessed by AbstractDAOFactory (@see
 * {@link AbstractDAOFactory} ) and every DAO should have a factory - for
 * obtaining JDBC connections and/or accessing other DAOs.
 * 
 * 
 * 
 */
public class UserDAO {
	private AbstractDAOFactory factory;
	private UserLoader userLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link AbstractDAOFactory} associated with this DAO, which
	 *            is used for obtaining SQL connections, etc.
	 */
	public UserDAO(AbstractDAOFactory factory) {
		this.factory = factory;
		this.userLoader = new UserLoader();
	}

	/**
	 * Returns the name for the given id
	 * 
	 * @param id
	 *            The ID of the user in question.
	 * @return A String representing the user's first name and last name.
	 * @throws IASException
	 * @throws DBException
	 */
	public String getName(long id) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT firstName, lastName FROM users WHERE id=?");
			ps.setLong(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				String result = rs.getString("firstname") + " "
						+ rs.getString("lastname");
				rs.close();
				ps.close();
				return result;
			} else {
				rs.close();
				ps.close();
				throw new IASException("User does not exist");
			}

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Adds an empty user to the table, returns the new id
	 * 
	 * @return The id of the user as a long.
	 * @throws DBException
	 */
	public long addEmptyUser() throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("INSERT INTO users(id) VALUES(NULL)");
			ps.executeUpdate();
			long a = DBUtil.getLastInsert(conn);
			ps.close();
			return a;
		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Adds a user to the table, returns the new id
	 * 
	 * @return The id of the user as a long.
	 * @throws DBException
	 */
	public long addNewUser(UserBean ub) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `webdesigner`.`users` (`id` ,`username` ,`email` ,`password` ,`firstname` ,`lastname`)VALUES (?,  ?,  ?,  ?,  ?,  ?);");
			if (ub.getId() == null) {
				ub.setId(0L);
			}
			userLoader.loadParameters(ps, ub);
			ps.executeUpdate();
			long a = DBUtil.getLastInsert(conn);
			ps.close();
			addUserToRoleTable(ub);
			return a;
		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	private void addUserToRoleTable(UserBean ub) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `webdesigner`.`user_role` (`user_username` ,`role_name`)VALUES (?,  'user');");
			ps.setString(1, ub.getUsername());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the user's information for a given ID
	 * 
	 * @param id
	 *            The ID of the user to retrieve.
	 * @return A UserBean representing the user.
	 * @throws DBException
	 */
	public UserBean getPatientByID(long id) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserBean pat = userLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return pat;
			} else {
				rs.close();
				ps.close();
				return null;
			}
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the user's information for given credentials
	 * 
	 * @param id
	 *            The ID of the user to retrieve.
	 * @return A UserBean representing the user.
	 * @throws DBException
	 */
	public UserBean getUserByCredentials(String username, String password,
			LoggedInUserBean loggedInUserBean) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM `users` WHERE `username` = ? AND `password` = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserBean pat = userLoader.loadSingle(rs, loggedInUserBean);
				rs.close();
				ps.close();
				return pat;
			} else {
				rs.close();
				ps.close();
				return null;
			}
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Updates a users's information for the given id
	 * 
	 * @param ub
	 *            The user bean representing the new information for the user.
	 * @throws DBException
	 */
	public void editUser(UserBean ub) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `webdesigner`.`users` SET  `id` =  ?,`username` =  '?,`email` =  ?,`password` =  ?,`firstname` =  ?,`lastname` =  ?' WHERE  `users`.`id` =?;");
			userLoader.loadParameters(ps, ub);
			int parameterCount = ps.getParameterMetaData().getParameterCount();
			ps.setLong(parameterCount, ub.getId());
			ps.executeUpdate();
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Updates a users's information for the given id
	 * 
	 * @param ub
	 *            The user bean representing the new information for the user.
	 * @throws DBException
	 */
	public void editUserPassword(UserBean ub) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `webdesigner`.`users` SET  `password` =  ? WHERE  `users`.`id` =?;");
			userLoader.loadParameters(ps, ub);
			int parameterCount = ps.getParameterMetaData().getParameterCount();
			ps.setString(1, ub.getPassword());
			ps.setLong(2, ub.getId());
			ps.executeUpdate();
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

}
