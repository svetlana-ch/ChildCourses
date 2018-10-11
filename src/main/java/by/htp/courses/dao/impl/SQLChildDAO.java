package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.ChildDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Child;


public class SQLChildDAO implements ChildDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(SQLChildDAO.class);
	
	
	private final static String CREATE_CHILD = "INSERT into children (name_child, surname_child, date_of_birth, parent_id) values(?, ?, ?, ?)";
	private final static String GET_ALL_CHILDREN = "SELECT * FROM children";
	private final static String UPDATE_CHILD = "UPDATE `courses`.`children` SET `name_child`=?, `surname_child`=?, `date_of_birth`=?, `parent_id`=? WHERE `id`=?;";
			
	private static final String DELETE_CHILD = "DELETE FROM children WHERE id=?";

	@Override
	public boolean create(Child child) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
			
		int countRows;
		logger.info("creating child");
		
		try {
			connection = conPool.takeConnection();			
			
			preparedStatment = connection.prepareStatement(CREATE_CHILD);
			
			preparedStatment.setString(1, child.getName());
			preparedStatment.setString(2, child.getSurname());
			preparedStatment.setDate(3, Date.valueOf(child.getDateOfBirth()));
			preparedStatment.setObject(4, child.getParentID());					
			
			countRows = preparedStatment.executeUpdate();	
					
			if (countRows == 1) {				
				logger.info("child {} is created", child.getName());
				return true;				
			}

		} catch (InterruptedException e) {
			logger.error("InterruptedException in CREATE child {}", e);	
			throw new DAOException("InterruptedException in CREATE child", e);
		} catch (SQLException e) {
			logger.error("SQLException in CREATE child {}", e);
			throw new DAOException("SQLException in CREATE child", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
		return false;
	}

	@Override
	public Child read(Child child) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Child> getAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		ResultSet rs = null;	
			
		List<Child> children = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();								
			preparedStatment = connection.prepareStatement(GET_ALL_CHILDREN);			
					
			rs = preparedStatment.executeQuery();
			
			while (rs.next()) {
				Child child = new Child();
				child.setId(rs.getInt("id"));
				child.setName(rs.getString("name_child"));
				child.setSurname(rs.getString("surname_child"));
				child.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
				child.setParentID(rs.getInt("parent_id"));
				//child.setParent((User) rs.getObject("parent_id"));
				children.add(child);				
			}
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException {}", e);	
			throw new DAOException("InterruptedException ", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLchildDAO getAll() {} ", e);			
			throw new DAOException("Exception in SQLchildDAO getAll()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}
	 
	       return children;
	}

	@Override
	public boolean update(Child child) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		int countRows = 0;		
		
		try {
			connection = conPool.takeConnection();					
			preparedStatment = connection.prepareStatement(UPDATE_CHILD);			

			
			preparedStatment.setString(1, child.getName());
			preparedStatment.setString(2, child.getSurname());
			preparedStatment.setDate(3, Date.valueOf(child.getDateOfBirth()));
			preparedStatment.setInt(4, child.getParentID());
			preparedStatment.setInt(5, child.getId());
			
			countRows = preparedStatment.executeUpdate();	
			
			logger.info("child {} is updated", child.getName());

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLchildDAO.updatechild() {}", e);			
			throw new DAOException("InterruptedException in SQLchildDAO.updatechild()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLchildDAO.updatechild() {} ", e);			
			throw new DAOException("SQLException in SQLchildDAO.updatechild()", e);
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
		logger.info("deleting child by id {}", id);			
			    
	    try {
	    	connection = conPool.takeConnection();
	    	preparedStatment = connection.prepareStatement(DELETE_CHILD);			
	    	preparedStatment.setInt(1, id);
			countRows = preparedStatment.executeUpdate();
					
			logger.info("deleting child by id {}", id);
		

		}catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.deletechild() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.deletechild()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.deletechild() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.deletechild()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
	    
		return countRows == 1;
	}

	@Override
	public List<Child> search(String where, String what) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void closeResources(AutoCloseable... resources) throws DAOException {
		for (AutoCloseable resource : resources) {
			if (resource != null) {
				try {
					logger.info("Closing resourse {} ", resource.getClass());
					resource.close();
				} catch (Exception e) {
					logger.error("Resourse not closed {} ", resource.getClass());
					throw new DAOException("Resourse not closed", e);

				}
			}
		}
	}

}
