package com.infinityappsolutions.server.webvideo.util;

import java.util.ArrayList;

public class VideoUtil {
	public static String HOME_VIDEO_URL = "home.infinityappsolutions.com/video";
	public static String HOME_VIDEO_DIR = "/media/BigDaddy/videos";
	public static String SERVER_VIDEO_DIR = "/media/BigDaddy/videos";
	public static String SERVER_VIDEO_TV_DIR = "/media/BigDaddy/videos/tv";
	public static String SERVER_VIDEO_MOVIES_DIR = "/media/BigDaddy/videos/movies";

	public static String VIDEO_DEFAULT_IMAGE_URL = "home.infinityappsolutions.com:8080/webvideo/images/movie.png";

	public static ArrayList<String> getAcceptedVideoExtensions() {
		ArrayList<String> acceptedExt = new ArrayList<>();
		acceptedExt.add(".mp4");
		acceptedExt.add(".mov");
		acceptedExt.add(".mkv");   
		return acceptedExt;
	}
 
	public static String getHomeFilePath(String path) {
		return HOME_VIDEO_DIR + path;
	}
	
}
