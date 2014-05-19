package com.infinityappsolutions.server.lib.webvideo.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.lib.webvideo.beans.ServerVideoBean;
import com.infinityappsolutions.server.lib.loaders.BeanLoader;

/**
 * A loader for TermsBean.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements.
 * Use the superclass to enforce consistency. For details on the paradigm for a
 * loader (and what its methods do), see {@link BeanLoader}
 */
public class ServerVideoLoader implements BeanLoader<ServerVideoBean> {
	// private final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("MM/dd/yyyy");

	public List<ServerVideoBean> loadList(ResultSet rs) throws SQLException {
		List<ServerVideoBean> list = new ArrayList<ServerVideoBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	public void loadCommon(ResultSet rs, ServerVideoBean vb)
			throws SQLException {
		vb.setId(rs.getLong("videoid"));
		vb.setName(rs.getString("name"));
		vb.setType(rs.getString("type"));
		vb.setUrl(rs.getString("url"));
		vb.setFile(rs.getString("file"));
		vb.setHash(rs.getString("hash"));
		vb.setImageurl(rs.getString("imageurl"));
		vb.setServervideoid(rs.getLong("servervideoid"));
		vb.setServerpath(rs.getString("servervideoid"));
		vb.setServerurl(rs.getString("servervideoid"));
	}

	public ServerVideoBean loadSingle(ResultSet rs) throws SQLException {
		ServerVideoBean vb = new ServerVideoBean();
		loadCommon(rs, vb);
		return vb;
	}

	public PreparedStatement loadParameters(PreparedStatement ps,
			ServerVideoBean vb) throws SQLException {
		int i = 1;
		ps.setLong(i++, vb.getServervideoid());
		ps.setLong(i++, vb.getId());
		ps.setString(i++, vb.getServerpath());
		ps.setString(i++, vb.getServerurl());
		return ps;
	}

	@Override
	public ServerVideoBean loadSingle(ResultSet rs, ServerVideoBean vb)
			throws SQLException {
		loadCommon(rs, vb);
		return vb;
	}
}
