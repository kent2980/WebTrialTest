package com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.trial.sql.dao.QtAnswerDAO;
import com.trial.sql.model.QtAnswer;

/**
 * テストデータをダウンロードするクラス
 * @author kent2
 *
 */
public class TestLoader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<QtAnswer> answer;
	
	/**
	 * コンストラクタ
	 * すべてのテストデータをダウンロードします
	 */
	public TestLoader() {
		QtAnswerDAO answerDAO = new QtAnswerDAO();
		answer = answerDAO.findAll();
	}
	
	/**
	 * 指定された問題をダウンロードします
	 * @param qtCode 問題指定コード
	 * @return 指定コードの問題
	 */
	public List<QtAnswer> getQtAnswer(int qtCode){
		List<QtAnswer> list = answer.stream()
									.filter(s -> s.getQtCode() == qtCode)
									.collect(Collectors.toList());
		return list;
	}
	
	/**
	 * すべての問題をダウンロード
	 * @return すべての問題
	 */
	public List<QtAnswer> getAnswer(){
		return answer;
	}
	
	/**
	 * すべての問題タイトルをダウンロードする
	 * @return すべての問題タイトル
	 */
	public List<String> getTitle(){
		List<String> title = answer.stream()
								   .sorted(Comparator.comparingInt(QtAnswer::getQtCode))
								   .map(s-> s.getQtName())
								   .distinct()
								   .collect(Collectors.toList());							
		return title;
	}
	
	/**
	 * 指定された問題タイトルを返します
	 * @param qtCode
	 * @return 指定された問題タイトル
	 */
	public String getTitle(int qtCode){
		String title = answer.stream()
						     .filter(s->s.getQtCode()==qtCode)
							 .sorted(Comparator.comparingInt(QtAnswer::getQtCode))
							 .map(s-> s.getQtName())
							 .distinct()
							 .findFirst()
							 .get();
		return title;
	}
	
	/**
	 * 指定された問題の出題数を返します
	 * @param qtCode
	 * @return 指定された問題の出題数
	 */
	public int getAnswerCount(int qtCode) {
		int t = (int)answer.stream()
						   .filter(s -> s.getQtCode() == qtCode)
						   .count();
		return t;
	}
	
	/**
	 * 出題された問題を配列としてダウンロードします
	 * @param qtCode
	 * @return 問題を配列でダウンロード
	 */
	public List<String[]> getAnswer(int qtCode){
		//qtCode(x)に限定する
		List<QtAnswer> list = answer.stream().filter(s -> s.getQtCode()==qtCode).collect(Collectors.toList());
		//処理を開始する
		List<String[]> answerList = new ArrayList<>();
		String[] ansArray = null;
		for(QtAnswer ans : list) {
			if(ans.getAnswerCount()==1) {
				ansArray = new String[1];
				ansArray[0] = ans.getAnswer1();
			}else if(ans.getAnswerCount()==2) {
				ansArray = new String[2];
				ansArray[0] = ans.getAnswer1();
				ansArray[1] = ans.getAnswer2();
			}else if(ans.getAnswerCount()==3) {
				ansArray = new String[3];
				ansArray[0] = ans.getAnswer1();
				ansArray[1] = ans.getAnswer2();
				ansArray[2] = ans.getAnswer3();
			}
			answerList.add(ansArray);
		}
		return answerList;
	}
	
	/**
	 * テストの正解/不正解を判定します。
	 * @param qtCode
	 * @param answerList
	 * @return テスト結果
	 */
	public List<Boolean> getScoringList(int qtCode,List<String[]> answerList){
		//結果を格納するArrayListを作成する
		List<Boolean> scoring = new ArrayList<>();
		//テストデータをダウンロードする
		List<String[]> testData = getAnswer(qtCode);
		//テストデータとアンサーリストのサイズを比較する
		if(testData.size()==answerList.size()) {
			//採点する
			for(int i = 0; i < testData.size(); i++) {
				String[] test = testData.get(i);
				String[] answer = answerList.get(i);
				boolean sco = Arrays.equals(test, answer);
				scoring.add(sco);
			}
		}
		return scoring;
	}
	
	public BigDecimal getScoring(int qtCode,List<Boolean> scoring) {
		System.out.println(qtCode);
		//出題数をダウンロードする
		int n = (int) answer.stream().filter(s->s.getQtCode()==qtCode).count();
		//正解数をダウンロードする
		int m = (int) scoring.stream().filter(s->s==true).count();
		//採点結果を計算する（ 正解数 / 出題数 )
		BigDecimal p = BigDecimal.valueOf(m).divide(BigDecimal.valueOf(n),3,RoundingMode.HALF_UP);
		p = p.scaleByPowerOfTen(2).setScale(0, BigDecimal.ROUND_HALF_UP);
		return p;
	}
}
