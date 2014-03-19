package com.infinityappsolutions.server.lib.webvideo.homeserver.views;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.lib.webvideo.util.VideoUtil;
import com.infinityappsolutions.server.lib.webvideo.views.ServerVideoView;

@ViewScoped
@ManagedBean(name = "homeVideoView")
public class HomeVideoView extends ServerVideoView implements Serializable {
	private static final long serialVersionUID = 4283536377928247297L;
	private long renameId = -1;

	public HomeVideoView() {
		checkedMap = new HashMap<>();
		selectedVideos = new ArrayList<>();
		generateVideos();
	}

	public String generateVideos() {
		return super.generateVideos();
	}

	public void saveVideo(VideoBean video) {
		super.saveVideo(video);
	}

	public void generateEmptyVideo(ActionEvent event) {
		super.generateEmptyVideo(event);
	}

	public String selectVideo(ActionEvent event) {
		return super.selectVideo(event);
	}

	public String unSelectVideo(ActionEvent event) {
		return super.unSelectVideo(event);
	}

	public void deleteVideos(ActionEvent event) {
		super.deleteVideos(event);
	}

	public void saveVideos(ActionEvent event) {
		super.saveVideos(event);
	}

	public void selectVideo() {
		super.selectVideo();
	}

	public String renameVideo() {
		String videoPath = VideoUtil.HOME_DIR + selectedVideo.getFile();
		File file = new File(videoPath);
		return null;
	}

	protected ArrayList<VideoBean> getCheckedVideos() {
		return super.getCheckedVideos();
	}

	public void handleFileUpload(FileUploadEvent event) {
		super.handleFileUpload(event);

	}

	public long getRenameId() {
		return renameId;
	}

	public void setRenameId(long renameId) {
		this.renameId = renameId;
	}
	
	public void renameVideo(VideoBean video){
		System.out.println(video.getName());
	}

}
