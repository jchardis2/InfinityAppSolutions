package com.infinityappsolutions.server.lib.webvideo.views;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.FileTypeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import com.infinityappsolutions.lib.util.FileMapUtil;
import com.infinityappsolutions.lib.util.FileTypeMapUtil;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.dao.mysql.VideoDAO;
import com.infinityappsolutions.server.lib.webvideo.homeserver.util.HomeVideoGeneratorUtil;
import com.infinityappsolutions.server.lib.webvideo.views.VideoView;
import com.infinityappsolutions.server.webvideo.util.VideoFileMapper;
import com.infinityappsolutions.server.webvideo.util.VideoUtil;

/**
 * View for Videos that have been uploaded to the server
 * 
 * @author jchardis
 * 
 */
@ViewScoped
@ManagedBean(name = "serverVideoView")
public class ServerVideoView extends VideoView implements Serializable {
	private static final long serialVersionUID = 4283536377928247297L;
	private VideoBean editVideoBean;
	private ArrayList<String> addedVideoBeans;
	private int notAdded = 0;

	public ServerVideoView() {
		checkedMap = new HashMap<>();
		selectedVideos = new ArrayList<>();
		generateVideos();
	}

	public String generateVideos() {
		try {
			FileTypeMapUtil<VideoBean> fileTypeMapUtil = new FileTypeMapUtil<>(
					VideoUtil.SERVER_VIDEO_DIR, new VideoFileMapper());
			fileTypeMapUtil.setAcceptExtenstions(VideoUtil
					.getAcceptedVideoExtensions());
			videoList = fileTypeMapUtil.generateFileList();
		} catch (Exception e) {
			addMessage("Failure", "Unable to generate videos.");
		}
		return null;
	}

	public String syncVideos() {
		VideoDAO dao = new VideoDAO(DAOFactory.getProductionInstance());
		for (VideoBean videoBean : videoList) {
			try {
				VideoBean existBean = dao.getVideo(videoBean.getName(),
						videoBean.getHash());

				if (existBean == null) {
					try {
						dao.insertVideo(videoBean);
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					dao.editVideo(videoBean);
					notAdded++;
				}
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IASException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).setAttribute("notAdded", notAdded);
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).setAttribute("addedVideoBeans",
				addedVideoBeans);
		// try {
		// FacesContext.getCurrentInstance().getExternalContext()
		// .redirect("home-sync-video.xhtml");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return "home-sync-video.xhtml";
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

	public String renameVideo() {
		String videoPath = VideoUtil.SERVER_VIDEO_DIR + selectedVideo.getFile();
		File file = new File(videoPath);
		return null;
	}

	protected ArrayList<VideoBean> getCheckedVideos() {
		return super.getCheckedVideos();
	}

	public void handleFileUpload(FileUploadEvent event) {
		super.handleFileUpload(event);

	}

	public void setEditVideo(VideoBean editVideoBean) {
		this.editVideoBean = editVideoBean;
		try {
			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).setAttribute(
					"editVideoBean", editVideoBean);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("home-edit-video.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public VideoBean getEditVideoBean() {
		return editVideoBean;
	}

	public void setEditVideoBean(VideoBean editVideoBean) {
		this.editVideoBean = editVideoBean;
	}

}
