<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main class="main">
	<h2 class="main title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">경로</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>

	<div class="search-form margin-top first align-right">
		<h3 class="hidden">공지사항 검색폼</h3>
		<form class="table-form">
			<fieldset>
				<legend class="hidden">공지사항 검색 필드</legend>
				<label class="hidden">검색분류</label> <select name="f">
					<option ${(param.f=="title")?selected:''} value="title">제목</option>
					<option ${(param.f=="memberName")?selected:''} value="memberName">작성자</option>
				</select> 
				<label class="hidden">검색어</label> 
				<input type="text" name="q" value="${param.q}" /> 
				<input class="btn btn-search" type="submit" value="검색" />
			</fieldset>
		</form>
	</div>
	<form action="pubAll" method="post">
		<div class="notice margin-top">
			<h3 class="hidden">공지사항 목록</h3>
			<table class="table">
				<thead>
					<tr>
						<th class="w60">번호</th>
						<th class="expand">제목</th>
						<th class="w100">작성자</th>
						<th class="w100">작성일</th>
						<th class="w60">조회수</th>
						<th class="w40">공개</th>
						<th class="w40">비공개</th>
						<th class="w40">삭제</th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach var="n" items="${list}">
					<c:set var="open" value=""/>
					<c:set var="close" value=""/>
					<c:if test="${n.pub}">
						<c:set var="open" value="checked"/>
					</c:if>
					<c:if test="${n.pub == false}">
						<c:set var="close" value="checked"/>
					</c:if>
					
						<tr>
							<td>${n.id }</td>
							<td class="title indent text-align-left"><a
								href="detail?id=${n.id}">${n.title }</a></td>
							<td>${n.memberName }</td>
							<td>${n.regdate }</td>
							<td>${n.hit }</td>
							<td><input type="checkbox" name="open-id" ${open} value="${n.id}"></td>
							<td><input type="checkbox" name="close-id" ${close} value="${n.id}"></td>
							<td><input type="checkbox" name="del-id" value="${n.id}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
		<c:set var="page" value="${(param.p == null)?1:param.p}" />
		<c:set var="startNum" value="${page-(page-1)%5}" />
		<c:set var="lastNum"
			value="${fn:substringBefore(Math.ceil(count/10), '.')}" />
			
		<div class="indexer margin-top align-right">
			<h3 class="hidden">현재 페이지</h3>
			<div>
				<span class="text-orange text-strong">${(empty param.p)?1:page}</span> / ${lastNum} pages
			</div>
		</div>
		
			
		<div class="text-align-right margin-top">
			<input type="submit" class="btn-text btn-default" name="cmd" value="선택공개">
			<input type="submit" class="btn-text btn-default" name="cmd" value="선택비공개">
			<input type="submit" class="btn-text btn-default" name="cmd" value="선택삭제">
			<a class="btn-text btn-default" href="reg">글쓰기</a>
		</div>
	</form>
	<div class="margin-top align-center pager">

		<div>
			
			<c:if test="${startNum > 1 }">
				<a class="btn btn-prev" href="?p=${startNum -1}&f=${param.f}&q=${param.q}">이전</a>
			</c:if>
			<c:if test="${startNum <=1 }">
				<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
			</c:if>

		</div>
			<ul class="-list- center">
				<c:forEach var="i" begin="0" end="4">
					<c:if test="${startNum+i <= lastNum}">
						<li><a class="-text- ${(page == (startNum+i))?'orange':''} bold" href="?p=${startNum + i}&f=${param.f}&q=${param.q}">${startNum + i}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		<div>

			<c:if test="${startNum + 4 < lastNum}">
				<a class="btn btn-prev" href="?p=${startNum+5}&f=${param.f}&q=${param.q}">다음</a>
			</c:if>
			<c:if test="${startNum + 4 >= lastNum}">
				<span class="btn btn-prev" onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>

		</div>

	</div>
</main>