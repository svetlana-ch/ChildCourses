package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.dao.LessonDAO;
import by.htp.courses.dao.connectionpool.ConnectionPool;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Lesson;

public class SQLLessonDAO implements LessonDAO{

	private static final Logger logger = LoggerFactory.getLogger(SQLLessonDAO.class);
	
	private final static String CREATE_LESSON = "INSERT into lessons (date_lesson, id_subject, id_teacher, id_group) values(?, ?, ?, ?)";
	private final static String GET_ALL_LESSONS = "SELECT * FROM lessons";
	private final static String UPDATE_LESSON = "UPDATE `courses`.`lessons` SET `date_lesson`=?, `id_subject`=?, `id_teacher`=?, `id_group`=? WHERE `id_lesson`=?;";			
	private final static String DELETE_LESSON = "DELETE FROM lessons WHERE id_lesson=?";
	private final static String GET_ALL_LESSONS_SEARCH = "SELECT lessons.id_lesson, lessons.date_lesson, lessons.id_subject, lessons.id_teacher, lessons.id_group, subjects.id, subjects.name_subject, users.id, users.name "
			+ "FROM lessons\n "
			+ "JOIN subjects\n"
			+ "ON lessons.id_subject = subjects.id\n"
			+ "JOIN users\n"
			+ "ON users.id = lessons.id_teacher\n"
			+ "WHERE users.role = 'TEACHER' AND" ;
	private final static String GET_ALL_LESSONS_SEARCH1 = "SELECT lessons.id_lesson, lessons.date_lesson, lessons.id_subject, lessons.id_teacher, lessons.id_group, subjects.id, subjects.name_subject, users.id, users.name "
			+ "FROM lessons\n "
			+ "JOIN subjects\n"
			+ "ON lessons.id_subject = subjects.id\n"
			+ "JOIN users\n"
			+ "ON users.id = lessons.id_teacher\n"
			+ "WHERE users.role = 'TEACHER' AND ? like ? AND lessons.date_lesson BETWEEN ? AND ?" ;
	
	
	
	

							
											
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
			
			preparedStatment.setDate(1, Date.valueOf(lesson.getDate()));
			preparedStatment.setInt(2, lesson.getSubjectID());
			preparedStatment.setInt(3, lesson.getTeacherID());
			preparedStatment.setInt(4, lesson.getGroupID());
									
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
				lesson.setId(rs.getInt("id_lesson"));
				lesson.setDate(rs.getDate("date_lesson").toLocalDate());
				lesson.setSubjectID(rs.getInt("id_subject"));
				lesson.setTeacherID(rs.getInt("id_teacher"));
				lesson.setGroupID(rs.getInt("id_group"));			
				
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
			
			preparedStatment.setDate(1, Date.valueOf(lesson.getDate()));
			preparedStatment.setInt(2, lesson.getSubjectID());
			preparedStatment.setInt(3, lesson.getTeacherID());
			preparedStatment.setInt(4, lesson.getGroupID());
			preparedStatment.setInt(5, lesson.getId());
			
			logger.info("lesson {} is !!!!!!!!!!!!!!!!!!!!!d", lesson.getDate());
			
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
	public List<Lesson> search(String where, String what, LocalDate date_start, LocalDate date_end) throws DAOException {
		String WHERE = " " +  where +  " like '%" + what + "%' AND lessons.date_lesson BETWEEN '" + date_start + "' AND '" + date_end +"'";		
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ConnectionPool conPool = ConnectionPool.getInstance();
		String sql = null;
		ResultSet rs = null;
		
		List<Lesson> lessons = new ArrayList<>();
		
		 try {
		    	connection = conPool.takeConnection();
				sql = GET_ALL_LESSONS_SEARCH + WHERE;
										
				preparedStatment = connection.prepareStatement(sql);				
				
				logger.info("where ==  {}    what == {}   ", where, what);
				logger.info("sql ==  {}", sql);	
				logger.info("PS =====     {}", preparedStatment);

				rs = preparedStatment.executeQuery();
				logger.info("RS =====     {}", rs);
				
				while (rs.next()) {
					Lesson lesson = new Lesson();
					lesson.setId(rs.getInt("id_lesson"));
					lesson.setDate(rs.getDate("date_lesson").toLocalDate());
					lesson.setSubjectID(rs.getInt("id_subject"));
					lesson.setTeacherID(rs.getInt("id_teacher"));
					lesson.setGroupID(rs.getInt("id_group"));			
					
					lessons.add(lesson);	
				}
				
			} catch (InterruptedException e) {
				logger.error("InterruptedException search() {}", e);	
				throw new DAOException("InterruptedException ", e);
			} catch (SQLException e) {
				logger.error("Exception in SQLlessonDAO search() {} ", e);			
				throw new DAOException("Exception in SQLlessonDAO search()", e);
			} finally {
				closeResources(rs, preparedStatment, connection);
			}
		 
		       return lessons;
	
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
