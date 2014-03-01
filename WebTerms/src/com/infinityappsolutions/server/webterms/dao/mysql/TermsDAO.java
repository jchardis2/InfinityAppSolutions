package com.infinityappsolutions.server.webterms.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.infinityappsolutions.lib.webterms.bean.Term;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.webterms.dao.DAOFactory;
import com.infinityappsolutions.server.webterms.loaders.TermsLoader;

public class TermsDAO {
	private DAOFactory factory;
	private TermsLoader termsLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link DAOFactory} associated with this DAO, which is used
	 *            for obtaining SQL connections, etc.
	 */
	public TermsDAO(DAOFactory factory) {
		this.factory = factory;
		this.termsLoader = new TermsLoader();
	}

	/**
	 * Returns the term for the given id
	 * 
	 * @param id
	 *            The ID of the term in question.
	 * @return A Term representing the term
	 * @throws IASException
	 * @throws DBException
	 */
	public Term getTerms(long id) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT id, name, definition, FROM terms WHERE id=?");
			ps.setLong(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				Term term = termsLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return term;
			} else {
				rs.close();
				ps.close();
				throw new IASException("Term does not exist");
			}

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the termsList for the given owner's userid
	 * 
	 * @param id
	 *            The owner's userid of the term in question.
	 * @return A Term representing the term
	 * @throws IASException
	 * @throws DBException
	 */
	public ArrayList<Term> getTermsByOwner(long id) throws IASException,
			DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT `terms`.*  FROM `terms` WHERE `terms`.`ownerid`=?");
			ps.setLong(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			ArrayList<Term> term = (ArrayList<Term>) termsLoader.loadList(rs);
			rs.close();
			ps.close();
			return term;
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
	public void editTerm(Term term, LoggedInUserBean liub) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `terms`.`terms` SET  `id` =?,`name` =?,`definition`=?,`ownerid`=? WHERE  `terms`.`id` =? AND `terms`.`ownerid` =?;");
			termsLoader.loadParameters(ps, term);
			int parameterCount = ps.getParameterMetaData().getParameterCount();
			ps.setLong(parameterCount - 1, term.getId());
			ps.setLong(parameterCount, liub.getId());
			ps.executeUpdate();
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * 
	 * @param term
	 *            The term bean representing the term.
	 * @throws DBException
	 */
	public void insertTerm(Term term) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `terms`.`terms` (`id` ,`name` ,`definition` ,`ownerid`)VALUES (? ,  ?,  ?,  ?);");
			termsLoader.loadParameters(ps, term);
			ps.executeUpdate();
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * 
	 * @param term
	 *            The term bean representing the term.
	 * @throws DBException
	 */
	public void deleteTerms(ArrayList<Term> terms) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (Term term : terms) {
				ps = conn
						.prepareStatement("DELETE FROM `terms`.`terms` WHERE `terms`.`id` = ?  AND `terms`.`ownerid` =?;");
				ps.setLong(1, term.getId());
				ps.setLong(2, term.getOwnerId());
				ps.executeUpdate();
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
	public void saveTerms(ArrayList<Term> terms) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (Term term : terms) {
				ps = conn
						.prepareStatement("UPDATE  `terms`.`terms` SET  `id` =?,`name` =?,`definition`=?,`ownerid`=? WHERE  `terms`.`id` =? AND `terms`.`ownerid` =?;");
				termsLoader.loadParameters(ps, term);
				int parameterCount = ps.getParameterMetaData()
						.getParameterCount();
				ps.setLong(parameterCount - 1, term.getId());
				ps.setLong(parameterCount, term.getOwnerId());
				ps.executeUpdate();
			}
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}
}
