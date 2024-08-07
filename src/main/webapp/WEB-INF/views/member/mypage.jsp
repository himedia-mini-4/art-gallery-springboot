<%--@elvariable id="loginMember" type="com.team4.artgallery.entity.MemberEntity"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <title>마이페이지 :: 메인</title>
        <link rel="stylesheet" href="<c:url value="/static/stylesheet/member/mypage.css"/>">
    </jsp:attribute>

    <jsp:attribute name="content">

<main class="mypage-info-container">
    <h1 class="mypage_title">마이 페이지</h1>
    <div class="mypage_info_list">
        <div class="mypage_info_wrapper">
            <div class="mypage_info_label">이름</div>
            <div class="mypage_info_data">${loginMember.name}</div>
        </div>
        <div class="mypage_info_wrapper">
            <div class="mypage_info_label">아이디</div>
            <div class="mypage_info_data">${loginMember.id}</div>
        </div>
        <div class="mypage_info_wrapper">
            <div class="mypage_info_label">연락처</div>
            <div class="mypage_info_data">${loginMember.phone}</div>
        </div>
        <div class="mypage_info_wrapper">
            <div class="mypage_info_label">이메일</div>
            <div class="mypage_info_data">${loginMember.email}</div>
        </div>
    </div>
    <div class="mypage_button_wrapper">
        <a class="mypage_button" href="<c:url value="/member/update"/>"> 개인정보수정 </a>
        <a class="mypage_button" href="<c:url value="/member/withdraw"/>"> 회원탈퇴 </a>
        <a class="mypage_button" href="<c:url value="/favorite"/>"> 관심예술품 </a>
    </div>
</main>

    </jsp:attribute>
</t:layout>