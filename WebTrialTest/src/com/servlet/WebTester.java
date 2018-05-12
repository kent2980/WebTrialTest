package com.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.TestLoader;

/**
 * Servlet implementation class WebTester
 */
@WebServlet("/WebTester")
public class WebTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TestLoader loader;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebTester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("answerLoader", loader);
		RequestDispatcher di = request.getRequestDispatcher("/WEB-INF/jsp/web-test.jsp");
		di.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		loader = new TestLoader();
	}

}