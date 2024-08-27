<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Inventory System</title>
  <script src="js/jquery-3.7.1.min.js"></script>
  <script src="js/common.js"></script>
  <link rel="stylesheet" href="css/styles.css">
  <link rel="icon" type="image/x-icon" href="images/favicon.ico">
</head>
<body>
  <jsp:include page="pages/header.jsp"></jsp:include>
  <hr>
  <div id="divMenu"></div>
  <div id="divContent">
    <jsp:include page="pages/login.jsp"></jsp:include>
  </div>
  <hr>
  <jsp:include page="pages/footer.jsp"></jsp:include>
</body>
</html>