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

        <link rel="stylesheet" type="text/css" href="/assets/css/index5.css">

        <link rel="icon" href="images/fv.ico" type="image/png">
        <link
            href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap"
            rel="stylesheet">
        <link
            rel="stylesheet"
            href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>

        <link rel="shortcut icon" href="./assets/img/Favicon/favicon.ico">
        <link rel="icon" href="./assets/img/Favicon/favicon.ico">
        <script src="./assets/js/jquery-3.4.1.min.js"></script>
        <script src="./assets/js/slider.js"></script>
        <title>퍼피런 - 친구와 산책 나가요</title>
    </head>
    <body>
        <div id="wrap">
            <header>
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
                            <a href="#" id="login-content">로그인</a>
                        </div>
                    </div>
                </div>
            </header>
            <nav>
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
            
            <!-- 메인!!!!! 이밑부터 수정!! -->
            <div id="main-content">
                <div id="content">
                    <div class="cal_top">
                    <h3>
                        반려견계산기
                    </h3>
                </div>
                    <!-- 위 -->
                    <div class="calculator">
                      
                        <!-- 나이계산기 -->
                        <ul>
                            <li class="active">
                                <a href="#">
                                    <div class ="cal_icon">
                                        <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal01_on.png" alt="나이계산기">
                                    </div>
                                    <span class="cal_txt">나이계산기</span>
                                </a>
                            </li>
                            <li class="nagative">
                                <a href="#">
                                    <div class="cal_icon">
                                     <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal02_off.png" alt="권장칼로리">
                                    </div>
                                    <span class="cal_txt">권장칼로리</span>
                                </a>
                            </li>
                            <li class="nagative1">
                                <a href="#">
                                    <div class="cal_icon">
                                     <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal02_off.png" alt="권장칼로리">
                                    </div>
                                    <span class="cal_txt">비만도계산기</span>
                                </a>
                            </li>
                        </ul>
                    
                    </div>
                    <div class="cal_mid">
                        <div class="cal_mid2" id="contents_tab_1" style="display: block;">
                            <div class="age_cal_top">
                                <img src="http://appdata.hungryapp.co.kr/images/hatdog/img//calculator/T_character01.png" alt="">
                                <div class="age_subtitle">
                                        <span class="cal_t01">
                                            내 반려견의 나이
                                            <span class="cal_t02">사람이라면 몇 살일까요?</span>
                                        </span>
                                </div>
                            </div>
                            <div class="cal_writer">
                                <span class="writer_input">
                                    <!-- <h5>몸무게선택</h5>
                                    
                                    <div class="cal_choice">
                                        <input type="hidden" id="dog_type" name="dog_type" value="0">
                                        <a class="dog_type0" onclick="weight_choice0">소(~9kg)</a>
                                        <a class="dog_type1" onclick="weight_choice1">중(9~23kg)</a>
                                        <a class="dog_type2" onclick="weight_choice2">대(23kg이상)</a>
                                    </div> -->
                                </span>
                            <div class="cal_community">
                                <span class="writer_input">
                                    <h5>BCS 선택</h5>
                                    <div class="cal_BCS">
                                        <input type="hidden" id="bcs_type" name="bcs_type" value=>
                                        <ul id="bcs_sel_0_img" style="display: block;">
                                        <li onclick="bcs_sel(0)" class="bcs_type_0">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_s_01_on.png" alt="BCS_s01">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(1)" class="bcs_type_1">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_s_06_on.png" alt="BCS_s02">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(2)" class="bcs_type_2">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_s_09_on.png" alt="BCS_s03">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(3)" class="bcs_type_3">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_m_01_on.png" alt="BCS_s04">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(4)" class="bcs_type_4">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_m_06_on.png" alt="BCS_s05">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(5)" class="bcs_type_5">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_m_09_on.png" alt="BCS_s06">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(6)" class="bcs_type_6">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_l_01_on.png" alt="BCS_s07">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(7)" class="bcs_type_7">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_l_05_on.png" alt="BCS_s08">
                                        </a>
                                        </li>
                                        <li onclick="bcs_sel(8)" class="bcs_type_8">
                                        <a>
                                            <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/BCS_l_09_on.png" alt="BCS_s09">
                                        </a>
                                        </li>
                                        
                                        </ul>
                                    </div>
                                    
                                </span>
                            </div>
                        </div>
                        <!-- 결과보기 -->
                        <div class="cal_btn">
                            <div class="cal_result" onclick="result_view1">
                           <a>결과보기</a>
                        </div>
                    </div>
                    </div>
                    <div>
                        <!-- <input type="" value=""> -->
                    </div>
                        </div>
                    </div>
                </div>

                <footer>
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
                </div>
            </footer>
        </div>
    </body>
</html>