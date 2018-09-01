package by.htp.courses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.LessonDAO;
import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Lesson;
import by.htp.courses.domain.Subject;
import by.htp.courses.service.LessonService;
import by.htp.courses.service.exception.ServiceException;

public class LessonServiceImpl implements LessonService {
	
	private static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

	@Override
	public boolean create(Lesson lesson) throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		LessonDAO dao = factory.getLessonDAO();
		logger.info("create lesson  {}", lesson.getDate() );
		try {
			return dao.create(lesson);
		} catch (DAOException e) {					
			throw new ServiceException("DAOException in creating Lesson", e);					
		}		
	}

	@Override
	public Lesson read(Lesson lesson) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> getAll() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		LessonDAO dao = factory.getLessonDAO();
		List<Lesson> lessons = null;
		
		
		try {
			lessons = dao.getAll();
		} catch (DAOException e) {
			logger.error("DAOException in getAll lessons");
			throw new ServiceException("DAOException in getAll lessons", e);
		}
					
		return lessons;
	}

	@Override
	public boolean update(Lesson lesson) throws ServiceException {

		DAOFactory factory = DAOFactory.getInstance();
		LessonDAO dao = factory.getLessonDAO();
		
		try {
			return dao.update(lesson);
		} catch (DAOException e) {
			logger.error("DAOException in update lesson");
			throw new ServiceException("DAOException in update lesson", e);
		}
			
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		logger.info( "trying to delete lesson by id {}", id);
		DAOFactory factory = DAOFactory.getInstance();
		LessonDAO dao = factory.getLessonDAO();
		
		try {			
			return dao.delete(id);			
		} catch (DAOException e) {
			logger.info( "can not delete by id {}", id);
			throw new ServiceException("DAOException in deleting", e);
		}	
	}

	@Override
	public List<Lesson> search(String where, String what) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
