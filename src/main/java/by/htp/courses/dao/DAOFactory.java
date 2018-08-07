package by.htp.courses.dao;

import by.htp.courses.dao.impl.SQLSubjectDAO;
import by.htp.courses.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final SubjectDAO subjectDAO = new SQLSubjectDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}



	public UserDAO getUserDAO() {
		return userDAO;
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}
}
