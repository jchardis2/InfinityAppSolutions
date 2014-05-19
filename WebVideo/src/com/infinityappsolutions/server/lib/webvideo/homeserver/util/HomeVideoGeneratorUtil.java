package com.infinityappsolutions.server.lib.webvideo.homeserver.util;

import java.io.File;
import java.util.ArrayList;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.webvideo.util.VideoUtil;

public class HomeVideoGeneratorUtil {
	protected File rootFile;
	// protected ArrayList<VideoBean> videoBeans;
	protected ArrayList<String> errors;
	protected String rootFileName;
	protected int rootFilePathIndex;

	public HomeVideoGeneratorUtil() {
		// videoBeans = new ArrayList<VideoBean>();
		errors = new ArrayList<String>();
		rootFileName = VideoUtil.SERVER_VIDEO_DIR;
		rootFilePathIndex = rootFileName.length();
		rootFile = new File(rootFileName);
	}

	public ArrayList<VideoBean> generateAllVideos() {

		ArrayList<VideoBean> videoBeans = new ArrayList<VideoBean>();
		generateVideoBeans(rootFile, videoBeans);
		return videoBeans;
	}

	public ArrayList<VideoBean> generateVideosByPaths(ArrayList<String> paths,
			ArrayList<VideoBean> videoBeans) {
		for (String path : paths) {
			videoBeans = generateVideoBeans(path, videoBeans);
		}
		generateVideoBeans(rootFile, videoBeans);
		return videoBeans;
	}

	public ArrayList<VideoBean> generateVideoBeans(String pathname,
			ArrayList<VideoBean> videoBeans) {
		File file = new File(pathname);
		return generateVideoBeans(file, videoBeans);
	}

	private ArrayList<VideoBean> generateVideoBeans(File parentDir,
			ArrayList<VideoBean> videoBeans) {
		if (!parentDir.isDirectory()) {
			parentDir = new File(parentDir.getAbsolutePath());
		}
		File fileList[] = parentDir.listFiles();
		for (File file : fileList) {
			if (file.isFile()) {
				String filePath = file.getAbsolutePath().substring(
						rootFilePathIndex);
				System.out.println(filePath);
				if (filePath.toLowerCase().endsWith(".mp4")
						|| filePath.toLowerCase().endsWith(".mkv")
						|| filePath.toLowerCase().endsWith(".mov")) {

					String filename = file.getName();

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
					VideoBean videoBean = new VideoBean(0, filename, "",
							fileUrl, filePath, "",
							VideoUtil.VIDEO_DEFAULT_IMAGE_URL);
					videoBeans.add(videoBean);
					// errors.add("There was an error reading " + filename);
				}
			} else {
				generateVideoBeans(file, videoBeans);
			}
		}
		return videoBeans;
	}
}
