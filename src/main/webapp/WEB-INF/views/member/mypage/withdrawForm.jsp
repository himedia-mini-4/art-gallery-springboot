<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="stylesheet" value="/static/stylesheet/member/login_form.css"/>
</jsp:include>
<main class="login-form-wrapper">
    <form class="login-form" method="post" action="<c:url value="/member/withdraw"/>" onsubmit="ajaxSubmit(event)">
        <input type="hidden" name="returnUrl" value="${returnUrl}">
        <div class="login_icon_box">
            <div>
                <img src="<c:url value="/static/image/ico_login_img.png"/>" style="width: 80px; height: 80px;">
            </div>
            <h2>회원 탈퇴</h2>
        </div>
        <div class="field">
            <label>
                비밀번호
                <input name="pwd" type="password" placeholder="비밀번호" required>
            </label>
        </div>
        <div class="btn">
            <input type="submit" value="회원탈퇴">
            <input type="button" value="취소" onclick="history.back();">
        </div>
    </form>
</main>
<jsp:include page="/WEB-INF/views/footer.jsp"/>