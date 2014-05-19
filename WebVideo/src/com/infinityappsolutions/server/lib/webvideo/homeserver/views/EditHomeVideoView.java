package com.infinityappsolutions.server.lib.webvideo.homeserver.views;

import java.io.File;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.dao.mysql.VideoDAO;
import com.infinityappsolutions.server.webvideo.util.VideoUtil;

@ViewScoped
@ManagedBean(name = "editHomeVideoView")
public class EditHomeVideoView implements Serializable {
	private static final long serialVersionUID = -307078284286577623L;
	private VideoBean editVideoBean;
	private VideoBean editDirtyVideoBean;

	public EditHomeVideoView() {
		try {
			editVideoBean = (VideoBean) ((HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false)).getAttribute("editVideoBean");
			if (editVideoBean != null) {
				editDirtyVideoBean = cloneVideoBean(editVideoBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VideoBean getEditVideoBean() {
		return editVideoBean;
	}

	public void setEditVideoBean(VideoBean editVideoBean) {
		this.editVideoBean = editVideoBean;
	}

	public VideoBean getEditDirtyVideoBean() {
		return editDirtyVideoBean;
	}

	public void setEditDirtyVideoBean(VideoBean editDirtyVideoBean) {
		this.editDirtyVideoBean = editDirtyVideoBean;
	}

	public String editVideo() {
		VideoDAO videoDAO = new VideoDAO(DAOFactory.getProductionInstance());
		String oldPath = VideoUtil.SERVER_VIDEO_DIR + editVideoBean.getFile();
		String newPath = VideoUtil.SERVER_VIDEO_DIR + editDirtyVideoBean.getFile();
		String fileSeperator = System.getProperty("file.separator");
		boolean success = false;
		if (!editDirtyVideoBean.getFile().equals(editVideoBean.getFile())) {
			int fileSep = newPath.lastIndexOf(System
					.getProperty("file.separator"));
			File file = new File(oldPath);
			System.out.println("File exists: " + file.exists());
			System.out.println("File Path: " + file.getPath() + "\t or "
					+ oldPath);
			System.out.println("File New Path: " + newPath);
			File newFile = new File(newPath);
			success = file.renameTo(newFile);

			if (success) {
				editVideoBean = cloneVideoBean(editDirtyVideoBean);
				((HttpSession) FacesContext.getCurrentInstance()
						.getExternalContext().getSession(false)).setAttribute(
						"editVideoBean", editVideoBean);
				addMessage(new FacesMessage("Success", editVideoBean.getName()
						+ " has been moved to " + newPath));
				compareAndUpload();
			} else {
				addMessage(new FacesMessage("Failure", editVideoBean.getName()
						+ " has not moved to " + newPath));
				if (editVideoBean != null) {
					editDirtyVideoBean = cloneVideoBean(editVideoBean);
				}
			}
		}

		return null;
	}

	private void compareAndUpload() {
		VideoDAO videoDAO = new VideoDAO(DAOFactory.getProductionInstance());
		if (compareForUpload(editVideoBean, editDirtyVideoBean)) {
			try {
				// if video on server, edit name also
				if (videoDAO.getVideo(editVideoBean.getName(),
						editVideoBean.getHash()) != null) {
					videoDAO.editVideo(editDirtyVideoBean);
					addMessage(new FacesMessage("Success", editVideoBean.getName()
							+ " changes were uploaded to the server"));
				}else{
					addMessage(new FacesMessage("Info", editVideoBean.getName()
							+ " does not exist on the server"));
				}
				
			} catch (DBException e) {
				e.printStackTrace();
				addMessage(new FacesMessage("Failure", editVideoBean.getName()
						+ " change was not uploaded to the server"));
			}
		}
	}

	private void addMessage(FacesMessage msg) {
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private VideoBean cloneVideoBean(VideoBean bean) {
		VideoBean newBean = new VideoBean();
		newBean = new VideoBean();
		newBean.setId(bean.getId());
		newBean.setName(bean.getName());
		newBean.setType(bean.getType());
		newBean.setUrl(bean.getUrl());
		newBean.setFile(bean.getFile());
		return newBean;
	}

	private boolean compareForUpload(VideoBean one, VideoBean two) {
		if (one.getName() == two.getName() || one.getFile() == two.getFile()) {
			return true;
		}
		return false;

	}
}
