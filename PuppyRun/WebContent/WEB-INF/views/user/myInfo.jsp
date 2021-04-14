<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");
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
        <link rel="stylesheet" type="text/css" href="/assets/css/slider.css">
        <link rel="stylesheet" type="text/css" href="/assets/css/scroll.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="./assets/img/Favicon/favicon.ico">
        <link rel="icon" href="./assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="assets/js/jquery-3.4.1.min.js"></script>
        <script src="/assets/js/slider.js"></script>
        <script src="assets/js/weather.js"></script>
        <script src="assets/js/scroll.js"></script>
        <title>퍼피런 - 마이페이지</title>
    </head>
    <body>
        <div id="wrap">
            <header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft">
                    <div id="search">
                            <form action="">
                                <input class="search-input" id="" type="text" placeholder="search">
                            </form>
                        </div>
                    </div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="#" id="logo"></a>
                    </div>
                    <div id="tright">
                        <div id="login">
                            <%
                        	if(user == null) {
                        	%>
                            	<a href="/login.jsp">
                                <i class="xi-face xi-2x"></i>
                           		</a>
                            	<a href="/login.jsp" id="login-content">로그인</a>
                            <%
                            } else {
                            %>
                            	<a href="/user/myInfo">
                                <img src="#"> <!-- 사진어케 가져와 -->
                           		</a>
                            	<a href="/user/myInfo" id="login-content"><%= user.getUserNick() %></a>
                            <%
                            }                        
                            %>
                        </div>
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
                            <a href="#">산책짝꿍</a>
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
            
            <!-- 메인영역 ----------------------------------------------------------------------------------------------------------->
            <div>
            	<!-- 상단 -->
            	<div>
            		<!-- 프로필 -->
		            <div>
		            	<p>프로필</p>
		            	<% if(user.getDogCheck() == 'Y')  {%>
			            	<div>
			            		<img src="#">
			            	</div>
			            	<div>
			            		<div>
			            			<p>닉네임</p>
			            			<p>강아지 이름</p>
			            		</div>
			            		<div>
			            			<ul>
			            				<li>품종</li>
			            				<li>성별</li>
			            				<li>나이</li>
			            				<li>몸무게</li>
			            			</ul>
			            			<ul>
			            				<li>품종</li>
			            				<li>성별</li>
			            				<li>나이</li>
			            				<li>몸무게</li>
			            			</ul>
			            		</div>
			            	</div>
			            <% } else { %>
			            	<div>
			            		<p>강아지 정보를 등록해주세요!</p>
			            		<form action="" method="post">
			            			<div id="id-area" class="area-div">
				                        <p class="subtitle">이름</p>
				                        <div id="id-input" class="input-div">
				                            <input type="text" name="dog-name" id="user-id" placeholder="강아지 이름">
				                        </div>
				                        <span class="id-error-msg error-msg"></span>
				                    </div>
			            			
			            		</form>
			            	</div>
			            <% } %>
		            	<div>
		            		<a href="/user/update">프로필 수정</a>
		            		<a href="/user/delete" onclick="return confirm('정말 탈퇴하시겠습니까?')">회원탈퇴</a>
		            	</div>
		            </div>
		            <!-- 반려견계산기 버튼 -->
		            <div onclick="location.href='#'">
		            	<!-- div 배경으로 사진 설정하기 -->
		            	<p>나이, 칼로리, 비만도<br>
		            	소중한 내 친구를 위한<br>
		            	필수 계산기</p>
		            	<p>반려견 계산기</p>
		            </div>
            	</div>
            	<!-- 하단 -->
            	<div>
            		<div>
            			<div>관심목록</div>
            			<div>MY작성글</div>
            			<div>MY댓글</div>
            		</div>
            	</div>
            </div>
            <!-- 메인영역 끝! ------------------------------------------------------------------------------------------------------->
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