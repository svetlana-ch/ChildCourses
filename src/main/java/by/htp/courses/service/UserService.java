package by.htp.courses.service;

import java.util.List;

import by.htp.courses.domain.User;
import by.htp.courses.service.exception.ServiceException;


public interface UserService {
	
	User signin(String login, String password) throws ServiceException;
	User getUser(String login) throws ServiceException;
	User signup(String name, String login, String email, String password) throws ServiceException;
	List<User> getAll(int currentPage, int elementsOnPage) throws ServiceException;
	int getAllUsersCount() throws ServiceException;
	List<User> search(String where, String what) throws ServiceException;
	boolean checkLogin(String login) throws ServiceException;
	boolean update(User user) throws ServiceException;
	boolean delete(int id) throws ServiceException;
	
	
	
}
