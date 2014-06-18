package com.infinityappsolutions.server.lib.webvideo.actions;

import java.util.ArrayList;

import com.infinityappsolutions.lib.webvideo.beans.VideoFolderBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.webvideo.dao.mysql.VideoFolderDAO;

public class VideoFolderAction {
	private DAOFactory daoFactory;
	private VideoFolderDAO videofolderDAO;

	public VideoFolderAction(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		videofolderDAO = new VideoFolderDAO(daoFactory);
	}

	public ArrayList<VideoFolderBean> generateVideos() throws DBException,
			IASException {
		return videofolderDAO.getVideosByName();
	}

	/**
	 * 
	 * @param videofolderbean
	 * @return the id of the videofolderbean
	 * @throws DBException
	 */
	public long insertVideoFolder(VideoFolderBean videofolderbean)
			throws DBException {
		return videofolderDAO.insertFolderVideo(videofolderbean);
	}

	/**
	 * 
	 * @param videofolderbean
	 * @return the id of the videofolderbean
	 * @throws IASException
	 */
	public VideoFolderBean insertOrGetVideoFolder(
			VideoFolderBean videofolderbean) throws DBException {
		VideoFolderBean tmp = null;
		try {
			tmp = videofolderDAO.getVideoFolder(
					videofolderbean.getParentfolderid(),
					videofolderbean.getName(), videofolderbean.getPath());
		} catch (IASException e) {
			// video exists
		}
		if (tmp == null) {
			long liID = videofolderDAO.insertFolderVideo(videofolderbean);
			videofolderbean.setVideofolderid(liID);
			tmp = videofolderbean;
		}
		return tmp;
	}

	public void saveVideoFolder(VideoFolderBean video) throws DBException {
		videofolderDAO.editVideoFolder(video);
	}

	public void generateEmptyVideoFolder(LoggedInUserBean liub)
			throws DBException {
		videofolderDAO.insertFolderVideo(new VideoFolderBean(0, 0, "", "",
				false, false, false));
	}

	public void deleteVideoFolders(ArrayList<VideoFolderBean> videos)
			throws DBException {
		videofolderDAO.deleteVideos(videos);
	}

	public void saveVideoFolder(ArrayList<VideoFolderBean> videoList)
			throws DBException {
		videofolderDAO.saveVideos(videoList);
	}

}
