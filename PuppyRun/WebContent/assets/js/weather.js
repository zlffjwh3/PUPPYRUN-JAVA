$(document).ready(function(){
    let weatherIcon = {
        '01' : 'fas fa-sun fa-7x',
        '02' : 'fas fa-cloud-sun',
        '03' : 'fas fa-cloud fa-5x',
        '04' : 'fas cloud-meatball',
        '09' : 'fas cloud-sun-rain',
        '10' : 'fas cloud-showers-heavy',
        '11' : 'fas poo-storm',
        '13' : 'far snowflake',
        '50' : 'fas fa-smog'
    };

    $.ajax({
        url : 'http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=9f79b4b7b5bb7ec20b98862a5abafae2&units=metric',
        dataType : 'json',
        type : 'GET',
        success : function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp);
            var $city = data.name;

            $('.Currlcon').append('<i class="' + weatherIcon[$Icon] + '"></i>');
            $('.CurrTemp').prepend('<span>' + $Temp + '</span> <span>°C<sapn>');
        
            $('.City').append('<p>서울광역시</p>');
        }
    })
});