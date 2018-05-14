package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.TestLoader;
import com.model.TestScoring;

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
		
		//文字コードをセット
		request.setCharacterEncoding("UTF-8");
		//解答を取得
		TestScoring scoring = new TestScoring(request);
		//採点する
		request.setAttribute("scoring", scoring);
		doGet(request, response);
		
	}

	@Override
	public void init() throws ServletException {
		loader = new TestLoader();
	}
	
}
