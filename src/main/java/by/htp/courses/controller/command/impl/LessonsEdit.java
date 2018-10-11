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
import by.htp.courses.domain.Lesson;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;
import by.htp.courses.service.LessonService;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class LessonsEdit implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(LessonsEdit.class);
	
	private static final String ID_PARAM_NAME = "id";
	private static final String DATE_LESSON_PARAM_NAME = "date_lesson";
	private static final String ID_SUBJECT_PARAM_NAME = "subject_id";
	private static final String ID_TEACHER_PARAM_NAME = "teacher_id";
	private static final String ID_GROUP_PARAM_NAME = "group_id";	
	
	private static final String UPDATE_PARAM_NAME = "update";
	private static final String DELETE_PARAM_NAME = "delete";
	private static final String CREATE_PARAM_NAME = "create";
	private static final String SEARCH_PARAM_NAME = "search";


	private static final String SEARCHTYPE_PARAM_NAME = "searchtype";
	private static final String SEARCHTERM_PARAM_NAME = "searchterm";
	private static final String DATE_START_PARAM_NAME = "date_start";
	private static final String DATE_END_PARAM_NAME = "date_end";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
				ServiceFactory factory = ServiceFactory.getInstance();
				LessonService lessonService = factory.getLessonService();
				UserService userService = factory.getUserService();
				SubjectService subjectService = factory.getSubjectService();
						

				List<Lesson> lessons = null;
				List<Subject> subjects = null;
				List<User> teachers = null;
				Lesson lesson = null;			
				String goToPage = null;	
				LocalDate date_start  = LocalDate.parse("2018-01-01");
				LocalDate date_end = LocalDate.parse("2018-12-31");
						
				try {	

					if (request.getParameter(SEARCH_PARAM_NAME) != null) {
													
						String searchtype = request.getParameter(SEARCHTYPE_PARAM_NAME);
						String searchterm = request.getParameter(SEARCHTERM_PARAM_NAME);
						if (!request.getParameter(DATE_START_PARAM_NAME).isEmpty()) {
						date_start  = LocalDate.parse(request.getParameter(DATE_START_PARAM_NAME));
						}
						if (!request.getParameter(DATE_END_PARAM_NAME).isEmpty()) {
						date_end  = LocalDate.parse(request.getParameter(DATE_END_PARAM_NAME));
						}
						

						lessons = lessonService.search(searchtype, searchterm, date_start, date_end); 							
						logger.info("Looking for {}   {} {}   {}", searchtype, searchterm,  date_start, date_end);
						
						goToPage = JSPPagePath.LESSONS_EDIT;
						
					}

					
					
					if (request.getMethod().toUpperCase().equals("POST")) {
						
						
						if (request.getParameter(CREATE_PARAM_NAME) != null) {					
						LocalDate date_lesson = LocalDate.parse(request.getParameter(DATE_LESSON_PARAM_NAME));							
						int subject_id = Integer.parseInt(request.getParameter(ID_SUBJECT_PARAM_NAME));
						int teacher_id = Integer.parseInt(request.getParameter(ID_TEACHER_PARAM_NAME));
						int group_id = Integer.parseInt(request.getParameter(ID_GROUP_PARAM_NAME));			
										
						
							
							lesson = new Lesson();					
							lesson.setDate(date_lesson);
							lesson.setSubjectID(subject_id);
							lesson.setTeacherID(teacher_id);
							lesson.setGroupID(group_id);
							
							lessonService.create(lesson);
							goToPage = JSPPagePath.LESSONS_EDIT;
						
						}
						
						if (request.getParameter(UPDATE_PARAM_NAME) != null) {
							int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
							LocalDate date_lesson = LocalDate.parse(request.getParameter(DATE_LESSON_PARAM_NAME));							
							int subject_id = Integer.parseInt(request.getParameter(ID_SUBJECT_PARAM_NAME));
							int teacher_id = Integer.parseInt(request.getParameter(ID_TEACHER_PARAM_NAME));
							int group_id = Integer.parseInt(request.getParameter(ID_GROUP_PARAM_NAME));		
							logger.info("Updating lesson id =  {} ", id);

							lesson = new Lesson();	
							lesson.setId(id);
							lesson.setDate(date_lesson);
							lesson.setSubjectID(subject_id);
							lesson.setTeacherID(teacher_id);
							lesson.setGroupID(group_id);
							
							lessonService.update(lesson);
							goToPage = JSPPagePath.LESSONS_EDIT;
							
						}

						if (request.getParameter(DELETE_PARAM_NAME) != null) {
							int id = Integer.parseInt(request.getParameter(ID_PARAM_NAME));
							lessonService.delete(id);							
							goToPage = JSPPagePath.LESSONS_EDIT;
						}
					}
					   
					
					if(lessons == null){
		          lessons =  lessonService.getAll();
					}
		          subjects = subjectService.getAll();
		          teachers = userService.search("role", "TEACHER");
		          logger.info("lessons {}", lessons);
		          logger.info("subjects {}", subjects);
		          logger.info("TEACHERS {}", teachers);
					if(lessons != null){
						request.setAttribute("lessons", lessons);
						request.setAttribute("subjects", subjects);
						request.setAttribute("teachers", teachers);
						goToPage = JSPPagePath.LESSONS_EDIT;
					}else{
						request.setAttribute("errorMessage", "Уроков нет");
						goToPage = JSPPagePath.LESSONS_EDIT;
					}	
					goToPage = JSPPagePath.LESSONS_EDIT;		
					
				} catch (ServiceException e) {
					request.setAttribute("errorMessage", "error");
					goToPage = JSPPagePath.ERROR_PAGE;
					logger.error("ServiceException  {}", e);
					e.printStackTrace();
				}		
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);		
				dispatcher.forward(request, response);			
		
	}

}
