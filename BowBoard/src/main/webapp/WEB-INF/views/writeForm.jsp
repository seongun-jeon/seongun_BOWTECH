<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
</head>
<body>
<center>
<%
	int num = 0, ref = 1, re_step = 0, re_level = 0; 

	try {
		if(request.getParameter("num") != null)	{	
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}
%>
		<body>
			<b> 글쓰기 </b><br>
			<form method="post" name="writeform" action="./write.do">
				<input type="hidden" name="num" value="<%=num %>">
				<input type="hidden" name="ref" value="<%=ref %>">
				<input type="hidden" name="re_step" value="<%=re_step %>">
				<input type="hidden" name="re_level" value="<%=re_level %>">
				
				<table width="400" border="1" cellspacing="0" cellpadding="0" 
					align="center">
					<tr>
						<td align="right" colspan="2" >
							<a href="./list.do"> 글목록 </a>
						</td>
					</tr>
					<tr>
						<td width="70"  align="center" > 이   름  </td>
						<td width="330"><input type="text" size="10"  name="writer"required="required" maxlength="5" ></td>
						
					</tr>
					<tr>
						<td width="70"  align="center"> 제   목 </td>
						<td width="330">
								<input type="text" size="40"  name="subject"required="required" maxlength="10" placeholder="10글자 이내로 가능합니다." >

						</td>
					</tr>
					 
					<tr>
						<td width="70"  align="center"> 내   용 </td>
						<td width="330"><textarea name="content" rows="13" cols="40"required="required" maxlength="300" placeholder="300자 이내로 작성 가능합니다."></textarea></td>
					</tr>
					<tr>
						<td width="70"  align="center"> 비밀번호 </td>
						<td width="330"><input type="password" size="8" maxlength="6" name="passwd"required="required"></td>
					</tr>
					<tr>
						<td colspan="2"  align="center">
							<input type="submit" value="글쓰기">
							<input type="reset" value="다시작성">
							<input type="button" value="목록보기" Onclick="javascript:history.go(-1)">
						</td>
					</tr>
				</table>
			</form>
		</body>
<%
	} catch(Exception ex) {}
%>
</html>