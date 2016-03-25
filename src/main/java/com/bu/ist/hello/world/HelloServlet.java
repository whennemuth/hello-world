package com.bu.ist.hello.world;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		String target = "WEB-INF/jsp/hello.jsp";
		
		if(task != null) {
			switch(task) {
			case "config":
				target = "WEB-INF/jsp/config.jsp";
				request.setAttribute("cfg", new HelloConfig());
				break;
			case "lookup":
				target = "WEB-INF/jsp/dblookup.jsp";
				request.setAttribute("db", new DBLookup(new HelloConfig()));
				break;
			}			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
