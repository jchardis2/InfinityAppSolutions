package com.infinityappsolutions.server.lib.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.lib.beans.UserBean;

/**
 * A loader for UserBeans.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements.
 * Use the superclass to enforce consistency. For details on the paradigm for a
 * loader (and what its methods do), see {@link BeanLoader}
 */
public class UserLoader implements BeanLoader<UserBean> {
	// private final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("MM/dd/yyyy");

	public List<UserBean> loadList(ResultSet rs) throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	public void loadCommon(ResultSet rs, UserBean ub) throws SQLException {
		ub.setId(rs.getLong("id"));
		ub.setUsername(rs.getString("username"));
		ub.setEmail(rs.getString("email"));
		ub.setPassword(rs.getString("password"));
		ub.setFirstname(rs.getString("firstname"));
		ub.setLastname(rs.getString("lastname"));
		// Date dateOfBirth = rs.getDate("DateOfBirth");
		// if (dateOfBirth != null) {
		// ub.setDateOfBirthStr(DATE_FORMAT.format(dateOfBirth));
		// }
		// Date dateOfDeath = rs.getDate("DateOfDeath");
		// if (dateOfDeath != null) {
		// ub.setDateOfDeathStr(DATE_FORMAT.format(dateOfDeath));
		// }

	}

	public UserBean loadSingle(ResultSet rs) throws SQLException {
		UserBean ub = new UserBean();
		loadCommon(rs, ub);
		return ub;
	}

	public PreparedStatement loadParameters(PreparedStatement ps, UserBean ub)
			throws SQLException {
		int i = 1;
		ps.setLong(i++, ub.getId());
		ps.setString(i++, ub.getUsername());
		ps.setString(i++, ub.getEmail());
		ps.setString(i++, ub.getPassword());
		ps.setString(i++, ub.getFirstname());
		ps.setString(i++, ub.getLastname());
		// Date date = null;
		// try {
		// date = new
		// java.sql.Date(DATE_FORMAT.parse(ub.getDateOfBirthStr()).getTime());
		// } catch (ParseException e) {
		//
		// }
		// ps.setDate(i++, date);
		// date = null;
		// try {
		// date = new
		// java.sql.Date(DATE_FORMAT.parse(ub.getDateOfDeathStr()).getTime());
		// } catch (ParseException e) {
		// if ("".equals(ub.getDateOfDeathStr())) {
		// date = null;
		// } else {
		//
		// }
		// }

		return ps;
	}

	@Override
	public UserBean loadSingle(ResultSet rs, UserBean ub) throws SQLException {
		loadCommon(rs, ub);
		return ub;
	}
}
