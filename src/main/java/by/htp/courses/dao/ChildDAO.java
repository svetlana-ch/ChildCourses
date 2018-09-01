package by.htp.courses.dao;

import java.util.List;

import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Child;

public interface ChildDAO {
	
	boolean create(Child child) throws DAOException;
	Child read(Child child) throws DAOException;
	List<Child> getAll() throws DAOException;
	boolean update(Child child) throws DAOException;
	boolean delete(int id) throws DAOException;
	List<Child> search(String where, String what) throws DAOException;
	

}
