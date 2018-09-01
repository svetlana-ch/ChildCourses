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
	
	/*public UserDTO logination(User user) throws ServiceException {
		if (!validation(user)) {
			return null;
		}
		try {
			user = userDAO.find(user);
			return user == null ? null : new UserDTO(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		try {
			return userDAO.save(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	} */
	
	
	
	@Override
	public User signin(String login, String password) throws ServiceException {
		
	/*	if (!Validator.validateLogin(login)) {
            throw new ServiceException("Wrong login");
        }
        if (!Validator.validatePassword(password)) {
            throw new ServiceException("Wrong password");
        }		
	*/					
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
		
		/*if (!Validator.validateLogin(login)) {
			logger.info("логин   пустой или не соответствует");
            throw new ServiceException("Invalid login");
        }
        if (!Validator.validatePassword(password)) {
            throw new ServiceException("Invalid password");
        }
        if (!Validator.validateName(name)) {
            throw new ServiceException("Invalid name");
        }
        if (!Validator.validateEmail(email)) {
            throw new ServiceException("Invalid email");
        }
		*/
		User user = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		logger.info("Crearing user in Service");

		if (!checkLogin(login)) {
			logger.info("checking login");
			try {
				user = dao.createUser(name, login, email, password);
			} catch (DAOException e) {
				logger.info("User is not created");
				throw new ServiceException("User is not created", e);
			}
		} else {
			throw new ServiceException("Login is not free");			
		}
		return user;
	}

	@Override
	public List<User> getAll(String WHERE) throws ServiceException {
				
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		List<User> users = null;
		try {			
			users = dao.getAll(WHERE);			
			
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


}
