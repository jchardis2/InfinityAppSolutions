package com.infinityappsolutions.lib.webvideo.beans;

public class VideoBean {
	private long id;
	private String name;
	private String type;
	private String url;
	private String file;

	public VideoBean() {
	}

	public VideoBean(long id, String name, String type, String url, String file) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
