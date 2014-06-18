package com.infinityappsolutions.server.webvideo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.infinityappsolutions.lib.webvideo.beans.ServerVideoBean;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.webvideo.loaders.ServerVideoLoader;
import com.infinityappsolutions.server.lib.webvideo.loaders.VideoLoader;
import com.infinityappsolutions.server.webvideo.dao.DAOFactory;

public class ServerVideoDAO {
	private DAOFactory factory;
	private ServerVideoLoader videoLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link DAOFactory} associated with this DAO, which is used
	 *            for obtaining SQL connections, etc.
	 */
	public ServerVideoDAO(DAOFactory factory) {
		this.factory = factory;
		this.videoLoader = new ServerVideoLoader();
	}

	/**
	 * Returns the video for the given id
	 * 
	 * @param servervideoid
	 *            The ID of the video in question.
	 * @return A Video representing the video
	 * @throws IASException
	 * @throws DBException
	 */
	public VideoBean getServerVideo(long servervideoid) throws IASException,
			DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT * FROM servervideo WHERE id=?");
			ps.setLong(1, servervideoid);
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
	 * Returns the video for the given name and hash
	 * 
	 * @param name
	 *            The name of the video in question.
	 * @param hash
	 *            The hash of the video in question.
	 * @return A Video representing the video
	 * @throws IASException
	 * @throws DBException
	 */
	public VideoBean getVideo(String name, String hash) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT `servervideo`.*, `video`.*, `videoimage`.`imageurl`  FROM `servervideo`, `video`, `videoimage` WHERE name=? AND hash=? AND `servervideo`.`videoid` =`video`.`id` AND `video`.`videoimageid` = `videoimage`.`videoimageid`");
			ps.setString(1, name);
			ps.setString(2, hash);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				ServerVideoBean video = videoLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return video;
			} else {
				rs.close();
				ps.close();
				return null;
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
	public ArrayList<ServerVideoBean> getVideos() throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT `servervideo`.*, `video`.*, `videoimage`.`imageurl`  FROM `servervideo`, `video`, `videoimage` WHERE `servervideo`.`videoid` =`video`.`id` AND `video`.`videoimageid` = `videoimage`.`videoimageid` GROUP BY  `video`.`name`");
			ResultSet rs;
			rs = ps.executeQuery();
			ArrayList<ServerVideoBean> svb = (ArrayList<ServerVideoBean>) videoLoader
					.loadList(rs);
			rs.close();
			ps.close();
			return svb;
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
	public void editVideo(ServerVideoBean video) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `servervideo` SET  `servervideoid` =?,`videoid` =?,`serverpath` =?,`serverurl`=? WHERE  `servervideo`.`servervideoid` =?;");
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
	public void insertVideo(ServerVideoBean video) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO  `video` (`servervideoid` ,`videoid` ,`serverpath` ,`serverurl``videoimageid`)VALUES (? , ?,  ?,  ?);");
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
	public void insertVideo(ArrayList<ServerVideoBean> videos) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (ServerVideoBean video : videos) {
				ps = conn
						.prepareStatement("INSERT INTO  `video` (`servervideoid` ,`videoid` ,`serverpath` ,`serverurl``videoimageid`)VALUES (? , ?,  ?,  ?);");
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
						.prepareStatement("DELETE FROM `servervideo` WHERE `servervideo`.`servervideoid` = ?;");
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
	public void saveVideos(ArrayList<ServerVideoBean> videos) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (ServerVideoBean video : videos) {
				ps = conn
						.prepareStatement("UPDATE  `servervideo` SET  `servervideoid` =?,`videoid` =?,`serverpath` =?,`serverurl`=? WHERE  `servervideo`.`servervideoid` =?;");
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
			ps = conn.prepareStatement("DELETE FROM servervideo;");
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
