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
		<c:set value="${dto }" var="dto"/>
		<table border="0" cellspacing="0">
			<tr>
				<th>글번호</th>
				<td>${dto.getBoard_no() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.getBoard_writer() }</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td>${dto.getBoard_title() }</td>
			</tr>
			<tr height="400">
				<th>글내용</th>
				<td>${dto.getBoard_cont() }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.getBoard_hit()+1 }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.getBoard_regdate().substring(0,16) }</td>
			</tr>
			<tr>
				<td colspan="2" cellspacing="right">
					<input type="button" value="수정" onclick="location.href='board_validation.do?no=${dto.getBoard_no()}&check_no=1'">
					<input type="button" value="삭제" onclick="location.href='board_validation.do?no=${dto.getBoard_no()}&check_no=2'">
					<input type="button" value="목록으로" onclick="location.href='board_list.do'">
				</td>
			</tr>
			
		
		</table>
	</div>

</body>
</html>