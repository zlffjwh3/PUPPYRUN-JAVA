function restartCalc(){
			location.href="age";
}

function typeChoice(typeVal) {
    $('#dogTypeVal').val(typeVal);
    
    if(typeVal == '1'){
        $('.dog_type0').addClass('cal_checked');
        $('.dog_type1').removeClass('cal_checked');
        $('.dog_type2').removeClass('cal_checked');
    } else if (typeVal == '2'){
        $('.dog_type1').addClass('cal_checked');
        $('.dog_type0').removeClass('cal_checked');
        $('.dog_type2').removeClass('cal_checked');
    } else if (typeVal == '3'){
        $('.dog_type2').addClass('cal_checked');
        $('.dog_type0').removeClass('cal_checked');
        $('.dog_type1').removeClass('cal_checked');
    } else {
        alert('잘못된 선택입니다.');
    }
}

function weight_choice() { // 무게계산
    var dogBirth = $('#dog_age_date').val();
    var y = dogBirth.substr(0, 4);
    var m = dogBirth.substr(4, 2);
    var d = dogBirth.substr(6, 2);
    
    var today = new Date();
    var sdt = new Date(y,m-1,d);
    var edt = today;
    var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24*30));
    var check = $('#dogTypeVal').val();
    var result_peopleAge;
    var result_puppyAge = Math.floor((dateDiff) / 12);
    if(check == '1') {
        if(dateDiff <= 24){
            result_peopleAge = dateDiff;
        }else {
            result_peopleAge = 24 + (Math.floor((dateDiff - 24) / 12)) * 4;
        }
    } else if(check == '2') {
        if(dateDiff <= 24){
            result_peopleAge = dateDiff;
        }else {
            result_peopleAge = 24 + (Math.floor((dateDiff - 24) / 12)) * 5;
        }
    } else if(check == '3') {
        if(dateDiff <= 24){
            result_peopleAge = dateDiff;
        }else {
            result_peopleAge = 24 + (Math.floor((dateDiff - 24) / 12)) * 6;
        }
    }
    
    $('#peopleAgeResultTxt').val(result_peopleAge);
    $('#puppyAgeResultTxt').val(result_puppyAge);
    
    // 계산식 보여주는 곳 
    $('.box-in-content').html('');
    var htmlRes = `
            <div id="cal_result_age">

            <div class="age_circle">
                <div class="age_img age_img1">
                    <img
                    src="/assets/img/dog-outline-chasing-balloons.png"
                    alt="강아지이미지">
                    <span class="age_num" id="dog_age">
                        `+result_puppyAge+`살
                    </span>
                </div>
            </div>
            
            
            <div class="age_circle">
                <div class="age_img age_img2">
                    <img
                    src="/assets/img/user.png"
                    alt="사람">
                    <span class="age_num" id="age_cal">
                        `+result_peopleAge+`살
                    </span>
                </div>
            </div>

            <div class="age_lifecycle" id="dog_life"></div>

            <div class="cal_btn">
                <input type="button" class="cal_result" onclick="restartCalc()" value="다시하기">
            </div>
        </div>
    `;
    $('.box-in-content').html(htmlRes);
}