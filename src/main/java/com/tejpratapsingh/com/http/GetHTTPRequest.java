package com.tejpratapsingh.com.http;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetHTTPRequest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");

		StringBuilder resposenJSONString = new StringBuilder();
		StringBuilder headerJSONString = new StringBuilder();
		StringBuilder argsJSONString = new StringBuilder();

		headerJSONString.append("{");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerJSONString.append(String.format("\"%s\":\"%s\"%s", headerName, request.getHeader(headerName), headerNames.hasMoreElements() ? ",":""));
		}
		headerJSONString.append("}");

		argsJSONString.append("{");
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			argsJSONString.append(String.format("\"%s\":\"%s\"%s", paramName, request.getParameter(paramName), params.hasMoreElements() ? ",":""));
		}
		argsJSONString.append("}");
		
		resposenJSONString.append(String.format("{\"%s\":%s,", "headers", headerJSONString));
		resposenJSONString.append(String.format("\"%s\":%s}", "args", argsJSONString));
		response.getWriter().print(resposenJSONString);
	}
}