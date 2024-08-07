<%--@elvariable id="qnaEntity" type="com.team4.artgallery.entity.QnaEntity"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <title>고객센터 :: <c:choose>
            <c:when test="${empty qnaEntity}">문의 작성</c:when>
            <c:otherwise>${qnaEntity.qseq}번 문의 수정</c:otherwise>
        </c:choose></title>
        <link rel="stylesheet" href="<c:url value="/static/stylesheet/qna/qna_icon.css"/>">
        <link rel="stylesheet" href="<c:url value="/static/stylesheet/qna/qna_write.css"/>">
        <script src="<c:url value="/static/script/qna.js"/>"></script>
        <script src="<c:url value="/static/script/qna/qna_write.js"/>"></script>
    </jsp:attribute>

    <jsp:attribute name="content">

<main class="qna-form">
    <div class="qna-form_header">
        <div class="qna-form_header_title">
            <h1>
                <c:choose>
                    <c:when test="${empty qnaEntity}">새로운 문의 작성</c:when>
                    <c:otherwise>${qnaEntity.qseq}번 문의 수정</c:otherwise>
                </c:choose>
            </h1>
        </div>
    </div>
    <form id="qna-form" class="qna-form_body" method="post" data-qseq="${qnaEntity.qseq}">
        <div class="qna-form_input-wrapper">
            <i class="qna-icon title"></i>
            <input type="text" name="title" id="title"
                   maxlength="100" autocomplete="off" value="${qnaEntity.title}" placeholder=" " required/>
            <label for="title">문의 제목</label>
        </div>
        <div class="qna-form_input-wrapper">
            <input type="hidden" name="displayyn" value="N"/>
            <input type="checkbox" name="displayyn" id="displayyn" value="Y"
                   <c:if test="${qnaEntity.display}">checked</c:if> />
            <label for="displayyn">전체 공개 여부</label>
        </div>
        <div class="qna-form_input-wrapper">
            <i class="qna-icon pwd"></i>
            <input type="password" name="pwd" id="pwd"
                   maxlength="45" autocomplete="off" value="${qnaEntity.pwd}" placeholder=" " required/>
            <label for="pwd">글 비밀번호</label>
        </div>
        <div class="qna-form_input-wrapper">
            <i class="qna-icon email"></i>
            <input type="email" name="email" id="email"
                   maxlength="45" value="${qnaEntity.email}" placeholder=" " required/>
            <label for="email">작성자 이메일</label>
        </div>
        <div class="qna-form_input-wrapper">
            <i class="qna-icon phone"></i>
            <input type="tel" name="phone" id="phone"
                   maxlength="45" value="${qnaEntity.phone}" placeholder=" " required/>
            <label for="phone">작성자 휴대폰</label>
        </div>
        <div class="qna-form_input-wrapper">
            <i class="qna-icon content"></i>
            <textarea name="content" id="content" oninput="this.parentNode.dataset.textareaInput = this.value"
                      placeholder=" " required>${qnaEntity.content}</textarea>
            <label for="content">문의 내용</label>
        </div>
        <div class="qna-form_button-wrapper">
            <input id="submit-btn" class="qna-form_button" type="submit" value="${empty qnaEntity ? '등록' : '수정'}"/>
            <label for="submit-btn">
                <i class="qna-icon upload"></i>
            </label>
        </div>
        <div class="qna-form_button-wrapper">
            <input id="cancel-btn" class="qna-form_button " type="button" value="취소" onclick="history.back();"/>
            <label for="cancel-btn">
                <i class="qna-icon cancel"></i>
            </label>
        </div>
    </form>
</main>

    </jsp:attribute>
</t:layout>
