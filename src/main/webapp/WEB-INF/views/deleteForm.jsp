<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
	<script language="JavaScript">
		function deletSave() {
			if(document.delForm.passwd.value == '') {
				alert("비밀번호를 입력하십시오.");
				document.delForm.passwd.focus();
				retuen false;
			}
			return true;
		}
	</script>
</head>
<body>
	<center>
		<b> 글삭제 </b><br>
		<form method="post" name="delForm" action="delete.do" onsubmit="return deleteSave()">
		<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
			<tr height="30">
				<td align="center" >
					<b> 비밀번호를 입력해 주세요. </b>
				</td>
			</tr>
			<tr height="30">
				<td align="center"> 비밀번호 :
					<input type="password" name="passwd" size="8" maxlength="12">
					<input type="hidden" name="num" value=<%=num %>>
					<input type="hidden" name="pageNum" value=<%=pageNum %>>
				</td>
			</tr>
			<tr height="30">
				<td align="center" > 
					<input type="submit" value="글삭제">
					<input type="button" value="글목록" onclick="document.location.href='./list.do?pageNum=<%=pageNum %>'">
				</td>
			</tr>			
		</table>
		</form>

</body>
</html>