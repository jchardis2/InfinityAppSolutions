package com.infinityappsolutions.server.lib.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.dao.AbstractDAOFactory;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.loaders.OrgUserLoader;

public class OrgUserDAO {
	private AbstractDAOFactory factory;
	private OrgUserLoader orgUserLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link AbstractDAOFactory} associated with this DAO, which is used
	 *            for obtaining SQL connections, etc.
	 */
	public OrgUserDAO(AbstractDAOFactory factory) {
		this.factory = factory;
		this.orgUserLoader = new OrgUserLoader();
	}

	/**
	 * Returns the OrgUserBean
	 * 
	 * @param id
	 *            The ID of the user in question.
	 * @return A list of OrgUserBean representing the list of orgs that a user
	 *         is part of
	 * @throws IASException
	 * @throws DBException
	 */
	public ArrayList<OrgUserBean> getOrgUsers(long id) throws IASException,
			DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT `org_users` . * , `org`.`name`  FROM org_users, org WHERE  `userid` =? AND  `org_users`.`orgid` =  `org`.`id` ");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			ArrayList<OrgUserBean> orgUsersList = (ArrayList<OrgUserBean>) orgUserLoader
					.loadList(rs);
			rs.close();
			ps.close();
			return orgUsersList;
		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	public int updateOrgUsers(OrgUserBean oub) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `webdesigner`.`org_users` SET  `orgid` =  ?, `userid` =  ?,`createProjects` =  ?,`deleteProjects` =  ?,`editProjects` =  ?,`adduser` =  ?,`addadmin` =  ?,`editAllProjects` =  ? WHERE  `org_users`.`orgid` =  ? AND  `org_users`.`userid` =?;");
			orgUserLoader.loadParameters(ps, oub);
			int i = ps.getParameterMetaData().getParameterCount();
			ps.setLong(i++, oub.getOrgid());
			ps.setLong(i++, oub.getUserid());
			int rs = ps.executeUpdate();
			ps.close();
			return rs;

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	public int insertOrgUsers(OrgUserBean oub) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `webdesigner`.`org_users` (`orgid` ,`userid`)	VALUES ( ?,  ?);");
			orgUserLoader.loadParameters(ps, oub);
			int rs = ps.executeUpdate();
			ps.close();
			return rs;

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	public int insertOrgUsersList(ArrayList<OrgUserBean> oub)
			throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			int rs = 0;
			for (OrgUserBean orgUserBean : oub) {
				ps = conn
						.prepareStatement("INSERT INTO  `webdesigner`.`org_users` (`orgid` ,`userid`  )	VALUES ( ?, ?);");
				orgUserLoader.loadParameters(ps, orgUserBean);
				rs += ps.executeUpdate();
			}
			ps.close();
			return rs;
		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

}
