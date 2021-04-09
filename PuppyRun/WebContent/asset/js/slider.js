$(document).ready(function () {
    $('.pop').slideDown(500);
    $('.control li')
        .first()
        .addClass('on')
    $('.control li').each(function (index) {
        $(this).attr('aa', index)
    });
    $('.control li').click(function () {
        $('.control li').removeClass('on');
        $(this).addClass('on');
        var i = $(this).attr('aa');
        move(i);
    });
    function move(i) {
        var n = -(i * 400);
        $('.image').animate({top: n});
        num = i
    }

    var num = 0
    var dir = 'next'
    $('.btn .xi-angle-right-thin').click(function () {
        dir = 'next'
        num++
        if (num > 3) {
            num = 0;
        }
        $('.control li')
            .eq(num)
            .trigger('click')
    });
    $('.btn .xi-angle-left-thin').click(function () {
        dir = 'prev'
        num--
        if (num < 0) {
            num = 3;
        }
        $('.control li')
            .eq(num)
            .trigger('click')
    });
    setInterval(function () {
        $('.xi-angle-right-thin').trigger('click')
    }, 3000);
});