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
		
		$('#list-btn').css('display', 'block');
		$('#post-btn').css('display', 'none');
		$('#comment-btn').css('display', 'none');
	});
	
	$('#post').click(function() {
		$('#like').css('background-color','#FFBD32');
		$('#post').css('background-color','#F0A300');
		$('#comment').css('background-color', '#FFBD32');
		
		$('#like-list').css('display', 'none');
		$('#post-list').css('display', 'block');
		$('#post-list2').css('display', 'block');
		$('#comment-list').css('display', 'none');
		
		$('#like-btn').css('display', 'none');
		$('#post-btn').css('display', 'block');
		$('#comment-btn').css('display', 'none');
	});
	
	$('#comment').click(function() {
		$('#like').css('background-color','#FFBD32');
		$('#post').css('background-color','#FFBD32');
		$('#comment').css('background-color', '#F0A300');
		
		$('#like-list').css('display', 'none');
		$('#post-list').css('display', 'none');
		$('#post-list2').css('display', 'none');
		$('#comment-list').css('display', 'block');
		
		$('#like-btn').css('display', 'none');
		$('#post-btn').css('display', 'none');
		$('#comment-btn').css('display', 'block');
	});
	
	$('#like-btn').click(function() {
      var change = $('input[type=checkbox]:checked').val();
      var url = "/like/change?" + change;
	  alert(url);    

      $('#like-form').attr('action', url);
      $('#like-btn').submit();
   });

	$('#post-btn').click(function() {
      var select = $('input[type=checkbox]:checked').attr("value2");
      var change = $('input[type=checkbox]:checked').attr("value1");
      var url = '';
      if(select == 'C') {
        url = "/community/delete?comNo=" + change;
      } else {
        url = "/matching/delete?matNo=" + change;
      }
	  alert(url);
      
      $('#post-form').attr('action', url);
      $('#post-btn').submit();
   });

	$('#comment-btn').click(function() {
      var change = $('input[type=checkbox]:checked').val();
      var url = "/comment/delete?" + change;
	  alert(url);

      $('#comment-form').attr('action', url);
      $('#comment-btn').submit();
   })
});