<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form method="post" action="board_writeOk.do">
			<table border="0" cellspacing="0">
			<tr>
				<td><input type="text" name="board_writer" placeholder="작성자를 입력하세요"> </td>
			</tr>
			<tr>
				<td><input type="text" name="board_title" placeholder="글제목을 입력하세요"> </td>
			</tr>
			<tr>
				<td>비밀번호<br/>
				<input type="password" name="board_pwd"> </td>
			</tr>
			<tr height="400">
				<td>글내용<br/>
					<textarea rows="30" cols="100" name="board_cont"></textarea>
				</td>
			</tr>
			<tr>
				<td align="right">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시쓰기"> 
				<input type="button" value="목록으로" onclick="location.href='board_list.do'"> 
				</td>
			</tr>
			
			</table>	
		</form>
	</div>
</body>
</html>