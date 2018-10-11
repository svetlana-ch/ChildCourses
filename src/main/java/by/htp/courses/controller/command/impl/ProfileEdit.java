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

public class ProfileEdit implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileEdit.class);
	
	private static final String ID_PARAM_NAME = "id";
	private static final String NAME_PARAM_NAME = "name";
	private static final String EMAIL_PARAM_NAME = "e-mail";
	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	private static final String ROLE_PARAM_NAME = "role";
	private static final String POST = "POST";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
		String login = request.getParameter(LOGIN_PARAM_NAME);
		String password = request.getParameter(PASSWORD_PARAM_NAME);
		String name= request.getParameter(NAME_PARAM_NAME);
		String email = request.getParameter(EMAIL_PARAM_NAME);
		String role = request.getParameter(ROLE_PARAM_NAME);
		
		String goToPage = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		try {			
			if (request.getMethod().toUpperCase().equals(POST)) {
				
				User user = null;			

				logger.info("Updating iser id = {}", id);

				user = new User();
				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setRole(role);
				user.setLogin(login);
				user.setPassword(password);
				
				userService.update(user);	
				request.getSession().setAttribute("user", user);
				goToPage = JSPPagePath.PROFILE_EDIT;
			}
			
		}catch (ServiceException e) {
			request.setAttribute("errorMessage", "error");
			goToPage = JSPPagePath.ERROR_PAGE;
			logger.error("ServiceException  {}", e);
		}	
		

		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);			
		

	

}

}
