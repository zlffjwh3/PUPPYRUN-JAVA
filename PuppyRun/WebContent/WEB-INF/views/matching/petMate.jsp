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
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/notice.css"> -->
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
                            <form action="/matching/list" method="get">
                                <input class="search-input" id="" type="text" placeholder="search">
                            </form>
                        </div>
                    <%
                    if(user == null) {
                    %>
                        <div id="login">
                            <a href="#">
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
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>산책짝꿍</h3>
                        <p>산책짝꿍에서 산책 같이할 친구를 찾아요!</p>
                        <!-- 태그 박스
                        <div id="tag-box">
                            <i class="fas fa-border-all"></i>
                        </div> -->
                    </div>
                    <div id="nbb-bottom">
                        <div id="all-content-box">
                            <ul>
                                <!-- 게시글1 -->
                                <% 
                                	for(int i=0; i<mList.size(); i++) {
                                %>
                                <li>
                                    <a href="/matching/detail?matNo=<%= mList.get(i).getMatNo()%>"> <!-- 게시글 확인용 a-->
                                    
                                        <div class="content-box">
                                            <div class="list-img-area">
                                                <img src="/upload/<%=mList.get(i).getMatPhoto() %>" alt="게시글 대표이미지">
                                            </div>
                                            <div class="list-content-area">
                                                <div>
                                                    <div class="user-profile-img-div">
                                                        <img src="" alt="프로필 이미지" class="user-profile-img">
                                                    </div>
                                                    <span class="user-name"><%=mList.get(i).getUserNick() %></span>
                                                    <span class="user-addr"><%=mList.get(i).getMatAddr() %></span>
                                                </div>
                                                <div>
                                                    <span class="user-mini-content"><%=mList.get(i).getMatContent() %></span>
                                                    <!-- <input type="submit" value="채팅하기" class="chat-btn"> -->
                                                    <a href = "/matching/detail?matNo=<%= mList.get(i).getMatNo()%>" class="chat-btn">채팅하기</a>
                                                </div>
                                            </div>
                                        </div>
                                    
                                    </a>
                                </li>
                                <% 
                                }
                                %>
                                <!-- 게시글1 끝 -->
                                
                                
                            </ul>
                        </div>
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
    </body>
</html>