package by.htp.courses.dao;

import by.htp.courses.dao.impl.SQLChildDAO;
import by.htp.courses.dao.impl.SQLLessonDAO;
import by.htp.courses.dao.impl.SQLSubjectDAO;
import by.htp.courses.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final SubjectDAO subjectDAO = new SQLSubjectDAO();
	private final ChildDAO childDAO = new SQLChildDAO();
	private final LessonDAO lessonDAO = new SQLLessonDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public ChildDAO getChildDAO() {
		return childDAO;
	}
	
	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}
	
	public LessonDAO getLessonDAO() {
		return lessonDAO;
	}
}
