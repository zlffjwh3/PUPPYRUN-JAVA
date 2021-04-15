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
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/reset.css"> -->
        <link rel="stylesheet" type="text/css" href="/assets/css/communityWrite.css">

        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/scroll.css"> -->
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="./assets/js/jquery-3.4.1.min.js"></script>
        <script src="/assets/js/scroll.js"></script>
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
                        </div></div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="/index.jsp" id="logo"></a>
                    </div>
                    <div id="tright">
                        <div id="login">
                            <a href="/user/myInfo">
                                <img src="#"> <!-- 사진어케 가져와 -->
                           		</a>
                            	<a href="/user/myInfo" id="login-content"><%= user.getUserNick() %></a>
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
            </div> -->
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>멍멍이야기 글 작성</h3>
                    </div>
                    <!-- 작업해야할 부분 -->
                    <div id="write-wrap">
                        <div id="write-box">
                            <form action="/community/write" method="post" enctype="multipart/form-data">
                             <div id="tag">
                                    <select name="tags" id="tegs">
                                        <option value="">태그를 선택해주세요</option>
                                        <option value="0">자유</option>
                                        <option value="1">나눔</option>
                                        <option value="2">질문</option>
                                        <option value="3">자랑</option>
                                    </select>
                                </div>
                                <div id="title">
                                    <p class="write-p-tag">제 목</p>  
                                    <div class="input-box">
                                        <input type="text" id="title-input" name="title" maxlength="50">
                                    </div>
                                </div>
                                <div id="file">
                                    <p class="write-p-tag">첨부 파일</p>
                                    <div class="input-box">
                                        <input type="file" id="file-input" name="upFile">
                                    </div>
                                </div>
                                <div id="content">
                                    <p class="write-p-tag">내 용</p>
                                    <div class="input-box" id="textarea-box">
                                        <textarea id="content" name="content"></textarea>
                                    </div>
                                </div>
                                <div id="btn-box">
                                    <input type="submit" id="submit-input" value="등록">
                                    <a href="/html/free-board.html"><p>취소</p></a>
                                </div>
                            </form>
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