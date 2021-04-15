$(document).ready(function() {
	$('#file-update').click(function() {
		$('#file-update-box').css('display','none');
		$('#file-update-box2').css('display','inline-block');
		var communityNum = $('#community-num').val();
		var url = '/community/update?comNo=' + communityNum + '&photoCheck=Y';
		$('#write-check').attr('action', url);
	});
});