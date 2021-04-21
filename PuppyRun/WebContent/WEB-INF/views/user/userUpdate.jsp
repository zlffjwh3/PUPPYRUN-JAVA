<%@page import="user.model.vo.Dog"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)request.getAttribute("user");
	System.out.println("아아..." + user);
	Dog dog = (Dog)request.getAttribute("dog");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/reset.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/join.css">
    <%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/index.css"> --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <%-- <script src="<%= request.getContextPath() %>assets/js/jquery-3.5.1.min.js"></script> --%>
    <script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/userUpdate.js"></script>
    <script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
    <title>회원정보 수정</title>
</head>
<body>
    <div id="wrap"><!-- ㄴㄴㄴ -->
        <div id="join-form">
            <div id="form-logo">
	            <a href="/index.jsp">
	                <img src="<%= request.getContextPath() %>/assets/img/main_logo.png" alt="">
	            </a>
            </div>
            <div id="form1-area">
                <form name="frm">
                <!-- <form action="/user/enroll" method="post" name="frm"> -->
                    <div id="id-area" class="area-div">
                        <p class="subtitle">아이디</p>
                        <div id="id-input" class="input-div">
                            <input value="<%= user.getUserId() %>" type="text" name="user-id" id="user-id" placeholder="아이디 입력(영문 대소문자와 숫자, 5~11자)" readonly>
                        </div>
                        <span class="id-error-msg error-msg"></span>
                    </div>
                    <div id="pwd-area" class="area-div">
                        <p class="subtitle">수정할 비밀번호</p>
                        <div id="pwd-input" class="input-div">
                            <input type="password" name="user-pwd" id="user-pwd" placeholder="비밀번호 (숫자, 영문, 특수문자 조합 최소 8글자)">
                        </div>
                        <span class="pwd-error-msg error-msg"></span>
                       <!--  <div id="pwd-re-input" class="input-div">
                            <input type="password" name="user-re-pwd" id="user-re-pwd" placeholder="비밀번호 확인">
                        </div>
                        <span class="re-pwd-error-msg error-msg"></span> ---------------------------------------------------------------->
                    </div>
                    <div id="nickname-area" class="area-div">
                        <p class="subtitle">닉네임</p>
                        <div id="nickname-input" class="input-div">
                            <input value="<%= user.getUserNick() %>" type="text" name="user-nickname" id="user-nickname" placeholder="닉네임" readonly>
                        </div><span class="nickname-error-msg error-msg"></span>
                    </div>
                    <div id="name-area" class="area-div">
                        <p class="subtitle">이름</p>
                        <div id="name-input" class="input-div">
                            <input value="<%= user.getUserName() %>" type="text" name="user-name" id="user-name" placeholder="이름"  readonly>
                        </div><span class="name-error-msg error-msg"></span>
                    </div>
                    <div id="phone-area" class="area-div">
                        <p class="subtitle">휴대전화</p>
                        <div id="phone-input" class="input-div">
                            <input value="<%= user.getPhone() %>" type="text" name="user-phone" id="user-phone" placeholder="휴대전화">
                        </div><span class="phone-error-msg error-msg"></span>
                    </div>
                    <div id="email-area" class="area-div">
                        <p class="subtitle">이메일 주소</p>
                        <div id="email-input" class="input-div">
                            <input value="<%= user.getEmail() %>" type="email" name="user-email" id="user-email">
                        </div>
                        <input type="button" value="인증" style="width:60px; height:32px;">
                        <span class="email-error-msg error-msg"></span>
                    </div>
                    
                    <div id="birth-area" class="area-div">
                        <p class="subtitle">생년월일</p>
                        <div id="year-input" class="input-div">
                            <input value="<%= user.getUserBirth().substring(0,4) %>" type="text" name="user-birth-year" id="user-birth-year" placeholder="년(4자)" readonly>
                        </div>
                        <div id="month-input" class="input-div">
                        <select name="user-birth-month" id="user-birth-month"  readonly>
                                <option value="" disabled>월</option>
                        <% for(int i = 1; i < 13; i++) { %>
                                <option value="0<%= i%>" disabled><%= i%>월</option>
                                <% if(Integer.parseInt(user.getUserBirth().substring(4,6)) == i) { %>
                                	<option value="0<%= i%>" selected ><%= i%>월</option>
                                <% } %>
                        <% } %>
                            </select>
                        </div>
                        <div id="day-input" class="input-div">
                            <input value="<%= user.getUserBirth().substring(6,8) %>" type="text" name="user-birth-day" id="user-birth-day" placeholder="일"  readonly>
                        </div>
                    </div>
					
					<%
						String date[] = user.getUserAddr().split("/");
					%>
                    <div id="addr-area" class="area-div">
                        <p class="subtitle">집주소</p>
                        <div id="zip-input" class="input-div">
                            <input value="<%= date[0] %>" type="text" id="zip" name="zip" placeholder="우편번호">
                        </div>
                        <button id="addr-search-btn" type="button" style="width:60px; height:32px;" onclick="openZipSearch()">입력</button>
                        <div id="aadr1-input" class="input-div">
                            <input value="<%= date[1] %>" type="text" name="addr1" id="addr1" placeholder="기본주소" readonly>
                        </div>
                        <div id="addr2-input" class="input-div">
                            <input value="<%= date[2] %>" type="text" name="addr2" id="addr2" placeholder="상세주소">
                        </div>
                    </div>
                 
                    <!-- 강아지가 있거나 없는경우 -->
                    <% if(user.getDogCheck() == 'Y') { %>
                    <div id="pet-select-area" class="area-div">
                       <!--  <span class="pet-ques">반려견이 있나요? </span> -->
                        <div class="pet-result">
                            <!-- <label for="pet-selectY">
                                예 <input type="radio" name="pet-select" id="pet-selectY" class="pet-select" value="yes">
                            </label>
                            <label for="pet-selectN">
                                아니요 <input type="radio" name="pet-select" id="pet-selectN" class="pet-select" value="no">
                            </label> -->
                            <script>
                            	$(document).ready(function() {
	                            	$('#pet-selectY').attr('checked', 'checked');
	                            	$('#user-join-submit').css('display','none');
	                    			$('#form2-area').css('display','block');
                            	});
                            </script>
                        </div>
                    </div>
                    
                    <% } else {%>
                    <div id="pet-select-area" class="area-div">
                        <span class="pet-ques">반려견이 있나요? </span>
                        <div class="pet-result">
                            <label for="pet-selectY">
                                예 <input type="radio" name="pet-select" id="pet-selectY" class="pet-select" value="yes">
                            </label>
                            <label for="pet-selectN">
                                아니요 <input type="radio" name="pet-select" id="pet-selectN" class="pet-select" value="no" checked>
                            </label>
                        </div>
                    </div>
                    
                    <% } %>
                    
                    <div id="user-join-submit" class="submitBtn">
                    	<input type="hidden" name="userId" value="<%= user.getUserId() %>">
                        <input type="button" class="joinBtn" value="수정" onclick="getPost('01')">
                    </div>
                </div>
                <% if(user.getDogCheck() == 'Y') { %> <!-- 반려견 있음(Y) 체크됐을 때 -->
                <div id="form2-area">
                    <p id="p-hr"></p>
                    <p id="dog-title">반려견의 정보를 입력해주세요</p><br>
                    <div id="dog-name-area" class="area-div">
                        <p class="subtitle">반려견 이름</p>
                        <div id="dog-name-input" class="input-div">
                            <input value="<%= dog.getDogName() %>" type="text" name="dog-name" id="dog-name" placeholder="이름(한글, 1~8글자)">
                        </div>
                        <span class="dog-name-error-msg error-msg"></span>
                    </div>
                    <div id="dog-kind-area" class="area-div">
                        <p class="subtitle">품종</p>
                        <div id="dog-kind-input" class="input-div">
                            <input value="<%= dog.getDogBreed() %>" type="text" name="dog-kind" id="dog-kind" placeholder="품종(한글, 1~15글자)">
                        </div>
                    </div>
                    <div id="dog-gender-area" class="area-div">
                        <p class="subtitle">성별</p>
                        <div id="dog-gender-input" class="input-div">
                            <select name="dog-gender" id="dog-gender">
                                <option value="" disabled selected>성별</option>
                               <% if(dog.getDogGender() == 'M') {%>
                               		<option value="M" selected>수컷</option>
                               		<option value="F">암컷</option>
                                	<option value="T">중성화</option>
                               <% } else if (dog.getDogGender() == 'F') { %>
	                                <option value="M">수컷</option>
	                                <option value="F" selected>암컷</option>
	                                <option value="T">중성화</option>
                               <% } else if (dog.getDogGender() == 'T') { %>
	                                <option value="M">수컷</option>
	                                <option value="F">암컷</option>
	                                <option value="T" selected>중성화</option>
                               <% } %>
                               
                            </select>
                        </div>
                    </div>
                    <div id="dog-age-area" class="area-div">
                        <p class="subtitle">나이(세)</p>
                        <div id="dog-age-input" class="input-div">
                            <input value="<%= dog.getDogAge() %>" type="text" name="dog-age" id="dog-age" placeholder="나이(숫자)">
                        </div>
                    </div>
                    <div id="dog-weight-area" class="area-div">
                        <p class="subtitle">몸무게(kg)</p>
                        <div id="dog-weight-input" class="input-div">
                            <input value="<%= dog.getDogWeight() %>" type="text" name="dog-weight" id="dog-weight" placeholder="몸무게(소수점 첫째자리까지 입력)">
                        </div>
                        <span class="dog-weight-error-msg error-msg"></span>
                    </div>
                    <div id="dog-and-user-submit" class="submitBtn">
                        <input type="button" class="joinBtn" value="확인" onclick="getPost('02')">
                    </div>
                </div>
                
                <% } else {%> <!-- 반려견 없음(N) 체크됐을 때 -->
                <div id="form2-area">
                    <p id="p-hr"></p>
                    <p id="dog-title">반려견의 정보를 입력해주세요</p><br>
                    <div id="dog-name-area" class="area-div">
                        <p class="subtitle">반려견 이름</p>
                        <div id="dog-name-input" class="input-div">
                            <input type="text" name="dog-name" id="dog-name" placeholder="이름(한글, 1~8글자)">
                        </div>
                        <span class="dog-name-error-msg error-msg"></span>
                    </div>
                    <div id="dog-kind-area" class="area-div">
                        <p class="subtitle">품종</p>
                        <div id="dog-kind-input" class="input-div">
                            <input type="text" name="dog-kind" id="dog-kind" placeholder="품종(한글, 1~15글자)">
                        </div>
                    </div>
                    <div id="dog-gender-area" class="area-div">
                        <p class="subtitle">성별</p>
                        <div id="dog-gender-input" class="input-div">
                            <select name="dog-gender" id="dog-gender">
                                <option value="" disabled selected>성별</option>
                                <option value="M">수컷</option>
                                <option value="F">암컷</option>
                                <option value="T">중성화</option>
                            </select>
                        </div>
                    </div>
                    <div id="dog-age-area" class="area-div">
                        <p class="subtitle">나이(세)</p>
                        <div id="dog-age-input" class="input-div">
                            <input type="text" name="dog-age" id="dog-age" placeholder="나이(숫자)">
                        </div>
                    </div>
                    <div id="dog-weight-area" class="area-div">
                        <p class="subtitle">몸무게(kg)</p>
                        <div id="dog-weight-input" class="input-div">
                            <input type="text" name="dog-weight" id="dog-weight" placeholder="몸무게(소수점 첫째자리까지 입력)">
                        </div>
                        <span class="dog-weight-error-msg error-msg"></span>
                    </div>
                    <div id="dog-and-user-submit" class="submitBtn">
                        <input type="button" class="joinBtn" value="수정" onclick="getPost('02')">
                    </div>
                </div>
                
                <% } %>
            </form>
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
</body>
</html>