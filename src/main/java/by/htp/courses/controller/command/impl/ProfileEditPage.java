package by.htp.courses.controller.command.impl;

import java.io.IOException;

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

public class ProfileEditPage implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileEditPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String login = user.getLogin();
		
		String goToPage = null;

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			user = userService.getUser(login);
			request.setAttribute("user", user);
			request.getSession().setAttribute("user", user);
				
			goToPage = JSPPagePath.PROFILE_EDIT;
			

		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "error");
			goToPage = JSPPagePath.ERROR_PAGE;
			logger.info("error in edit profile");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);	

	}
}
