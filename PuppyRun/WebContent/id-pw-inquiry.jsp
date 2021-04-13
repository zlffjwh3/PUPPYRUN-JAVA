<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/id-pw-inquiry.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/reset.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/assets/js/id-pw-inquiry.js"></script>
    <title>회원 정보 찾기</title>
</head>
<body>
<!-- 테스트 -->
    <div id="wrap">
            <div id="header">
                <a href="#"><img src="<%= request.getContextPath() %>/assets/img/main_logo.png" alt=""></a>
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
                    <h2>이메일 정보로 아이디 찾기   </h2>
                    <p></p>
                    <span>회원가입시 등록한 정보로 찾을 수 있습니다</span>
                    <div id="email-form">
                        <form action="/user/findId" method="post">
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
                    </div>
                </div>
            </div>
            <div id="pwd-group">
                <h2>이메일 정보로 비밀번호 찾기</h2>
                <p></p>
                <span>회원가입시 등록한 정보로 찾을 수 있습니다</span>
                <div id="pwd-form">
                    <form action="" method="">
                        <div id="p-username-area">
                            <input type="text" name="p-user-name" id="p-user-name" placeholder="이름">
                        </div>
                        <div id="p-userid-area">
                            <input type="email" name="p-user-id" id="p-user-id" placeholder="아이디">
                        </div>
                        <div id="p-useremail-area">
                            <input type="email" name="p-user-email" id="p-user-email" placeholder="이메일">
                        </div>
                        <div id="pwd-submit">
                            <input type="submit" value="확인">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>