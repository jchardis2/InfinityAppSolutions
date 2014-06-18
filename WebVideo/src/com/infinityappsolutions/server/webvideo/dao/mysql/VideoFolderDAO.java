package com.infinityappsolutions.server.webvideo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.infinityappsolutions.lib.webvideo.beans.VideoFolderBean;
import com.infinityappsolutions.server.lib.dao.DBUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.webvideo.loaders.VideoFolderLoader;
import com.infinityappsolutions.server.webvideo.dao.DAOFactory;

public class VideoFolderDAO {
	private DAOFactory factory;
	private VideoFolderLoader videoLoader;

	/**
	 * The typical constructor.
	 * 
	 * @param factory
	 *            The {@link DAOFactory} associated with this DAO, which is used
	 *            for obtaining SQL connections, etc.
	 */
	public VideoFolderDAO(DAOFactory factory) {
		this.factory = factory;
		this.videoLoader = new VideoFolderLoader();
	}

	/**
	 * Returns the video folder for the given id
	 * 
	 * @param videofolderid
	 *            The ID of the video folder in question.
	 * @return A Video Folder representing a folder containing other folders or
	 *         videos
	 * @throws IASException
	 * @throws DBException
	 */
	public VideoFolderBean getVideo(long videofolderid) throws IASException,
			DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM videofolder WHERE videofolderid=?");
			ps.setLong(1, videofolderid);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				VideoFolderBean video = videoLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return video;
			} else {
				rs.close();
				ps.close();
				throw new IASException("VideoFolder does not exist");
			}

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the video folder for the given id
	 * 
	 * @param videofolderid
	 *            The ID of the video folder in question.
	 * @return A Video Folder representing a folder containing other folders or
	 *         videos
	 * @throws IASException
	 * @throws DBException
	 */
	public VideoFolderBean getVideoFolder(long parentfolderid, String name,
			String path) throws IASException, DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT * FROM videofolder WHERE parentfolderid=? AND name=? AND path=?");
			int i = 1;
			ps.setLong(i++, parentfolderid);
			ps.setString(i++, name);
			ps.setString(i++, path);
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
				VideoFolderBean video = videoLoader.loadSingle(rs);
				rs.close();
				ps.close();
				return video;
			} else {
				rs.close();
				ps.close();
				throw new IASException("VideoFolder does not exist");
			}

		} catch (SQLException | NamingException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * Returns the videofolderList grouped by the name
	 * 
	 * @return A VideoFolderList representing the videosfolders
	 * @throws IASException
	 * @throws DBException
	 */
	public ArrayList<VideoFolderBean> getVideosByName() throws IASException,
			DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("SELECT `videofolder`.*  FROM `videofolder` GROUP BY `videofolder`.`name`");
			ResultSet rs;
			rs = ps.executeQuery();
			ArrayList<VideoFolderBean> term = (ArrayList<VideoFolderBean>) videoLoader
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
	public void editVideoFolder(VideoFolderBean video) throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("UPDATE  `webvideo`.`videofolder` SET  `videofolderid` =  ?,`parentfolderid`=?, `name` =  ?,`path` =  ?, `ismovie` =  ?,`isshow` =  ?,`isseason` =  ? WHERE  `videofolder`.`videofolderid` =?;");
			videoLoader.loadParameters(ps, video);
			int parameterCount = ps.getParameterMetaData().getParameterCount();
			ps.setLong(parameterCount, video.getVideofolderid());
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
	 * @param videofolder
	 *            The video folder bean representing the video folder.
	 * @throws DBException
	 */
	public long insertFolderVideo(VideoFolderBean videofolder)
			throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT INTO `webvideo`.`videofolder` (`videofolderid`, `parentfolderid`, `name`, `path`, `ismovie`, `isshow`, `isseason`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			videoLoader.loadParameters(ps, videofolder);
			ps.executeUpdate();

			long a = DBUtil.getLastInsert(conn);
			ps.close();
			return a;
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * Ignores it already existing
	 * 
	 * @param videofolder
	 *            The video folder bean representing the video folder.
	 * @throws DBException
	 */
	public long insertIgnoreFolderVideo(VideoFolderBean videofolder)
			throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn
					.prepareStatement("INSERT IGNORE INTO `webvideo`.`videofolder` (`videofolderid`, `parentfolderid`, `name`, `path`, `ismovie`, `isshow`, `isseason`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			videoLoader.loadParameters(ps, videofolder);
			ps.executeUpdate();

			long a = DBUtil.getLastInsert(conn);
			ps.close();
			return a;
		} catch (SQLException | NamingException e) {

			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * 
	 * @param videofolder
	 *            The video bean representing the video.
	 * @throws DBException
	 */
	public void insertVideoFolders(ArrayList<VideoFolderBean> videosfolder)
			throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoFolderBean video : videosfolder) {
				ps = conn
						.prepareStatement("INSERT INTO `webvideo`.`videofolder` (`videofolderid`, `parentfolderid`, `name`, `path`, `ismovie`, `isshow`, `isseason`) VALUES ('0', ?, ?, ?, ?, ?, ?)");
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
	 * @param videofolders
	 *            The videofolders bean representing the videofolders.
	 * @throws DBException
	 */
	public void deleteVideos(ArrayList<VideoFolderBean> videofolders)
			throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoFolderBean video : videofolders) {
				ps = conn
						.prepareStatement("DELETE FROM `video` WHERE `video`.`id` = ?;");
				ps.setLong(1, video.getVideofolderid());
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
	 * @param videosfolders
	 * @throws DBException
	 */
	public void saveVideos(ArrayList<VideoFolderBean> videosfolders)
			throws DBException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			for (VideoFolderBean video : videosfolders) {
				ps = conn
						.prepareStatement("UPDATE  `webvideo`.`videofolder` SET  `videofolderid` =  ?, `parentfolderid`=?, `name` =  ?,`path` =  ?, `ismovie` =  ?,`isshow` =  ?,`isseason` =  ? WHERE  `videofolder`.`videofolderid` =?;");
				videoLoader.loadParameters(ps, video);
				int parameterCount = ps.getParameterMetaData()
						.getParameterCount();
				ps.setLong(parameterCount, video.getVideofolderid());
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
			ps = conn.prepareStatement("DELETE FROM videofolder;");
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
