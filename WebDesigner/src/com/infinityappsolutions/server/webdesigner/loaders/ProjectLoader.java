package com.infinityappsolutions.server.webdesigner.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.server.lib.loaders.BeanLoader;
import com.infinityappsolutions.server.webdesigner.beans.ProjectBean;

public class ProjectLoader implements BeanLoader<ProjectBean> {

	@Override
	public List<ProjectBean> loadList(ResultSet rs) throws SQLException {
		List<ProjectBean> list = new ArrayList<ProjectBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public ProjectBean loadSingle(ResultSet rs) throws SQLException {
		ProjectBean pb = new ProjectBean();
		loadCommon(rs, pb);
		return pb;
	}

	@Override
	public ProjectBean loadSingle(ResultSet rs, ProjectBean pb)
			throws SQLException {
		loadCommon(rs, pb);
		return pb;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps, ProjectBean pb)
			throws SQLException {
		int i = 1;
		ps.setLong(i++, pb.getId());
		ps.setLong(i++, pb.getOrgid());
		ps.setString(i++, pb.getName());
		ps.setString(i++, pb.getType());
		ps.setString(i++, pb.getDescriptor());
		return ps;
	}

	@Override
	public void loadCommon(ResultSet rs, ProjectBean pb) throws SQLException {
		pb.setId(rs.getLong("id"));
		pb.setOrgid(rs.getLong("orgid"));
		pb.setName(rs.getString("name"));
		pb.setType(rs.getString("type"));
		pb.setDescriptor(rs.getString("descriptor"));
	}

}
