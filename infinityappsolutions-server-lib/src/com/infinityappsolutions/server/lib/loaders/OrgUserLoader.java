package com.infinityappsolutions.server.lib.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.server.lib.beans.OrgUserBean;

public class OrgUserLoader implements BeanLoader<OrgUserBean> {

	@Override
	public List<OrgUserBean> loadList(ResultSet rs) throws SQLException {
		List<OrgUserBean> list = new ArrayList<OrgUserBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public OrgUserBean loadSingle(ResultSet rs) throws SQLException {
		OrgUserBean pb = new OrgUserBean();
		loadCommon(rs, pb);
		return pb;
	}

	@Override
	public OrgUserBean loadSingle(ResultSet rs, OrgUserBean oub)
			throws SQLException {
		loadCommon(rs, oub);
		return oub;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps,
			OrgUserBean oub) throws SQLException {
		int i = 1;
		ps.setLong(i++, oub.getOrgid());
		ps.setLong(i++, oub.getUserid());
		return ps;
	}

	@Override
	public void loadCommon(ResultSet rs, OrgUserBean ub) throws SQLException {
		ub.setOrgid(rs.getLong("orgid"));
		ub.setUserid(rs.getLong("userid"));
		ub.setName(rs.getString("name"));

	}

}
