<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "com.spring.springboard2.BoardVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
</head>
<%

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try {
		BoardVO article = (BoardVO)request.getAttribute("article");
			
%>
		<body>
			<center>
				<b> 글 내용 보기 </b><br>
				<form>
					<table width="555" height="500" border="1" cellspacing="0" cellpadding="0" align="center" style="table-layout: fixed">
							<tr height="30" align="center" width="125">
								<td> 글 번호 </td>
								<td> ${number } </td>
								<td> 조회수 </td>
								<td width="180" ><%=article.getReadcount() %></td>
							</tr>
							<tr height="30" align="center" width="125">
								<td> 작성자 </td>
								<td><%=article.getWriter() %></td>
								<td> 작성일 </td>
								<td width="180"><%=sdf.format(article.getReg_date()) %></td>
							</tr>
							<tr height="30">
								<td align="center" width="125"> 글제목 </td>
								<td align="left" colspan="3" > &nbsp;<%=article.getSubject() %> </td>
							</tr>
							<tr>
								<td align="center"> 글내용 </td>
								<td align="left" colspan="3" style="word-break:break-all;"><%=article.getContent() %></td>
							</tr>
							<tr height="30">
								<td colspan="4" align="center">
									<input type="button" value="글수정" onclick="document.location.href='updateform.do?num=<%=article.getNum() %>&pageNum=${pageNum }'">
										&nbsp;&nbsp;
									<input type="button" value="글삭제" onclick="document.location.href='deleteform.do?num=<%=article.getNum() %>&pageNum=${pageNum }'">
										&nbsp;&nbsp;
									<input type="button" value="글목록" onclick="document.location.href='./list.do?pageNum=${pageNum }'">
								</td>
							</tr>
					</table>
			 	</form>
	</body>
<%
	} catch(Exception ex) {}
%>
</html>