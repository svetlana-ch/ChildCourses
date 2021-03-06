package by.htp.courses.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.User;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
		
	@Override
	public User signin(String login, String password) throws ServiceException {
							
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();		
		
		User user = null;
		try {
			user = dao.checkUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException("DAOException User signin()   ", e);
		}		
		
		return user;
	}
	
	

	@Override
	public User signup(String name, String login, String email, String password) throws ServiceException {
		
		User user = null;
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		if (checkLogin(login)) {
			logger.info("checking login");
			throw new ServiceException("Login_is_not_free");				
		} 
		
		if (!Validator.validateLogin(login)) {
			logger.info("Login is empty or does not match");			
            throw new ServiceException("Invalid_login");
        }
       
        if (!Validator.validateName(name)) {
        	logger.info("Name is empty or  does not match");
            throw new ServiceException("Invalid_name");
        }
        if (!Validator.validateEmail(email)) {
        	logger.info("Email does not match");
            throw new ServiceException("Invalid_email");
        }
		
         if (!Validator.validatePassword(password)) {
        	 logger.info("Password is empty or does not match"); 
        	 throw new ServiceException("Invalid_password");
    }
		
		logger.info("Crearing user in Service");
		
			try {
				user = dao.createUser(name, login, email, password);
			} catch (DAOException e) {
				logger.info("User is not created");
				throw new ServiceException("User is not created", e);
			}
		
		return user;
	}

	@Override
	public List<User> getAll(int currentPage, int elementsOnPage) throws ServiceException {
				
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();	
		List<User> users = null;
		try {			
			users = dao.getAll(currentPage, elementsOnPage);			
			
		} catch (DAOException e) {
			logger.error("DAOException in getAll users");
			throw new ServiceException("DAOException in getAll users", e);
		}
		
		return users;	
	}
	

	@Override
	public boolean update(User user) throws ServiceException {
		
		/*if (!Validator.validateLogin(user.getLogin())) {
			logger.info("login is empty or not matchers");
            throw new ServiceException("Invalid login");
        }
        if (!Validator.validatePassword(user.getPassword())) {
        	logger.info("password is empty or not matchers");
            throw new ServiceException("Invalid password");
        }
        if (!Validator.validateName(user.getName())) {
        	logger.info("user Name is empty or not matchers");
            throw new ServiceException("Invalid name");
        }
        if (!Validator.validateEmail(user.getEmail())) {
        	logger.info("email is empty or not matchers");
            throw new ServiceException("Invalid email");
        }*/
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();		
		
		try {			
			return dao.update(user);			
		} catch (DAOException e) {
			throw new ServiceException("User is not updated", e);
		}
	}
	

	@Override
	public boolean delete(int id) throws ServiceException {
		logger.info( "trying to delete by id {}", id);
		if ( id == 0L) {
		      logger.info( "invalid id {}", id);
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();		
		
		try {			
			return dao.delete(id);			
		} catch (DAOException e) {
			logger.info( "can not delete by id {}", id);
			throw new ServiceException("DAOException", e);
		}		
	}
	
	

	@Override
	public List<User> search(String where, String what) throws ServiceException {
			
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		List<User> users = null;
		
		try {			
			users = dao.search(where, what);			
		} catch (DAOException e) {
			throw new ServiceException("DAOExeption ", e);
		}		
		
		return users;
	}

	@Override
	public boolean checkLogin(String login) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		Boolean result = false;
		
		try {
			result = dao.checkLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("DAOExeption ", e);
		}
		return result;
	}



	@Override
	public int getAllUsersCount() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		int result = 0;
		
		try {
			result = dao.getAllUsersCount();
		} catch (DAOException e) {
			throw new ServiceException("DAOExeption ", e);
		}
		return result;
	}



	@Override
	public User getUser(String login) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		User user = null;
		try {
			user = dao.getUser(login);
		} catch (DAOException e) {
			throw new ServiceException("DAOExeption ", e);
		}
		
		return user;
	}


}
