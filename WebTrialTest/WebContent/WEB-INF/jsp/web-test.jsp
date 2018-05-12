<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.trial.sql.model.QtAnswer"
		 import="java.util.List" 
		 import="com.model.TestLoader"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/webtest.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	TestLoader loader = (TestLoader) request.getAttribute("answerLoader");
	List<QtAnswer> list = loader.getAnswer(1);
%>
<title>Insert title here</title>
</head>
<body>
<h1><%= list.get(0).getQtName()%></h1>
<%
	for(int i = 0; i < list.size();i++){
		QtAnswer answer = list.get(i);
		int columns = answer.getColumns();
		
		out.println("<h2>" + answer.getQtNo() + "</h2>");
		//ここに画像を表示したい
		String image = String.format("%02d", answer.getQtCode()) + String.format("%02d", answer.getQtNo());
		out.println("<img src=\"./picture/" + image + ".jpg\" class=\"question\">\n");
		out.println("<p>");
		for(int t = 0x0041 ; t < (0x0041 + columns);t++){
			char s = (char) t;
			out.println("<label><input type=\"radio\" name=\"q" + i + "\" value=\"" + s + "\">" + s + "</label>");
		}
		out.println("</p>");	
	}
%>

</body>
</html>