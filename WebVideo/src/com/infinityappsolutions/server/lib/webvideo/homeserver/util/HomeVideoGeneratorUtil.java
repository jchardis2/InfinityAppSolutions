package com.infinityappsolutions.server.lib.webvideo.homeserver.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.infinityappsolutions.lib.security.SecureHashUtil;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.lib.webvideo.util.VideoUtil;

public class HomeVideoGeneratorUtil {
	File rootFile;
	ArrayList<VideoBean> videoBeans;
	ArrayList<String> errors;
	String rootFileName;
	int rootFilePathIndex;

	public HomeVideoGeneratorUtil() {
		videoBeans = new ArrayList<VideoBean>();
		errors = new ArrayList<String>();
		rootFileName = VideoUtil.HOME_DIR;
		rootFilePathIndex = rootFileName.length();
		rootFile = new File(rootFileName);
	}

	public ArrayList<VideoBean> generateVideos() {
		generateVideoBeans(rootFile);
		return videoBeans;
	}

	private void generateVideoBeans(File parentDir) {
		System.out.println("parentDir.isDirectory()" + parentDir.isDirectory());
		if (!parentDir.isDirectory()) {
			parentDir = new File(parentDir.getAbsolutePath());
		}
		System.out.println(parentDir.getAbsolutePath());
		File fileList[] = parentDir.listFiles();
		for (File file : fileList) {
			if (file.isFile()) {
				String filePath = file.getAbsolutePath().substring(
						rootFilePathIndex);
				if (filePath.toLowerCase().endsWith(".mp4")
						|| filePath.toLowerCase().endsWith(".mkv")
						|| filePath.toLowerCase().endsWith(".mov")) {

					String filename = file.getName();

					String fileUrl = VideoUtil.HOME_URL + filePath;
					SecureHashUtil hashUtil = new SecureHashUtil();
					// String hash = hashUtil.sha256Hash(file);
					VideoBean videoBean = new VideoBean(0, filename, "",
							fileUrl, filePath, "");
					videoBeans.add(videoBean);
//					errors.add("There was an error reading " + filename);
				}
			} else {
				generateVideoBeans(file);
			}
		}

	}
}
