package by.htp.courses.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import by.htp.courses.dao.DAOFactory;
import by.htp.courses.dao.UserDAO;
import by.htp.courses.dao.exception.DAOException;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.UserService;
import by.htp.courses.service.exception.ServiceException;
import by.htp.courses.service.impl.UserServiceImpl;

public class UserServiceImplTest {
	
	 private static UserService userService;
	 private static UserDAO userDAO;
	 
	 
	// DAOFactory factory = DAOFactory.getInstance();
	// userDAO = factory.getUserDAO();



	  @BeforeAll
	  public static void setup() {		  
		  userDAO = Mockito.mock(UserDAO.class);
		  //userService = new UserServiceImpl(userDAO);
		  ServiceFactory factory = ServiceFactory.getInstance();
		  UserService userService = factory.getUserService();
	  }



	  @AfterEach
	  public void reset() {
		  Mockito.reset(userDAO);
	  }



	  @Test
	  public void exceptionFlow() throws DAOException, ServiceException {
	    final String errorMessage = "unknown error";
	    Mockito.doThrow(new RuntimeException(errorMessage)).when(userDAO).delete(1);
	    try {
	      userService.delete(1);
	      Assertions.fail("expected exception");
	    } catch ( IllegalArgumentException e ) {
	      Assertions.assertEquals(errorMessage, e.getMessage());
	    }
	  }

	  @Test
	  public void successfulFlow() throws ServiceException, DAOException {
	    userService.delete(1);
	    Mockito.verify(userDAO).delete(1);
	  }



	  @Test
	  public void invalidFlow() throws ServiceException {
	    userService.delete(0);
	    Mockito.verifyZeroInteractions(userDAO);
	  }

}
