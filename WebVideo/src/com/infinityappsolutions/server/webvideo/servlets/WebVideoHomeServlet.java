package com.infinityappsolutions.server.webvideo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webvideo.beans.VideoBean;
import com.infinityappsolutions.server.lib.beans.LoggedInUserBean;

public class WebVideoHomeServlet extends VideoGeneratorServlet {
	private static final long serialVersionUID = 1458278335635638361L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoggedInUserBean liub = (LoggedInUserBean) session
				.getAttribute("loggedInUserBean");

		DataBean<VideoBean> dataBean = new DataBean<VideoBean>();
		dataBean.setUserBean(liub);
		IASGson<DataBean<VideoBean>> iasGson = new IASGson<DataBean<VideoBean>>();
		String dataBeanGsonString = iasGson.toGson(dataBean);
		String requestURI = request.getRequestURI();
		response.setHeader("Location", requestURI);
		response.getWriter().write(dataBeanGsonString);
	}
}
