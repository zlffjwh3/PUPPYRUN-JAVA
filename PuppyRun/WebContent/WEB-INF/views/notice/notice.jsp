<%@page import="user.model.vo.User"%>
<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user");
	ArrayList<Notice> nList = (ArrayList<Notice>)request.getAttribute("nList");
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/notice.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <title>퍼피런 - 공지사항</title>
    </head>
    <body>
        <div id="wrap">
            <header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft"></div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="/index.html" id="logo"></a>
                    </div>
                    <div id="tright">
                        <div id="search">
                            <form action="">
                                <input class="search-input" id="" type="text" placeholder="search">
                            </form>
                        </div>
                        <div id="login">
                            <a href="#">
                                <i class="xi-face xi-2x"></i>
                            </a>
                            <a href="#" id="login-content">로그인</a>
                        </div>
                    </div>
                </div>
            </header>
            <nav>
                <!-- 메뉴 -->
                <div id="main-menu">
                    <ul id="main-navi-ul">
                        <li class="main-navi-li">
                            <a href="#">산책일기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="#">산책짝꿍</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="#">멍멍이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="#">퍼피런이야기</a>
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
                        <h3>퍼피런 이야기</h3>
                        <p>퍼피런 공지사항 & 퍼피런만의 강아지 정보</p>
                    </div>
                    <div id="nbb-bottom">
                        <%
                        int n = 0;
                        for(int i=0; i<3; i++) {
                        %>
                        	<!-- 게시물 3개씩 묶어두는 박스 -->
                    		<div class="line-box">
                        <%	
                        	for(int j=0; j<3; j++) {
                        		if(n < nList.size()) {
                        %>			
                        			<a href="/notice/detail?noticeNo=<%= nList.get(n).getNoticeNo() %>">
                        				<div class="nbb-content">
			                       		<span class="nbb-photo">
			                            	<img src="/assets/img/Margarita Yazhina.jpg">
			                            			<!-- 사진 어떻게 가져와?..ㅜ -->
			                            </span>
			                            <dl class="nbb-info">
			                                <dt><%= nList.get(n).getNoticeDate() %></dt>
			                                <dd><%= nList.get(n).getNoticeTitle() %></dd>
			                            </dl>
		                            </div>
                        			</a>
                        <%		
                        		}
                       			n++;
                       		}
                        %>
                       		</div>
                        <%
                       	}
                        %>
                    </div>
                    <%
                    if(user.getUserId().equals("admin")) {
                    %>
                    	<!-- 글쓰기 버튼 -->
                        <div id="post-wrap">
                                <a href="/notice/write" class="link-post">글쓰기</a>
                        </div>
                    <%
                    }
                    %>
                    
                    <!-- 여기에 페이징 번호 -->
                    <div class="pagin-box">
                        <%= pageNavi %>
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