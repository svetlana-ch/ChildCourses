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
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class TeachersPage implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(TeachersPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		List<User> teachers = null;
		
		try {
			teachers = userService.search("role", "TEACHER");
			request.setAttribute("teachers", teachers);
			logger.info("родители   {}", teachers);

		} catch (ServiceException e1) {
			logger.error("Exception   {}", e1);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.TEACHERS_PAGE);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("Exception   {}", e);
		}
		
	}

}
