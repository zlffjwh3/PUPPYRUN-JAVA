$(document).ready(function() {
	$('#submit-input').click(function() {
		if($('#title-input').val() == null || $('#title-input').val() == "") {
			alert('제목을 입력해주세요!');
			$('#title-input').focus();
		} else {
			$('#write-check').submit();
		}
	});
});