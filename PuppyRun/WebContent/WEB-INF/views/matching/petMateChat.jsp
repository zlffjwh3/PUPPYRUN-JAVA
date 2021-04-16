<%@page import="matching.model.vo.Matching"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User user = (User)session.getAttribute("user");
	Matching matching = (Matching)request.getAttribute("matching");
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
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/notice.css"> -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/petMate.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/petMateChat.css">
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
                        <p>같이 산책할 친구 찾아요 !</p>
                        <!-- 태그 박스
                        <div id="tag-box">
                            <i class="fas fa-border-all"></i>
                        </div> -->
                    </div>
                    <div id="nbb-bottom"></div>
                        <!-- 채팅창 -->
            <div id="chat-box">
                <div id="chat-box-left">
                    <div id="chat-img-box">
                        <!-- 사용자 게시물 첨부사진 -->
                        <img src="/upload/<%= matching.getMatPhoto() %>" alt="사용자 첨부파일">
                    </div>
                    <div id="chat-profile-box">
                                <div class="chat-content-area">
                                    <div>
                                        <div class="user-profile-img-div">
                                            <!-- 프로필사진 -->
                                            <% if(user.getUserPhoto() != null) { %>
                                            <img src="/upload/<%= user.getUserPhoto() %>" class="user-profile-img">
                                            <% } else { %>
                                            <img src="/assets/img/user-no-img.png">
		                        			<% } %>
                                        </div>
                                        <div class="user-profile-name-div">
			                        		<a href="javascript:showPopup()" id="login-content" class="logining-userName"><%= user.getUserNick() %></a>
	                                       <%-- <span class="user-name"><%= matching.getUserNick() %></span> --%>
                                        </div>
                                        <div class="user-profile-addr-div">
	                                        <span class="user-addr"><%= matching.getMatAddr() %></span>
                                        </div>
                                    </div>
                                    <div>
                                        <!-- 글내용이 길어지면 자동으로 스크롤바가 생김 -->
                                        <p id="user-write-content"><%= matching.getMatContent() %></p>
                                    </div>
                                </div>
                                <a href="/matching/modify?matNo=<%= matching.getMatNo() %>" class="matching-btn matching-btn1" style="color: white;">수정하기</a>
                                <a href="/matching/delete?matNo=<%= matching.getMatNo() %>" class="matching-btn matching-btn2" style="color: white;" onclick="return confirm('정말 삭제하시겠습니까?')">삭제하기</a>
                    </div>
                </div>
                <div id="chat-box-right"> <!-- 오른쪽(채팅창) 부분 -->
                    <div id="chat-close">
                        <a href="#">
                            <img src="/assets/img/x_mark.png" alt="창 제거 이미지">
                        </a>
                    </div>
                    <!-- 채팅창 보는 영역 + 속 내용이 길어지면 스크롤 자동생성 설정해놈(overflow : auto) -->
                    <div id="chat-area">
                        <!-- 상대 -->
                        <div class="other-user">
                            <img class="other-profile-img" src="" alt="상대프로필이미지">
                            <div class="other-chat-block"> <!-- 상대 채팅창 -->
                                <p class="other-chat-content">
                                    안녕하세요~ 같이 산책 가실래요?
                                </p>
                            </div>
                        </div>
                        <!-- 나 -->
                        <div class="my-user">
                            <div class="my-chat-block"> <!-- 내 채팅창 -->
                                <p class="my-chat-content">
                                    네 좋아요!
                                </p>
                            </div>
                        </div>
                        <div class="my-user">
                            <div class="my-chat-block"> <!-- 내 채팅창 -->
                                <p class="my-chat-content">
                                    ㅁㅁ에서 만나요!
                                </p>
                            </div>
                        </div>
                        <!-- 상대 -->
                        <div class="other-user">
                            <img class="other-profile-img" src="" alt="상대프로필이미지">
                            <div class="other-chat-block"> <!-- 상대 채팅창 -->
                                <p class="other-chat-content">
                                    금방 나갈게요
                                </p>
                            </div>
                        </div>
                         <!-- 나 -->
                         <div class="my-user">
                            <div class="my-chat-block"> <!-- 내 채팅창 -->
                                <p class="my-chat-content">
                                    넵
                                </p>
                            </div>
                        </div>
                    </div>
                    <!-- 채팅작성 -->
                    <div id="chat-write-area">
                        <form action="" method="">
                            <div>
                                <textarea name="chat-content" id="" cols="50" rows="20"></textarea>
                                <input type="submit" value="보내기">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- 채팅창 끝 -->
                    
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
    </body>
</html>