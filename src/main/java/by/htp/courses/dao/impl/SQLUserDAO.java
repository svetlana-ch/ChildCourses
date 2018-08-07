package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.User;



public class SQLUserDAO implements UserDAO {
	
	private static final Logger log = LogManager.getLogger(SQLUserDAO.class.getName());

	@Override
	public User checkUser(String login, String password) throws DAOException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;	
		
		User user = null;
		
		ConnectionPool conPool = ConnectionPool.getInstance();

		
		try {
			connection = conPool.takeConnection();
			statement = connection
					.prepareStatement("select name, role from users where login=? and password=?");
			statement.setString(1, login);
			statement.setString(2, password);

			rs = statement.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setName(rs.getString(1));				
				user.setRole(rs.getString(2));
								
			}

		} catch (SQLException | InterruptedException e) {
			throw new DAOException("message foe change", e);
		} finally {
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					// log error
				}
			}

			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {					
					log.debug("Test debug   User check");
				}
			}
		}

		return user;
	}

	@Override
	public User createUser(String name, String login, String email, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		User user = null;
		System.out.println("createUser() " );
		
		System.out.println("login ==   " + login);
		System.out.println("password ==     " + password);
		System.out.println("e-mail ==     " + email);
		
		try {
			connection = conPool.takeConnection();
			sql = "insert into users (name, login, email, password, role) values(?, ?, ?, ?, ?)";				
			
			statement = connection.prepareStatement(sql);			
			statement.setString(1, name);
			statement.setString(2, login);		
			statement.setString(3, email);
			statement.setString(4, password);
			statement.setString(5, "USER");
			
						
			System.out.println("                                            Hello Log4J");
			log.debug("Logger Debug               createUser");
			log.info("Logger Info         createUser");

			System.out.println("пароль  ==     " + password);
			System.out.println("e-mail ==     " + email);
			
			int countRows = statement.executeUpdate();
			
			System.out.println("      " + name);
			if (countRows == 1) {
				user = new User();
				user.setName(name);
				user.setRole("USER");
				System.out.println(user);
			}

		} catch (SQLException | InterruptedException e) {
			throw new DAOException("Error createUser", e);
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// log error
				}
			}
		}
		return user;
	}

	@Override
	public List<User> getAll(String WHERE) throws DAOException{
		Connection connection = null;
		PreparedStatement statement = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		ResultSet rs = null;	
		
		List<User> users = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();
			sql = "SELECT * FROM users" + WHERE;
						
			statement = connection.prepareStatement(sql);			
			//statement.setString(1, WHERE);
			
			System.out.println(sql);
			System.out.println(" SELECT * FROM users ?  WHERE ==            " + WHERE);
			/*System.out.println("РїР°СЂРѕР»СЊ  ==     " + password);
			System.out.println("e-mail ==     " + email);*/
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
				//System.out.println(user);
			}


		} catch (SQLException | InterruptedException e) {
			throw new DAOException("Error", e);
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// log error
				}
			}
		}
	 
	       return users;
	}

	@Override
	public boolean update(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		int countRows = 0;
		
	
		System.out.println("Р»РѕРіРёРЅ ==   " + user.getLogin());
		System.out.println("РїР°СЂРѕР»СЊ  ==     " + user.getPassword());
		System.out.println("e-mail ==     " + user.getEmail());

		System.out.println("id ==   " + user.getId());
		System.out.println("name  ==     " + user.getName());
		System.out.println("Role ==     " + user.getRole());
		
		
		try {
			connection = conPool.takeConnection();
			
			sql = "UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ? WHERE id = ?"; 
			
			statement = connection.prepareStatement(sql);			
			statement.setString(1,  user.getName());
			statement.setString(2,  user.getLogin());		
			statement.setString(3,  user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getRole());
			statement.setInt(6, user.getId());			
			
			countRows = statement.executeUpdate();			

		} catch (SQLException | InterruptedException e) {
			throw new DAOException("РћРЁР�Р‘РљРђ Р”РћР‘РђР’Р›Р•РќР�РЇ Р’ Р‘Р”", e);
		} finally {
			System.out.println("Р‘Р»РѕРє finally      " );
			System.out.println("Р РµР·СѓР»СЊС‚Р°С‚ Update t/f  ==  "  + countRows);
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// log error
				}
			}
		}
		return countRows == 1; 
	}

	@Override
	public boolean delete(int id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;	
		ConnectionPool conPool = ConnectionPool.getInstance();
		int countRows = 0;
		System.out.println("РњС‹ РІ Р”РђРћ   delete(int id) " );	
		
			    
	    try {
	    	connection = conPool.takeConnection();
			statement = connection.prepareStatement("DELETE FROM users WHERE id=?");			
			statement.setInt(1, id);
			countRows = statement.executeUpdate();
					
			System.out.println("РЈР”РђР›Р•РќР�Р• РџРћР›Р¬Р—РћР’РђРўР•Р›РЇ==       id ==      "   + id);
		

		} catch (SQLException | InterruptedException e) {
			throw new DAOException("РћРЁР�Р‘РљРђ РЈР”РђР›Р•РќР�РЇ Р�Р— Р‘Р”", e);
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// log error
				}
			}
		}
	 
	      		
		return countRows == 1;
	}

	@Override
	public List<User> search(String where, String what) throws DAOException {
		
		//String WHERE = String.format("where '%s' like '%'%s'%'", where, what);
		String WHERE = " where " +  where +  " like '%" + what + "%'";
		
		List<User> users = getAll(WHERE);
		 return users;
	}

}
