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
        <title>퍼피런 - 친구와 산책 나가요</title>
    </head>
    <body>
        <div id="wrap">
            <header>
                <!-- 헤더-->
                <div id="header">
                    <div id="tleft"></div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="#" id="logo"></a>
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
                            <a href="/login.jsp" id="login-content">로그인</a>
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
                                <img src="./assets/img/slide01.jpg" alt="메인베너2">
                                <img src="./assets/img/slide02.jpg" alt="메인베너2">
                                <img src="./assets/img/slide03.jpg" alt="메인베너2">
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
                    <div id="walkcontent-box">
                        <p id="text01">산책 정보를 확인하려면
                            <b>로그인</b>
                            해주세요.</p>
                        <div id="dog-image-box"></div>
                        <div id="login-wrap">
                            <a href="/html/login.html" class="link-login">로그인</a>
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
                            <dt>2021-04-06</dt>
                            <dd>오늘 강아지 꿈을
                                꾸었습니다1111111111111111111111111111111111111111111111111111.111111111111111111111111111111111111</dd>
                            <dd>개꿈이었을까요? 개꿈맞을까요? 아닌가요?
                                궁짱짱꿍깡쭝깡쭝깡쭝깡쭝깡깡깡깡깡깡깡깡깡깡깡깡깡1ㅇㅁ111111111111111111111111111111111111111131231313313131313131ㄹ안ㄹ나랠래랜ㅁ래랠매ㄴㅇㅁㅇㅇㅁㄴㅇㅇ</dd>
                            <div class="profile-image"></div>
                            <dt>개꿈 꾸는 사람</dt>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-05</dt>
                            <dd>오늘 한강 놀러왔어요~~</dd>
                            <dd>우리 귀여운 강아지랑 같이 산책하려고 왔어요~ 날씨도 너무 좋더라고요~~</dd>
                            <div class="profile-image"></div>
                            <dt>한강 가는 사람</dt>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-05</dt>
                            <dd>치킨이닭</dd>
                            <dd>치킨췩킨취킨</dd>
                            <div class="profile-image"></div>
                            <dt>치킨</dt>
                        </dl>
                        <dl class="fbb-cotent">
                            <dt>2021-04-04</dt>
                            <dd>게시판 망했나요</dd>
                            <dd>게시물이 없네요</dd>
                            <div class="profile-image"></div>
                            <dt>가끔 오는 사람</dt>
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