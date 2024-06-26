function submitIdCheck(id) {
    ajax('/member/idCheck', {id}, 'POST')
        .then(defaultAjaxHandler)
        .then(({status, body}) => {
            // 결과 상태 코드가 200이면 id 필드에 id를 설정하고, 그렇지 않으면 빈 문자열을 설정
            // 메시지 출력은 defaultAjaxHandler 에서 처리
            document.getElementById('idCheck').value = status === 200 ? id : '';
        });
}

function searchPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }

            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== '' && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 address 필드에 넣는다.
            document.getElementById('address').value = roadAddr + extraRoadAddr;
        }
    }).open();
}