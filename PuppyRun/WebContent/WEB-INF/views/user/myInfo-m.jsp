<%@page import="user.controller.DogCheckServlet"%>
<%@page import="matching.model.vo.Matching"%>
<%@page import="notice.model.vo.Notice"%>
<%@page import="user.model.vo.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<User> uList = (ArrayList<User>)request.getAttribute("uList");
	ArrayList<User> allUser = (ArrayList<User>)request.getAttribute("allUser");
	String pageNavi = (String)request.getAttribute("pageNavi");
	ArrayList<Notice> nList = (ArrayList<Notice>)request.getAttribute("nList");
	ArrayList<Matching> mList = (ArrayList<Matching>)request.getAttribute("mList");
%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 폰트, 이모티콘, JQUERY CDN-->
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous">
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
        <!-- CSS 파일 가져오기 -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/myInfo-m.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/myInfo-m.js"></script>
		<title>퍼피런 - 관리자페이지</title>
	</head>
	<body>
		<div id="wrap">
            <header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft">
	                    <div id="search">
	                    	<form action="" method="get">
		                    	<input class="search-input" name="searchKeyword" type="text" placeholder="search">
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
                            	<img src="/assets/img/user-no-img.png" onclick="showPopup()">
                            	<a href="javascript:showPopup()" id="login-content">관리자</a>
		                    </div>
		                    <div id="pop-up" style="display:none">
		                    	<p id="show-id">admin</p>
		                    	<p><a href="/user/list?dogCheck=all">관리자페이지</a></p>
		                    	<p><a href="/user/logout">로그아웃</a></p>
		                    </div>
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
                        	<a href="/petdiary/list">산책일기</a>
                        </li>
                        <li class="main-navi-li">
                        	<a href="/matching/list">산책짝꿍</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/community/list">멍멍이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/notice/list">퍼피런이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="#">반려견계산기</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 메인 ---------------------------------------------------------------------------------------------------------------------->
            <div id="main-content">
            	<div id="choice">
            		<div id="user">
            			<p class="material-icons">account_circle</p>
            			<p>회원 관리</p>
            		</div>
            		<div id="post">
            			<p class="material-icons">article</p>
            			<p>게시물 관리</p>
            		</div>
            	</div>
            	<!-- 회원 --------------------------------------------------------->
               	<div class="list" id="user-list">
               		<div class="list-top">
               			회원 목록
               		</div>
               		<div class="list-bottom">
               			<div class="list-bottom-top">
               				<div class="select">
		               			반려견
		               				<a href="/user/list?dogCheck=Y" id="Y">유</a>
		               				<a href="/user/list?dogCheck=N" id="N">무</a>
		               				<a href="/user/list?dogCheck=all" id="all">전체</a>
	               			</div>
	               			<div class="search">
	               				<!-- <form action="/admin/search" method="get"> -->
	               				<form action="/user/list" method="get"> 
	               					<select id="user-choice" name="userChoice">
	               						<option value="userId">아이디</option>
	               						<option value="userName">이름</option>
	               					</select>
               						<input type="text" id="user-search" name="searchKeyword">
	               					<input type="submit" value="검색" id="user-search-btn" >
	               				</form>
	               			</div>
               			</div>
               				<div class="list-bottom-bottom">
               					<table>
	               					<tr>
	               						<th>선택</th>
	               						<th>아이디</th>
	               						<th>이름</th>
	               						<th>닉네임</th>
	               						<th>휴대전화</th>
	               						<th>이메일</th>
	               						<th>생년월일</th>
	               						<th>집주소</th>
	               						<th>반려견 유무</th>
	               						<th>등록일</th>
	               					</tr>
	               					<% if(uList != null) { %>
	               					<% for(User user : uList) { %>
	               					<tr>   
	               						<td><input type="checkbox"  class="user-input-box" value="<%= user.getUserId() %>"></td>
	               						<td><%= user.getUserId() %></td>
	               						<td><%= user.getUserName() %></td>
	               						<td><%= user.getUserNick()  %></td>
	               						<td><%= user.getPhone() %></td>
	               						<td><%= user.getEmail() %></td>
	               						<td><%= user.getUserBirth() %></td>
	               						<td><%= user.getUserAddr() %></td>
	               						<td><%= user.getDogCheck() %></td>
	               						<td><%= user.getEnrollDate() %></td>
	               					</tr>
	               					<% } %>
	               					<% } %>    
	               				</table>
               				</div>
	               		<form action=""  method="post" id="uDelete">
	               			<div class="list-btn">
	               				<input type="submit" value="삭제" id="userDelete">
	               			</div>
	               		</form>
	               	</div>
	               	
	               	
	            </div>
	            <!-- 퍼피런 게시글 ----------------------------------------------------->
	            <div class="list" id="prun-story-list">
               		<div class="list-top">
               			게시판 목록
               		</div>
               		<div class="list-bottom">
               			<div class="list-bottom-top">
               				<div class="select">
		               			<!-- 게시글 종류 -->
		               			<select id="content-kind" class="content-kind-puppy">
		               				<option value="puppy-story" class="puppy1">퍼피런 이야기</option>
		               				<option value="puppy-mate" class="puppy2">산책짝꿍</option>
		               			</select>
	               			</div>
	               			<div class="search">
	               				<form action="/admin/notice" method="get">
	               					<select id="user-choice" name="contentChoice">
	               						<option value="puppyTitle">제목</option>
	               						<option value="puppyContent">내용</option>
	               					</select>
               						<input type="text" id="user-search" name="searchKeyword">
	               					<input type="submit" value="검색" id="user-search-btn">
	               				</form>
	               			</div>
               			</div>
               			<div class="list-bottom-bottom">
               				<table>
	               				<tr>
	               					<th>선택</th>
	               					<th>게시글번호</th>
	               					<th>제목</th>
	               					<th>내용</th>
	               					<th>조회수</th>
	               					<th>작성일자</th>
	               				</tr>
	               				<% for(Notice notice : nList) { %>
	               				<tr>
	               					<td><input type="checkbox" value=""></td>
	               					<td><%= notice.getNoticeNo() %></td>
	               					<td><%= notice.getNoticeTitle() %></td>
	               					<td><%= notice.getNoticeContent()  %></td>
	               					<td><%= notice.getNoticeView()  %></td>
	               					<td><%= notice.getNoticeDate()  %></td>
	               				</tr>
	               				<% } %>
	               			</table>
               			</div>
            			<%-- <div class="page-navi">
            				<td><%= pageNavi %></td>
            			</div> --%>
	               	</div>
	               	<div class="list-btn">
	               		<a href="">삭제</a>
	               	</div>
	            </div>
	             <!-- 퍼피런 게시글 끝 ----------------------------------------------------->
	             
	             <!-- 산책짝꿍 게시글 ----------------------------------------------------->
	             <div class="list" id="matching-list" style="display:none;">
               		<div class="list-top">
               			게시판 목록
               		</div>
               		<div class="list-bottom">
               			<div class="list-bottom-top">
               				<div class="select">
		               			<!-- 게시글 종류 -->
		               			<select id="content-kind" class="content-kind-match">
		               				<option value="puppy-story" class="match1">퍼피런 이야기</option>
		               				<option value="puppy-mate" class="match2">산책짝꿍</option>
		               			</select>
	               			</div>
	               			<div class="search">
	               				<form action="/admin/matching" method="get">
	               					<select id="user-choice" name="matchingChoice">
	               						<option value="matchingTitle">제목</option>
	               						<option value="matchingContent">내용</option>
	               					</select>
               						<input type="text" id="user-search" name="searchKeyword">
	               					<input type="submit" value="검색" id="user-search-btn">
	               				</form>
	               			</div>
               			</div>
               			<div class="list-bottom-bottom">
               				<table>
	               				<tr>
	               					<th>선택</th>
	               					<th>게시글번호</th>
	               					<th>제목</th>
	               					<th>작성자ID</th>
	               					<th>내용</th>
	               					<th>주소</th>
	               					<th>작성일자</th>
	               				</tr>
	               				<% for(Matching matching : mList) { %>
	               				<tr>
	               					<td><input type="checkbox" value=""></td>
	               					<td><%= matching.getMatNo() %></td>
	               					<td><%= matching.getMatTitle() %></td>
	               					<td><%= matching.getMatId()  %></td>
	               					<td><%= matching.getMatContent()  %></td>
	               					<td><%= matching.getMatAddr()  %></td>
	               					<td><%= matching.getMatDate()  %></td>
	               				</tr>
	               				<% } %>
	               			</table>
               			</div>
            			<%-- <div class="page-navi">
            				<%= pageNavi %></td>
            			</div> --%>
	               	</div>
	               	<div class="list-btn">
	               		<a href="">삭제</a>
	               	</div>
	            </div>
	             
	           <!-- 산책짝꿍 게시글 끝 ----------------------------------------------------->
	             
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