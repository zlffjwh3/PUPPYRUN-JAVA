
$(document).ready(function(){
    // $("div").css("color", "red");
    // $("#wrap").css('background','pink');
    
    const findPwd = $("#find-pwd>a");
    const findId = $("#find-id>a");

    $("#find-pwd>a").click(function(){
        $("#email-group").css('display','none');
        $("#pwd-group").css('display', 'block');
        findPwd.css('background-color','#F0A300');
        findId.css('background-color','#FEB415');
    });
    $("#find-id>a").click(function(){
        $("#pwd-group").css('display','none');
        $("#email-group").css('display', 'block');
        findId.css('background-color','#F0A300');
        findPwd.css('background-color','#FEB415');
    });
	
});

// 로그인페이지로 이동하는 펑션만들어서 링크달기
function goLogin() {
	location.href = "/login.jsp";
}