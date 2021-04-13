<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/assets/css/reset.css">
        <link rel="stylesheet" href="/assets/css/login.css">
        <title>로그인</title>
    </head>
    <body>
        <div id="wrap">
            <div id="id-container">
                <div id="header">
                    <a href="/index.html"><img src="/assets/img/main_logo.png" alt=""></a>
                </div>
                <div id="content">
                    <form action="/user/login" method="post">
                        <div id="id-area">
                            <input type="text" name="userId" id="userId" placeholder="아이디">
                        </div>
                        <div id="pw-area">
                            <input type="password" name="userPw" id="userPwd" placeholder="비밀번호">
                        </div>
                        <div id="login-check-box">
                            <input type="checkbox" name="loginCheck" id="loginCheck" checked>
                            <label for="loginCheck">로그인 상태 유지</label>
                        </div>
                        <div id="login-btn">
                            <input type="submit" value="로그인">
                        </div>
                    </form>
                </div>
                <div id="join-find-id-pwd">
                    <a href="/id-pw-inquiry.jsp">아이디 · 비밀번호 찾기</a>
                    <a href="/join.jsp">회원가입</a>
                </div>
            </div>
            <div id="pwd-container"></div>
        </div>
    </body>
</html>