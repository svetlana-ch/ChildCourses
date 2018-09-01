package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private static final String POST = "POST";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String login = request.getParameter(LOGIN_PARAM_NAME);
		String password = request.getParameter(PASSWORD_PARAM_NAME);
		String name= request.getParameter(NAME_PARAM_NAME);
		String email = request.getParameter(EMAIL_PARAM_NAME);
		String role = request.getParameter(ROLE_PARAM_NAME);

		String goToPage = null;	
		
		
         /*   User user2 = (User) request.getSession().getAttribute("user");
            if (!user2.getRole().equals("ADMIN")){
                request.setAttribute("Error", "Wrong user. ");
                goToPage = JSPPagePath.USERS_EDIT;    // на страницу ошибок У вас нет прав доступа или на страницу входа
                
                RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);        		
        		dispatcher.forward(request, response);			
        		
            }*/
		
	
			
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
	
		List<User> users = null;
		User user = null;
							
		try {			
			if (request.getMethod().toUpperCase().equals(POST)) {
				int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
				if (request.getParameter(UPDATE_PARAM_NAME) != null) {

					logger.info("Updating iser id = {}", id);

					user = new User();
					user.setId(id);
					user.setName(name);
					user.setEmail(email);
					user.setRole(role);
					user.setLogin(login);
					user.setPassword(password);
					
					userService.update(user);					
					goToPage = JSPPagePath.USERS_EDIT;
				}
				

				if (request.getParameter(DELETE_PARAM_NAME) != null) {

					userService.delete(id);
					logger.info("Deleting iser id = {}", id);
					goToPage = JSPPagePath.USERS_EDIT;
				}
			}
			   
            users = userService.getAll("");			
			if(users != null){
				request.getSession(true).setAttribute("users", users);				
				goToPage = JSPPagePath.USERS_EDIT;
			}else{
				request.setAttribute("errorMessage", "ПОльзователей нет");
				goToPage = JSPPagePath.USERS_EDIT;//поменять
			}		
					
			
		} catch (ServiceException e) {
			//goToPage = JSPPagePath.ERROR_PAGE;
			// log
			e.printStackTrace();
		}	
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		
		dispatcher.forward(request, response);			
		
		
		}
	
	
}
