package com.infinityappsolutions.server.webvideo.actions;

import java.util.ArrayList;

import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUsersOrgContainerBean;
import com.infinityappsolutions.server.lib.beans.OrgUserBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.webvideo.dao.mysql.VideoDAO;

public class VideoAction {
	private DAOFactory daoFactory;

	public VideoAction(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ArrayList<VideoBean> generateVideos() throws DBException,
			IASException {
		VideoDAO dao = new VideoDAO(daoFactory);
		return dao.getVideos();
	}

	public void saveVideo(VideoBean video) throws DBException {
		VideoDAO videosDAO = new VideoDAO(daoFactory);
		videosDAO.editVideo(video);
	}

	public void generateEmptyVideo(LoggedInUserBean liub) throws DBException {
		VideoDAO videosDAO = new VideoDAO(daoFactory);
		videosDAO.insertVideo(new VideoBean(0L, "", "", ""));
	}

	public void setCurrentOrgUserBean(OrgUserBean oub) {
		LoggedInUsersOrgContainerBean liucb = IASRootFacesProvider
				.getInstance().getLoggedInUsersOrgContainerBean();
		liucb.setCurrentOrgUsersBean(oub);
	}

	public void deleteVideos(ArrayList<VideoBean> videos) throws DBException {
		VideoDAO videosDAO = new VideoDAO(daoFactory);
		videosDAO.deleteVideos(videos);
	}

	public void saveVideos(ArrayList<VideoBean> videoList) throws DBException {
		VideoDAO videoDAO = new VideoDAO(daoFactory);
		videoDAO.saveVideos(videoList);
	}

}
