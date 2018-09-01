package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Subject;

public class SQLSubjectDAO implements SubjectDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(SQLSubjectDAO.class);

	
	private final static String CREATE_SUBJECT = "INSERT into subjects (name_subject, age_child_from, age_child_to, sex_child, time_spending, number_per_week, is_new, cost)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String GET_ALL_SUBJECTS = "SELECT * FROM subjects";
	private final static String UPDATE_SUBJECT = "UPDATE `courses`.`subjects` SET `name_subject`=?, `age_child_from`=?, `age_child_to`=?, `sex_child`=?, `time_spending`=?, `number_per_week`=?, `is_new`=?, `cost`=? WHERE `id`=?;";
			
	private static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE id=?";
	
	
	@Override
	public boolean create(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
			
		int countRows;
		logger.info("creating subject");
		
		try {
			connection = conPool.takeConnection();			
			
			preparedStatment = connection.prepareStatement(CREATE_SUBJECT);
			
			preparedStatment.setString(1, subject.getSubjectName());
			preparedStatment.setInt(2, subject.getAgeChildFrom());
			preparedStatment.setInt(3, subject.getAgeChildTo());
			preparedStatment.setString(4, subject.getSexChild());
			preparedStatment.setString(5, subject.getTimeSpending());
			preparedStatment.setInt(6, subject.getNumberPerWeek());
			preparedStatment.setBoolean(7, subject.isNew());
			preparedStatment.setDouble(8, subject.getCost());			
			
			countRows = preparedStatment.executeUpdate();	
					
			if (countRows == 1) {				
				logger.info("Subject {} is created", subject.getSubjectName());
				return true;				
			}

		} catch (InterruptedException e) {
			logger.error("InterruptedException in CREATE SUBJECT {}", e);	
			throw new DAOException("InterruptedException in CREATE SUBJECT", e);
		} catch (SQLException e) {
			logger.error("SQLException in CREATE SUBJECT {}", e);
			throw new DAOException("SQLException in CREATE SUBJECT", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
		return false;
	}

	@Override
	public Subject read(Subject subject) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getAll() throws DAOException {
		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		ResultSet rs = null;	
			
		List<Subject> subjects = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();								
			preparedStatment = connection.prepareStatement(GET_ALL_SUBJECTS);			
					
			rs = preparedStatment.executeQuery();
			
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setSubjectName(rs.getString("name_subject"));
				subject.setAgeChildFrom(rs.getInt("age_child_from"));
				subject.setAgeChildTo(rs.getInt("age_child_to"));
				subject.setSexChild(rs.getString("sex_child"));
				subject.setTimeSpending(rs.getString("time_spending"));
				subject.setNumberPerWeek(rs.getInt("number_per_week"));
				subject.setNew(rs.getBoolean("is_new"));
				subject.setCost(rs.getDouble("cost"));
				subjects.add(subject);	
			}
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException {}", e);	
			throw new DAOException("InterruptedException ", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLSubjectDAO getAll() {} ", e);			
			throw new DAOException("Exception in SQLSubjectDAO getAll()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}
	 
	       return subjects;
	}

	@Override
	public boolean update(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		int countRows = 0;		
		
		try {
			connection = conPool.takeConnection();					
			preparedStatment = connection.prepareStatement(UPDATE_SUBJECT);			

			preparedStatment.setString(1, subject.getSubjectName());
			preparedStatment.setInt(2, subject.getAgeChildFrom());
			preparedStatment.setInt(3, subject.getAgeChildTo());
			preparedStatment.setString(4, subject.getSexChild());
			preparedStatment.setString(5, subject.getTimeSpending());
			preparedStatment.setInt(6, subject.getNumberPerWeek());
			preparedStatment.setBoolean(7, subject.isNew());
			preparedStatment.setDouble(8, subject.getCost());	
			preparedStatment.setInt(9, subject.getId());
			
			countRows = preparedStatment.executeUpdate();	
			
			logger.info("Subject {} is updated", subject.getSubjectName());

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLSubjectDAO.updateSubject() {}", e);			
			throw new DAOException("InterruptedException in SQLSubjectDAO.updateSubject()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLSubjectDAO.updateSubject() {} ", e);			
			throw new DAOException("SQLException in SQLSubjectDAO.updateSubject()", e);
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
		logger.info("deleting subject by id {}", id);			
			    
	    try {
	    	connection = conPool.takeConnection();
	    	preparedStatment = connection.prepareStatement(DELETE_SUBJECT);			
	    	preparedStatment.setInt(1, id);
			countRows = preparedStatment.executeUpdate();
					
			logger.info("deleting subject by id {}", id);
		

		}catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.deleteSubject() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.deleteSubject()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.deleteSubject() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.deleteSubject()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
	    
		return countRows == 1;
	}

	@Override
	public List<Subject> search(String where, String what) throws DAOException {
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
