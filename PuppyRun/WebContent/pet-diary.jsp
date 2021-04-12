<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Calendar tDay = Calendar.getInstance();

	int y = tDay.get(Calendar.YEAR);
	int m = tDay.get(Calendar.MONTH);
	int d = tDay.get(Calendar.DATE);

	Calendar dSet = Calendar.getInstance();
	dSet.set(y, m, 1);
	int yo = dSet.get(Calendar.DAY_OF_WEEK);
	int last_day = tDay.getActualMaximum(Calendar.DATE);
	%>

	<table border="1">
		<tr>
			<td align="center"colspan="7"><%=y%>년<%=(m+1)%>월 달력</td>
		</tr>
		<tr>
			<%
			String[] a = { "sun", "mon", "tue", "wed", "thu", "fri", "sat" };
			for (int i = 0; i < 7; i++)
			{%>
			<td width="35"><%=a[i]%></td>
			<%
			}
			%>
		</tr>
		<tr>
			<%
			for (int k = 1; k < yo; k++) {
			%>
			<td></td>
			<%
			}
			for (int j = 1; j <= last_day; j++) {
			%>
			<td>
			<a href="/petdiary/list"><%=j%></a>
			<%
			if ((yo+j-1) % 7 == 0) {
			%>
			</td>
		</tr>
		<tr>
			<%
			}}for(int e=1;e<(7-yo);e++){
			%>
			<td></td>
			<%
			} %>
		</tr>
	</table>
</body>
</html>