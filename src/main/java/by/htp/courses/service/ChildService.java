package by.htp.courses.service;

import java.util.List;

import by.htp.courses.service.exception.ServiceException;
import by.htp.courses.domain.Child;

public interface ChildService {
	
	boolean create(Child child) throws ServiceException;
	Child read(Child child) throws ServiceException;
	List<Child> getAll() throws ServiceException;
	boolean update(Child child) throws ServiceException;
	boolean delete(int id) throws ServiceException;
	List<Child> search(String where, String what) throws ServiceException;

}
