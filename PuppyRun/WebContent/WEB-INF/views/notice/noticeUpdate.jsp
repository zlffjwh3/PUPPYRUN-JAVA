<%@page import="user.model.vo.User"%>
<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");
	Notice notice = (Notice)request.getAttribute("notice");
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/notice-write.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/notice-update.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/notice-write.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <title>퍼피런 - 공지사항</title>
    </head>
    <body>
		<div id="wrap">
            <header>
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
		                        	<a href="javascript:showPopup()" id="login-content"><%= user.getUserNick() %></a>
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
            <!-- 스크롤 메뉴 -->
            <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <a href="#" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
            </div>
           <!-- 메인 ---------------------------------------------------------------------------------------------------------->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>퍼피런 이야기 글 수정</h3>
                    </div>
                    <!-- 작업해야할 부분 -->
                    <div id="write-wrap">
                        <div id="write-box">
                            <form id="write-check" action="/notice/update?noticeNo=<%= notice.getNoticeNo() %>&photoCheck=N" method="post" enctype="multipart/form-data">
                            	<input id="notice-num" type="hidden" name="noticeNo" value="<%= notice.getNoticeNo() %>">
                                <div id="title">
                                    <p class="write-p-tag">제 목</p>  
                                    <div class="input-box">
                                        <input type="text" id="title-input" name="title" maxlength="50" value="<%= notice.getNoticeTitle() %>">
                                    </div>
                                </div>
                                <div id="file">
                                    <p class="write-p-tag">첨부 파일</p>
                                    <% if(notice.getNoticePhoto() != null) { %>
                                    <div class="input-box" id="file-wrapper">
                                    	<div id="file-update-box">
	                                        <span><%= notice.getNoticePhoto() %></span>
                                    		<input type="button" id="file-update" value="수정하기">
                                    	</div>
                                    	<div id="file-update-box2">
	                                       	<input type="file" id="file-input" name="upFile">
	                                    </div>
	                                </div>
                                    <% } else { %>
	                                    <div class="input-box">
	                                        <input type="file" id="file-input" name="upFile">
	                                    </div>
                                    <% } %>
                                </div>
                                <div id="content">
                                    <p class="write-p-tag">내 용</p>
                                    <div class="input-box" id="textarea-box">
                                   	 	<% if(notice.getNoticeContent() != null) { %>
                                        <textarea id="content" name="content"><%= notice.getNoticeContent() %></textarea>
                                        <% } %>
                                    </div>
                                </div>
                                <div id="btn-box">
                                    <input type="button" id="submit-input" value="수정">
                                    <a href="/notice/detail?noticeNo=<%= notice.getNoticeNo() %>"><p>취소</p></a>
                                </div>
                            </form>
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
    </body>
</html>