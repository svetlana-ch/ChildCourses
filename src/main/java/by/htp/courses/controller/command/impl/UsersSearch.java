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

public class UsersSearch implements Command{
	
	private static final String SEARCHTYPE_PARAM_NAME = "searchtype";
	private static final String SEARCHTERM_PARAM_NAME = "searchterm";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchtype;
		String searchterm;
		
		searchtype = request.getParameter(SEARCHTYPE_PARAM_NAME);
		searchterm = request.getParameter(SEARCHTERM_PARAM_NAME);
		
		System.out.println(searchtype + "    " + searchterm);		
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		System.out.println("Мы в  EditSeaerch.java");

		List<User> users = null;
		User user = null;
			
		String goToPage = null;	
				
		try {				
						
			if (request.getParameter("update") != null) {			        
               
                       
               userService.update(user);
               
         //      System.out.println("НАЖАЛИ НА   update id==   "+ id + "   " + login + "   " + name + "   " + password);
                
               goToPage = JSPPagePath.USERS_SEARCH;
            }
			
			
			
			   
            users = userService.search(searchtype, searchterm);
			System.out.println("Users ==   "   + users);
			if(users != null){
				request.getSession(true).setAttribute("users", users);
				//System.out.println("SIGN_UP2="+ users);
				//System.out.println("SIGN_UP2="+ users.size() + "   размер   " + users.toString());
				goToPage = JSPPagePath.USERS_SEARCH;
			}else{
				request.setAttribute("errorMessage", "ПОльзователей нет");
				goToPage = JSPPagePath.USERS_EDIT;
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
