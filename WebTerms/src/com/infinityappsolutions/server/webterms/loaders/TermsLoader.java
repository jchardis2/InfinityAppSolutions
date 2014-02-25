package com.infinityappsolutions.server.webterms.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.server.lib.beans.UserBean;
import com.infinityappsolutions.server.lib.loaders.BeanLoader;
import com.infinityappsolutions.server.webterms.beans.Term;

/**
 * A loader for TermsBean.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements.
 * Use the superclass to enforce consistency. For details on the paradigm for a
 * loader (and what its methods do), see {@link BeanLoader}
 */
public class TermsLoader implements BeanLoader<Term> {
	// private final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("MM/dd/yyyy");

	public List<Term> loadList(ResultSet rs) throws SQLException {
		List<Term> list = new ArrayList<Term>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	public void loadCommon(ResultSet rs, Term term) throws SQLException {
		term.setId(rs.getLong("id"));
		term.setName(rs.getString("name"));
		term.setDefinition(rs.getString("definition"));
		term.setOwnerId(rs.getLong("ownerid"));
	}

	public Term loadSingle(ResultSet rs) throws SQLException {
		Term term = new Term();
		loadCommon(rs, term);
		return term;
	}

	public PreparedStatement loadParameters(PreparedStatement ps, Term term)
			throws SQLException {
		int i = 1;
		ps.setLong(i++, term.getId());
		ps.setString(i++, term.getName());
		ps.setString(i++, term.getDefinition());
		ps.setLong(i++, term.getOwnerId());
		return ps;
	}

	@Override
	public Term loadSingle(ResultSet rs, Term term) throws SQLException {
		loadCommon(rs, term);
		return term;
	}
}
