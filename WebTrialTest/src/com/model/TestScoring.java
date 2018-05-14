package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestScoring implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String[]> answerList;
	
	public TestScoring() {}
	
	public TestScoring(HttpServletRequest request) {
		this.answerList = setAnswerList(request);
	}
	
	private List<String[]> setAnswerList(HttpServletRequest request) {
		int qtCount = Integer.valueOf(request.getParameter("qtcount"));
		List<String[]> qList = new ArrayList<>();
		for(int i=0;i<qtCount;i++) {
			qList.add(request.getParameterValues("q" + i));
		}
		return qList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String[]> getAnswerList() {
		return answerList;
	}

}