package by.htp.courses.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;

public class SQLSubjectDAO implements SubjectDAO{

	@Override
	public boolean create(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = null;		
		int countRows;
		System.out.println("Мы в SQLSubjectDAO      " );
		
		 System.out.println(" предмет ==     возраст  ="+ subject.getAgeChildFrom() + "  стоимость =" + subject.getCost()
		 + " количество в неделю  " + subject.getNumberPerWeek() +
				 " наименование = " + subject.getSubjectName()
				 + " время проведения = " + subject.getTimeSpending()
				 + "  пол ребенка  = " + subject.getSexChild()
				 + " НОВИНКА = " + subject.isNew());
		
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/courses?useSSL=false",
					"root", "1234");
			System.out.println("Подключили драйвер  ");
			sql = "insert into subjects (name_subject, age_child_from, sex_child, time_spending, number_per_week, is_new, cost)"
					+ " values(?, ?, ?, ?, ?, ?, ?)";
			
			//sql = "insert into subjects (name_subject) values(?)";
			
			statement = connection.prepareStatement(sql);
			
			System.out.println("Подготовленный запрос   ");
			
			statement.setString(1, subject.getSubjectName());
			statement.setInt(2, subject.getAgeChildFrom());		
			statement.setString(3, subject.getSexChild());
			statement.setString(4, subject.getTimeSpending());
			statement.setInt(5, subject.getNumberPerWeek());
			statement.setBoolean(6, subject.isNew());
			statement.setDouble(7, subject.getCost());
			
			System.out.println("Получили параметры   statement.setString(1, );" + subject.getSubjectName());
			
			
			countRows = statement.executeUpdate();	
			
			System.out.println("Update с параметрами   ");
			
			System.out.println("countRows ==    " + countRows);
			
			if (countRows == 1) {				
				System.out.println("**************    СТРОКА добавлена****");
				return true;				
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new DAOException("ОШИБКА ДОБАВЛЕНИЯ В БД", e);
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// log error
				}
			}
		}
		return false;
	}

	@Override
	public Subject read(Subject subject) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getAll(String WHERE) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Subject subject) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> search(String where, String what) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
