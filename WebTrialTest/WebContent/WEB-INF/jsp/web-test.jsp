<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.trial.sql.model.QtAnswer"
		 import="java.util.List" 
		 import="com.model.TestLoader"
		 import="com.model.TestScoring"
		 import="java.util.Arrays"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/webtest.css" rel="stylesheet">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- ***************************************************************************************************************** -->

<%
//フィールドの宣言
	boolean pageRequest = request.getParameter("qtselect")!=null;
	final int qtCode = pageRequest?Integer.valueOf(request.getParameter("qtselect")):1;
//オブジェクトの宣言
	TestLoader loader = (TestLoader) request.getAttribute("answerLoader");
	TestScoring scoring = (TestScoring) request.getAttribute("scoring");
	List<String> qtTitle = loader.getTitle();
	List<QtAnswer> list = loader.getQtAnswer(qtCode);
//正解/不正解を判定する
	List<Boolean> scoringList;
	try{
		scoringList = loader.getScoringList(qtCode, scoring.getAnswerList());
	}catch(NullPointerException e){
		scoringList = null;
	}
	
%>
<title><%= pageRequest?qtTitle.get(qtCode-1):"テストを始めましょう"%></title>
</head>
<body>

<!-- ***************************************************************************************************************** -->

<!-- 問題を選択します -->
<form action="/WebTrialTest/WebTester" method="get">
<p>
<select name="qtselect">
<%	int i = 1;
	for(String title: qtTitle){
		String selected = qtCode==i?" selected":"";
		out.println("<option value=\"" + i + "\"" + selected + ">");
		out.println(title);
		out.println("</option>");
		i++;
	}
%>
</select>
</p>
<p><input type="submit" value="選択する"></p>
</form>

<!-- ***************************************************************************************************************** -->

<!-- 問題タイトルです！ -->
<h1><%= list.get(qtCode).getQtName()%></h1>
<sub class="tensu">
<% 
	try{
		out.println("点数：" + loader.getScoring(qtCode, scoringList) + "点");
	}catch(NullPointerException | ArithmeticException e){

	}
%>
</sub>

<!-- ***************************************************************************************************************** -->

<!-- ここから問題を出題します -->
<form action="/WebTrialTest/WebTester" method="post">
<%	

//章の詳細情報をPOSTする
	out.println("<input type=\"hidden\" name=\"qtselect\" value=\"" + qtCode + "\">");
	out.println("<input type=\"hidden\" name=\"qtcount\" value=\"" + loader.getAnswerCount(qtCode) + "\">");

//問題文を表示する
	for(i = 0; i < list.size();i++){
	//問題の詳細情報を取得
		QtAnswer answer = list.get(i);
		int columns = answer.getColumns();
		int answerCount = answer.getAnswerCount();
		String inputType = answerCount==1?"radio":"checkbox";
	//問題No
		out.println("<h2>" + answer.getQtNo() + "</h2>");
	//画像を表示
		String imagePath = "./picture/qt/" + String.format("%02d", answer.getQtCode()) + "/";
		String imageName = String.format("%02d", answer.getQtCode()) + String.format("%02d", answer.getQtNo()) + ".jpg";
		out.println("<img src=\"" + imagePath + imageName + "\" class=\"question\">\n");
	//ラジオ/チェック ボタンを表示する
		if(inputType.equals("radio"));
		String qq = pageRequest?request.getParameter("q" + i):"";
		if(inputType.equals("checkbox"));
		String[] ww = pageRequest?request.getParameterValues("q" + i):null;
	//qtCodeとq(i)パラメータがある場合は選択された項目を選択しておく	
		out.println("<p>");
		for(int t = 0 ; t < columns;t++){	//項目の数だけ繰り返し処理
			char s = (char) (0x0041 + t);	//項目名を指定
			//POST or GET で処理を分岐する
			if(request.getMethod().equals("POST")){
				try{
					List<String> qi = Arrays.asList(request.getParameterValues("q" + i));
					for(int n = 0; ;n++){
						String qu = qi.get(n);
						if(String.valueOf(s).equals(qu)){
							out.println("<label><input type=\"" + inputType + "\" name=\"q" + i + "\" value=\"" + s + "\" checked>" + s + "</label>");
							break;
						}
					}
				}catch(IndexOutOfBoundsException | NullPointerException e){
					out.println("<label><input type=\"" + inputType + "\" name=\"q" + i + "\" value=\"" + s + "\">" + s + "</label>");	
				}				
			}else{
				//<label><input type="radio" name="q1" value="A">A</label>
				out.println("<label><input type=\"" + inputType + "\" name=\"q" + i + "\" value=\"" + s + "\">" + s + "</label>");				
			}
			
		}
		out.println("</p>");
	//採点結果があれば表示する
		if(scoringList!=null && request.getMethod().equals("POST")){
			out.println("<p>");
			out.println(scoringList.get(i)==true?"〇":"×");
			out.println("</p>");	
			out.println("<p>");
			for(String ans:loader.getAnswer(qtCode, i + 1)){
				out.println(ans);
			}
			out.println("</p>");	
		}
	}
%>
<input type="submit" value="採点する">
</form>
</body>
</html>