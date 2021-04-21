<%@page import="matching.model.vo.MatchingChat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="matching.model.vo.Matching"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	User user = (User)session.getAttribute("user");
	Matching matching = (Matching)request.getAttribute("matching");
	ArrayList<MatchingChat> matChat = (ArrayList<MatchingChat>)request.getAttribute("matChat");
	ArrayList<User> uList = (ArrayList<User>)request.getAttribute("uList");
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
        <title>퍼피런 :: 산책짝꿍</title>
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
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>산책짝꿍</h3>
                        <p>같이 산책할 친구 찾아요 !</p>
                    </div>
                    <!-- 채팅창 -->
		            <div id="chat-box">
		                <div id="chat-box-left">
		                    <div id="chat-img-box">
		                        <% if(matching.getMatPhoto() != null) { %>
                                    <img src="/upload/<%=matching.getMatPhoto() %>" alt="게시글 대표이미지">
                                <% } else { %>
                                    <img src="/assets/img/no-img.jpg">
                                <% } %>
		                    </div>
		                    <div id="chat-profile-box">
	                             <div class="chat-content-area">
	                                 <div>
	                                     <div class="user-profile-img-div">
	                                         <% int m = 0;
                                            	for(int u=0; u<uList.size(); u++) { 
                                            		if(uList.get(u).getUserId().equals(matching.getMatId())) {
                                            			m = u;
                                            			break;
                                            		}
                                            	} %>
                                           	<% if(uList.get(m).getUserPhoto() != null) { %>
                            					<img src="/upload/<%= uList.get(m).getUserPhoto() %>" class="user-profile-img">
	                       					<% } else { %>
                            					<img src="/assets/img/user-no-img.png" class="user-profile-img">
	                        				<% } %>
	                                     </div>
	                                     <div class="user-profile-name-div">
	                                     	<span class="user-name"><%= matching.getUserNick() %></span>
	                                     </div>
	                                     <div class="user-profile-addr-div">
	                                      <span class="user-addr"></span>
	                                      <% if(matching.getMatAddr() != null) { %>
                                          <span class="user-addr"><%= matching.getMatAddr() %></span>
                                          <% } %>
	                                     </div>
	                                 </div>
	                                 <div>
	                                     <!-- 글내용이 길어지면 자동으로 스크롤바가 생김 -->
	                                     <p id="user-write-content"><%= matching.getMatContent() %></p>
	                                 </div>
	                             </div>
	                             <!-- 수정 또는 삭제 -->
	                             <% if(user.getUserId().equals(matching.getMatId())) { %>
	                             <a href="/matching/modify?matNo=<%= matching.getMatNo() %>" class="matching-btn matching-btn1" style="color: white;">수정하기</a>
	                             <a href="/matching/delete?matNo=<%= matching.getMatNo() %>" class="matching-btn matching-btn2" style="color: white;" onclick="return confirm('정말 삭제하시겠습니까?')">삭제하기</a>
	                             <% } %>
		                    </div>
		                </div>
		                <div id="chat-box-right"> <!-- 오른쪽(채팅창) 부분 -->
		                    <div id="chat-close">
		                        <a href="/matching/list">
		                            <img src="/assets/img/x_mark.png" alt="창 제거 이미지">
		                        </a>
		                    </div>
		                    <!-- 채팅창 보는 영역 + 속 내용이 길어지면 스크롤 자동생성 설정해놈(overflow : auto) -->
		                    <div id="chat-area">
		                    <% if(matChat != null) { 
		                    		for(int i = 0; i < matChat.size(); i++) {
		                    			if(matChat.get(i).getSendId().equals(user.getUserId())) {
		                    %>
		                        <!-- 나 -->
		                        <div class="my-user">
		                            <div class="my-chat-block"> <!-- 내 채팅창 -->
		                                <p class="my-chat-content">
		                                    <%= matChat.get(i).getContent() %>
		                                </p>
		                            </div>
		                        </div>
		                        <% }
		                    	if(matChat.get(i).getRcvId() != null) {
		                        if(!(matChat.get(i).getSendId().equals(user.getUserId()))) {%>
		                        <!-- 상대 -->
		                        <div class="other-user">
		                        	<!-- 상대 프로필 이미지 -->
		                            <% if(uList.get(m).getUserPhoto() != null) { %>
                       				<img src="/upload/<%= uList.get(m).getUserPhoto() %>" class="other-profile-img">
                   					<% } else { %>
                       				<img src="/assets/img/user-no-img.png" class="user-profile-img">
                      				<% } %>
                      				<!-- 상대 채팅창 -->
		                            <div class="other-chat-block">
		                                <p class="other-chat-content">
		                                    <%= matChat.get(i).getContent() %>
		                                </p>
		                            </div>
		                        </div>
		                        			<% } %>
		                        		<% } %>
		                        	<% } %>
		                        <% } %>
		                        
		                    </div>
		                    <!-- 채팅작성 -->
		                    <div id="chat-write-area">
		                        <form action="/matching/detail" method="post">
		                       		<input type="hidden" name="send-id" value="<%= user.getUserId()%>">
		                       		<% if(!matChat.isEmpty() && user.getUserId().equals(matching.getMatId())) { %>
									<input type="hidden" name="rcv-id" value="<%= matChat.get(0).getSendId() %>">
		               				<% } else { %>
		               				<input type="hidden" name="rcv-id" value="<%= matching.getMatId()%>">
		               				<% } %>
		               				<input type="hidden" name="matching-no" value="<%= matching.getMatNo()%>">
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