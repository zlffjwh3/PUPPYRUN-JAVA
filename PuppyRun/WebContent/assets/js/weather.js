
$(document).ready(function(){
    let weatherIcon = {
        '01' : 'fas fa-sun fa-7x', // 태양
        '02' : 'fas fa-sun fa-7x',
        '03' : 'fas fa-sun fa-7x',
        '04' : 'fas fa-sun fa-7x',
        '09' : 'fas fa-sun fa-7x',
        '10' : 'fas fa-sun fa-7x', // 비구름
        '11' : 'fas poo-storm',
        '13' : 'far snowflake',
        '50' : 'fas fa-smog'
    };

	let weatherBack = {
        '01' : 'weather-sunny', // 태양
        '02' : 'weather-sunny',
        '03' : 'weather-sunny',
        '04' : 'weather-sunny',
        '09' : 'weather-sunny',
        '10' : 'weather-sunny', // 비구름
        '11' : 'weather-sunny',
        '13' : 'weather-sunny',
        '50' : 'weather-sunny'
    };

/*weather-sunny*/
/*weather-cloudy*/
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
			$('#mWeather').css('background-image', 'url(/assets/img/' + weatherBack[$Icon] + '.jpg)');
        
            $('.City').append('<p>서울시</p>');
        }
    })
});