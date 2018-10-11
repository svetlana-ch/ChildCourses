package by.htp.courses.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;

public class Controller extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	private static final long serialVersionUID = 1L;
       
	private static final String COMMAND_PARAM_NAME = "command";	
	
	private final CommandProvider provider = new CommandProvider();
	
    public Controller() {    	
        super();         
    }    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(" doGet" );
		String commandName = request.getParameter(COMMAND_PARAM_NAME);			
		logger.info("commandNAme=" + commandName);
		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" doPost" );
		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		logger.info("commandNAme=" + commandName);		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);
	}

}
