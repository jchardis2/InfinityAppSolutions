package com.infinityappsolutions.server.webvideo.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.exceptions.DBException;
import com.infinityappsolutions.server.lib.exceptions.IASException;
import com.infinityappsolutions.server.lib.webvideo.dao.DAOFactory;
import com.infinityappsolutions.server.lib.webvideo.dao.mysql.VideoDAO;

public class VideoGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		VideoDAO videoDAO = new VideoDAO(DAOFactory.getProductionInstance());
		try {

			ArrayList<VideoBean> videoBeans = videoDAO.getVideos();
			IASGson<ArrayList<VideoBean>> iasGson = new IASGson<>();
			String jsonTermList = iasGson.toGson(videoBeans);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(jsonTermList);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
