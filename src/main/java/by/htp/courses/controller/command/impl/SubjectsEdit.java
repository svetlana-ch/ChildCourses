package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class SubjectsEdit implements Command{
	

	private static final String ID_PARAM_NAME = "id";	
	private static final String NAME_SUBJECT_PARAM_NAME = "name_subject";
	private static final String AGE_CHILD_PARAM_NAME = "age_child";
	private static final String SEX_CHILD_PARAM_NAME = "sex_child";
	private static final String TIME_SPENDING_PARAM_NAME = "time_spending";
	private static final String NUMBER_PER_WEEK_PARAM_NAME = "number_per_week";
	private static final String IS_NEW_PARAM_NAME = "is_new";
	private static final String COST_PARAM_NAME = "cost";
	
	
	private static final String SEARCHTYPE_PARAM_NAME = "searchtype";
	private static final String SEARCHTERM_PARAM_NAME = "searchterm";	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name_subject;
		//String age_child;
		String sex_child;
		String time_spending;
		//String number_per_week;
		//String is_new;
		//String cost;
						
		name_subject = request.getParameter(NAME_SUBJECT_PARAM_NAME);
		//age_child = request.getParameter(AGE_CHILD_PARAM_NAME);
		sex_child = request.getParameter(SEX_CHILD_PARAM_NAME);
		time_spending = request.getParameter(TIME_SPENDING_PARAM_NAME);
		//number_per_week = request.getParameter(NUMBER_PER_WEEK_PARAM_NAME);
		//is_new = request.getParameter(IS_NEW_PARAM_NAME);
		//cost = request.getParameter(COST_PARAM_NAME);
		
		//searchtype = request.getParameter(SEARCHTYPE_PARAM_NAME);
		//searchterm = request.getParameter(SEARCHTERM_PARAM_NAME);
		
		
		System.out.println(name_subject + time_spending);		
				
		
		
		ServiceFactory factory = ServiceFactory.getInstance();
		SubjectService subjectService = factory.getSubjectService();
		
		System.out.println("Мы в  SublectsEdit.java");

		List<Subject> subjects = null;
		Subject subject = null;
			
		String goToPage = null;	
				
		try {	
			
			if (request.getMethod().toUpperCase().equals("POST")) {
				
				int age_child = Integer.parseInt(request.getParameter(AGE_CHILD_PARAM_NAME));				
				int number_per_week = Integer.parseInt(request.getParameter(NUMBER_PER_WEEK_PARAM_NAME));
				
				boolean is_new = Boolean.parseBoolean(request.getParameter(IS_NEW_PARAM_NAME));
				double cost = Double.parseDouble(request.getParameter(COST_PARAM_NAME));
				
				//int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
				
				if (request.getParameter("create") != null) {
					String id222 = request.getParameter("id");
					System.out.println("НАЖАЛИ НА UPDATE     " + id222);// проверка ip в текстовом формате

					subject = new Subject();
					
					subject.setSubjectName(name_subject);
					subject.setAgeChildFrom(age_child);
					subject.setSexChild(sex_child);
					subject.setTimeSpending(time_spending);
					subject.setNumberPerWeek(number_per_week);
					subject.setNew(is_new);
					subject.setCost(cost);
					
					 System.out.println(" предмет ==     возраст  ="+ subject.getAgeChildFrom() + "  стоимость =" + subject.getCost()
					 + " количество в неделю  " + subject.getNumberPerWeek() +
							 " наименование = " + subject.getSubjectName()
							 + " время проведения = " + subject.getTimeSpending()
							 + "  пол ребенка  = " + subject.getSexChild()
							 + " НОВИНКА = " + subject.isNew());


					subjectService.create(subject);
					

					goToPage = JSPPagePath.SUBJECTS_EDIT;
				}

				/*if (request.getParameter("update") != null) {
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
					// System.out.println("НАЖАЛИ НА update id== "+ id + " " + login + " " + name +
					// " " + password);

					goToPage = JSPPagePath.SUBJECTS_EDIT;
				}

				if (request.getParameter("delete") != null) {

					userService.delete(id);
					System.out.println(
							"НАЖАЛИ НА DELETE  id==   " + id + "   " + login + "   " + name + "   " + password);

					goToPage = JSPPagePath.SUBJECTS_EDIT;
				}*/

			}
			   
           /* subjectService.getAll("");
            
            
			System.out.println("Users ==   "   + users);
			if(users != null){
				request.getSession(true).setAttribute("users", users);
				//System.out.println("SIGN_UP2="+ users);
				//System.out.println("SIGN_UP2="+ users.size() + "   размер   " + users.toString());
				goToPage = JSPPagePath.SUBJECTS_EDIT;
			}else{
				request.setAttribute("errorMessage", "ПОльзователей нет");
				goToPage = JSPPagePath.SUBJECTS_EDIT;//поменять
			}	*/	
			goToPage = JSPPagePath.SUBJECTS_EDIT;		
			
		} catch (ServiceException e) {
			//goToPage = JSPPagePath.ERROR_PAGE;
			// log
			e.printStackTrace();
		}	
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
		
		dispatcher.forward(request, response);			
		
		
	}

}
