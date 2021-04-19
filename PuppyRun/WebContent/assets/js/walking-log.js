$(document).ready(function() {
	$('.chart').easyPieChart({
	    barColor: '#FEB415',  //차트가 그려질 색
	    trackColor: '#ccc',  // 차트가 그려지는 트랙의 기본 배경색(chart1 의 회색부분)
	    scaleColor: '#fff', // 차트 테두리에 그려지는 기준선 (chart2	의 테두리 선)
	    lineCap: 'round', // 차트 선의 모양 chart1 butt / chart2 round / chart3 square
	    lineWidth: 15, // 차트 선의 두께
	    size: 200, // 차트크기
	    animate: 1000, // 그려지는 시간 
	    onStart: $.noop,
	    onStop: $.noop
	  });
});