$(document).ready(function() {
	$('#change-photo-btn').click(function () {
		if(confirm('기존 사진이 변경됩니다!')) {
			$('#change-photo').css('display','block');
			$('#change-photo-btn').css('display','none');
		}
	});
	
	$('#like').click(function() {
		$('#like').css('background-color','#F0A300');
		$('#post').css('background-color','#FFBD32');
		$('#comment').css('background-color', '#FFBD32');
		
		$('#like-list').css('display', 'block');
		$('#post-list').css('display', 'none');
		$('#post-list2').css('display', 'none');
		$('#comment-list').css('display', 'none');
	});
	
	$('#post').click(function() {
		$('#like').css('background-color','#FFBD32');
		$('#post').css('background-color','#F0A300');
		$('#comment').css('background-color', '#FFBD32');
		
		$('#like-list').css('display', 'none');
		$('#post-list').css('display', 'block');
		$('#post-list2').css('display', 'block');
		$('#comment-list').css('display', 'none');
	});
	
	$('#comment').click(function() {
		$('#like').css('background-color','#FFBD32');
		$('#post').css('background-color','#FFBD32');
		$('#comment').css('background-color', '#F0A300');
		
		$('#like-list').css('display', 'none');
		$('#post-list').css('display', 'none');
		$('#post-list2').css('display', 'none');
		$('#comment-list').css('display', 'block');
	});
});