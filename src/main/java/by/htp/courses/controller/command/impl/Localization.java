package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;

public class Localization implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(Localization.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer URL = request.getRequestURL();
		String local = request.getParameter("local");
		logger.info("local ==  {} ", local);
		request.getSession().setAttribute("local", local);
		
		String previousQuery = (String) request.getSession().getAttribute("previousQuery");

		String nextPage = URL + "?" + previousQuery;
		logger.info("nextPage ==  {} ", nextPage);

		

		response.sendRedirect(nextPage);
		
		
		/*String referer = request.getHeader("Referer");		
		response.sendRedirect(referer);*/
		
	}

}
