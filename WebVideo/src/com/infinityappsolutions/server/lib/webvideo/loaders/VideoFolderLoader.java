package com.infinityappsolutions.server.lib.webvideo.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.infinityappsolutions.lib.webvideo.beans.VideoFolderBean;
import com.infinityappsolutions.server.lib.loaders.BeanLoader;

public class VideoFolderLoader implements BeanLoader<VideoFolderBean> {

	public List<VideoFolderBean> loadList(ResultSet rs) throws SQLException {
		List<VideoFolderBean> list = new ArrayList<VideoFolderBean>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	public void loadCommon(ResultSet rs, VideoFolderBean vb)
			throws SQLException {
		vb.setVideofolderid(rs.getLong("videofolderid"));
		vb.setParentfolderid(rs.getLong("parentfolderid"));
		vb.setName(rs.getString("name"));
		vb.setPath(rs.getString("path"));
		vb.setMovie(rs.getBoolean("ismovie"));
		vb.setShow(rs.getBoolean("isshow"));
		vb.setSeason(rs.getBoolean("isseason"));
	}

	public VideoFolderBean loadSingle(ResultSet rs) throws SQLException {
		VideoFolderBean vb = new VideoFolderBean();
		loadCommon(rs, vb);
		return vb;
	}

	public PreparedStatement loadParameters(PreparedStatement ps,
			VideoFolderBean vb) throws SQLException {
		int i = 1;
		ps.setLong(i++, vb.getVideofolderid());
		ps.setLong(i++, vb.getParentfolderid());
		ps.setString(i++, vb.getName());
		ps.setString(i++, vb.getPath());
		ps.setBoolean(i++, vb.isMovie());
		ps.setBoolean(i++, vb.isSeason());
		ps.setBoolean(i++, vb.isShow());
		return ps;
	}

	@Override
	public VideoFolderBean loadSingle(ResultSet rs, VideoFolderBean vb)
			throws SQLException {
		loadCommon(rs, vb);
		return vb;
	}
}
