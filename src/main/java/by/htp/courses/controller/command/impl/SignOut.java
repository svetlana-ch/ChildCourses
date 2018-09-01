package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.courses.controller.command.Command;

public class SignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

	       
	            request.removeAttribute("user");
	            request.getSession().invalidate();
	            response.sendRedirect("index.jsp");
	       
		
			
		//RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.MAIN_PAGE);
		//dispatcher.forward(request, response);
		

		
	}

}
