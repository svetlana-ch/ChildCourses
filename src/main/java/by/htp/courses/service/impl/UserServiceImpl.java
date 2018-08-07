package by.htp.courses.service.impl;

import java.util.List;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.domain.User;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	@Override
	public User signin(String login, String password) throws ServiceException {
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
		//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		User user = null;
		try {
			user = dao.checkUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException("ServiceException  public User signin()   ", e);
		}
		
		
		return user;
	}

	@Override
	public User signup(String name, String login, String email, String password) throws ServiceException {
		
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
		//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 
		User user = null;
		if(login == null || login == "" || password == null || password == "" ) {
			return user;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		System.out.println("Мы в signupImpl    СЕРВИСЫ" );
		System.out.println("логин ==   " + login);
		System.out.println("пароль  ==     " + password);
		System.out.println("e-mail ==     " + email);
		
		try {
			user = dao.createUser(name, login, email, password);
		} catch (DAOException e) {
			throw new ServiceException("ОШИБКА РЕГИСТРАЦИИ", e);
		}
		
		
		return user;
	}

	@Override
	public List<User> getAll(String WHERE) throws ServiceException {
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
				//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		List<User> users = null;
		try {
			System.out.println("Мы в сервисах getAll   1");
			users = dao.getAll(WHERE);
			System.out.println("Мы в сервисах  getAll   2");
			
		} catch (DAOException e) {
			throw new ServiceException("ОШИБКА в get.All()   !!!!!! ", e);
		}
		
		
		return users;
	/*	 User userFromForm = new User();
         try {
             userFromForm.setId(Form.getInt(req, "ID"));
             userFromForm.setLogin(Form.getString(req, "Login", Patterns.LOGIN));
             userFromForm.setPassword(Form.getString(req, "Password", Patterns.PASSWORD));
             userFromForm.setEmail(Form.getString(req, "Email", Patterns.EMAIL));
             userFromForm.setFk_Role(Form.getInt(req, "fk_Role"));
             if (userFromForm.getId() > 0) {
                 dao.user.update(userFromForm);
             } else if (userFromForm.getId() == 0) {
                 dao.user.create(userFromForm);
             } else {
                 userFromForm.setId(-1 * userFromForm.getId());
                 dao.user.delete(userFromForm);
             }
         } catch (ParseException e) {
             e.printStackTrace();
         }
         List<User> users = dao.user.getAll("");
         req.setAttribute("users", users);
         List<Role> roles = dao.role.getAll("");
         req.setAttribute("roles", roles);*/
	}

	@Override
	public boolean update(User user) throws ServiceException {
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
		//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 

		if(user.getLogin() == null || user.getLogin() == "" || user.getPassword() == null || user.getPassword() == "" ) {
			return false;
		}
		
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();		
		
		try {
			System.out.println("Мы в сервисах update   1");
			return dao.update(user);			
		} catch (DAOException e) {
			throw new ServiceException("Сервисы ОШИБКА Редактирования пользователя", e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();		
		
		try {
			System.out.println("Мы в сервисах delete   1");
			return dao.delete(id);			
		} catch (DAOException e) {
			throw new ServiceException("Сервисы ОШИБКА УДАЛЕНИЯ", e);
		}
		
		
	}

	@Override
	public List<User> search(String where, String what) throws ServiceException {
		//validation  что логин не пустой, содержит нужное количество символов что пароль имеет допустимую для вашей системы длину
		//и что если пользователь передал абс, то вы даже к базе с этим не будете отращаться 
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		List<User> users = null;
		try {
			System.out.println("Мы в сервисах search   1");
			users = dao.search(where, what);
			System.out.println("Мы в сервисах search   2");
			
		} catch (DAOException e) {
			throw new ServiceException("ОШИБКА в search(where, what)   !!!!!! ", e);
		}
		
		
		return users;
	}


}
