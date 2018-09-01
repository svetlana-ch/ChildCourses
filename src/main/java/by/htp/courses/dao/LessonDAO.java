package by.htp.courses.dao;

import java.util.List;

import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Lesson;

public interface LessonDAO {
	
	boolean create(Lesson lesson) throws DAOException;
	Lesson read(Lesson lesson) throws DAOException;
	List<Lesson> getAll() throws DAOException;
	boolean update(Lesson lesson) throws DAOException;
	boolean delete(int id) throws DAOException;
	List<Lesson> search(String where, String what) throws DAOException;

}
