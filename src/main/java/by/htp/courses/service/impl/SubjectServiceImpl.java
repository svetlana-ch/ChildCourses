package by.htp.courses.service.impl;

import java.util.List;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.SubjectDAO;
import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.Subject;
import by.htp.courses.domain.User;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.exception.ServiceException;

public class SubjectServiceImpl implements SubjectService {

	@Override
	public boolean create(Subject subject) throws ServiceException {
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
		//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 
				
								
				DAOFactory factory = DAOFactory.getInstance();
				SubjectDAO dao = factory.getSubjectDAO();
				System.out.println("Мы в     СЕРВИСЫ subject" );
				 System.out.println(" предмет ==     возраст  ="+ subject.getAgeChildFrom() + "  стоимость =" + subject.getCost()
				 + " количество в неделю  " + subject.getNumberPerWeek() +
						 " наименование = " + subject.getSubjectName()
						 + " время проведения = " + subject.getTimeSpending()
						 + "  пол ребенка  = " + subject.getSexChild()
						 + " НОВИНКА = " + subject.isNew());
								
				try {
					return dao.create(subject);
				} catch (DAOException e) {					
					throw new ServiceException("ОШИБКА создания предмета", e);					
				}				
		
	}

	@Override
	public Subject read(Subject subject) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getAll(String WHERE) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Subject subject) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> search(String where, String what) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
