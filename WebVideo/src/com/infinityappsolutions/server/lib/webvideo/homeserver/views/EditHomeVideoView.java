package com.infinityappsolutions.server.lib.webvideo.homeserver.views;

import java.io.File;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.lib.webvideo.util.VideoUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.dao.mysql.VideoDAO;

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
		String oldPath = VideoUtil.HOME_DIR + editVideoBean.getFile();
		String newPath = oldPath;
		String fileSeperator = System.getProperty("file.separator");
		if (!editDirtyVideoBean.getName().equals(editVideoBean.getName())) {
			int fileSep = newPath.lastIndexOf(System
					.getProperty("file.separator"));
			newPath = newPath.substring(0, fileSep)
					+ editDirtyVideoBean.getName();
		}
		SecurityManager manager = new SecurityManager();

		try {
			int fileSep = newPath.lastIndexOf(System
					.getProperty("file.separator"));
			manager.checkWrite(newPath.substring(0, fileSep));
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File(oldPath);
		System.out.println("File exists: " + file.exists());
		System.out.println("File Path: " + file.getPath() + "\t or " + oldPath);
		System.out.println("File New Path: " + newPath);
		File newFile = new File(newPath);
		boolean success = file.renameTo(newFile);

		VideoDAO videoDAO = new VideoDAO(DAOFactory.getProductionInstance());
		try {
			videoDAO.editVideo(editDirtyVideoBean);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (success) {
				success = false;
				newFile.renameTo(new File(oldPath));
			}
		}

		FacesMessage msg;
		if (success) {

			msg = new FacesMessage("Success", editVideoBean.getName()
					+ "has been moved to" + newPath);
		} else {
			msg = new FacesMessage("Failure", editVideoBean.getName()
					+ "has not moved to" + newPath);
			if (editVideoBean != null) {
				editDirtyVideoBean = cloneVideoBean(editVideoBean);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}

	private VideoBean cloneVideoBean(VideoBean bean) {
		VideoBean newBean = new VideoBean();
		newBean = new VideoBean();
		newBean.setId(editVideoBean.getId());
		newBean.setName(editVideoBean.getName());
		newBean.setType(editVideoBean.getType());
		newBean.setUrl(editVideoBean.getUrl());
		newBean.setFile(editVideoBean.getFile());
		return newBean;
	}
}
