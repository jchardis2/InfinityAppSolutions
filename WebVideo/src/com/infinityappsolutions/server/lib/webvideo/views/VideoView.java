package com.infinityappsolutions.server.lib.webvideo.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.faces.IASRootFacesProvider;
import com.infinityappsolutions.server.lib.views.JSFView;
import com.infinityappsolutions.server.lib.webvideo.actions.UploadVideoAction;
import com.infinityappsolutions.server.lib.webvideo.actions.VideoAction;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;

@ViewScoped
@ManagedBean(name = "videoView")
public class VideoView extends JSFView implements Serializable {
	private static final long serialVersionUID = 6752442236530393770L;
	public ArrayList<VideoBean> videoList;
	public ArrayList<VideoBean> selectedVideos;
	public VideoBean selectedVideo;
	public VideoBean savedVideo;
	public boolean isSelected;
	public HashMap<Long, Boolean> checkedMap;

	public VideoView() {
		checkedMap = new HashMap<>();
		selectedVideos = new ArrayList<>();
		generateVideos();
	}

	public String generateVideos() {
		VideoAction videoAction = new VideoAction(
				DAOFactory.getProductionInstance());
		try {
			videoList = videoAction.generateVideos();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	public String uploadVideos() {
		UploadVideoAction uploadVideoAction = new UploadVideoAction();
		selectedVideos = getCheckedVideos();
		for (VideoBean vb : selectedVideos) {
			try {
				uploadVideoAction.upload(vb);
			} catch (Exception e) {
				addMessage("Failure", "Failed to upload" + vb.getName());
			}
		}
		return null;
	}

	public void saveVideo(VideoBean video) {
		VideoAction generateVideosAction = new VideoAction(
				DAOFactory.getProductionInstance());

		try {
			generateVideosAction.saveVideo(getSelectedVideo());
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateEmptyVideo(ActionEvent event) {
		VideoAction generateVideosAction = new VideoAction(
				DAOFactory.getProductionInstance());

		try {
			generateVideosAction.generateEmptyVideo(IASRootFacesProvider
					.getInstance().getLoggedInUserBean());
			generateVideos();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String selectVideo(ActionEvent event) {
		VideoBean video = (VideoBean) event.getComponent().getAttributes()
				.get("selectedVideo");
		System.out.println("Video name:" + video.getName());
		if (video != null)
			selectedVideos.add(video);
		return null;
	}

	public String unSelectVideo(ActionEvent event) {
		VideoBean video = (VideoBean) event.getComponent().getAttributes()
				.get("selectedVideo");
		if (video != null)
			selectedVideos.remove(video);
		return null;
	}

	public void deleteVideos(ActionEvent event) {
		VideoAction generateVideosAction = new VideoAction(
				DAOFactory.getProductionInstance());
		ArrayList<VideoBean> checkedItems = getCheckedVideos();
		try {
			generateVideosAction.deleteVideos(checkedItems);
			generateVideos();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveVideos(ActionEvent event) {
		VideoAction generateVideosAction = new VideoAction(
				DAOFactory.getProductionInstance());
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String string = (String) names.nextElement();
			System.out.println(string);
		}
		Collection<String[]> values = req.getParameterMap().values();
		for (String[] strings : values) {
			for (String string : strings) {
				System.out.println(string);
			}
		}
		try {
			generateVideosAction.saveVideos(videoList);
			generateVideos();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectVideo() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String video = params.get("video");
		Boolean b = checkedMap.get(video);

		System.out.println("Boolean: " + b);

	}

	protected ArrayList<VideoBean> getCheckedVideos() {
		ArrayList<VideoBean> checkedItems = new ArrayList<VideoBean>();
		Iterator<Long> it = checkedMap.keySet().iterator();
		for (VideoBean item : videoList) {
			if (checkedMap.get(item.getId())) {
				checkedItems.add(item);
			}
		}
		return checkedItems;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public ArrayList<VideoBean> getVideoList() {
		return videoList;
	}

	public void setVideoList(ArrayList<VideoBean> videoList) {
		this.videoList = videoList;
	}

	public ArrayList<VideoBean> getSelectedVideos() {
		return selectedVideos;
	}

	public void setSelectedVideos(ArrayList<VideoBean> selectedVideos) {
		this.selectedVideos = selectedVideos;
	}

	public VideoBean getSelectedVideo() {
		return selectedVideo;
	}

	public void setSelectedVideo(VideoBean selectedVideo) {
		this.selectedVideo = selectedVideo;
	}

	public VideoBean getSavedVideo() {
		return savedVideo;
	}

	public void setSavedVideo(VideoBean savedVideo) {
		this.savedVideo = savedVideo;
		saveVideo(savedVideo);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public HashMap<Long, Boolean> getCheckedMap() {
		return checkedMap;
	}

	public void setCheckedMap(HashMap<Long, Boolean> checkedMap) {
		this.checkedMap = checkedMap;
	}

}
