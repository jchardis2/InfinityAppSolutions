package com.infinityappsolutions.server.lib.webvideo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.loaders.VideoLoader;

public class VideoDAO {
	private DAOFactory factory;
	private VideoLoader videoLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link DAOFactory} associated with this DAO, which is used
	 *            for obtaining SQL connections, etc.
	 */
	public VideoDAO(DAOFactory factory) {
		this.factory = factory;
		this.videoLoader = new VideoLoader();
	}

	/**
	 * Returns the video for the given id
	 * 
	 * @param id
	 *            The ID of the video in question.
	 * @return A Video representing the video
	 * @throws IASException
	 * @throws DBException
	 */
	public VideoBean getVideo(long id) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT * FROM webvideo WHERE id=?");
			ps.setLong(1, id);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				VideoBean video = videoLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return video;
			} else {
				rs.close();
				ps.close();
				throw new IASException("Video does not exist");
			}

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the videoList
	 * 
	 * @return A VideoList representing the videos
	 * @throws IASException
	 * @throws DBException
	 */
	public ArrayList<VideoBean> getVideos() throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT `video`.*  FROM `video`");
			ResultSet rs;
			rs = ps.executeQuery();
			ArrayList<VideoBean> term = (ArrayList<VideoBean>) videoLoader
					.loadList(rs);
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
	 * @throws DBException
	 */
	public void editVideo(VideoBean video) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `video` SET  `id` =?,`name` =?,`type`=?,`url`=?, file`=? WHERE  `video`.`id` =?;");
			videoLoader.loadParameters(ps, video);
			int parameterCount = ps.getParameterMetaData().getParameterCount();
			ps.setLong(parameterCount - 1, video.getId());
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
	 * @param video
	 *            The video bean representing the video.
	 * @throws DBException
	 */
	public void insertVideo(VideoBean video) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `video` (`id` ,`name` ,`type` ,`url`, `file`)VALUES (? ,  ?,  ?,  ?,  ?);");
			videoLoader.loadParameters(ps, video);
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
	 * @param video
	 *            The video bean representing the video.
	 * @throws DBException
	 */
	public void insertVideo(ArrayList<VideoBean> videos) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoBean video : videos) {
				ps = conn
						.prepareStatement("INSERT INTO  `video` (`id` ,`name` ,`type` ,`url`, `file`)VALUES (? ,  ?,  ?,  ?,  ?);");
				videoLoader.loadParameters(ps, video);
				ps.executeUpdate();
			}
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * 
	 * @param video
	 *            The video bean representing the video.
	 * @throws DBException
	 */
	public void deleteVideos(ArrayList<VideoBean> videos) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoBean video : videos) {
				ps = conn
						.prepareStatement("DELETE FROM `video` WHERE `video`.`id` = ?;");
				ps.setLong(1, video.getId());
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
	public void saveVideos(ArrayList<VideoBean> videos) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoBean video : videos) {
				ps = conn
						.prepareStatement("UPDATE `video` SET  `id` =?,`name` =?,`type`=?,`url`=? ,`file`=? WHERE  `video`.`id` =?;");
				videoLoader.loadParameters(ps, video);
				int parameterCount = ps.getParameterMetaData()
						.getParameterCount();
				ps.setLong(parameterCount, video.getId());
				ps.executeUpdate();
			}
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	public void dropAllVideos() {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("DELETE FROM video;");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
