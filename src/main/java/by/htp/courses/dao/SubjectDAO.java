package by.htp.courses.dao;

import java.util.List;

import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Subject;

public interface SubjectDAO {	
	
	boolean create(Subject subject) throws DAOException;
	Subject read(Subject subject) throws DAOException;
	List<Subject> getAll() throws DAOException;
	boolean update(Subject subject) throws DAOException;
	boolean delete(int id) throws DAOException;
	List<Subject> search(String where, String what) throws DAOException;
	
	
}
