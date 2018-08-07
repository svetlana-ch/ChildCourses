package by.htp.courses.service;

import by.htp.courses.service.impl.SubjectServiceImpl;
import by.htp.courses.service.impl.UserServiceImpl;


public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory(){}
	
	private final UserService userService = new UserServiceImpl();
	private final SubjectService subjectService = new SubjectServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public SubjectService getSubjectService(){
		return subjectService;
	}


}
