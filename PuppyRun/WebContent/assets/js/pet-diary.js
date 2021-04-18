var goalCheckFlag1 = false;
	           			var goalCheckFlag2 = false;
	           			
$(document).ready(function() {
	var goalExp = /^[0-9]*$/;
	
	$('#goal-input1').on('keyup', function() {
        if(!goalExp.test($('#goal-input1').val())) {
         	$('#goal-input1').css('border', '1px solid #FF0000');
            goalCheckFlag1 = false;
        } else {
         	$('#goal-input1').css('border', '1px solid #B0B0B0');
            goalCheckFlag1 = true;
        }
    });
	
	$('#goal-input2').on('keyup', function() {
        if(!goalExp.test($('#goal-input2').val())) {
         	$('#goal-input2').css('border', '1px solid #FF0000');
            goalCheckFlag2 = false;
        } else {
         	$('#goal-input2').css('border', '1px solid #B0B0B0');
            goalCheckFlag2 = true;
        }
    });
	
	$('.goal-btn').click(function() {
		$('#goal-box').css('display','block');
		event.stopPropagation();
		event.cancelBubble = true;
	});
	
	$('#goal-close').click(function() {
		$('#goal-box').css('display', 'none');
	});
	
	$('#goal-write').click(function() {
		if(goalCheckFlag1 && goalCheckFlag2) {
			$('#goal-submit').submit();
		}
	});
});