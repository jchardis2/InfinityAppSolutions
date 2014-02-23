package com.infinityappsolutions.server.faces;

import com.infinityappsolutions.lib.server.faces.IASRootFacesProvider;

/**
 * A Singleton class for returning the different beans for production. This also
 * allows us to test jsf in some instances without running the server.
 * 
 * @author Jimmy Hardison
 * 
 */
public class FacesProvider extends IASRootFacesProvider {
	/**
	 * Static instance for each user
	 */
	private static FacesProvider fp;

	/**
	 * A Singleton class for returning the different beans for production
	 */
	protected FacesProvider() {

	}

	/**
	 * 
	 * @return An instance of IASRootFacesProvider
	 */
	public static FacesProvider getInstance() {
		if (fp == null) {
			fp = new FacesProvider();
		}
		return fp;
	}

}
