package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * リクエストパラメータから回答をリスト化するクラス
 * @author kent2
 *
 */
public class TestScoring implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String[]> answerList;
	
	public TestScoring() {}
	
	/**
	 * コンストラクタ
	 * @param request
	 */
	public TestScoring(HttpServletRequest request) {
		this.answerList = setAnswerList(request);
	}
	
	/**
	 * リクエストパラメータから全ての回答をリストにセットします
	 * @param request
	 * @return 解答リスト
	 */
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

	/**
	 * リクエストパラメータから取得した回答をリストで返します
	 * @return 回答リスト
	 */
	public List<String[]> getAnswerList() {
		return answerList;
	}

}