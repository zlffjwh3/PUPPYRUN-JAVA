function restartCalc(){
    location.href="age?calc_type=2";
}

function calorieResult() {
    var dogWeight = $('#dog_weight').val();
    if(dogWeight == null || dogWeight == '' || dogWeight.length == 0){
        alert('강아지의 몸무게를 입력해주세요! :-)');
        return;
    }
    
    var selectedVal = $('#dog_jisu option:selected').val();
    var rerVal = Math.ceil(70 * Math.pow(dogWeight, 0.75));
    var result = '';
    /* 
    <option value="1">생후 4개월 미만 반려견</option>
     <option value="2">중성화한 반려견</option>
     <option value="3">중성화하지 않은 반려견</option>
     <option value="4">비만 상태인 성견</option>
     <option value="5">활동량이 많은 성견</option>
     <option value="6">활동량이 많지 않은 성견</option>
     */
    if(selectedVal == '1'){
        result = rerVal * 3;
    } else if(selectedVal == '2'){
        result = rerVal * 1.6;
    } else if(selectedVal == '3'){                	
        result = rerVal * 1.8;
    } else if(selectedVal == '4'){
        result = rerVal * 1.3;
    } else if(selectedVal == '5'){
        result = rerVal * 2;
    } else if(selectedVal == '6'){
        result = rerVal;
    }
     
    $('#calResultValue').val(result);
    
    // 이 부분이 결과창 부분 
    $('.box-in-content').html('');
    var htmlRes = `
    <div class="cal_result_cal">
                    <div class="cal_circle">
                        <div class="result-img-box result-img-box1">
                            <div class="cal_img"></div>
                            <dl>
                                <dt>1일 기초 대사량</dt>
                                <dd id ="basic_meta">`+rerVal+`kcal</dd>
                            </dl>
                        </div>
                        <!-- </div> -->

                        <div class="result-img-box result-img-box2">
                            <div class="cal_img"></div>
                            <dl>
                                <dt>1일 권장 칼로리</dt>
                                <dd id="basic_kal">`+result+`kcal</dd>
                            </dl>
                        </div>
                        
                        <div class="cal_txt">
                            <span class="txt_b">
                                ※ 기초대사량
                            </span>
                            <span class="txt_c">
                                : 생물체가 생명을 유지하는데 필요한 최소한의 에너지량
                            </span>
                            <br>
                                
                            
                            <span class="txt_b">※ 권장칼로리</span> 
                            <span class="txt_c">
                                : 몸을 움직이는 데 필요한 에너지인 작업 대사량을 위한 칼로리
                            </span>
            
                        </div>
                        <!-- <div class="age_lifecycle" id="dog_life"></div> -->
                    
                    </div>

                    <div class="cal_btn">
                        <input type="button" class="cal_result" onclick="restartCalc()" value="다시하기">
                    </div>


                </div>
`;
$('.box-in-content').html(htmlRes);
}