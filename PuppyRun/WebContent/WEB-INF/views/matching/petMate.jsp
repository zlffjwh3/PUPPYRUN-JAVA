<%@page import="matching.model.vo.Matching"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User user = (User)session.getAttribute("user");
	ArrayList<Matching> mList = (ArrayList<Matching>)request.getAttribute("mList");
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
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
        <!-- CSS 파일 가져오기 -->
        <link rel="stylesheet" type="text/css" href="/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="/assets/css/scroll.css">
        <link rel="stylesheet" type="text/css" href="/assets/css/petMate.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="./assets/js/jquery-3.4.1.min.js"></script>
        <script src="/assets/js/slider.js"></script>
        <script src="/assets/js/scroll.js"></script>
        <title>산책짝꿍 - 산책친구 찾기</title>
    </head>
    <body>
        <header>
        <div id="wrap">
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft">
	                    <div id="search">
	                    	<form action="" method="get">
		                    	<input class="search-input" type="text" placeholder="search">
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
		                    	<p><a href="/user/list">관리자페이지</a></p>
		                    	<% } %>
		                    	<p><a href="/user/logout">로그아웃</a></p>
		                    </div>
		                    <% } %>
	                    </div>
	                    <!-- index.js로 옮김 -->
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
                            <a href="#">반려견계산기</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 스크롤 메뉴 -->
            <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <a href="#" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
            </div>
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>산책짝꿍</h3>
                        <p>산책짝꿍에서 산책 같이할 친구를 찾아요!</p>
                    </div>
                    <div id="nbb-bottom">
                        <div id="all-content-box">
                        <% 
                        int n = 0;
            			for(int i=0; i<3; i++) {
                        %>
                            <!-- 게시물 3개씩 묶어두는 박스 -->
                   			<div class="line-box">
                            <% for(int j=0; j<3; j++) {
                       			if(n < mList.size()) { %>
                                	<!-- 게시글 하나 -->
                                    <div class="content-box" onclick="location.href='/matching/detail?matNo=<%= mList.get(n).getMatNo()%>'">
                                    	<!-- 게시글 사진 -->
                                        <div class="list-img-area">
                                       	<% if(mList.get(n).getMatPhoto() != null) { %>
                                            <img src="/upload/<%=mList.get(n).getMatPhoto() %>" alt="게시글 대표이미지">
                                        <% } else { %>
                                            <img src="/assets/img/no-img.jpg">
                                        <% } %>
                                        </div>
                                        <!-- 게시글 내용 -->
                                        <div class="list-content-area">
                                            <div>
                                            	<!-- 프로필 사진 -->
                                            	<div class="user-profile-img-div">
                                            	<% if(user.getUserPhoto() != null) { %>
	                            					<img src="/upload/<%= user.getUserPhoto() %>" class="user-profile-img">
		                       					<% } else { %>
	                            					<img src="/assets/img/user-no-img.png" class="user-profile-img">
		                        				<% } %>
                                        		</div>
                                                <!-- 닉네임 / 주소 -->
                                               	<span class="user-name"><%=mList.get(n).getUserNick() %></span>
                                               	<% if(mList.get(n).getMatAddr() != null) { %>
                                               	<span class="user-addr"><%=mList.get(n).getMatAddr() %></span>
                                               	<% } %>
                                            </div>
                                         	<div>
                                         		<% if(mList.get(n).getMatContent() != null) { %>
                                                <span class="user-mini-content"><%=mList.get(n).getMatContent() %></span>
                                                <% } else { %>
                                                <span class="user-mini-content"></span>
                                                <% } %>
                                                <a href="/matching/detail?matNo=<%= mList.get(n).getMatNo()%>" class="chat-btn">채팅하기</a>
                                            </div>
                                       </div>
                                  </div>
                                <% 
                                	}
                       				n++;
                              	}
                                %>
                        </div>
                    <% } %>
                    </div>
                    
                    <!-- 글쓰기 버튼 -->
                    <div id="post-wrap">
                            <a href="/matching/write" class="link-post">글쓰기</a>
                    </div>
                    <!-- 여기에 페이징 번호 -->
                    <div class="pagin-box">
                        <%= pageNavi %>
                    </div>
                </div>
            </div>
            <footer>
                <!-- 푸터 -->
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
                            <li>운영방침</li>
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
        </div>
    </div>
    </body>
</html>