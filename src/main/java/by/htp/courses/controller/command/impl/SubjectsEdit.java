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
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.exception.ServiceException;

public class SubjectsEdit implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectsEdit.class);
	
	private static final String ID_PARAM_NAME = "id";
	private static final String NAME_SUBJECT_PARAM_NAME = "name_subject";
	private static final String AGE_CHILD_FROM_PARAM_NAME = "age_child_from";
	private static final String AGE_CHILD_TO_PARAM_NAME = "age_child_to";
	private static final String SEX_CHILD_PARAM_NAME = "sex_child";
	private static final String TIME_SPENDING_PARAM_NAME = "time_spending";
	private static final String NUMBER_PER_WEEK_PARAM_NAME = "number_per_week";
	private static final String IS_NEW_PARAM_NAME = "is_new";
	private static final String COST_PARAM_NAME = "cost";	
	private static final String UPDATE_PARAM_NAME = "update";
	private static final String DELETE_PARAM_NAME = "delete";
	private static final String CREATE_PARAM_NAME = "create";
	private static final String POST = "POST";
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (! ((User) request.getSession().getAttribute("user")).getRole().equals("ADMIN") ) {		
			request.setAttribute("errorAccessMessage", "Вам необходимо войти либо зарегистрироваться!");			
			request.getRequestDispatcher(JSPPagePath.ERROR_PAGE).forward(request, response);
            return;
		}
				
		
		ServiceFactory factory = ServiceFactory.getInstance();
		SubjectService subjectService = factory.getSubjectService();		

		List<Subject> subjects = null;
		Subject subject = null;			
		String goToPage = null;	
				
		try {				
			if (request.getMethod().toUpperCase().equals(POST)) {
				
				
				//if (request.getParameter(CREATE_PARAM_NAME) != null) {					
				String name_subject = request.getParameter(NAME_SUBJECT_PARAM_NAME);				
				String sex_child  = request.getParameter(SEX_CHILD_PARAM_NAME);
				String time_spending  = request.getParameter(TIME_SPENDING_PARAM_NAME);						
				int age_child_from = Integer.parseInt(request.getParameter(AGE_CHILD_FROM_PARAM_NAME));
				int age_child_to = Integer.parseInt(request.getParameter(AGE_CHILD_TO_PARAM_NAME));
				int number_per_week = Integer.parseInt(request.getParameter(NUMBER_PER_WEEK_PARAM_NAME));			
				boolean is_new = Boolean.parseBoolean(request.getParameter(IS_NEW_PARAM_NAME));
				double cost = Double.parseDouble(request.getParameter(COST_PARAM_NAME));				
				
				if (request.getParameter(CREATE_PARAM_NAME) != null) {	

					subject = new Subject();					
					subject.setSubjectName(name_subject);
					subject.setAgeChildFrom(age_child_from);
					subject.setAgeChildTo(age_child_to);
					subject.setSexChild(sex_child);
					subject.setTimeSpending(time_spending);
					subject.setNumberPerWeek(number_per_week);
					subject.setNew(is_new);
					subject.setCost(cost);					
					
					subjectService.create(subject);
					goToPage = JSPPagePath.SUBJECTS_EDIT;
				//}
				}			
				
				if (request.getParameter(UPDATE_PARAM_NAME) != null) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));					

					subject = new Subject();	
					subject.setId(id);
					subject.setSubjectName(name_subject);
					subject.setAgeChildFrom(age_child_from);
					subject.setAgeChildTo(age_child_to);
					subject.setSexChild(sex_child);
					subject.setTimeSpending(time_spending);
					subject.setNumberPerWeek(number_per_week);
					subject.setNew(is_new);
					subject.setCost(cost);	

					subjectService.update(subject);
					logger.info("  new cost = {}", subject.getCost());

					goToPage = JSPPagePath.SUBJECTS_EDIT;
				}

				if (request.getParameter(DELETE_PARAM_NAME) != null) {
					int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));

					subjectService.delete(id);
					
					goToPage = JSPPagePath.SUBJECTS_EDIT;
				}
			}
			   
			
			
          subjects =  subjectService.getAll();		
			if(subjects != null){
				 request.setAttribute("subjects", subjects);			
				goToPage = JSPPagePath.SUBJECTS_EDIT;
			}else{
				request.setAttribute("errorMessage", "Предметов нет");
				goToPage = JSPPagePath.SUBJECTS_EDIT;
			}	
			goToPage = JSPPagePath.SUBJECTS_EDIT;		
			
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", "error");
			goToPage = JSPPagePath.ERROR_PAGE;
			logger.error("ServiceException  {}", e);
		}		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
		dispatcher.forward(request, response);				
	}

}
