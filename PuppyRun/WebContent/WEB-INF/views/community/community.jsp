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
        <link rel="stylesheet" type="text/css" href="/assets/css/index.css">
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/notice.css"> -->
        <link rel="stylesheet" type="text/css" href="/assets/css/scroll.css">
        <link rel="stylesheet" type="text/css" href="/assets/css/community.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="./assets/js/jquery-3.4.1.min.js"></script>
        <script src="/assets/js/slider.js"></script>
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
                        </div>
                    </div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="/index.html" id="logo"></a>
                    </div>
                    <div id="tright">
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
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>멍멍이야기</h3>
                        <p>다양한 이야기를 나눠요!</p>
                        <!-- 태그 박스 -->
                        <div id="category-wrap">
                            <!-- 태그들 -->
                            <ul id="category">
                                <li><i class="fas fa-border-all"></i><p>전체</p></li>
                                <li><i class="far fa-comments"></i><p>자유</p></li>
                                <li><i class="fas fa-cubes"></i><p>나눔</p></li>
                                <li><i class="fas fa-question"></i><p>질문</p></li>
                                <li><i class="fas fa-child"></i><p>자랑</p></li>
                            </ul>
                        </div>
                    </div>
                    <div id="nbb-bottom">
                        <div id="content-box">
                            <ul class="list">
                                <!-- 게시물 1개 -->
                                <li class="post">
                                    <div class="img-area"><img src="/assets/img/freetest2.jpg"></div>
                                    <div class="text-area">
                                        <!-- 태그 이름 -->
                                        <div class="post-category"><p>자유</p></div>
                                        <!-- 제목 -->
                                        <a class="title" href="/html/free-board-detail.html">강아지</a>
                                        <!-- 내용 -->
                                        <div class="post-content"><p>멍멍</p></div>
                                        <div class="post-info">
                                            <!-- 프로필 이미지 -->
                                            <div class="profile">
                                                <div class="img"></div>
                                                <!-- 닉네임 -->
                                                <p class="post-nickname">민트</p>
                                            </div>
                                            <div class="counting-bar">
                                                <!-- 작성일 -->
                                                <span>2021-04-04 14:33:13</span>
                                                <!-- 댓글 -->
                                                <span>댓글 3</span>
                                                <!-- 조회수 -->
                                                <span>조회수 12312</span>
                                                <!-- 좋아요-->
                                                <span>좋아요 123</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 글쓰기 버튼 -->
                    <div id="post-wrap">
                            <a href="/community/write" class="link-post">글쓰기</a>
                    </div>
                    <!-- 여기에 페이징 번호 -->
                    <div class="pagin-box">
                        <a href="#" id="page-prev">이전</a>
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#">6</a>
                        <a href="#">7</a>
                        <a href="#">8</a>
                        <a href="#">9</a>
                        <a href="#">10</a>
                        <a href="#" id="page-next">다음</a>
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