package by.htp.courses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Subject;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.exception.ServiceException;

public class SubjectServiceImpl implements SubjectService {
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

	@Override
	public boolean create(Subject subject) throws ServiceException {
		//validation
								
				DAOFactory factory = DAOFactory.getInstance();
				SubjectDAO dao = factory.getSubjectDAO();
				logger.info("create subject isNew  {}", subject.isNew() );
				try {
					return dao.create(subject);
				} catch (DAOException e) {					
					throw new ServiceException("DAOException in creating Subject", e);					
				}				
		
	}

	@Override
	public Subject read(Subject subject) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getAll() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		SubjectDAO dao = factory.getSubjectDAO();
		List<Subject> subjects = null;
		
		
		try {
			subjects = dao.getAll();
		} catch (DAOException e) {
			logger.error("DAOException in getAll subjects");
			throw new ServiceException("DAOException in getAll subjects", e);
		}
			
		
		
		return subjects;
	}

	@Override
	public boolean update(Subject subject) throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		SubjectDAO dao = factory.getSubjectDAO();
		
		try {
			return dao.update(subject);
		} catch (DAOException e) {
			logger.error("DAOException in update subjects");
			throw new ServiceException("DAOException in update subjects", e);
		}
			
		
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		
		logger.info( "trying to delete subject by id {}", id);
		DAOFactory factory = DAOFactory.getInstance();
		SubjectDAO dao = factory.getSubjectDAO();
		
		try {			
			return dao.delete(id);			
		} catch (DAOException e) {
			logger.info( "can not delete by id {}", id);
			throw new ServiceException("DAOException", e);
		}	
		
	}

	@Override
	public List<Subject> search(String where, String what) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
