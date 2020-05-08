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
	<form method="post" action="board_updateOk.do">
		<c:set value="${dto }" var="dto"/>
		<table border="0" cellspacing="0">
			<tr>
				<th>글번호</th>
				<td><input type="hidden" value="${dto.getBoard_no() }" name="board_no">${dto.getBoard_no() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.getBoard_writer() }</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" value="${dto.getBoard_title() }" name="board_title"></td>
			</tr>
			<tr height="400">
				<th>글내용</th>
				<td><textarea rows="30" cols="100" value="board_cont" name="board_cont"></textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.getBoard_regdate().substring(0,16) }</td>
			</tr>
			<tr>
				<td colspan="2" cellspacing="right">
					<input type="submit" value="수정하기">
					<input type="reset" value="다시쓰기">
					<input type="button" value="목록으로" onclick="location.href='board_list.do'">
				</td>
			</tr>
			
		
		</table>
	</div>
	
	
</body>
</html>