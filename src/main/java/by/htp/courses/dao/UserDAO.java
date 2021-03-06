package by.htp.courses.dao;

import java.util.List;

import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.User;

public interface UserDAO {
	
	User checkUser(String login, String password) throws DAOException;
	User getUser(String login) throws DAOException;
	User createUser(String name, String login, String email, String password) throws DAOException;
	List<User> getAll(int currentPage, int elementsOnPage) throws DAOException;
	int getAllUsersCount() throws DAOException;
	List<User> search(String where, String what) throws DAOException;
	boolean checkLogin(String login) throws DAOException;
	boolean update(User user) throws DAOException;
	boolean delete(int id) throws DAOException;
	

}
