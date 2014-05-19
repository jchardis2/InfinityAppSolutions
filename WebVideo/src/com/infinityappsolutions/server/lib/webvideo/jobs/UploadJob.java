package com.infinityappsolutions.server.lib.webvideo.jobs;

import java.io.File;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.webvideo.util.VideoUtil;

public class UploadJob extends Job {

	public void uploadHomeVideo(VideoBean videoBean) {
		String path = VideoUtil.getHomeFilePath(videoBean.getFile());
		File file = new File(path);
	}
	
	public void uploadHomeVideoHTTP(VideoBean videoBean){
		
	}
}
