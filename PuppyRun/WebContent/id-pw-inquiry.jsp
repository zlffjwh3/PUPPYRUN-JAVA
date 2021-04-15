<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = (String)request.getAttribute("userId");
	String userName = (String)request.getAttribute("userName");
	String userPwd = (String)request.getAttribute("userPwd");
	String pUserId = (String)request.getAttribute("PuserId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/id-pw-inquiry.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/reset.css">
    <!-- 스크립트 -->
    
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/assets/js/id-pw-inquiry.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- 파비콘 이미지 가져오기 -->
   	<link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
   	<link rel="icon" href="/assets/img/Favicon/favicon.ico">
    <title>회원 정보 찾기</title>
</head>
<body>
<!-- 테스트 -->
    <div id="wrap">
            <div id="header">
                <a href="/index.jsp"><img src="<%= request.getContextPath() %>/assets/img/main_logo.png" alt=""></a>
            </div>
            <div id="content">
                <div id="utilTab">
                    <div id="find-id">
                        <a href="#">
                            아이디 찾기
                        </a>
                    </div>
                    <div id="find-pwd">
                        <a href="#">
                            비밀번호 찾기
                        </a>
                    </div>
                    <!-- https://www.lotteon.com/p/member/find/findId 참고 -->
                </div>
                <div id="email-group">
                    <h2>이메일 정보로 아이디 찾기</h2>
                    <span>회원가입시 등록한 정보로 찾을 수 있습니다</span>
                    <p></p>
                    <div id="email-form">
                    <% if(userId == null) { %>
                        <form action="/user/findId" method="POST" class="findIdForm">
                            <div id="e-username-area">
                                <input type="text" name="e-user-name" id="e-user-name" placeholder="이름">
                            </div>
                            <div id="e-useremail-area">
                                <input type="email" name="e-user-email" id="e-user-email" placeholder="이메일">
                            </div>
                            <div id="email-submit">
                                <input type="submit" value="확인">
                            </div>
                        </form>
                    <% } %>
                    </div>
                    
                    <% if(userId != null && userId != "") { %>
                    <!-- 아이디 찾기 결과페이지 -->
                    <div class="result-form">
                    	<br><br>
                    	<h4 class="result1"><%= userName %>님의 아이디 :</h4><br>
                    	<h3 class="result2"><%= userId %></h3>
                    	<br><br>
                    	<button onclick="goLogin()" class="go-login-btn">로그인</button>
                    	<br>
                    </div>
                    <% } %>
                    
                </div>
            </div>
            <div id="pwd-group">
                <h2>이메일 정보로 비밀번호 찾기</h2>
                <span>회원가입시 등록한 정보로 찾을 수 있습니다</span>
                <p></p>
                <div id="pwd-form">
                <% if(userPwd == null) { %>
                    <form action="/user/findPwd" method="POST">
                        <div id="p-username-area">
                            <input type="text" name="p-user-name" id="p-user-name" placeholder="이름">
                        </div>
                        <div id="p-userid-area">
                            <input type="text" name="p-user-id" id="p-user-id" placeholder="아이디">
                        </div>
                        <div id="p-useremail-area">
                            <input type="email" name="p-user-email" id="p-user-email" placeholder="이메일">
                        </div>
                        <div id="pwd-submit">
                            <input type="submit" value="확인">
                        </div>
                    </form>
				<% } %>
				<% if(userPwd != null && userPwd != "") { %>
				<!-- 해당부분 찌부러짐있음 수정필요함 -->
				<script>$('#email-group').css('display','none');$('#pwd-group').css('display','block');$('#find-pwd > a').css('background','rgb(240,163,0)');$('#find-id > a').css('background','#FEB415');</script>
					<div class="result-form">
					<br><br>
                   	<h4 class="result1"><%= pUserId  %>님의 비밀번호 : </h4><br>
                   	<h3 class="result2"><%= userPwd %></h3>
                   	<br><br>
                    	<button onclick="goLogin()" class="go-login-btn">로그인</button>
                    <br><br><br>
					</div>
				<% } %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>