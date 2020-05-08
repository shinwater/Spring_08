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
	<form action="board_validationOk.do" method="post">
		<c:set value="${check_no }" var="check_no"/>
		<input type="hidden" value="${no }" name="board_no">
		<input type="hidden" value="${check_no }" name="chk_no">
		<input type="hidden" value="${check_pwd }" name="check_pwd">
		
		<h3>비밀번호 화긴</h3>
		<input type="password" name="board_pwd" required="required">
		
		
		<input type="submit" value="확인">	
	</form>
	</div>
</body>
</html>