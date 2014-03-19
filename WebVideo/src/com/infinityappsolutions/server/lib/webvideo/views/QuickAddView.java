package com.infinityappsolutions.server.lib.webvideo.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.eclipse.jetty.util.URIUtil;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.lib.webvideo.util.VideoUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.dao.mysql.VideoDAO;

@SessionScoped
@ManagedBean(name = "quickAddView")
public class QuickAddView implements Serializable {
	private static final long serialVersionUID = -7383937855943372442L;

	private String videoUrlList;

	public QuickAddView() {
	}

	public String getVideoUrlList() {
		return videoUrlList;
	}

	public void setVideoUrlList(String videoUrlList) {
		this.videoUrlList = videoUrlList;
	}

	public String quickAdd() {
		ArrayList<VideoBean> videoBeans = new ArrayList<>();
		Scanner scan = new Scanner(videoUrlList);

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			VideoBean video = new VideoBean();
			int lio = line.lastIndexOf("/");
			// if (lio + 1 < line.substring(lio).length()) {
			lio++;
			// }
			String name = line.substring(lio);
			video.setName(name);
			video.setType("HD");
			String url = VideoUtil.HOME_URL + line;
			url = URIUtil.encodePath(url);
			video.setUrl(url);
			String filename = VideoUtil.HOME_DIR + line;
			video.setFile(filename);
			videoBeans.add(video);
		}
		VideoDAO dao = new VideoDAO(DAOFactory.getProductionInstance());
		dao.dropAllVideos();

		try {
			dao.insertVideo(videoBeans);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
