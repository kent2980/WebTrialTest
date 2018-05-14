package com.test;

import java.util.List;

import com.model.TestLoader;
import com.trial.sql.model.QtAnswer;

public class DataBaseConnectTest {

	public static void main(String[] args) {
		TestLoader loader = new TestLoader();
		List<QtAnswer> list = loader.getQtAnswer(1);
		System.out.println(list.get(0).getAnswer1());
	}

}
