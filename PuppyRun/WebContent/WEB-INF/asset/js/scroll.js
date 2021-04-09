$(document).ready( function() {
    // 처음에는 안보이게 숨기기
    // $(".top").hide();
    //  스크롤 탑 + fadein 효과 
    // $(window).scroll(function(){
    //   if(	$(this).scrollTop() > 200){	
    //     $(".top").fadeIn();	
    //   }
    //   else{	
    //     $(".top").fadeOut();	
    //   }			
    // });
    //클릭했을 때 스르륵 올라가도록 애니메이션 효과
    $(".top").click(function(){
        $('body,html').animate({
        scrollTop:0 
        },400 );
        return false;
    });
});
