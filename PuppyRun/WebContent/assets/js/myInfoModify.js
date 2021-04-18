// 전역변수

var userIdCheckFlag = false;
var userPwdCheckFlag = false;
var userPwdReCheckFlag = false;
var userNicknameCheckFlag = false;
var userNameCheckFlag = false;
var userPhoneCheckFlag = false;
var userEamilCheckFlag = false;
var userYearCheckFlag = false
var userMonthCheckFlag = false;
var userDayCheckFlag = false;
var dogNameCheckFlag = false;
var dogKindCheckFlag = false;
var dogGenderCheckFlag = false;
var dogAgeCheckFlag = false;
var dogWeightCheckFlag = false;

$(document).ready(function() {
	
	const petSelectYes = $('#pet-selectY');
	const petSelectNo = $('#pet-selectN');
	
	petSelectYes.change(function() {
		if(petSelectYes.is(":checked")) {
			$('#user-join-submit').css('display','none');
			$('#form2-area').css('display','block');
		}
	});
	
	petSelectNo.change(function() {
		if(petSelectNo.is(":checked")) {
			$('#user-join-submit').css('display','block');
			$('#form2-area').css('display','none');
		}
	});
	

	// 정규표현식
		var idExp = /^[a-zA-Z0-9]{5,11}$/;
        var pwdExp = /^[a-zA-Z0-9|!@#&]{8,}$/;
        var nicknameExp = /^[a-zA-Z0-9|가-힣]{2,8}$/;
        var nameExp = /^[가-힣]{2,6}$/;
        var phoneExp = /^01[0|1|6-9][\w]{3,4}[\w]{3,4}$/;
        var emailExp = /^[\w]{1,12}@[0-9a-zA-Z]{2,8}.[\w]{2,3}/i;
        var yearExp = /^19[0-9]{2,}$/;
        var dayExp = /^0[1-9]|1[0-9]|2[0-9]$/; //수정해야할듯
        var dogNameExp = /^[가-힣]{1,8}$/;
        var dogKindExp = /^[가-힣]{1,15}$/;
        var dogAgeExp = /^\d{1,3}$/;
        var dogWeightExp = /^[\d]{1,3}[.][\d]{1,}$/; //수정해야할듯


        var userId = $('#user-id');
        var userPwd = $('#user-pwd');
        var userPwdRe = $('#user-re-pwd');
        var userNicknaem = $('#user-nickname');
        var userName = $('#user-name');
        var userPhone = $('#user-phone');
        var userEmail = $('#user-email');
        var userYear = $('#user-birth-year');
        var userDay = $('#user-birth-day');
        var dogName = $('#dog-name');
        var dogKind = $('#dog-kind');
        var dogAge = $('#dog-age');
        var dogWeight = $('#dog-weight');

        var idErrorMsg = $('.id-error-msg');
        var pwdErrorMsg = $('.pwd-error-msg');
        var pwdReErrorMsg = $('.re-pwd-error-msg');
        var nickErrorMsg = $('.nickname-error-msg');
        var nameErrorMsg = $('.name-error-msg');
        var phoneErrorMsg = $('.phone-error-msg');
        var emailErrorMsg = $('.email-error-msg');
        var dogNameErrorMsg = $('.dog-name-error-msg');
        
       


        userId.on('keyup', function() {
            if(!idExp.test(userId.val())) {
                idErrorMsg.html('아이디는 영문 대소문자, 5~11자만 가능합니다');
                $('#id-input').css('border', '1px solid #FF0000');
                userIdCheckFlag = false;
            } else {
                idErrorMsg.html('');
                $('#id-input').css('border', '1px solid #B0B0B0');
                userIdCheckFlag = true;
            }
        });

        userPwd.on('keyup', function() {
            if(!pwdExp.test(userPwd.val())) {
                pwdErrorMsg.html('숫자, 영문, 특수문자(!,@,#,&) 조합 최소 8글자 이상이어야 합니다');
                $('#pwd-input').css('border', '1px solid #FF0000');
                userPwdCheckFlag = false;
            } else {
                pwdErrorMsg.html('');
                $('#pwd-input').css('border', '1px solid #B0B0B0');
                userPwdCheckFlag = true;
            }
        });

        userPwdRe.on('keyup', function() {
            if(userPwd.val() != userPwdRe.val()) {
                pwdReErrorMsg.html('입력한 비밀번호와 똑같이 입력해주세요');
                $('#pwd-re-input').css('border', '1px solid #FF0000');
                userPwdReCheckFlag = false;
            } else {
                pwdReErrorMsg.html('');
                $('#pwd-re-input').css('border', '1px solid #B0B0B0');
                userPwdReCheckFlag = true;
            }
        });

        userNicknaem.on('keyup', function() {
            if(!nicknameExp.test(userNicknaem.val())) {
                nickErrorMsg.html('닉네임은 최소 2글자 최대 8글자여야 합니다.');
                $('#nickname-input').css('border', '1px solid #FF0000');
                userNicknameCheckFlag = false;
            } else {
                nickErrorMsg.html('');
                $('#nickname-input').css('border', '1px solid #B0B0B0');
                userNicknameCheckFlag = true;
            }
        });

        userName.on('keyup', function() {
            if(!nameExp.test(userName.val())) {
                nameErrorMsg.html('이름은 한글 2~6글자만 가능합니다');
                $('#user-name').css('border', '1px solid #FF0000');
                userNameCheckFlag = false;
            } else {
                nameErrorMsg.html('');
                $('#user-name').css('border', '1px solid #B0B0B0');
                userNameCheckFlag = true;
            }
        });

        userPhone.on('keyup', function() {
            if(!phoneExp.test(userPhone.val())) {
                phoneErrorMsg.html('핸드폰 번호를 입력해주세요');
                $('#phone-input').css('border', '1px solid #FF0000');
                userPhoneCheckFlag = false;
            } else {
                phoneErrorMsg.html('');
                $('#phone-input').css('border', '1px solid #B0B0B0');
                userPhoneCheckFlag = true;
            }
        });
        
        userEmail.on('keyup', function() {
            if(!emailExp.test(userEmail.val())) {
                emailErrorMsg.html('');
                $('#email-input').css('border', '1px solid #FF0000');
                userEamilCheckFlag = false;
            } else {
                emailErrorMsg.html('');
                $('#email-input').css('border', '1px solid #B0B0B0');
                userEamilCheckFlag = true;
            }
        });

        userYear.on('keyup', function() {
            if(!yearExp.test(userYear.val())) {
                $('#year-input').css('border', '1px solid #FF0000');
                userYearCheckFlag = false;
            } else {
                $('#year-input').css('border', '1px solid #B0B0B0');
                userYearCheckFlag = true;
            }
        });
        
        userDay.on('keyup', function() {
            if(!dayExp.test(userDay.val())) {
                $('#day-input').css('border', '1px solid #FF0000');
                userDayCheckFlag = false;
            } else {
                $('#day-input').css('border', '1px solid #B0B0B0');
                userDayCheckFlag = true;
            }
        });


        

        // 반려견
        var dogSelectCheck = false;

        dogName.on('keyup', function() {
            if(!dogNameExp.test(dogName.val())) {
                $('#dog-name-input').css('border', '1px solid #FF0000');
                dogNameCheckFlag = false;
            } else {
                $('#dog-name-input').css('border', '1px solid #B0B0B0');
                dogNameCheckFlag = true;
            }
        });

        dogKind.on('keyup', function() {
            if(!dogKindExp.test(dogKind.val())) {
                $('#dog-kind-input').css('border', '1px solid #FF0000');
                dogKindCheckFlag = false;
            } else {
                $('#dog-kind-input').css('border', '1px solid #B0B0B0');
                dogKindCheckFlag = true;
            }
        });
        
        dogAge.on('keyup', function() {
            if(!dogAgeExp.test(dogAge.val())) {
                $('#dog-age-input').css('border', '1px solid #FF0000');
                dogAgeCheckFlag = false;
            } else {
                $('#dog-age-input').css('border', '1px solid #B0B0B0');
                dogAgeCheckFlag = true;
            }
        });

        dogWeight.on('keyup', function() {
            if(!dogWeightExp.test(dogWeight.val())) {
                $('#dog-weight-input').css('border', '1px solid #FF0000');
                dogWeightCheckFlag = false;
            } else {
                $('#dog-weight-input').css('border', '1px solid #B0B0B0');
                dogWeightCheckFlag = true;
            }
        });
		
		
		
	
	
})

	function getPost(mode) {
		var frm = document.frm;
		var email = $('#user_email');
		var userId = $('#user-id');
		var idNum = userId.val();
		
        if(mode== "01") { // 반려견 없음
			/*if(userIdCheckFlag == false) {
                return;
            }
            if(userPwdCheckFlag == false) {
                return;
            }
            if(userPwdReCheckFlag == false) {
                return;
            }
            if(userNicknameCheckFlag == false) {
                return;
            }
            if(userNameCheckFlag == false) {
                return;
            }
            if(userPhoneCheckFlag == false) {
                return;
            }
            if(userEamilCheckFlag == false) {
                return;
            }
            if(userYearCheckFlag == false) {
                return;
            }
            if($('#user-birth-month').val() == null) {
                return;
            }
            if(userDayCheckFlag == false) {
                return;
            }
            if($('#zip').val() == '' || $('#addr1').val() == '' || $('#addr2').val() == '') {
                return;
            }*/
	
            frm.method = "post";
            frm.action = '/user/modify?userId =' + idNum;
		}   else if (mode == "02") {
				/*if(userIdCheckFlag == false) {
                	return;
	            }
	            if(userPwdCheckFlag == false) {
	                return;
	            }
	            if(userPwdReCheckFlag == false) {
	                return;
	            }
	            if(userNicknameCheckFlag == false) {
	                return;
	            }
	            if(userNameCheckFlag == false) {
	                return;
	            }
	            if(userPhoneCheckFlag == false) {
	                return;
	            }
	            if(userEamilCheckFlag == false) {
	                return;
	            }
	            if(userYearCheckFlag == false) {
	                return;
	            }
	            if($('#user-birth-month').val() == null) {
	                return;
	            }
	            if(userDayCheckFlag == false) {
	                return;
	            }
	            if($('#zip').val() == '' || $('#addr1').val() == '' || $('#addr2').val() == '') {
	                return;
	            }
				// 강아지
				if(dogNameCheckFlag == false) {
					return;
				}
				if(dogKindCheckFlag == false) {
					return;
				}
				if($('#dog-gender').val() == null) {
					return;
				}
				if(dogAgeCheckFlag == false) {
					return;
				}
				if(dogWeightCheckFlag == false) {
					return;
				}*/
			
			frm.method = "post";
            frm.action = '/user/modify?userId =' + idNum;
		}
            frm.submit(); // 실행
    }

		// 주소검색 api
	function openZipSearch() {
		new daum.Postcode({
			oncomplete: function(data) {
				$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
				$('[name=addr1]').val(data.address);
				$('[name=addr2]').val(data.buildingName);
			}
		}).open();
	}