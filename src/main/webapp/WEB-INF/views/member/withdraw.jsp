<%--@elvariable id="loginMember" type="com.team4.artgallery.entity.MemberEntity"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <title>회원탈퇴</title>
        <script src="<c:url value="/static/script/member/withdraw_form.js"/>"></script>
    </jsp:attribute>

    <jsp:attribute name="content">

<section class="form-wrapper">
    <form id="withdraw-form" method="post">
        <img src="<c:url value="/static/image/ico_login_img.png"/>" alt="form-logo">
        <h2>회원탈퇴</h2>
        <div class="field">
            <label for="pwd">비밀번호</label>
            <input type="password" name="pwd" id="pwd" placeholder="비밀번호" required>
        </div>
        <div class="btn-container">
            <input type="submit" value="회원탈퇴">
            <input type="button" value="취소" onclick="history.back();">
        </div>
    </form>
</section>

    </jsp:attribute>
</t:layout>
