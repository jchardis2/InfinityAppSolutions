package com.infinityappsolutions.server.lib.webvideo.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.loaders.BeanLoader;

/**
 * A loader for TermsBean.
 * 
 * Loads in information to/from beans using ResultSets and PreparedStatements.
 * Use the superclass to enforce consistency. For details on the paradigm for a
 * loader (and what its methods do), see {@link BeanLoader}
 */
public class VideoLoader implements BeanLoader<VideoBean> {
	// private final SimpleDateFormat DATE_FORMAT = new
	// SimpleDateFormat("MM/dd/yyyy");

	public List<VideoBean> loadList(ResultSet rs) throws SQLException {
		List<VideoBean> list = new ArrayList<VideoBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	public void loadCommon(ResultSet rs, VideoBean vb) throws SQLException {
		vb.setId(rs.getLong("id"));
		vb.setVideofolderid(rs.getLong("videofolderid"));
		vb.setName(rs.getString("name"));
		vb.setType(rs.getString("type"));
		vb.setUrl(rs.getString("url"));
		vb.setFile(rs.getString("file"));
		vb.setHash(rs.getString("hash"));
		vb.setImageurl(rs.getString("imageurl"));
	}

	public VideoBean loadSingle(ResultSet rs) throws SQLException {
		VideoBean vb = new VideoBean();
		loadCommon(rs, vb);
		return vb;
	}

	public PreparedStatement loadParameters(PreparedStatement ps, VideoBean vb)
			throws SQLException {
		int i = 1;
		ps.setLong(i++, vb.getId());
		ps.setLong(i++, vb.getVideofolderid());
		ps.setString(i++, vb.getName());
		ps.setString(i++, vb.getType());
		ps.setString(i++, vb.getUrl());
		ps.setString(i++, vb.getFile());
		ps.setString(i++, vb.getHash());
		// ps.setString(i++, vb.getImageurl());
		return ps;
	}

	@Override
	public VideoBean loadSingle(ResultSet rs, VideoBean vb) throws SQLException {
		loadCommon(rs, vb);
		return vb;
	}
}
