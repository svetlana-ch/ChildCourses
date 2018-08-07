package by.htp.courses.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.courses.controller.command.Command;



public class MainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher dispatcher = request
				.getRequestDispatcher(JSPPagePath.MAIN_PAGE);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			//log ?????????????
		}

	}

}
