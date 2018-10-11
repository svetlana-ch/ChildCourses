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
import by.htp.courses.service.impl.UserServiceImpl;

public class SignUp implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(SignUp.class);
	
	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	private static final String NAME_PARAM_NAME = "name";
	private static final String EMAIL_PARAM_NAME = "e-mail";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login;
		String password;
		String name;
		String email;
		
		login = request.getParameter(LOGIN_PARAM_NAME);
		password = request.getParameter(PASSWORD_PARAM_NAME);
		name = request.getParameter(NAME_PARAM_NAME);
		email = request.getParameter(EMAIL_PARAM_NAME);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();		
		User user = null;			
		String goToPage = null;		
		
		try {
			if (request.getMethod().toUpperCase().equals("POST")) {
				user = userService.signup(name, login, email, password);
				if (user != null) {
					 request.getSession(true).setAttribute("user", user);					
					goToPage = JSPPagePath.MAIN_PAGE;
				} else {
					request.setAttribute("errorRegistrationMessage", "User is not created.");
					logger.error("registration error");
					goToPage = JSPPagePath.SIGN_UP;
				}
			}

		} catch (ServiceException e) {			
            request.setAttribute("errorMessage", e.getMessage());
			logger.error("registration exception", e);
			logger.error("error      {}", e.getMessage());
			goToPage = JSPPagePath.SIGN_UP;
			
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);				
		
	}

}
