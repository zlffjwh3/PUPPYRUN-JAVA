function weight_choice(check) { // 무게계산

    var dogBirth = $('#dog_date').val();
    if(check == '0') {
        $('.dog_type0').addClass('cal_checked');
        $('.dog_type1').removeClass('cal_checked');
        $('.dog_type2').removeClass('cal_checked');
        return dogBirth * ($('.dog_type0').val());
    } else if(check == '1') {
        $('.dog_type1').addClass('cal_checked');
        $('.dog_type0').removeClass('cal_checked');
        $('.dog_type2').removeClass('cal_checked');
        return dogBirth + ($('.dog_type1').val());
        // cal_checked
    } else if(check == '2') {
        $('.dog_type2').addClass('cal_checked');
        $('.dog_type0').removeClass('cal_checked');
        $('.dog_type1').removeClass('cal_checked');
        return dogBirth + ($('.dog_type2').val());
    }
    
}