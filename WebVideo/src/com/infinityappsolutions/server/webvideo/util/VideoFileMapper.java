package com.infinityappsolutions.server.webvideo.util;

import java.io.File;

import com.infinityappsolutions.lib.interfaces.IFileMap;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
public class VideoFileMapper implements IFileMap<VideoBean> {

	@Override
	public VideoBean getType(File file) {
		String filename = file.getName();
		String filePath = file.getAbsolutePath();
		String fileUrl = VideoUtil.HOME_VIDEO_URL + filePath;
		fileUrl = fileUrl.replaceAll(" ", "%20");
		// try {
		// fileUrl = new URI(fileUrl).toURL().toString();
		// } catch (MalformedURLException | URISyntaxException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// fileUrl = fileUrl.replaceAll(" ", "%20");
		// }
		// SecureHashUtil hashUtil = new SecureHashUtil();
		// String hash = hashUtil.sha256Hash(file);
		return new VideoBean(0, 1L, filename, "", fileUrl, filePath, "",
				VideoUtil.VIDEO_DEFAULT_IMAGE_URL);  
	}
    
	public VideoBean getNextFile() {
		return null;
	} 

}
