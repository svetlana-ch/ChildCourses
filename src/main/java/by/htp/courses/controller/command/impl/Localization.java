package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.courses.controller.command.Command;

public class Localization implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String local = request.getParameter("local");
		System.out.println("Локаль ==   " + local);
		request.getSession(true).setAttribute("local", local);
		
		
		String referer = request.getHeader("Referer");		
		response.sendRedirect(referer);
		
		 
		//RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.MAIN_PAGE);
		//dispatcher.forward(request, response);
		
	}

}
