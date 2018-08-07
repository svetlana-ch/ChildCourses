package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

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
	
	private static final String ID_PARAM_NAME = "id";	
	private static final String NAME_PARAM_NAME = "name";
	private static final String EMAIL_PARAM_NAME = "e-mail";
	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	private static final String ROLE_PARAM_NAME = "role";
	
	
	private static final String SEARCHTYPE_PARAM_NAME = "searchtype";
	private static final String SEARCHTERM_PARAM_NAME = "searchterm";	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String login;
		String password;
		String name;
		String email;
		String role;
		String searchtype;
		String searchterm;
		
		login = request.getParameter(LOGIN_PARAM_NAME);
		password = request.getParameter(PASSWORD_PARAM_NAME);
		name = request.getParameter(NAME_PARAM_NAME);
		email = request.getParameter(EMAIL_PARAM_NAME);
		role = request.getParameter(ROLE_PARAM_NAME);
		searchtype = request.getParameter(SEARCHTYPE_PARAM_NAME);
		searchterm = request.getParameter(SEARCHTERM_PARAM_NAME);
		
		
		System.out.println(login + password + name);
					
		
				
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		System.out.println("Мы в  EditUsers.java");

		List<User> users = null;
		User user = null;
			
		String goToPage = null;	
				
		try {	
			
			if (request.getMethod().toUpperCase().equals("POST")) {
				int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));

				if (request.getParameter("update") != null) {
					String id222 = request.getParameter("id");
					System.out.println("НАЖАЛИ НА UPDATE     " + id222);// проверка ip в текстовом формате

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
				

				if (request.getParameter("delete") != null) {

					userService.delete(id);
					System.out.println(
							"НАЖАЛИ НА DELETE  id==   " + id + "   " + login + "   " + name + "   " + password);

					goToPage = JSPPagePath.USERS_EDIT;
				}

			}
			   
            users = userService.getAll("");
			System.out.println("Users ==   "   + users);
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
