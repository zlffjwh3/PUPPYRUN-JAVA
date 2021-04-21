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
	
		$('.user-search-btn1').click(function() {
			if($('#user-search').val() == null || $('#user-search').val() == "") {
			alert('제목을 입력해주세요!');
			$('#user-search').focus();
		} else {
			$('.user-search-btn1').submit();
		}
	});
	
	/*input[type=checkbox]*/
	$('#userDelete').click(function() {
		var change = $('input[type=checkbox]:checked').val();
		var url = "/user/delete?userId=" + change;
		
		$('#uDelete').attr('action', url);
		$('#userDelete').submit();
	})
	
});