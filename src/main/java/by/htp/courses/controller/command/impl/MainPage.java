package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.Child;
import by.htp.courses.service.ChildService;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.exception.ServiceException;



public class MainPage implements Command {
	
	private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ChildService childService = factory.getChildService();
		
		List<Child> children = null;
		
		try {
			children = childService.getAll();
			request.setAttribute("children", children);			

		} catch (ServiceException e1) {
			logger.error("Exception   {}", e1);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.MAIN_PAGE);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("Exception   {}", e);
		}

	}

}
