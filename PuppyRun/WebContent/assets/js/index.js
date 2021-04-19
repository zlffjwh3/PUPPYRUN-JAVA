function showPopup() {
	var popUp = document.getElementById("pop-up")
	
	if(popUp.style.display == 'none') {
		popUp.style.display = 'block';
	}else {
		popUp.style.display = 'none';
	}
	return false;
}