package by.htp.courses.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.LessonDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Child;
import by.htp.courses.domain.Lesson;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;

public class SQLLessonDAO implements LessonDAO{

	private static final Logger logger = LoggerFactory.getLogger(SQLLessonDAO.class);
	
	private final static String CREATE_LESSON = "INSERT into lessons (name_lesson, age_child_from, age_child_to, sex_child, time_spending, number_per_week, is_new, cost)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String GET_ALL_LESSONS = "SELECT * FROM lessons";
	private final static String UPDATE_LESSON = "UPDATE `courses`.`lessons` SET `name_lesson`=?, `age_child_from`=?, `age_child_to`=?, `sex_child`=?, `time_spending`=?, `number_per_week`=?, `is_new`=?, `cost`=? WHERE `id`=?;";
			
	private static final String DELETE_LESSON = "DELETE FROM lessons WHERE id=?";
	
	@Override
	public boolean create(Lesson lesson) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
			
		int countRows;
		logger.info("creating lesson");
		
		try {
			connection = conPool.takeConnection();			
			
			preparedStatment = connection.prepareStatement(CREATE_LESSON);
			
			preparedStatment.setDate(1, (java.sql.Date) lesson.getDate());
			preparedStatment.setObject(2, lesson.getSubject());
			preparedStatment.setObject(3, lesson.getTeacher());
			preparedStatment.setArray(4, (Array) lesson.getGroup());
									
			countRows = preparedStatment.executeUpdate();	
					
			if (countRows == 1) {				
				logger.info("lesson {} is created", lesson.getDate());
				return true;				
			}

		} catch (InterruptedException e) {
			logger.error("InterruptedException in CREATE lesson {}", e);	
			throw new DAOException("InterruptedException in CREATE lesson", e);
		} catch (SQLException e) {
			logger.error("SQLException in CREATE lesson {}", e);
			throw new DAOException("SQLException in CREATE lesson", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
		return false;
	}

	@Override
	public Lesson read(Lesson lesson) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> getAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		ResultSet rs = null;	
			
		List<Lesson> lessons = new ArrayList<>();	
	    
	    try {
	    	connection = conPool.takeConnection();								
			preparedStatment = connection.prepareStatement(GET_ALL_LESSONS);			
					
			rs = preparedStatment.executeQuery();
			
			while (rs.next()) {
				Lesson lesson = new Lesson();
				lesson.setId(rs.getInt("id"));
				lesson.setDate(rs.getDate("date_lesson"));
				lesson.setSubject((Subject) rs.getObject("subject"));
				lesson.setTeacher((User) rs.getObject("teacher"));
				lesson.setGroup((List<Child>) rs.getArray("group"));
				
				
				preparedStatment.setDate(1, (java.sql.Date) lesson.getDate());
				preparedStatment.setObject(2, lesson.getSubject());
				preparedStatment.setObject(3, lesson.getTeacher());
				preparedStatment.setArray(4, (Array) lesson.getGroup());
				
				lessons.add(lesson);	
			}
			
		} catch (InterruptedException e) {
			logger.error("InterruptedException {}", e);	
			throw new DAOException("InterruptedException ", e);
		} catch (SQLException e) {
			logger.error("Exception in SQLlessonDAO getAll() {} ", e);			
			throw new DAOException("Exception in SQLlessonDAO getAll()", e);
		} finally {
			closeResources(rs, preparedStatment, connection);
		}
	 
	       return lessons;
	}

	@Override
	public boolean update(Lesson lesson) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();		
		int countRows = 0;		
		
		try {
			connection = conPool.takeConnection();					
			preparedStatment = connection.prepareStatement(UPDATE_LESSON);			

			preparedStatment.setDate(1, (java.sql.Date) lesson.getDate());
			preparedStatment.setObject(2, lesson.getSubject());
			preparedStatment.setObject(3, lesson.getTeacher());
			preparedStatment.setArray(4, (Array) lesson.getGroup());
			
			countRows = preparedStatment.executeUpdate();	
			
			logger.info("lesson {} is updated", lesson.getDate());

		} catch (InterruptedException e) {
			logger.error("InterruptedException in SQLlessonDAO.updatelesson() {}", e);			
			throw new DAOException("InterruptedException in SQLlessonDAO.updatelesson()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLlessonDAO.updatelesson() {} ", e);			
			throw new DAOException("SQLException in SQLlessonDAO.updatelesson()", e);
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
		logger.info("deleting lesson by id {}", id);			
			    
	    try {
	    	connection = conPool.takeConnection();
	    	preparedStatment = connection.prepareStatement(DELETE_LESSON);			
	    	preparedStatment.setInt(1, id);
			countRows = preparedStatment.executeUpdate();
					
			logger.info("deleting lesson by id {}", id);
		
		}catch (InterruptedException e) {
			logger.error("InterruptedException in SQLUserDAO.deletelesson() {}", e);		
			throw new DAOException("InterruptedException in SQLUserDAO.deletelesson()", e);
		} catch (SQLException e) {
			logger.error("SQLException in SQLUserDAO.deletelesson() {} ", e);			
			throw new DAOException("SQLException in SQLUserDAO.deletelesson()", e);
		} finally {
			closeResources(preparedStatment, connection);
		}
	    
		return countRows == 1;
	}

	@Override
	public List<Lesson> search(String where, String what) throws DAOException {
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
