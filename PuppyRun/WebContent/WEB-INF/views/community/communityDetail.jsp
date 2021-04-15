<%@page import="user.model.vo.User"%>
<%@page import="community.model.vo.Community"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Community community = (Community)request.getAttribute("community");
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/communityDetail.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/notice-write.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/scroll.css">
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/reset.css"> -->
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="<%= request.getContextPath() %>/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <title>퍼피런 :: 멍멍이야기</title>
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
                        <a href="/index.jsp" id="logo"></a>
                    </div>
                    <div id="tright">
                        <div id="login">
                        <%
                        if(user == null) {
                        %>
                            <a href="#">
                                <i class="xi-face xi-2x"></i>
                            </a>
                            <a href="/login.jsp" id="login-content">로그인</a>
                        <%
                        }else {
                        %>
                            <a href="/user/myInfo">
                                <img src="#"> <!-- 사진어케 가져와 -->
                           		</a>
                            <a href="#" id="login-content"><%= user.getUserNick() %></a>
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
                        <h3>멍멍이야기</h3>
                    </div>
                    <!-- 작업해야할 부분 -->
                    <div id="view-wrap">
                        <div id="write-box">
                            <div id="notice-head">
                                <!-- 글 제목 -->
                                <input type="hidden" name="communityTagNo" value="<%= community.getTagNo() %>">
                                <h2><%=community.getComTitle() %></h2>
                                <div class="fl">
                                    <span><%=community.getUserNick() %></span>
                                </div>
                                <div class="fr">
                                    <!-- 작성일 -->
                                    <span><%= community.getComDate() %></span>
                                    <!-- 조회수 -->
                                    <span>조회수 <%= community.getComview() %></span>
                                </div>
                            </div>
                            <div id="notice-content">
                                <div class="write-div">
                                	<div><img src="/upload/<%=community.getComPhoto()%>"></div>
                                    <p><%= community.getComContent() %></p>
                                </div>
                            </div>
                            <div id="comment-wrap">
                                <p>댓글  2<!-- 이부분에 댓글 수 넣을 것--></p>
                                <div id="comment-input">
                                <%
                                if(user != null) {
                               	%>
                                    <form action="" method="">
                                        <textarea name="comment"></textarea>
                                        <input type="submit" value="등록">
                                    </form>
                                <%
                                }
                                %>
                                </div>
                                <ul class="list">
                                    <!-- 여기에 입력한 댓글 넣기   -->
                                    <li class="comment">
                                        <!-- 댓글 프로필 이미지 -->
                                        <div class="profile-image"></div>
                                        <div class="info">
                                            <div class="nickname">
                                                <!-- 닉네임 -->
                                                <p>땅콩</p>
                                                <!-- 작성일 -->
                                                <span>2021-04-12 13:42:21</span>
                                                <span><a>삭제</a></span>
                                                <span><a>수정</a></span>
                                            </div>
                                            <!-- 댓글 내용 -->
                                            <div class="comment"><p>dasdsadasdasdadsadadaddsa</p></div>
                                        </div>
                                    </li>
                                    <li class="comment">
                                        <!-- 댓글 프로필 이미지 -->
                                        <div class="profile-image"></div>
                                        <div class="info">
                                            <div class="nickname">
                                                <!-- 닉네임 -->
                                                <p>땅콩</p>
                                                <!-- 작성일 -->
                                                <span>2021-04-12 13:42:21</span>
                                                <span><a>삭제</a></span>
                                                <span><a>수정</a></span>
                                            </div>
                                            <!-- 댓글 내용 -->
                                            <div class="comment"><p>dasdsadasdasdadsadadaddsa</p></div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div id="function-btn">
                            <div class="bl">
                                <a href="/community/list"><span>글 목록</span></a>
                            </div>
                           	<%
                            if(user != null) {
                            %>
                            <div class="br">
                                <a href="/community/update?communityNo=<%= community.getComNo()%>&communityTagNo=<%= community.getTagNo() %>"><span>수정</span></a>
                                <a href="#"><span>삭제</span></a>
                                <a href="/community/write"><span>글쓰기</span></a>
                            </div>
                             <%
                            }
                            %>
                        </div>
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