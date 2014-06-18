package com.infinityappsolutions.server.lib.webvideo.homeserver.util;

import java.io.File;
import java.util.ArrayList;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.lib.webvideo.beans.VideoFolderBean;
import com.infinityappsolutions.lib.webvideo.util.WebVideoUtil;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.webvideo.actions.VideoFolderAction;
import com.infinityappsolutions.server.webvideo.dao.DAOFactory;
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
		generateVideoBeans(rootFile, videoBeans, getRootVideoFolder());
		return videoBeans;
	}

	public ArrayList<VideoBean> generateVideosByPaths(ArrayList<String> paths,
			ArrayList<VideoBean> videoBeans) {
		for (String path : paths) {
			videoBeans = generateVideoBeans(path, videoBeans);
		}
		generateVideoBeans(rootFile, videoBeans, getRootVideoFolder());
		return videoBeans;
	}

	public ArrayList<VideoBean> generateVideoBeans(String pathname,
			ArrayList<VideoBean> videoBeans) {
		File file = new File(pathname);
		return generateVideoBeans(file, videoBeans, getRootVideoFolder());
	}

	private ArrayList<VideoBean> generateVideoBeans(File parentDir,
			ArrayList<VideoBean> videoBeans, VideoFolderBean parentfolder) {
		try {

			if (!parentDir.isDirectory()) {
				parentDir = new File(parentDir.getAbsolutePath());
			}
			File fileList[] = parentDir.listFiles();
			VideoFolderBean childVideoFolderBean = null;
			for (File file : fileList) {
				String filePath = file.getAbsolutePath().substring(
						rootFilePathIndex);
				// System.out.println(filePath);
				if (file.isFile()) {
					if (filePath.toLowerCase().endsWith(".mp4")
							|| filePath.toLowerCase().endsWith(".mkv")
							|| filePath.toLowerCase().endsWith(".mov")) {

						String filename = file.getName();

						String fileUrl = VideoUtil.HOME_VIDEO_URL + filePath;
						fileUrl = fileUrl.replaceAll(" ", "%20");
						// try {
						// fileUrl = new URI(fileUrl).toURL().toString();
						// } catch (MalformedURLException | URISyntaxException
						// e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// fileUrl = fileUrl.replaceAll(" ", "%20");
						// }
						// SecureHashUtil hashUtil = new SecureHashUtil();
						// String hash = hashUtil.sha256Hash(file);
						VideoBean videoBean = new VideoBean(0,parentfolder.getVideofolderid(), filename, "",
								fileUrl, filePath, "",
								VideoUtil.VIDEO_DEFAULT_IMAGE_URL);
						videoBeans.add(videoBean);
						// errors.add("There was an error reading " + filename);
					}
				} else {
					childVideoFolderBean = new VideoFolderBean();
					String subFilePath = filePath.substring(getLocalFilePath(
							parentDir).length());
					String subFileName = filePath.substring(getLocalFilePath(
							parentDir).length() + 1);
					if (filePath.startsWith(getFileSeperator()
							+ WebVideoUtil.VIDEO_TYPE_MOVIE)) {
						childVideoFolderBean.setMovie(true);
					} else if (filePath.startsWith(getFileSeperator()
							+ WebVideoUtil.VIDEO_TYPE_TV)) {

						if (subFilePath.toLowerCase().contains(
								WebVideoUtil.VIDEO_TV_DELIMETER)) {
							childVideoFolderBean.setSeason(true);
						} else {
							childVideoFolderBean.setShow(true);
						}

					}
					childVideoFolderBean.setPath(filePath);
					childVideoFolderBean.setParentfolderid(parentfolder
							.getVideofolderid());
					childVideoFolderBean.setName(subFileName);
					VideoFolderAction folderAction = new VideoFolderAction(
							DAOFactory.getProductionInstance());
					try {
						childVideoFolderBean = folderAction
								.insertOrGetVideoFolder(childVideoFolderBean);
						generateVideoBeans(file, videoBeans,
								childVideoFolderBean);
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			return videoBeans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoBeans;
	}

	private String getFileSeperator() {
		return System.getProperty("file.separator");
	}

	// returns the file path for the url
	private String getLocalFilePath(File file) {
		return file.getAbsolutePath().substring(rootFilePathIndex);
	}

	private VideoFolderBean getRootVideoFolder() {

		String subFilePath = rootFileName;
		VideoFolderBean rootFolderBean = new VideoFolderBean();
		rootFolderBean.setPath(subFilePath);
		rootFolderBean.setName(rootFileName);
		rootFolderBean.setVideofolderid(1);
		rootFolderBean.setParentfolderid(1);
		return rootFolderBean;
	}
}
