<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
   		//getInstance 는 Calendar의 객체만의 생성한 것이다.
   		Calendar cal = Calendar.getInstance();
   
   		//현재 년도와 현재 달을 받아온 것이다. +1은 0 ~11월달까지 주어지기 때문에 + 1을 해 준 것이다.
   		int nowYear = cal.get(Calendar.YEAR);
   		int nowMonth = cal.get(Calendar.MONTH)+1;
   		
   		//year 년과 month을 값을 받아 온것이다.(이전달, 다음달을 클릭하였을때 받아오는 값)
   		String fyear = request.getParameter("year");
   		String fmonth = request.getParameter("month");
   		
   		//현재년도와 현재 달을 year과 month로 선언해주었따.
   		int year = nowYear;
   		int month = nowMonth;
   		
   		//넘오온값이 널갑이 아니면 해당되는 fyear값은 year의 값인 것이다.
   		if(fyear != null){
   			year = Integer.parseInt(fyear);
   		}
   		//넘어온 값이 널값이 아니면 해당되는 fmonth값은  month의 값인 것이다.
   		if(fmonth != null){
   			month = Integer.parseInt(fmonth);
   		}
   		
   		//넘어온값을 새롭게 cal 객체생성한 곳에 입력이 된다 (년,월,일)초기화 
   		cal.set(year, month-1, 1);
   		
   		//입력되어진 년과 달을 값을 다시 year, month 로 선언해주었다.
   		year = cal.get(Calendar.YEAR);
   		month = cal.get(Calendar.MONTH)+1;
   		
   		//cal.getActualMaximum은 그달의 마지막일을 출력한것이다.
   		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
   		//현재, 즉 오늘날짜를 말한것이다/
   		int week = cal.get(Calendar.DAY_OF_WEEK);
   %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href = "petdiary2.jsp?year=<%=year %>&month=<%=month -1 %>">before</a>&nbsp;
<b><%=year %>년&nbsp;&nbsp;<%=month %>월</b>
<a href = "petdiary2.jsp?year=<%=year %>&month=<%=month + 1 %>">&nbsp; next</a>

	<table border = "1">
	<tr>
		<td style = color:red;>일</td>
		<td>월</td>
		<td>화</td>
		<td>수</td>
		<td>목</td>
		<td>금</td>
		<td style = color:blue;>토</td>
	</tr>
	
	<tr>
	<!-- 그달의 1일 까지 공백처리하기 위한 것임. -->
	<%
		for(int i = 1; i < week; i++){
	%>		
	<td height = "20">&nbsp;</td>
	<%
	}
	%>
<!-- 	끝나는 날까지 for 문을 통해서 숫자를 출력한 것이다. week는 1일 제외하고 계산됨 -->
<%
	for(int j = 1; j<=endDay; j ++){
		week++;
		if(week % 7 ==2){
 %>
</tr>
	<tr>
	<% } %>
		<% if(week % 7 == 2 ){ %>
		<td style = "color:red";><%=j%></td>
		<%}else if(week%7 == 1){%>
		<td style="color: blue;"><%=j %></td>
		<%}else {%>
		<td style = "color: block";><%=j %></td>
		<% }

	}%>
		</tr>
	</table>
</body>
</html>