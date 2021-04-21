<%@page import="community.model.vo.Like"%>
<%@page import="matching.model.vo.Matching"%>
<%@page import="community.model.vo.Comment"%>
<%@page import="community.model.vo.Community"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.model.vo.Dog"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");
	Dog dog = (Dog)request.getAttribute("dog");
	
	ArrayList<Like> lList = (ArrayList<Like>)request.getAttribute("lList");
	ArrayList<Community> cList = (ArrayList<Community>)request.getAttribute("cList");
	ArrayList<Comment> comList = (ArrayList<Comment>)request.getAttribute("comList");
	ArrayList<Matching> mList = (ArrayList<Matching>)request.getAttribute("mList");
	ArrayList<Community> allCList = (ArrayList<Community>)request.getAttribute("allCList");
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 폰트, 이모티콘, JQUERY CDN-->
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous">
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
        <!-- CSS 파일 가져오기 -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/myInfo.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bg-middle.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/myInfo.js"></script>
		<title>퍼피런 :: 마이페이지</title>
	</head>
	<body>
		<div id="wrap">
			<header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft">
	                    <div id="search">
	                    	 <form action="/community/search" method>
                                <input class="search-input" id="" name="searchKeyword" type="text" placeholder="search">
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
		                    <div id="pop-up" class="animate__animated animate__fadeIn animate__delay-la" style="display:none">
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
            <!-- 메인 ---------------------------------------------------------------------------------------------------------------------->
            <div id="main-content">
            	<div id="info-top">
	               	<div id="info-profile">
	               		<div id="info0">프로필</div>
               			<!--------------------------------------------->
               			<div id="info1">
               			<% if(user.getUserPhoto() != null) { %>
               				<div><img src="/upload/<%= user.getUserPhoto() %>"></div>
               			<% } else { %>
               				<div><img src="/assets/img/user-no-img.png"></div>
               			<% } %>
               				<img src="/assets/img/change-photo.png" id="change-photo-btn">
               				<form action="/user/photo" method="post" id="change-photo" enctype="multipart/form-data">
               				<% if(user.getUserPhoto() != null) { %>
	               				<input type="file" id="user-photo" name="upFile">
	               				<input type="submit" value="수정">
								
               				
               				<% } else { %>
	               				<input type="file" id="user-photo" name="upFile">
	               				<input type="submit" value="수정">
               				
               				<% } %>
               				</form>
               			</div>
               			<!--------------------------------------------->
               			<% if(dog != null) { %>
               			<div id="info2">
               				<div id="info-name">
               					<p class="user-nick"><%= user.getUserNick() %></p>
               					<p id="dog-name"><%= dog.getDogName() %></p>
               				</div>
               				<div id="info-dog">
               					<ul id="dog-list">
               						<li>품종</li>
               						<li>성별</li>
               						<li>나이</li>
               						<li>몸무게</li>
               					</ul>
               					<ul id="my-dog-list">
               						<li><%= dog.getDogBreed() %></li>
               						<li>
               						<% switch(dog.getDogGender()) { 
               						case 'M' : %>수컷
               							<% break;
               						case 'F' : %>암컷
               							<% break;
               						case 'T' : %>중성화
               							<% break;
               						} %>
               						</li>
               						<li><%= dog.getDogAge() %> 세</li>
               						<li><%= dog.getDogWeight() %> kg</li>
               					</ul>
               				</div>
               			</div>
               			<% } else { %>
               			<div id="info2_2">
               				<div id="no-info-name"><p class="user-nick"><%= user.getUserNick() %></p></div>
               				<div id="no-info">
               					강아지 정보가 없습니다.<br>
               					강아지 정보를 확인하려면<br>
               					프로필을 수정해주세요.
               				</div>
               			</div>
               			<% } %>
               			<!--------------------------------------------->
               			<div id="info3">
               				<a href="/user/modify?userId=<%= user.getUserId() %>" id="modify-btn">프로필 수정</a>
               				<a href="/user/delete?dogCheck=<%= user.getDogCheck() %>" id="delete-btn" onclick="return confirm('정말 탈퇴하시겠습니까?')">회원탈퇴</a>
               			</div>
	               	</div>
	               	<a href="/calculator/age">
               		<div id="info-cal">
	               			<p id="cal-text">
	               				나이, 칼로리, 비만도<br>
	               				소중한 내 친구를 위한<br>
	               				필수 계산기
	               			</p>
	               			<p id="cal-title">반려견 계산기</p>
	               	</div>
	               	</a>
               	</div>
               	<div id="info-bottom">
               		<div id="list-top">
               			<div id="like">관심목록</div>
               			<div id="post">MY작성글</div>
               			<div id="comment">MY댓글</div>
               		</div>
               		<div id="list-bottom">
               			<table id="like-list" class="table">
               				<% for(Like like : lList) { 
               					int n = 0;
               					for(int i=0; i<allCList.size(); i++) {
               						if(like.getComNo() == allCList.get(i).getComNo()) {
               							n = i;
               							break;
               						}
               					}
               				%>
               				<tr>
               					<td><input type="checkbox" class="checkbox" value="<%= like.getComNo() %>"></td>
               					<td>멍멍이야기</td>
               					<% switch(allCList.get(n).getTagNo()) { 
               					case 0 :%> <td>자유</td>
               					<% break;
               					case 1 : %><td>나눔</td>
               					<% break;
               					case 2 : %><td>질문</td>
               					<% break;
               					case 3 : %><td>자랑</td>
               					<% break;
               					} %>
               					<td onclick="location.href='/community/detail?comNo=<%= allCList.get(n).getComNo() %>&userId=<%= user.getUserId() %>'"><%= allCList.get(n).getComTitle() %></td>
               					<td><%= allCList.get(n).getComDate() %></td>
               				</tr>
               				<% } %>
               			</table>
	               		<table id="post-list" class="table">
	               			<% for(int i=0; i<cList.size(); i++) { %>
               				<tr>
               					<td><input type="checkbox" class="checkbox" value="<%= cList.get(i).getComNo() %>"></td>
               					<td>멍멍이야기</td>
               					<% switch(cList.get(i).getTagNo()) { 
               					case 0 :%> <td>자유</td>
               					<% break;
               					case 1 : %><td>나눔</td>
               					<% break;
               					case 2 : %><td>질문</td>
               					<% break;
               					case 3 : %><td>자랑</td>
               					<% break;
               					} %>
               					<td onclick="location.href='/matching/detail?matNo=<%%>'"><%= cList.get(i).getComContent() %></td>
               					<td><%= cList.get(i).getComDate() %></td>
               				</tr>
               				<% } %>
               			</table>
               			<table id="post-list2" class="table">
	               			<% for(Matching mat : mList) { %>
               				<tr>
               					<td><input type="checkbox" class="checkbox" value="<%= mat.getMatNo() %>"></td>
               					<td>산책짝꿍</td>
               					<td></td>
               					<td onclick="href.location='/matching/detail?matNo=<%= mat.getMatNo() %>'"><%= mat.getMatTitle() %></td>
               					<td><%= mat.getMatDate() %></td>
               				</tr>
               				<% } %>
               			</table>
               			<table id="comment-list" class="table">
               				<% for(Comment com : comList) { 
               					int n = 0;
               					for(int i=0; i<allCList.size(); i++) {
               						if(allCList.get(i).getComNo() == com.getComNo()) {
               							n = i;
               							break;
               						}
               					}
               				%>
               				<tr>
               					<td><input type="checkbox" class="checkbox" value="<%= allCList.get(n).getComNo() %>"></td>
               					<td>멍멍이야기</td>
               					<% switch(allCList.get(n).getTagNo()) { 
               					case 0 :%> <td>자유</td>
               					<% break;
               					case 1 : %><td>나눔</td>
               					<% break;
               					case 2 : %><td>질문</td>
               					<% break;
               					case 3 : %><td>자랑</td>
               					<% break;
               					} %>
               					<td onclick="location.href='/community/detail?comNo=<%= allCList.get(n).getComNo() %>&userId=<%= user.getUserId() %>'"><%= com.getCommentContents() %></td>
               					<td><%= allCList.get(n).getComDate() %></td>
               				</tr>
               				<% } %>
               			</table>
	               	</div>
	               	<div id="btns">
	            		<input type="button" value="삭제" id="change">
	            	</div>
	            </div>
	     	</div> <!-- main -->
            <!-- 메인 끝 ! ---------------------------------------------------------------------------------------------------------------------->
	            <footer>
	                <div id="footer">
	                    <div id="footer-right-box">
	                        <img src="/assets/img/main_logo.png" alt="하단로고">
	                    </div>
	                    <div id="footer-left-box"> 
	                        <ul>
	                            <li>퍼피런 소개</li>
	                            <li>사이트맵</li>
	                            <li>이용약관</li>
	                            <li>개인정보처리방침</li>
	                        </ul>
	                        <p id="footer-ptag">
	                            서울특별시 송파구 올림픽로 300 대표자 : 오재승 전화 : 1661-2000<br>
	                            전자우편주소 : puppyrun@naver.com<br>
	                            사업자등록번호 : 230-85-024691 통신판매업신고번호 : 송파 제12038호<br>
	                            Copyright 2021 PUPPYRUN. All Rights Reserved.
	                        </p>
	                    </div>
	                </div>
	            </footer>
            </div> <!-- wrap -->
        </div>
</body>
</html>