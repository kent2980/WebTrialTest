package com.test;

import java.util.List;

import com.model.TestLoader;

public class GetTitleTest {

	public static void main(String[] args) {
		TestLoader loader = new TestLoader();
		List<String> title = loader.getTitle();
		title.stream()
			.forEach(System.out::println);
	}

}
