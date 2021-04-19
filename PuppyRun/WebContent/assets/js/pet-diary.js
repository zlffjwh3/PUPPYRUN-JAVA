var goalCheckFlag = false;
	           			
$(document).ready(function() {
	var goalExp = /^[0-9]*$/;
	
	$('#goal-input2').on('keyup', function() {
        if(!goalExp.test($('#goal-input2').val())) {
         	$('#goal-input2').css('border', '1px solid #FF0000');
            goalCheckFlag = false;
        } else {
         	$('#goal-input2').css('border', '1px solid silver');
            goalCheckFlag = true;
        }
    });

	$('#dis-check').off('click').on('click', function() {
		var walkTime = $('#goal-input2').val();
		var distance = walkTime * 67;
		$('#goal-input1').val(distance);
	})
	
	$('.goal-btn').click(function() {
		$('#goal-box').css('display','block');
		$('#goal-date').val(goalDate);
		event.stopPropagation();
		event.cancelBubble = true;
	});
	
	$('#goal-close').click(function() {
		$('#goal-box').css('display', 'none');
	});
	
	$('#goal-write').click(function() {
		if(goalCheckFlag) {
			$('#goal-submit').submit();
		}
	});
});