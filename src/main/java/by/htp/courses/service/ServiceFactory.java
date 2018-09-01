package by.htp.courses.service;

import by.htp.courses.service.impl.ChildServiceImpl;
import by.htp.courses.service.impl.LessonServiceImpl;
import by.htp.courses.service.impl.SubjectServiceImpl;
import by.htp.courses.service.impl.UserServiceImpl;


public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory(){}
	
	private final UserService userService = new UserServiceImpl();
	private final SubjectService subjectService = new SubjectServiceImpl();
	private final ChildService childService = new ChildServiceImpl();
	private final LessonService lessonService = new LessonServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public SubjectService getSubjectService(){
		return subjectService;
	}

	public ChildService getChildService(){
		return childService;
	}

	public LessonService getLessonService(){
		return lessonService;
	}


}
