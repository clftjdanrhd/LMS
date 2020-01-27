function loginCheck() {
	if (document.frm.userno.value.length == 0) {
		alert("아이디를 써주세요");
		frm.userno.focus();
		return false;
	}
	if (document.frm.userpw.value == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.userpw.focus();
		return false;
	}
	return true;
}

function idCheck() {
	if (document.frm.userno.value == "") {
		alert('아이디를 입력하여 주십시오.');
		document.formm.userno.focus();
		return;
	}
	var url = "idCheck.do?userno=" + document.frm.userno.value;
	window.open(url, "_blank_1",
					"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok(userno) {
	opener.frm.userno.value = document.frm.userno.value;
	opener.frm.reid.value = document.frm.userno.value;
	self.close();
}

function joinCheck() {
	if (document.frm.name.value.length == 0) {
		alert("이름을 써주세요.");
		frm.name.focus();
		return false;
	}
	if (document.frm.userno.value.length == 0) {
		alert("아이디를 써주세요");
		frm.userno.focus();
		return false;
	}
	if (document.frm.userno.value.length < 4) {
		alert("아이디는 4글자이상이어야 합니다.");
		frm.userno.focus();
		return false;
	}
	if (document.frm.userpw.value == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.userpw.focus();
		return false;
	}
	if (document.frm.userpw.value != document.frm.userpw_check.value) {
		alert("암호가 일치하지 않습니다.");
		frm.userpw.focus();
		return false;
	}
	if (document.frm.reid.value.length == 0) {
		alert("중복 체크를 하지 않았습니다.");
		frm.userno.focus();
		return false;
	}
	return true;
}