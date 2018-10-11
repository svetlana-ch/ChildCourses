package by.htp.courses.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FilterLocalization implements Filter{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

		String previousQuery = httpRequest.getQueryString();

		

		if(previousQuery != null ) {

			httpRequest.getSession().setAttribute("previousQuery", previousQuery);

			

		}

		filterChain.doFilter(httpRequest, servletResponse);
		
	}

}



