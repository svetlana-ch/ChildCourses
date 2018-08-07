package by.htp.courses.service;

import java.util.List;

import by.htp.courses.domain.Subject;
import by.htp.courses.service.exception.ServiceException;

public interface SubjectService {
	
	boolean create(Subject subject) throws ServiceException;
	Subject read(Subject subject) throws ServiceException;
	List<Subject> getAll(String WHERE) throws ServiceException;
	boolean update(Subject subject) throws ServiceException;
	boolean delete(int id) throws ServiceException;
	List<Subject> search(String where, String what) throws ServiceException;

}
