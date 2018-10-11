package by.htp.courses.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.courses.controller.command.Command;
import by.htp.courses.domain.Child;
import by.htp.courses.domain.Subject;
import by.htp.courses.service.ChildService;
import by.htp.courses.service.ServiceFactory;
import by.htp.courses.service.SubjectService;
import by.htp.courses.service.exception.ServiceException;

public class SubjectsPage implements Command{
	
	private static final Logger logger = LoggerFactory.getLogger(SubjectsPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		SubjectService subjectService = factory.getSubjectService();
		
		List<Subject> subjects = null;
		
		try {
			subjects = subjectService.getAll();
			request.setAttribute("subjects", subjects);			

		} catch (ServiceException e1) {
			logger.error("Exception   {}", e1);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.SUBJECTS_PAGE);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("Exception   {}", e);
		}
		
	}

}
