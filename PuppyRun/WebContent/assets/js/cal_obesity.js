function restartCalc(){
	location.href="age?calc_type=3";
}

function bcs_sel(selectedVal){
    $('#bcs_sel_0_img li').removeClass('bcs_on');
    $('#bcs_sel_0_img li:nth-child('+(selectedVal+1)+')').addClass('bcs_on');
    $('#obsesitySelectedVal').val(selectedVal);
}

function obesityResult() {
    // html 태그
    
    var selectedVal = $('#obsesitySelectedVal').val();
    var num = 0;
    var mid_content = null;
    if(selectedVal == null || selectedVal == '' || selectedVal.length == 0){
        alert('BCS를 선택해주세요.');
        return;
    }
    if(selectedVal == '0'){
        num = `1`;
        mid_content = `갈비뼈, 척추뼈, 골반뼈가 두드러지게 보입니다.<br>체지방이 없어 보이고,<br>몸 전체적으로 근육이 없어 보입니다.`;

    } else if (selectedVal == '1'){
        num = `2`;
        mid_content = `갈비뼈, 척추뼈, 골반뼈가 두드러지게 보입니다.<br>만져지는 지방이 거의 없고,<br> 갈비뼈가 불거진 것이 느껴집니다.<br>
        몸 전체적으로 근육 손식이 느껴집니다.`;
        
    } else if (selectedVal == '2'){
        num = `3`;
        mid_content = `갈비뼈, 척추뼈, 골반뼈가 밖에서 보이며 쉽게 만져집니다.<br>갈비뼈를 만졌을 때 만져지는 지방이 거의 없습니다.<br>옆에서 보면 허리가 매우 잘록하게 들어가 있고,<br>앞에서 보면 복부가 매우 잘록해 보입니다.`;
        
    } else if (selectedVal == '3'){
        num = `4`;
        mid_content = `갈비뼈 아래로 약간의 피하지방이 느껴집니다.<br>지방 아래로 갈비뼈가 쉽게 만져집니다.<br>위에서 보면 허리를 쉽게 알아차릴 수 있습니다.<br>옆에서 보면 복부가 뚜렷한 곡선을 그리며 올라갑니다.`;
        
    } else if (selectedVal == '4'){
        num = `5`;
        mid_content = `갈비뼈 아래에 약간의 지방이 있는 것이 느껴집니다.<br>갈비뼈가 쉽게 만져집니다.<br>위에서 보면 갈비뼈 뒤쪽으로 허리선이 잘 보입니다.<br>옆에서 보면 복부가 부드러운 곡선을 그리며 올라갑니다.`;
        
    } else if (selectedVal == '5'){
        num = `6`;
        mid_content = `손에 힘을 주지 않아도 갈비뼈가 만져집니다.<br>갈비뼈 아래에서 지방을 느낄 수 있습니다.<br>위에서 보면 허리선이 명확하게 보이지 않습니다.<br>옆에서 보면 복부의 곡선이 살짝 보입니다.`;
    
    } else if (selectedVal == '6'){
        num = `7`;
        mid_content = `척추와 꼬리 부분에 많은 지방이 있어 보입니다.<br>갈비뼈 아래의 지방이 두꺼워 힘을 주어야 뼈가 만져집니다.<br>위에서 보면 허리가 잘 구분되지 않습니다.<br>옆에서 보면 복부의 굴곡이 보이지 않습니다.`;
    
    } else if (selectedVal == '7'){
        num = `8`;
        mid_content = `척추와 꼬리 부분에 매우 많은 지방이 있어 보입니다.<br>갈비뼈 아래의 지방이 매우 두꺼워 큰 힘을 주어야 뼈가 만져집니다.<br>위에서 보면 허리를 구분하기 어렵습니다.<br>옆에서 보면 복부 굴곡이 보이지 않습니다.`;
    
    } else if (selectedVal == '8'){
        num = `9`;
        mid_content = `흉부, 척추, 꼬리, 다리, 목 부분에 지방이 매우 많아 살이 접힙니다.<br>피하지방이 몸 전체에 많아서, 갈비뼈를 찾기 어렵습니다.<br>위에서 보면 허리가 없거나 바깥으로 튀어 나와 있습니다.<br>옆에서 보면 복부가 아래로 쳐져 있습니다.`;
        
    }
    var htmlRes = `<div class="cal_result_cal">
                            <div class="cal_txt">
                                <p>`+num+`단계</p>
                                <span>
                                    `+mid_content+`
                                </span>
                            </div>
                        </div>
                        <div class="cal_btn">
                            <input type="button" class="cal_result" onclick="restartCalc()" value="다시하기">
                        </div>
                    </div> `;
    $('#calcu_box').removeClass('calcu_box');
    $('#calcu_box').addClass('result_calcu_box');
    $('.box-in-content').html('');
    $('.box-in-content').html(htmlRes);
}