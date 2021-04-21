<%@page import="java.util.ArrayList"%>
<%@page import="petdiary.model.vo.Goal"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");

	Goal weekGoal = (Goal)request.getAttribute("weekGoal");
	ArrayList<Goal> gList = (ArrayList<Goal>)request.getAttribute("gList");
	String pageNavi = (String)request.getAttribute("pageNavi");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 폰트, 이모티콘, JQUERY CDN-->
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
    <!-- CSS 파일 가져오기 -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/walking-log.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
    <!-- 파비콘 이미지 가져오기 -->
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
    <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
    <!-- JS 파일 가져오기 -->
    <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/walking-log.js"></script>
	<!-- 차트 api -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/jquery.easy-pie-chart.js"></script>
	<title>퍼피런 :: 산책일기</title>
</head>
<body>
	<div id="wrap">
		<header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft">
	                    <div id="search">
	                    	 <form action="/community/search" method>
                                <input class="search-input" id="" type="text" placeholder="search">
                                <input id="search-btn" type="submit" value="">
                            </form>
	                    </div>
                	</div>
	                <!-- 헤더 메인 로고 -->
	                <div id="header-logo">
	                    <a href="/index.jsp" id="logo"></a>
	                </div>
	                <div id="tright">
	                	<div id="tright-wrapper">
		                    <div id="login">
		                    	<% if(user == null) { %>
		                        	<a href="/login.jsp">
		                            	<i class="xi-face xi-2x"></i>
		                       		</a>
		                        	<a href="/login.jsp" id="login-content">로그인</a>
		                        <% } else { %>
		                        	<% if(user.getUserPhoto() != null) { %>
	                            	<img src="/upload/<%= user.getUserPhoto() %>" onclick="showPopup()">
		                       		<% } else { %>
	                            	<img src="/assets/img/user-no-img.png" onclick="showPopup()">
		                        	<% } %>
		                        	<a href="javascript:showPopup()" id="login-content" class="logining-userName"><%= user.getUserNick() %></a>
		                        <% } %>
		                    </div>
		                    <% if(user != null) { %>
		                    <div id="pop-up" style="display:none">
		                    	<p id="show-id"><%= user.getUserId() %></p>
		                    	<% if(user.getAdminCheck() == 'N') { %>
		                    	<p><a href="/user/myInfo">마이페이지</a></p>
		                    	<% } else { %>
		                    	<p><a href="/user/list?dogCheck=all">관리자페이지</a></p>
		                    	<% } %>
		                    	<p><a href="/user/logout">로그아웃</a></p>
		                    </div>
		                    <% } %>
	                    </div>
	                    <script>
	                    		function showPopup() {
	                    			var popUp = document.getElementById("pop-up");
	                    			
	                    			if(popUp.style.display == 'none') {
	                    				popUp.style.display = 'block';
	                    			}else {
	                    				popUp.style.display = 'none';
	                    			}
		                    	}
	                    </script> 
            		</div>
           		</div>
            </header>
            <nav>
                <!-- 메뉴 -->
                <div id="main-menu">
                    <ul id="main-navi-ul">
                        <li class="main-navi-li">
                        	<% if(user != null) { %>
                        		<a href="/petdiary/list">산책일기</a>
                        	<% } else { %>
                        		<a href="/login.jsp" onclick="return alert('로그인이 필요합니다.')">산책일기</a>
                        	<% } %>
                        </li>
                        <li class="main-navi-li">
                        	<% if(user != null) { %>
                        		<a href="/matching/list">산책짝꿍</a>
                        	<% } else { %>
                        		<a href="/login.jsp" onclick="return alert('로그인이 필요합니다.')">산책짝꿍</a>
                        	<% } %>
                        </li>
                        <li class="main-navi-li">
                            <a href="/community/list">멍멍이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/notice/list">퍼피런이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/calculator/age">반려견계산기</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 스크롤 메뉴 -->
            <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <% if( user != null) { %>
                <a href="/mychatting/list" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
                <% } else { %>
                <a href="login.jsp" class="message" onclick="return alert('로그인이 필요합니다.')"><div><i class="far fa-comment-alt"></i></div>메시지</a>
                <% } %>
            </div>
            <!-- 스크롤 메뉴 -->
            <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <a href="#" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
            </div>
            
            <!-- 메인 ---------------------------------------------------------------------------------------------------------->
	   		<div id="main-content">
	   			<div id="nbb-top">
                    <h3>산책기록</h3>
                    <p>지금까지 달성한 목표를 확인해보세요!</p>
                </div>
                
                <div id="goal-stamp-box">
                	<div id="goal-stamp-box-left">
                		<div id="left-title">현재 목표</div>
                		<% if(weekGoal != null) { 
                			double percent = (double)weekGoal.getWeekTime() / (double)weekGoal.getGoalTime() * 100;
                		%>
                		<div id="left-chart">
				   			<div class="chart char1" data-percent="<%= percent %>">
				   				<span class="title">
				   					<img id="dog-image-box" src="/assets/img/dog-img.png" onclick="location.href='/user/myInfo'">		
									<% if(user.getUserPhoto() != null) { %>
			                        	<script>$('#dog-image-box').attr('src','/upload/<%=user.getUserPhoto()%>');</script>
			                       	<% } else { %>
			                        	<script>$('#dog-image-box').attr('src','/assets/img/user-no-img2.png');</script>
			                       	<% } %>
								</span>
				   			</div>
                		</div>
                		<div id="left-time">
                			<span class="user-time"><%= weekGoal.getWeekTime() %></span>
                			<span class="time-set"> / </span>
                			<span class="goal-time time-set"><%= weekGoal.getGoalTime() %></span>
                			<span class="time-set">분</span>
                		</div>
                		<% } else { %>
                		<div id="left-chart">
				   			<div class="chart char1" data-percent="0">
				   				<span class="title">
				   					<img id="dog-image-box" src="/assets/img/dog-img.png" onclick="location.href='/user/myInfo'">		
									<% if(user.getUserPhoto() != null) { %>
			                        	<script>$('#dog-image-box').attr('src','/upload/<%=user.getUserPhoto()%>');</script>
			                       	<% } else { %>
			                        	<script>$('#dog-image-box').attr('src','/assets/img/user-no-img2.png');</script>
			                       	<% } %>
								</span>
				   			</div>
                		</div>
                		<div id="left-time">
                			<p>현재 목표가 없습니다 !</p>
                			<a class="goal-btn" href="/petdiary/list">목표 설정</a>
                		</div>
                		<% } %>
                	</div>
                	<div id="goal-stamp-box-right">
			   			<div id="stamp-box"></div>
			   				<div id="right-title">지난 목표</div>
			   				<% if(!gList.isEmpty()) { %>
			   				<div id="stamp-wrap">
				   				<div id="right-stamp">
				   					<% int n = 0;
				   					for(int i=0; i<3; i++) { %>
				   					<div id="stamp-div">
				   						<% for(int j=0; j<3; j++) { %>
				   							<% if(n < gList.size()) { %>
						   						<!-- 목표를 성공했다면 (이미지 변경) -->
				   								<% if(gList.get(n).getGoalCheck() == 'Y') { %>
					   								<img class="goal-stamp" src="/assets/img/user-no-img3.png" onclick="location.href='/goal/detail?goalDate=<%= gList.get(n).getGoalDate() %>'">
				   								<% } else { %>
				   									<img class="goal-stamp" src="/assets/img/user-no-img2.png" onclick="location.href='/goal/detail?goalDate=<%= gList.get(n).getGoalDate() %>'">
				   								<% } %>
				   							<% } %>
				   						<% n++; } %>
			   						</div>
			   						<% } %>
				   				</div>
			   				</div>
			   				<% } else { %>
			   				<div id="right-stamp">
			   					<div id="no-goal-stamp">
			   						<img src="/assets/img/main_logo.png">
					   				<p>목표를 설정해주세요</p>
					   				<a class="goal-btn" href="/petdiary/list">목표 설정</a>
			   					</div>
			   				</div>
			   				<% } %>
			   				<!-- 페이징 -->
			   				<div id="right-page">
			   					<p><%= pageNavi %></p>
			   				</div>
                	</div>
                </div>
	   		</div>
	   		<!-- 메인 끝 ---------------------------------------------------------------------------------------------------------->
        <footer>
               <!-- 푸터 -->
               <div id="footer">
                   <div id="footer-right-box">
                       <img src="/assets/img/main_logo.png" alt="하단로고">
                   </div>
                   <div id="footer-left-box"> 
                       <ul>
                           <li>퍼피런소개</li>
                           <li>사이트맵</li>
                           <li>이용약관</li>
                           <li>개인정보처리방침</li>
                           <li>운영방침</li>
                       </ul>
                       <p id="footer-ptag">
                           서울특별시 송파구 올림픽로 300 대표자 : 이혜썽 전화 : 1661-2000<br>
                           전자우편주소 : puppyrun@naver.com<br>
                           사업자등록번호 : 230-85-024691 통신판매업신고번호 : 송파 제12038호<br>
                           Copyright 2021 PUPPYRUN. All Rights Reserved.
                       </p>
                   </div>
               </div>
        </footer>
    </body>
</html>
