package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.User;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;

public class SignIn implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	 

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		 * 
@WebServlet("/cities")

public class CitiesServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(CitiesServlet.class);



    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/jsp/errorPage.jsp");

    }



    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        CitiesDao citiesDao = new CitiesDao();

        List cities = citiesDao.getAll();

        req.setAttribute("cities", cities);

        req.getRequestDispatcher("/jsp/createFlight.jsp").forward(req, resp);



    }

		 */
		
		
		
		
		
		
		String login;
		String password;
		
		login = request.getParameter(LOGIN_PARAM_NAME);
		password = request.getParameter(PASSWORD_PARAM_NAME);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		
		User user = null;
		String goToPage = null;
		// request.setAttribute(ERROR_MESSAGE.inString, NO_SUCH_USER.inString); 
		//request.getSession(true).setAttribute(ROLE.inString, userDTO.getRole());
		//request.getSession().setAttribute(USER_NAME.inString, userDTO.getLogin());
		//request.getSession(true).setAttribute(LAST_COMMAND.inString, lastCMD);
		//request.getRequestDispatcher(goToPage).forward(request, response); 
		//ValidationError validationError = newUserValidation.validate(newUser, password);

       // if (validationError.hasValidationErrors()) { request.setAttribute("errors", validationError.getErrors()); 
		
		/*else {
                    if (!userService.isUserExists(newUser.getLogin())) {
                        userService.create(newUser, PasswordToHash.getHash(password));
                        page = MAIN;
                        request.setAttribute("newUserMessage", "Welcome " + newUser.getName() + " " + newUser.getLastName() + " to our site!");
                    } else {
                        validationError.putValidationError("User Already Exists");
                        request.setAttribute("errors", validationError.getErrors());
                    } */
		
		if (request.getMethod().toUpperCase().equals("POST")) {

			try {
				user = userService.signin(login, password);
				if (user != null) {
					request.getSession(true).setAttribute("user", user);
					goToPage = JSPPagePath.MAIN_PAGE;
					logger.debug("User " + user.getName() + " SignIn");
				} else {
					request.setAttribute("errorMessage", "Incorrect login or password.");
					goToPage = JSPPagePath.MAIN_PAGE;
				}

			} catch (ServiceException e) {
				// goToPage = JSPPagePath.ERROR_PAGE;
				logger.debug("User " + user.getName() + " hasn`t sign in");
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);

			dispatcher.forward(request, response);
		}

	}

}
