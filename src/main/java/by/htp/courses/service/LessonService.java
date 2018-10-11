package by.htp.courses.service;

import java.time.LocalDate;
import java.util.List;

import by.htp.courses.service.exception.ServiceException;
import by.htp.courses.domain.Lesson;

public interface LessonService {
	
	boolean create(Lesson lesson) throws ServiceException;
	Lesson read(Lesson lesson) throws ServiceException;
	List<Lesson> getAll() throws ServiceException;
	boolean update(Lesson lesson) throws ServiceException;
	boolean delete(int id) throws ServiceException;
	List<Lesson> search(String where, String what, LocalDate date_start, LocalDate date_end) throws ServiceException;


}
