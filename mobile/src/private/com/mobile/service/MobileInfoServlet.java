package com.mobile.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import nc.bs.framework.adaptor.IHttpServletAdaptor;

public class MobileInfoServlet implements IHttpServletAdaptor {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		String pwd = request.getParameter("pwd");
		pwd = new String(pwd.getBytes("ISO-8859-1"),"UTF-8");
		
		JsonObject resultJson = new JsonObject();
		resultJson.addProperty("helloword", ""+name+",ÄãºÃ£¬ÄãµÄÃÜÂëÊÇ£º"+pwd);
		
		response.setHeader("Conttent-type","text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultJson.toString());
		
	}

}
