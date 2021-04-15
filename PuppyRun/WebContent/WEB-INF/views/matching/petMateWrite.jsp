<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/petMateWrite.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="./assets/js/jquery-3.4.1.min.js"></script>
        <script src="/assets/js/slider.js"></script>
        <script src="/assets/js/scroll.js"></script>
        <title>산책짝꿍</title>
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
                            <a href="/notice.html">퍼피런이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="#">반려견계산기</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 스크롤 메뉴 -->
            <!-- <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <a href="#" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
            </div> -->
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>산책짝꿍 글 작성</h3>
                        <p>산책할 친구를 찾기 위한 글을 작성해보세요.<br></p>
                    </div>
                    <!-- 작업해야할 부분 -->
                    <div id="write-wrap">
                        <div id="write-box">
                            <form action="/matching/write" method="post">
                                <div id="address">
                                    <p class="write-p-tag">주소</p>
                                    <div class="addr-box addr1-box">
                                        <input type="text" id="addr1-input" class="addr-input" name="addr1" maxlength="50" placeholder="구">
                                    </div>                                 
                                    <div class="addr-box addr2-box">
                                        <input type="text" id="addr2-input" class="addr-input" name="addr2" maxlength="50" placeholder="동">
                                    </div>
                                </div>
                                <div id="title">
                                    <p class="write-p-tag">제 목</p>  
                                    <div class="input-box">
                                        <input type="text" id="title-input" name="title" maxlength="50">
                                    </div>
                                </div>
                                <div id="content">
                                    <p class="write-p-tag">내 용</p>
                                    <div class="input-box" id="textarea-box">
                                        <textarea id="content" name="content"></textarea>
                                    </div>
                                </div>
                                <div id="file">
                                    <p class="write-p-tag">첨부 파일</p>
                                    <div class="input-box">
                                        <input type="file" id="file-input" name="file">
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