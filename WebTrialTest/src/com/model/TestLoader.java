package com.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.trial.sql.dao.QtAnswerDAO;
import com.trial.sql.model.QtAnswer;

public class TestLoader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<QtAnswer> answer;
	
	public TestLoader() {
		QtAnswerDAO answerDAO = new QtAnswerDAO();
		answer = answerDAO.findAll();
	}
	
	public List<QtAnswer> getAnswer(int i){
		List<QtAnswer> list = answer.stream()
									.filter(s -> s.getQtCode() == i)
									.collect(Collectors.toList());
		return list;
	}
}
