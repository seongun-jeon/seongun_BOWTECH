<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.spring.springboard2.BoardVO" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
	int currentPage = (int)request.getAttribute("currentPage");
	int startRow = (int)request.getAttribute("startRow");	
	int endRow = (int)request.getAttribute("endRow");	
	int count = (int)request.getAttribute("count");
	int number = (int)request.getAttribute("number");
	int pageSize = (int)request.getAttribute("pageSize");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm");
	ArrayList<BoardVO> articleList = (ArrayList<BoardVO>)request.getAttribute("articleList");
%>

<script>
 function searchCheck(){
  var form = document.searchForm;
  if (form.word.value == '' || form.word.value == null){
   alert('검색어를 입력하세요.');
   form.word.focus();
   return false;
  } else {
   form.submit;
  }
 }
 </script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
</head> 

<body>
	<center><b> 글목록 </b>
		<table width="600">
			<tr>
				<td align="right">
					<a href="./writeform.do"> 글쓰기 </a>
				</td>
			</tr>
		</table>
<%
	if(count == 0) {	//글이 없을경우
%>		
		<table width="600" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center"> 게시판에 저장된 글이 없습니다. </td>
			</tr>
		</table>
<% 
	} else {	//출력할 글이 있을 경우
%>
		<table border="1" width="600" cellpadding="0" cellspacing="0" align="center">
			<tr height="30">
				<td align="center" width="50"> 번   호 </td>
				<td align="center" width="250"> 제   목 </td>
				<td align="center" width="100"> 작성자 </td>
				<td align="center" width="150"> 작성일 </td>
				<td align="center" width="50"> 조   회 </td>
			</tr>
<%
		for(int i = 0; i < articleList.size(); i++) {
			BoardVO article = (BoardVO)articleList.get(i);
%>		
			<tr height="30">
				<td align="center" width="50"> <%=number %></td>
				<td width="250">

							<a href="./content.do?num=<%=article.getNum() %>&pageNum=<%=currentPage %>
								&number=<%=number %>"> <%=article.getSubject() %> </a>

			    </td> 
			    <td align="center" width="100">	
			    	<a href="mailto:<%=article.getEmail() %>"> <%=article.getWriter() %> </a>  
			    </td>
			    <td align="center" width="150"> <%=sdf.format(article.getReg_date()) %> </td>
			    <td align="center" width="50"> <%=article.getReadcount() %> </td>
		   	</tr>
<%
		   		number--;
	   		}
%>
			</table>
			<br>
<%
		}

		if(count > 0) {
			int pageCount = ((count-1) / pageSize)+1;	//페이지 계산
			int startPage = 1;
			int i;
			
			if(currentPage%10 != 0) {	//페이지 블락 다음/이전 페이지
				startPage = (int) (currentPage/10)*10 + 1;		
			} else {
				startPage = currentPage - 9;
			}
			
			int pageBlock = 5; 
			
			if(startPage > 0) {
%>

				<a href="./list.do?pageNum=<%=startPage%>">[처음]</a>
				<a href="./list.do?pageNum=<%=startPage - 10 %>">[이전]</a>
<%
			}
			for(i = startPage; (i <= startPage + 9) && (i <= pageCount); i++) {
%>		
				<a href="./list.do?pageNum=<%=i %>">[<%=i %>]</a>
			 
<%
			}
			
		
%>
				<a href="./list.do?pageNum=<%=startPage + 10 %>">[다음]</a>
				<a href="./list.do?pageNum=<%=startPage + 10 %>">[마지막]</a>
<%
		
		}	
%>
		<form name="form1" method="post" action="list.do">

   		<select name="search_option">
   		
        	<option value="writer" 
        	<c:if test="${map.search_option == 'writer'}">selected</c:if>
        	>작성자</option>

      		<option value="subject" 
				<c:if test="${map.search_option == 'subject'}">selected</c:if>
      						 >제목</option>

      		<option value="content" 
				<c:if test="${map.search_option == 'content'}">selected</c:if>
        					>내용</option>

 
   		</select>
			    <input name="keyword" value="${map.keyword}">
			    <input type="submit" id="search" value="조회" onclick="searchCheck()">
	</form>

<script>
	
</script>
  
</body>
</html>