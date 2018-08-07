package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class SignIn implements Command{
	
	private static final Logger log = LogManager.getLogger(SignIn.class.getName());

	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	 

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String password;
		
		login = request.getParameter(LOGIN_PARAM_NAME);
		password = request.getParameter(PASSWORD_PARAM_NAME);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		User user = null;
		String goToPage = null;
		
		if (request.getMethod().toUpperCase().equals("POST")) {

			try {
				user = userService.signin(login, password);
				if (user != null) {
					request.getSession(true).setAttribute("user", user);
					goToPage = JSPPagePath.MAIN_PAGE;
					log.debug("Test debug   User " + user.getName() + " SignIn");
				} else {
					request.setAttribute("errorMessage", "Incorrect login or password.");
					goToPage = JSPPagePath.MAIN_PAGE;
				}

			} catch (ServiceException e) {
				// goToPage = JSPPagePath.ERROR_PAGE;
				log.debug("Test debug   User " + user.getName() + " hasn`t sign in");
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);

			dispatcher.forward(request, response);
		}

	}

}
