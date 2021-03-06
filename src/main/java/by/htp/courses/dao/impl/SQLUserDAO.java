package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.User;



public class SQLUserDAO implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SQLUserDAO.class);
	
	private final static String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT name, login, role FROM users WHERE login=? and password=?";
	private final static String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private final static String CREATE_USER = "INSERT INTO users (name, login, email, password, role) values(?, ?, ?, ?, ?)";
	private final static String GET_ALL_USERS_LIMIT = "SELECT * FROM users LIMIT ?,?";
	private final static String GET_ALL_USERS = "SELECT * FROM users";
	private static final String COUNT_USERS = "SELECT COUNT(*) FROM users";
	private final static String UPDATE_USER = "UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ? WHERE id = ?";
	private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
	private static final String CHECK_LOGIN = "SELECT login FROM users WHERE login=?";
	private final static String DELETE_USERS_LESSONS = "DELETE FROM lessons WHERE id_teacher = ?";
	private final static String DELETE_USERS_CHILDREN = "DELETE FROM children WHERE parent_id = ?";
	
	private static final String ROLE_USER = "USER";    
	    
	

	@Override
	public User checkUser(String login, String password) throws DAOException {		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet rs = null;		
		User user = null;		
		ConnectionPool conPool = ConnectionPool.getInstance();
		
		try {
			connection = conPool.takeConnection();
			preparedStatment = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
			preparedStatment.setString(1, login);
			preparedStatment.setString(2, password);

			rs = preparedStatment.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setName(rs.getString(1));
				user.setLogin(rs.getString(2));
				user.setRole(rs.getString(3));
				logger.info("User {} exists", user.getName());
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.checkUser() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.checkUser()", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLUserDAO.checkUser() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.checkUser()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}

		return user;
	}
	
	


	@Override
	public User getUser(String login) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet rs = null;		
		User user = null;		
		ConnectionPool conPool = ConnectionPool.getInstance();
		
		try {
			connection = conPool.takeConnection();
			preparedStatment = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			preparedStatment.setString(1, login);			

			rs = preparedStatment.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); 
                user.setRole(rs.getString("role"));
				logger.info("User {} exists", user.getName());
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.getUser() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.getUser()", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLUserDAO.getkUser() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.getUser()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}

		return user;
	}


	

	@Override
	public User createUser(String name, String login, String email, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		
		User user = null;
		
		try {
			connection = conPool.takeConnection();				

				preparedStatment = connection.prepareStatement(CREATE_USER);
				preparedStatment.setString(1, name);
				preparedStatment.setString(2, login);
				preparedStatment.setString(3, email);
				preparedStatment.setString(4, password);
				preparedStatment.setString(5, ROLE_USER);

				int countRows = preparedStatment.executeUpdate();

				if (countRows == 1) {
					user = new User();
					user.setName(name);
					user.setLogin(login);
					user.setRole(ROLE_USER);
					logger.info("User {} created", user.getName());
				}
			

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.createUser() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.createUser()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.createUser() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.createUser()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
		return user;
	}

	

	@Override
	public int getAllUsersCount() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		ResultSet rs = null;			
		int result = 0;	
	    
	    try {
	    	connection = conPool.takeConnection();
			sql = COUNT_USERS;
						
			preparedStatment = connection.prepareStatement(sql);						
			rs = preparedStatment.executeQuery();
			
			rs.next();
            result = rs.getInt(1);
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.getAllCount {}", e);	
			throw new DAOException("InterruptedException in SQLUserDAO.getAllCount", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.getAllCount {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.getAllCount", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}	 
	     
		return result;
	}

	
	
	
	@Override
	public List<User> getAll(int currentPage, int elementsOnPage) throws DAOException{
		
		logger.info("CERRENT PAAAAAAGR     {}  ELEM ON PAGE   {} ", currentPage, elementsOnPage);
		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		ResultSet rs = null;	
		
			
		List<User> users = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();
			sql = GET_ALL_USERS_LIMIT;					
			preparedStatment = connection.prepareStatement(sql);					
			preparedStatment.setInt(1, (currentPage-1)*elementsOnPage);
			preparedStatment.setInt(2, elementsOnPage);			
			rs = preparedStatment.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);	
			}
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.getAll {}", e);	
			throw new DAOException("InterruptedException in SQLUserDAO.getAll", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.getAll {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.getAll", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}
	 
	       return users;
	}
	
	

	@Override
	public boolean update(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		int countRows = 0;		
		
		try {
			connection = conPool.takeConnection();
			
			sql = UPDATE_USER; 
			
			preparedStatment = connection.prepareStatement(sql);			
			preparedStatment.setString(1,  user.getName());
			preparedStatment.setString(2,  user.getLogin());		
			preparedStatment.setString(3,  user.getEmail());			
			preparedStatment.setString(4, user.getPassword());
			preparedStatment.setString(5, user.getRole());
			preparedStatment.setInt(6, user.getId());			
			
			countRows = preparedStatment.executeUpdate();	
			
			logger.info("User {} is updated", user.getName());

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.updateUser() {}", e);			
			throw new DAOException("InterruptedException in SQLUserDAO.updateUser()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.updateUser() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.updateUser()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
		return countRows == 1; 
	}

	
	@Override
	public boolean delete(int id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		int countRows = 0;
		logger.info("deleting user by id {}", id);

		try {
			connection = conPool.takeConnection();
			connection.setAutoCommit(false);
			try {
				preparedStatment = connection.prepareStatement(DELETE_USERS_CHILDREN);
				preparedStatment.setInt(1, id);
				preparedStatment.executeUpdate();

				preparedStatment = connection.prepareStatement(DELETE_USERS_LESSONS);
				preparedStatment.setInt(1, id);
				preparedStatment.executeUpdate();

				preparedStatment = connection.prepareStatement(DELETE_USER);
				preparedStatment.setInt(1, id);
				countRows = preparedStatment.executeUpdate();

				connection.commit();
				logger.info("User id {}   is deleted", id);

			} catch (SQLException e) {
				connection.rollback();
				logger.error("Transaction failed", e);

			} finally {
				connection.setAutoCommit(true);
			}

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.deleteUser() {}", e);
			throw new DAOException("InterruptedException in SQLUserDAO.deleteUser()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.deleteUser() {} ", e);
			throw new DAOException("SQLException in SQLUserDAO.deleteUser()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}

		return countRows == 1;
	}

	@Override
	public List<User> search(String where, String what) throws DAOException {			
		String WHERE = " where " +  where +  " like '%" + what + "%'";		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		ResultSet rs = null;	
			
		List<User> users = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();
			sql = GET_ALL_USERS + WHERE;
						
			preparedStatment = connection.prepareStatement(sql);					
			rs = preparedStatment.executeQuery();
			
			while (rs.next()) {				
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);	                
			}
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.search {}", e);	
			throw new DAOException("InterruptedException in SQLUserDAO.search", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.search {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.search", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}
	 
	       return users;
	}
		

	
	@Override
	public boolean checkLogin(String login) throws DAOException {		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet rs = null;	
				
		ConnectionPool conPool = ConnectionPool.getInstance();
		
		try {
			connection = conPool.takeConnection();
			preparedStatment = connection.prepareStatement(CHECK_LOGIN);
			preparedStatment.setString(1, login);
			
			rs = preparedStatment.executeQuery();

			if (rs.next()) {
				logger.info("User {} exists", rs.getString(1));
				return true;				
			}
		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.checkLogin() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.checkLogin()", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLUserDAO.checkLogin() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.checkLogin()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}

		return false;		
	}	
	
	
	
	private void closeResources(AutoCloseable... resources) throws DAOException {
		for (AutoCloseable resource : resources) {
			if (resource != null) {
				try {
					//logger.info("Closing resourse {} ", resource.getClass());
					resource.close();
				} catch (Exception e) {
					logger.error("Resourse not closed {} ", resource.getClass());
					throw new DAOException("Resourse not closed", e);

				}
			}
		}
	}


}
