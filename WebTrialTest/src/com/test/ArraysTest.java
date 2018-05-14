package com.test;

import java.util.Arrays;
import java.util.List;

public class ArraysTest {

	public static void main(String[] args) {
		//配列の場合
		String[] array1 = {"a","b","c"};
		String[] array2 = {"a","b","c"};
		System.out.println(Arrays.equals(array1, array2));
		
		//Listの場合
		List<String> list1 = Arrays.asList(array1);
		List<String> list2 = Arrays.asList(array2);
		System.out.println(list1.equals(list2));
	}

}
