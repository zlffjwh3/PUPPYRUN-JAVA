$(document).ready(function() {
	
	$('#user').click(function() {
		$('#user-list').css('display', 'block');
		$('#prun-story-list').css('display', 'none');
		$('#matching-list').css('display', 'none');
	});
	
	$('#post').click(function() {
		$('#user-list').css('display', 'none');
		$('#prun-story-list').css('display', 'block');
		$('#matching-list').css('display', 'block');
	});
	
	$('#Y').click(function() {
		$('#Y').css('display', 'block');
		$('#N').css('display', 'none');
	});
	$('#N').click(function() {
		$('#Y').css('display', 'none');
		$('#N').css('display', 'block');
	});
	
});