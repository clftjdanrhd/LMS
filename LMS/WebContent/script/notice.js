function noticeCheck() {
	if (document.frm.userName.value.length == 0) {
		alert("작성자를 입력하세요.");
		return false;
	}
	if (document.frm.noticePass.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if (document.frm.noticeTitle.value.length == 0) {
		alert("제목을 입력하세요.");
		return false;
	}
	return true;
}
function open_win(url, name) {
	window.open(url, name, "width=500, height=230");
}
function passCheck() {
	if (document.frm.noticePass.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}