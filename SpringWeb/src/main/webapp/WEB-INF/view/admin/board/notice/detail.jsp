<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<main>
	<h2 class="main title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">breadlet</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>
	
	<c:set var="n" value="${notice}" />

	<div class="margin-top first">
		<h3 class="hidden">공지사항 내용</h3>
		<table class="table">
			<tbody>
				<tr>
					<th>제목</th>
					<td class="text-align-left text-indent text-strong text-orange"
						colspan="3">${n.title}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td class="text-align-left text-indent" colspan="3">${n.regdate}
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${n.memberName}</td>
					<th>조회수</th>
					<td>${n.hit}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3"></td>
				</tr>
				<tr class="content">
					<td colspan="4">
						${n.content}
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="margin-top text-align-center">
		<a class="btn-text btn-cancel" href="list">목록</a> <a
			class="btn-text btn-default" href="edit.html">수정</a> <a
			class="btn-text btn-default" href="del.html">삭제</a>
	</div>

	<c:set var="prevNotice" value="${prevNotice}"/>
	<c:set var="nextNotice" value="${nextNotice}"/>

	<div class="margin-top">
		<table class="table border-top-default">
			<tbody>
				<tr>
					<th>다음글</th>
					<c:if test="${nextNotice.id != null}">
					<td colspan="3" class="text-align-left text-indent">
					<a href="detail?id=${nextNotice.id}">${nextNotice.title}</a>
					</td>
					</c:if>
					<c:if test="${empty nextNotice.id}">
					<td colspan="3" class="text-align-left text-indent">다음글이 없습니다.</td>
					</c:if>
				</tr>

				<tr>
					<th>이전글</th>
					<c:if test="${prevNotice.id != null}">
					<td colspan="3" class="text-align-left text-indent">
					<a class="text-blue text-strong" href="detail?id=${prevNotice.id}">${prevNotice.title}</a>
					</td>
					</c:if>
					<c:if test="${empty prevNotice.id}">
					<td colspan="3" class="text-align-left text-indent">이전글이 없습니다.
					</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
</main>