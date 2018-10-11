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

public class UsersSearch implements Command{
	private static final Logger logger = LoggerFactory.getLogger(UsersSearch.class);
	
	private static final String SEARCHTYPE_PARAM_NAME = "searchtype";
	private static final String SEARCHTERM_PARAM_NAME = "searchterm";
	private static final String POST = "POST";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!((User) request.getSession().getAttribute("user")).getRole().equals("ADMIN") ) {		
			request.setAttribute("errorAccessMessage", "Вам необходимо войти либо зарегистрироваться!");			
			request.getRequestDispatcher(JSPPagePath.ERROR_PAGE).forward(request, response);
            return;
		}
		

		String searchtype;
		String searchterm;
		
		searchtype = request.getParameter(SEARCHTYPE_PARAM_NAME);
		searchterm = request.getParameter(SEARCHTERM_PARAM_NAME);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		

		List<User> users = null;			
		String goToPage = null;	
				
		try {			
			if (request.getMethod().toUpperCase().equals(POST)) {
				 users = userService.search(searchtype, searchterm);               
            }			   
           	
			if(users != null){
				request.setAttribute("users", users);
				goToPage = JSPPagePath.USERS_SEARCH;
			}else{				
				goToPage = JSPPagePath.USERS_EDIT;
			}		
					
			
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "error");
			goToPage = JSPPagePath.ERROR_PAGE;
			 logger.error("ServiceException  {}", e);
			
		}	
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);	
		
	}

}
