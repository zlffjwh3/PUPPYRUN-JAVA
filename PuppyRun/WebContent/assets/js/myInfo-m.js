$(document).ready(function() {
	
	$('#user').click(function() {
		$('#user-list').css('display', 'block');
		$('#prun-story-list').css('display', 'none');
		$('#matching-list').css('display', 'none');
	});
	
	$('#post').click(function() {
		$('#user-list').css('display', 'none');
		$('#prun-story-list').css('display', 'block');
		$('#matching-list').css('display', 'none');
	});
	
	$('.content-kind-puppy').change(function(){
		var selectUser = $(this).val();
		var puppyrunStory = $('#prun-story-list');
		var matching = $('#matching-list');
			matching.css("display", "none");
			
		if(selectUser == "puppy-story") {
			matching.css("display", "none");
			puppyrunStory.css("display", "block");
			$('.puppy1').attr('selected','selected');
			
		}else if(selectUser == "puppy-mate") {
			matching.css("display", "block");
			puppyrunStory.css("display", "none");
			$('.match2').attr('selected','selected');
		}
		
	});
	
		$('.content-kind-match').change(function(){
		var selectUser = $(this).val();
		var puppyrunStory = $('#prun-story-list');
		var matching = $('#matching-list');
			puppyrunStory.css("display", "none");
			
		if(selectUser == "puppy-story") {
			matching.css("display", "none");
			puppyrunStory.css("display", "block");
			$('.puppy1').attr('selected','selected');
		}else if(selectUser == "puppy-mate") {
			matching.css("display", "block");
			puppyrunStory.css("display", "none");
			$('.match2').attr('selected','selected');
		}
		
	});
	
	
});