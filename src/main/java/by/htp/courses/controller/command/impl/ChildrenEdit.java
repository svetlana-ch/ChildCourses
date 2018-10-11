package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.Child;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;
import by.htp.courses.service.ChildService;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class ChildrenEdit implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(ChildrenEdit.class);
	
	private static final String ID_PARAM_NAME = "id";
	private static final String NAME_CHILD_PARAM_NAME = "name_child";
	private static final String SURNAME_CHILD_PARAM_NAME = "surname_child";
	private static final String BIRTH_CHILD_PARAM_NAME = "birth_child";
	private static final String PARENT_CHILD_PARAM_NAME = "parent_child";
	
	private static final String UPDATE_PARAM_NAME = "update";
	private static final String DELETE_PARAM_NAME = "delete";
	private static final String CREATE_PARAM_NAME = "create";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getSession().getAttribute("user"));
		
		

		if ((User) request.getSession().getAttribute("user") == null ) {		
			request.setAttribute("errorAccessMessage", "Вам необходимо войти либо зарегистрироваться!");			
			request.getRequestDispatcher(JSPPagePath.ERROR_PAGE).forward(request, response);
            return;
		}
			
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ChildService childService = factory.getChildService();
		UserService userService = factory.getUserService();
		
		System.out.println("Мы в  ChildrenEdit.java");

		List<Child> children = null;
		List<User> parents = null;
		Child child = null;			
		String goToPage = null;	
				
		try {				
			if (request.getMethod().toUpperCase().equals("POST")) {				
				
									
				String name = request.getParameter(NAME_CHILD_PARAM_NAME);				
				String surname  = request.getParameter(SURNAME_CHILD_PARAM_NAME);
				LocalDate birthday  = LocalDate.parse(request.getParameter(BIRTH_CHILD_PARAM_NAME));						
				int parent_id = Integer.parseInt(request.getParameter(PARENT_CHILD_PARAM_NAME));						
				
				if (request.getParameter(CREATE_PARAM_NAME) != null) {	

					child = new Child();					
					child.setName(name);
					child.setSurname(surname);
					child.setDateOfBirth(birthday);
					child.setParentID(parent_id);					
					childService.create(child);					
					
					goToPage = JSPPagePath.CHILDREN_EDIT;
				
				}				
				
				
				if (request.getParameter(UPDATE_PARAM_NAME) != null && request.getMethod().toUpperCase().equals("POST")) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));					
					child = new Child();	
					child.setId(id);
					child.setName(name);
					child.setSurname(surname);
					child.setDateOfBirth(birthday);
					child.setParentID(parent_id);			

					childService.update(child);
					logger.info("  new child update= {}", child.getName());
					goToPage = JSPPagePath.CHILDREN_EDIT;
				}

				if (request.getParameter(DELETE_PARAM_NAME) != null && request.getMethod().toUpperCase().equals("POST")) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
					childService.delete(id);					
					goToPage = JSPPagePath.CHILDREN_EDIT;
				}
			}
			   
						
          children =  childService.getAll();
          parents = userService.search("role", "USER");          
			if(children != null){
				request.getSession(true).setAttribute("children", children);
				request.setAttribute("parents", parents);				
				goToPage = JSPPagePath.CHILDREN_EDIT;
			}else{
				request.setAttribute("errorMessage", "There are no children");
				goToPage = JSPPagePath.CHILDREN_EDIT;
			}	
			goToPage = JSPPagePath.CHILDREN_EDIT;		
			
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "error");
			goToPage = JSPPagePath.ERROR_PAGE;
			 logger.error("ServiceException  {}", e);
		}		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);				
	
		
	}

}
