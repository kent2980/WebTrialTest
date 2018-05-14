package com.test;

import java.math.BigDecimal;
import java.util.List;

import com.model.TestLoader;

public class ScoringTest {
	
	public static void main(String[] args) {
		//test1をダウンロード
		TestLoader test1 = new TestLoader();
		List<String[]> test1Data = test1.getAnswer(7);
		
		//正解/不正解をダウンロードする
		TestLoader scoring = new TestLoader();
		List<Boolean> scoringData = scoring.getScoringList(7, test1Data);
		
		//結果を操作する
		scoringData.removeAll(scoringData);
		scoringData.add(true);
		scoringData.stream().forEach(System.out::println);
		
		//採点する
		BigDecimal sco = scoring.getScoring(7, scoringData);
		System.out.println("採点結果は・・・");
		System.out.println(sco + "点");
	}	
}
