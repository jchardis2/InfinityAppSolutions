package com.infinityappsolutions.server.lib.webvideo.homeserver.views;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;

@ViewScoped
@ManagedBean(name = "syncedHomeVideoView")
public class SyncedHomeVideoView implements Serializable {
	private static final long serialVersionUID = 8929300313225762408L;
	private ArrayList<VideoBean> videoList = new ArrayList<VideoBean>();
	private int notAdded;

	@SuppressWarnings("unchecked")
	public SyncedHomeVideoView() {
		notAdded = (int) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false))
				.getAttribute("notAdded");
		videoList = (ArrayList<VideoBean>) ((HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false))
				.getAttribute("addedVideoBeans");
	}

	public ArrayList<VideoBean> getVideoList() {
		return videoList;
	}

	public void setVideoList(ArrayList<VideoBean> videoList) {
		this.videoList = videoList;
	}

	public int getNotAdded() {
		return notAdded;
	}

	public void setNotAdded(int notAdded) {
		this.notAdded = notAdded;
	}

}
