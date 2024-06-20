function qnaPwdCheck(qseq, mode, pwd) {
    ajax(
        {command: 'qnaPwdCheck', qseq, mode, pwd},
        defaultAjaxHandler.then(function (status, response) {
            var pwd = prompt(qseq + "번 QnA 글의 비밀번호를 입력하세요:");
            if (pwd !== null && pwd !== undefined && pwd !== "") {
                qnaPwdCheck(qseq, mode, pwd);
            } else {
                alert("비밀번호 입력이 취소되었습니다.");
            }
            return false;
        }, 401)
    );
}