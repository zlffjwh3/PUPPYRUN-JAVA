/**
 * 
 */
window.onload = function() {
	document.getElementById("btn-confirm").addEventListener("click", function() {
		var result = window.confirm("정말 삭제하시겠습니까?");
		if(result == true) {
			/*var element = document.createElement("input");
			element.setAttribute("type", "hidden");
			element.setAttribute("name", "delete-confirm");*/
			
		}
	})
}