package com.test;

import java.util.Arrays;

public class EqueseTest {

	public static void main(String[] args) {
		String a = "A";
		String[] A = {"A","B","C"};
		Boolean w = Arrays.asList(A).stream().anyMatch(s->s.equals(a));
		System.out.println(w);
	}

}
