<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <title>관리자 :: 갤러리 관리</title>
        <link rel="stylesheet" href="<c:url value="/static/stylesheet/admin.css"/>">
        <script src="<c:url value="/static/script/admin.js"/>"></script>
    </jsp:attribute>

    <jsp:attribute name="content">

<%@ include file="/WEB-INF/views/admin/sub_menu.jsp" %>
<section class="admin-list">
    <form method="post" name="adminForm">
        <div class="admin-list-btn">
            <input type="hidden" name="memberIds">

            <!-- 기능 버튼 -->
            <div class="admin-list-func-btn">
                <input type="button" value="삭제" onclick="deletePost('adminDeleteGallery', 'li:nth-child(2)')">
            </div>

            <!-- 검색 기능 -->
            <div class="admin-list-search">
                <input type="text" placeholder="ID 또는 이름으로 검색하세요" name="searchWord" value="${searchWord}">
                <input type="button" value="검색" onclick="searchAdmin('adminGalleryList')">
            </div>
        </div>
        <ul class="admin-list-header admin-artwork-list">
            <li>
                <input type="checkbox" onclick="checkAll()" class="select-all-box">
            </li>
            <li>번호</li>
            <li>ID</li>
            <li>이름</li>
            <li>제목</li>
            <li>내용</li>
            <li>등록일</li>
            <li>조회수</li>
            <li>미리보기</li>
        </ul>
        <c:forEach items="${galleryList}" var="galleryDto" varStatus="status">
            <c:set var="previewId" value="preview-${galleryDto.gseq}-${status.index}"/>
            <ul class="admin-list-main admin-artwork-list" onclick="go_check(event)">
                <li><input type="checkbox" class="check-box"></li>
                <li>${galleryDto.gseq}</li>
                <li>${galleryDto.authorId}</li>
                <li>${galleryDto.authorName}</li>
                <li class="view-link" onclick="location.href='museum.do?command=galleryView&gseq=${galleryDto.gseq}'">
                        ${galleryDto.title}
                </li>
                <li>${galleryDto.content}</li>
                <li>${galleryDto.writedate}</li>
                <li>${galleryDto.readcount}</li>
                <li>
                    <img alt="artwork-img" src="static/image/gallery/${galleryDto.savefilename}"
                         onmouseover="previewImg('${previewId}')"
                         onmouseleave="previewImg('${previewId}')">
                </li>
            </ul>
            <div id="${previewId}" class="preview hidden">
                <img alt="artwork-img" src="static/image/gallery/${galleryDto.savefilename}">
            </div>
        </c:forEach>
    </form>
    <t:pagination/>
</section>

    </jsp:attribute>
</t:layout>
