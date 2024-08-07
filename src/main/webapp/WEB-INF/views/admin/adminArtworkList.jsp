<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.team4.artgallery.dto.enums.ArtworkCategory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <title>관리자 :: 예술품 관리</title>
        <link rel="stylesheet" href="<c:url value="/static/stylesheet/admin.css"/>">
        <script src="<c:url value="/static/script/admin.js"/>"></script>
    </jsp:attribute>

    <jsp:attribute name="content">

<%@ include file="/WEB-INF/views/admin/sub_menu.jsp" %>
<section class="admin-list">
    <form name="adminForm" method="get" action="<c:url value="/admin/artwork"/>">
        <div class="admin-list-btn">
            <!-- 기능 버튼 -->
            <div class="admin-list-func-btn">
                <input type="button" value="등록" onclick="location.href = '/artwork/write'">
                <input type="button" value="수정" onclick="updateSelected('/artwork/write?aseq=')">
                <input type="button" value="삭제" onclick="deleteSelected('/api/artworks/')">
            </div>

            <!-- 검색 기능 -->
            <div class="admin-list-search">
                <label><input type="text" placeholder="작품명 또는 작가명을 입력하세요" name="keyword"
                              value="${filter.keyword}"></label>
                <input type="submit">
            </div>
        </div>
        <ul class="admin-list-header admin-member-list">
            <li>
                <label><input type="checkbox" onclick="checkAll()" class="select-all-box"></label>
            </li>
            <li>
                <label for="displayyn"></label>
                <select name="displayyn" id="displayyn" class="admin-select" onchange="this.form.submit();">
                    <option value="">전체</option>
                    <option value="Y" <c:if test="${'Y'.equals(filter.displayyn)}">selected</c:if>>공개</option>
                    <option value="N" <c:if test="${'N'.equals(filter.displayyn)}">selected</c:if>>비공개</option>
                </select>
            </li>
            <li>
                <label for="category"></label>
                <select name="category" id="category" class="admin-select" onchange="this.form.submit();">
                    <c:forEach items="${ArtworkCategory.values()}" var="c">
                        <%--@elvariable id="c" type="com.team4.artgallery.dto.enums.ArtworkCategory"--%>
                        <option value="${c.value}"
                                <c:if test="${c.isEquals(filter.category)}">selected</c:if>>${c.korName}</option>
                    </c:forEach>
                </select>
            </li>
            <li>작품명</li>
            <li>작가명</li>
            <li>제작연도</li>
            <li>재료</li>
            <li>규격</li>
            <li>등록일</li>
            <li>미리보기</li>
        </ul>
        <c:forEach items="${artworkList}" var="artworkEntity" varStatus="status">
            <ul
                    class="admin-list-main admin-artwork-list"
                    onclick="checkChildCheckbox(this)"
                    data-seq="${artworkEntity.aseq}"
            >
                <li>
                    <label><input type="checkbox"></label>
                </li>
                <li>${artworkEntity.display ? 'Y' : 'N'}</li>
                <li>${artworkEntity.aseq}</li>
                <li>${artworkEntity.category}</li>
                <li class="view-link"><a
                        href="<c:url value="/artwork/${artworkEntity.aseq}"/>">${artworkEntity.name}</a></li>
                <li>${artworkEntity.artist}</li>
                <li>${artworkEntity.year}</li>
                <li>${artworkEntity.material}</li>
                <li>${artworkEntity.size}</li>
                <li>${artworkEntity.indate}</li>
                <li>
                    <img alt="artwork-img" src="${artworkEntity.imageSrc}" onclick="previewImage(this)">
                </li>
            </ul>
        </c:forEach>
    </form>
    <t:pagination/>
</section>

    </jsp:attribute>
</t:layout>
