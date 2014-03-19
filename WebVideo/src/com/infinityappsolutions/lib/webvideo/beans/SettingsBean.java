package com.infinityappsolutions.lib.webvideo.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "settingsBean")
public class SettingsBean {
	private ArrayList<String> webSources;

	public SettingsBean() {
	}
}
