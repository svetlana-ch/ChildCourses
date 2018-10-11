package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class UsersEdit implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(UsersEdit.class);
	
	private static final String ID_PARAM_NAME = "id";	
	private static final String NAME_PARAM_NAME = "name";
	private static final String EMAIL_PARAM_NAME = "e-mail";
	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	private static final String ROLE_PARAM_NAME = "role";
	private static final String UPDATE_PARAM_NAME = "update";
	private static final String DELETE_PARAM_NAME = "delete";
	private static final String CREATE_PARAM_NAME = "create";
	private static final String POST = "POST";
	
	private static final String CURRENT_PAGE = "page";
    private static final int ELEMENTS_PER_PAGE = 25;
    private static final int DEFAULT_PAGE = 1;
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		if (!((User) request.getSession().getAttribute("user")).getRole().equals("ADMIN") ) {		
			request.setAttribute("errorAccessMessage", "Вам необходимо войти либо зарегистрироваться!");			
			request.getRequestDispatcher(JSPPagePath.ERROR_PAGE).forward(request, response);
            return;
		}
		
		String login = request.getParameter(LOGIN_PARAM_NAME);
		String password = request.getParameter(PASSWORD_PARAM_NAME);
		String name= request.getParameter(NAME_PARAM_NAME);
		String email = request.getParameter(EMAIL_PARAM_NAME);
		String role = request.getParameter(ROLE_PARAM_NAME);
		int page = DEFAULT_PAGE;
			
		if (request.getParameter(CURRENT_PAGE) != null) {
			page = Integer.parseInt(request.getParameter(CURRENT_PAGE));
		}			
				
		logger.info("Curent page {}", page);
		       	
			
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
	
		List<User> users = null;
		User user = null;
		String goToPage = null;	
							
		try {			
			if (request.getMethod().toUpperCase().equals(POST)) {				
				
				if (request.getParameter(CREATE_PARAM_NAME) != null) {	
					
					try {					
							user = userService.signup(name, login, email, password);
							if (user != null) {
								 request.getSession(true).setAttribute("user", user);					
								goToPage = JSPPagePath.MAIN_PAGE;
							} else {
								request.setAttribute("errorRegistrationMessage", "User is not created.");
								logger.error("registration error");
								goToPage = JSPPagePath.SIGN_UP;
							}	

					} catch (ServiceException e) {			
			            request.setAttribute("errorMessage", e.getMessage());
						logger.error("registration exception", e);
						logger.error("error      {}", e.getMessage());
						
					}											
				}

				
				if (request.getParameter(UPDATE_PARAM_NAME) != null) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));

					logger.info("Updating iser id = {}", id);
					logger.info("Curent page   {}", page);

					user = new User();
					user.setId(id);
					user.setName(name);
					user.setEmail(email);
					user.setRole(role);
					user.setLogin(login);
					user.setPassword(password);
					
					userService.update(user);					
					
				}
				
				if (request.getParameter(DELETE_PARAM_NAME) != null) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
					userService.delete(id);
					logger.info("Deleting iser id = {}", id);
					
				}
			}
			
			
            users = userService.getAll(page, ELEMENTS_PER_PAGE);	
            logger.info("Curent page   {}", page);
            int usersCount = userService.getAllUsersCount();
            int noOfPages = (int)Math.ceil(((double)usersCount)/ELEMENTS_PER_PAGE);              
            goToPage = JSPPagePath.USERS_EDIT;

            request.setAttribute("users", users);
            request.setAttribute("noOfPages", noOfPages);
            request.getSession().setAttribute("currentPage", page);
           
			
		} catch (ServiceException e) {
			goToPage = JSPPagePath.ERROR_PAGE;			
			e.printStackTrace();
		}			
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);			
		
		}	
}
