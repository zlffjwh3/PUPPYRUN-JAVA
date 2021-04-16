<%@page import="community.model.vo.Community"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous">
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
        <!-- CSS 파일 가져오기 -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bg-middle.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/slider.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/weather.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/index.js"></script>
        <title>퍼피런 - 친구와 산책 나가요</title>
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
                    <!-- 슬라이드 -->
                    <div id="mContent-Box1">
                        <div id="banner">
                            <div class="image">
                                <img src="./assets/img/slide01.jpg" alt="메인베너1">
                                <img src="./assets/img/slide02.jpg" alt="메인베너2">
                                <img src="./assets/img/slide03.jpg" alt="메인베너3">
                            </div>
                            <ul class="control">
                                <li></li>
                                <li></li>
                                <li></li>
                            </ul>
                            <div class="btn">
                                <i class="xi-angle-left-thin"></i>
                                <i class="xi-angle-right-thin"></i>
                            </div>
                        </div>
                    </div>
                    <!-- 날씨 -->
                    <div id="mWeather">
                        <div class="weather">
                            <div class="City"></div>
                            <div class="Currlcon"></div>
                            <div class="CurrTemp"></div>
                        </div>
                    </div>
                </div>
                <!-- 산책정보 -->
                <div id="mWalkInfo">
                	<% if(user != null) { %>
                    <div id="walkcontent-box">
                        <p id="text01">오늘
                            <b>덕배</b>
                            는 산책 다녀왔어요!</p>
                        <div id="dog-image-box">
                        	<% if(user.getUserPhoto() != null) { %>
                        	<script>$('#dog-image-box').css('background-image','url(/upload/<%=user.getUserPhoto()%>)');</script>
                        	<% } else { %>
                        	<script>$('#dog-image-box').css('background-image','url(/assets/img/user-no-img.png)');</script>
                        	<% } %>
                        </div>
                        <div id="login-wrap">
                            <a href="#" class="link-login">산책기록</a>
                        </div>
                        <div id="info-wrap">
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">/ 7</span>
                                <p class="text" id="text">이번주 총 산책 횟수</p>
                            </div>
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">km</span>
                                <p class="text" id="text">총 산책거리</p>
                            </div>
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">분</span>
                                <p class="text" id="text">총 산책시간</p>
                            </div>
                        </div>
                    </div>
                    <% } else { %>
                    <div id="walkcontent-box">
                        <p id="text01">산책 정보를 확인하려면
                            <b>로그인</b>
                            해주세요.</p>
                        <div id="dog-image-box"></div>
                        <div id="login-wrap">
                            <a href="/login.jsp" class="link-login">로그인</a>
                        </div>
                        <div id="info-wrap">
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">/ 7</span>
                                <p class="text" id="text">이번주 총 산책 횟수</p>
                            </div>
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">km</span>
                                <p class="text" id="text">총 산책거리</p>
                            </div>
                            <div class="info-content">
                                <span class="set-info">0</span>
                                <span class="text">분</span>
                                <p class="text" id="text">총 산책시간</p>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
                <!-- 멍멍이야기 -->
                <div class="free-board-box">
                    <div id="fbb-top">
                        <p>멍멍이야기</p>
                        <a href="#">
                            <i class="xi-plus-thin"></i>
                        </a>
                        <p>NEW</p>
                    </div>
                    <div id="fbb-bottom">
                    	
                        <dl class="fbb-cotent">
                            <dt>데이터</dt>
                            <dd>타이틀</dd>
                            <dd>내용</dd>
                            <div class="profile-image"></div>
                            <dt>닉네임</dt>
                        </dl>
                       
                    </div>
                </div>
                <!-- 산책친구 -->
                <div class="free-board-box">
                    <div id="fbb-top">
                        <p>산책친구</p>
                        <a href="#">
                            <i class="xi-plus-thin"></i>
                        </a>
                        <p>NEW</p>
                    </div>
                    <div id="fbb-bottom">
                        <dl class="fbb-cotent">
                            <dt>2021-04-06</dt>
                            <dd>같이 산책하실 분~</dd>
                            <dd>비밀글입니다.</dd>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-06</dt>
                            <dd>애견 사진 교환해요</dd>
                            <dd>비빔면입니다.</dd>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-05</dt>
                            <dd>모임 구해요.</dd>
                            <dd>비밀글입니다?</dd>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-05</dt>
                            <dd>제 강아지 볼 사람</dd>
                            <dd>비밀글입니다.</dd>
                        </dl>
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