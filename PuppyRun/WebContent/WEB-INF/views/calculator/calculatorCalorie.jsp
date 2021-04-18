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

       <!--  <link rel="stylesheet" type="text/css" href="/assets/css/index3.css"> 
       <link rel="stylesheet" type="text/css" href="/assets/css/index4.css">  -->
       <link rel="stylesheet" type="text/css"
	href="/assets/css/calculatorCalorie.css">
        

        <link rel="icon" href="images/fv.ico" type="image/png">
        <link
            href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap"
            rel="stylesheet">
        <link
            rel="stylesheet"
            href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>

        <link rel="shortcut icon" href="./assets/img/Favicon/favicon.ico">
        <link rel="icon" href="./assets/img/Favicon/favicon.ico">
        <script	src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slider.js"></script>
        <script type="text/javascript">
	        function restartCalc(){
				location.href="<%= request.getContextPath() %>/calculator/age?calc_type=2";
			}
        
        	function calorieResult() {
				var dogWeight = $('#dog_weight').val();
				if(dogWeight == null || dogWeight == '' || dogWeight.length == 0){
					alert('강아지의 몸무게를 입력해주세요! :-)');
					return;
				}
				
				var selectedVal = $('#dog_jisu option:selected').val();
				var rerVal = Math.ceil(70 * Math.pow(dogWeight, 0.75));
				var result = '';
				/* 
				<option value="1">생후 4개월 미만 반려견</option>
                 <option value="2">중성화한 반려견</option>
                 <option value="3">중성화하지 않은 반려견</option>
                 <option value="4">비만 상태인 성견</option>
                 <option value="5">활동량이 많은 성견</option>
                 <option value="6">활동량이 많지 않은 성견</option>
                 */
                if(selectedVal == '1'){
                	result = rerVal * 3;
                } else if(selectedVal == '2'){
                	result = rerVal * 1.6;
                } else if(selectedVal == '3'){                	
                	result = rerVal * 1.8;
                } else if(selectedVal == '4'){
                	result = rerVal * 1.3;
                } else if(selectedVal == '5'){
                	result = rerVal * 2;
                } else if(selectedVal == '6'){
                	result = rerVal;
                }
                 
                $('#calResultValue').val(result);
                
                // 이 부분이 결과창 부분 
                $('.contentArea').html('');
                var htmlRes = `
                <div class="cal_result_cal">
                <div class="cal_circle">
                    <span class="cal_img">
                       
                       <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/result_img04.png" alt="저울">
                    </span>
                    <dl>
                        <dt>1일 기초 대사량</dt>
                        <dd id ="basic_meta">`+rerVal+`kcal</dd>
                    </dl>
                    </div>
                        
                        <div class="cal_circle">
                            <span class="cal_img">
                             <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/result_img04.png" alt="저울">
                            </span> 
                            <dl>
                                <dt>1일 권장 칼로리</dt>
                                <dd id="basic_kal">`+result+`kcal</dd>
                            </dl>
                        </div>
                        <div class="cal_txt">
                        <span class="txt_b">※ 기초대사량
                        </span> 
                        " : 생물체가 생명을 유지하는데 필요한 최소한의 에너지량"
                        <br>
                            
                        
                        <span class="txt_b">※ 권장칼로리</span> 
                        " : 몸을 움직이는 데 필요한 에너지인 작업 대사량을 위한 칼로리"
			
                        </div>
                        <!-- <div class="age_lifecycle" id="dog_life"></div> -->
                
            </div>
            <div class="cal_btna">
            	<div class="cal_resulta" onclick="restartCalc()">
           			<a class="btn">다시하기</a>
        		</div>
    		</div>
            `;
        	$('.contentArea').html(htmlRes);
			}
        </script>
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
                        권장칼로리
                    </h3>
                </div>
                    <!-- 위 -->
                    <div class="calculator">
                        <!-- 나이계산기 -->
                        <ul>
                            <li class="active">
                                <a href="?calc_type=1">
                                    <div class ="cal_icon">
                                        <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal01_off.png" alt="나이계산기">
                                    </div>
                                    <span class="cal_txt">나이계산기</span>
                                </a>
                            </li>
                            <li class="nagative">
                                <a href="?calc_type=2">
                                    <div class="cal_icon">
                                     <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal02_on.png" alt="권장칼로리">
                                    </div>
                                    <span class="cal_txt">권장칼로리</span>
                                </a>
                            </li>
                            <li class="nagative1">
                                <a href="?calc_type=3">
                                    <div class="cal_icon">
                                     <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/pc_img/calculator/btnicon_cal03_off.png" alt="비만도계산기">
                                    </div>
                                    <span class="cal_txt">비만도계산기</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="cal_mid">
                        <div class="cal_mid2" id="contents_tab_1" style="display: block;">
                            <div class="age_cal_top">
                                <img src="http://appdata.hungryapp.co.kr/images/hatdog/img/calculator/T_character02.png" alt="">
                                <div class="age_subtitle">
                                        <span class="cal_t01">
                                            내 반려견의
                                            <span class="cal_t02">권장 칼로리는 얼마일까요?</span>
                                        </span>
                                </div>
                            </div>
                            <div class="contentArea">
	                            <div class="cal_writer">
		                            <div class="cal_community">
		                                <span class="writer_input_select">
		                                    <h5>몸무게</h5>
		                                    <input type="text" name="dog_weight" id="dog_weight" inputmode="numeric" maxlength="10" xonkeyup="myFunction()" placeholder="kg단위로 입력하세요">
		                                </span>
		                                <span class="writer_input_select">
		                                   
		                                    <label>
		                                        <select name="dog_jisu" id="dog_jisu">
		                                            <option value>반려견 상태선택</option>
		                                            <option value="1">생후 4개월 미만 반려견</option>
		                                            <option value="2">중성화한 반려견</option>
		                                            <option value="3">중성화하지 않은 반려견</option>
		                                            <option value="4">비만 상태인 성견</option>
		                                            <option value="5">활동량이 많은 성견</option>
		                                            <option value="6">활동량이 많지 않은 성견</option>
		                                        </select>
		                                    </label>
		                                </span>
		                            </div>
	                    		</div>
	                    		<!-- 결과보기 -->
		                        <div class="cal_btn">
		                            <div class="cal_result" onclick="calorieResult()">
		                           <a>결과보기</a>
		                        </div>
                        	</div>
                    	</div>
                    </div>
                    <div>
                        <!-- <input type="" value=""> -->
                    </div>
                        </div>
                    </div>
                </div>
				<input type="hidden" id="calResultValue" style="display: none">
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