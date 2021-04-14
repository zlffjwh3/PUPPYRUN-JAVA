$(document).ready(function() {
	$('#file-update').click(function() {
		$('#file-update-box').css('display','none');
		$('#file-update-box2').css('display','inline-block');
		var noticeNum = $('#notice-num').val();
		var url = '/notice/update?noticeNo=' + noticeNum + '&photoCheck=Y';
		$('#write-check').attr('action', url);
	});
});