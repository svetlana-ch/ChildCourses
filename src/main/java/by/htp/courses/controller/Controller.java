package by.htp.courses.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.courses.controller.command.Command;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String COMMAND_PARAM_NAME = "command";	
	
	private final CommandProvider provider = new CommandProvider();
	
    public Controller() {    	
        super(); 
        System.out.println("Мы в конструкторе контроллера" );
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" метод doGet" );
		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		System.out.println("commandNAme=" + commandName);
		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" метод doPost" );
		String commandName = request.getParameter(COMMAND_PARAM_NAME);
		System.out.println("commandNAme=" + commandName);
		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);
	}

}
