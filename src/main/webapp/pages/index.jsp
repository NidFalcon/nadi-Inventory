<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" 
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" 
		crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/js/index.js"></script>
		<script src="${pageContext.request.contextPath}/js/login.js" defer></script>
</head>
<body>
	<h1>Hello World</h1>
	<p>${pageContext.request.contextPath}</p>
	<div id='content'>
		<jsp:include page="login.jsp"/>
	</div>
</body>
</html>