<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<hr width="500" color="green">
	<h3>BOARD 테이블 전체 리스트</h3>
	<hr width="500" color="green">
		<table border="0" cellspacing="0" width="600">
		
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>글제목</th>
				<th>글내용</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:set value="${List }" var="list"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="List">
					<tr>
						<td>${List.getBoard_no() }</td>
						<td>${List.getBoard_writer() }</td>
						<td>${List.getBoard_title() }</td>
						<td>
							<a href="board_cont.do?no=${List.getBoard_no() }">
								<c:if test="${List.getBoard_cont().length() >=4 }">
									${List.getBoard_cont().substring(0,3) }...
								</c:if>
								<c:if test="${List.getBoard_cont().length() <4 }">
									${List.getBoard_cont() }
								</c:if>
							</a>
						</td>
						<td>${List.getBoard_regdate().substring(0,16) }</td>
						<td>${List.getBoard_hit() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="6" align="center">
						<h3>검색된 레코드가 업	ㅅㅅ븐디아.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="6" align="right">
					<input type="button" value="글쓰기" onclick="location.href='board_write.do'">
				</td>
			</tr>
		</table>
		<form method="post" action="board_search.do">
			<select name="search1">
				<option value="writer">글쓴이</option>
				<option value="title+cont">글 제목+내용</option>
			</select>
			
			<input type="text" name="search2" required="required">
			<input type="submit" value="검색">		
		</form>
	</div>
</body>
</html>