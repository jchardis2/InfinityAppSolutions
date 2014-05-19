package com.infinityappsolutions.server.lib.webvideo.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.infinityappsolutions.lib.webvideo.beans.VideoBean;

public class UploadVideoAction {
	public static final long MAX_VIDEO_FILE_SIZE = 10737418240L;// in bytes
	public static final String SERVER_VIDEO_FILE = "/home/jchardis/WebVideo/videos";

	public UploadVideoAction() {
		// TODO Auto-generated constructor stub
	}

	public void upload(VideoBean vb) throws IOException {
		URL url = new URL("http://"+vb.getUrl());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();

		long fileLength = connection.getContentLengthLong();
		if (acceptFile(fileLength)) {
			InputStream inputStream = connection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			FileOutputStream fileOutputStream = new FileOutputStream(
					SERVER_VIDEO_FILE + System.getProperty("file.separator")+ vb.getName());
			int count;
			byte buffer[] = new byte[1024];
			while ((count = bufferedInputStream.read(buffer, 0, buffer.length)) != -1)
				fileOutputStream.write(buffer, 0, count);
		}
	}

	/**
	 * 
	 * @param fileLength
	 * @return true if there is enough space for the file. False if otherwise
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public boolean acceptFile(long fileLength) throws IOException,
			NumberFormatException {
		long newFileLength = fileLength;// Long.parseLong(fileLength);
		File file = new File(SERVER_VIDEO_FILE);
		boolean success = true;
		if (!file.exists()) {
			success = file.createNewFile();
		}
		if (success) {
			Long length = file.length();
			if (newFileLength + length > MAX_VIDEO_FILE_SIZE) {
				return false;
			}
		}
		return success;
	}

}
