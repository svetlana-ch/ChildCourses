package by.htp.courses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.ChildDAO;
import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Child;
import by.htp.courses.domain.Subject;
import by.htp.courses.service.ChildService;
import by.htp.courses.service.exception.ServiceException;

public class ChildServiceImpl implements ChildService{
	
	private static final Logger logger = LoggerFactory.getLogger(ChildServiceImpl.class);

	@Override
	public boolean create(Child child) throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		ChildDAO dao = factory.getChildDAO();
		logger.info("create Child  {}", child.getName() );
		try {
			return dao.create(child);
		} catch (DAOException e) {					
			throw new ServiceException("DAOException in creating Child", e);					
		}		
	}

	@Override
	public Child read(Child child) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Child> getAll() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		ChildDAO dao = factory.getChildDAO();
		List<Child> children = null;		
			
		try {
			children = dao.getAll();
			logger.info("Service getAll children OK {}", children);
		} catch (DAOException e) {
			logger.error("DAOException in getAll child");
			throw new ServiceException("DAOException in getAll child", e);
		}			
		
		return children;
	}

	@Override
	public boolean update(Child child) throws ServiceException {

		DAOFactory factory = DAOFactory.getInstance();
		ChildDAO dao = factory.getChildDAO();
		
		try {
			return dao.update(child);
		} catch (DAOException e) {
			logger.error("DAOException in update child");
			throw new ServiceException("DAOException in update child", e);
		}
			
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		
		logger.info( "trying to delete child by id {}", id);
		DAOFactory factory = DAOFactory.getInstance();
		ChildDAO dao = factory.getChildDAO();
		
		try {			
			return dao.delete(id);			
		} catch (DAOException e) {
			logger.info( "can not delete by id {}", id);
			throw new ServiceException("DAOException in deleting", e);
		}	
	}

	@Override
	public List<Child> search(String where, String what) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
