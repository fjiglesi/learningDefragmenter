package com.uah.learningdefragmenter.servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LearningDefragmenterFilter
 */
@WebFilter("/LearningDefragmenterFilter")
public class LearningDefragmenterFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LearningDefragmenterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		Cookie[]cqs=httpRequest.getCookies();
		String resUrl=httpRequest.getParameter("resource");
		HttpSession session=httpRequest.getSession();
		if(resUrl!=null){
			URL url=new URL(resUrl);
			String basePath=url.toString();
			session.setAttribute("basePath",basePath);
			session.setAttribute("resourceURI","");
		}else{
			session.setAttribute("resourceURI",httpRequest.getRequestURI());
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
