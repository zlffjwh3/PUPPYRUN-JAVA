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
        <title>ë¡ê·¸ì¸</title>
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
                            <input type="text" name="userId" id="userId" placeholder="ìì´ë">
                        </div>
                        <div id="pw-area">
                            <input type="password" name="userPwd" id="userPwd" placeholder="ë¹ë°ë²í¸">
                        </div>
                        <div id="login-check-box">
                            <input type="checkbox" name="loginCheck" id="loginCheck">
                            <label for="loginCheck">ë¡ê·¸ì¸ ìí ì ì§</label>
                        </div>
                        <div id="login-btn">
                            <input type="submit" value="ë¡ê·¸ì¸">
                        </div>
                    </form>
                </div>
                <div id="join-find-id-pwd">
                    <a href="/html/id-pw-inquiry.html">ìì´ë Â· ë¹ë°ë²í¸ ì°¾ê¸°</a>
                    <a href="#">íìê°ì</a>
                </div>
            </div>
            <div id="pwd-container"></div>
        </div>
    </body>
</html>