package com.test;

public class UnicodeTest {

	public static void main(String[] args) {
		char s = 0x0041 + 2;
		System.out.println(s);
		String a = "A";
		int t = Integer.valueOf(Integer.toHexString(s));
		System.out.println(t);
		System.out.println(0x0043==t);
		
	}

}
