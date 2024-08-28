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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link href="Tabulator-dist/css/tabulator.min.css" rel="stylesheet">
  <script src="Tabulator-dist/js/tabulator.min.js"></script>
</head>
<body>
  <div id="divHeader"></div>
  <div id="divMenu"></div>
  <div id="divContent" class="container-fluid">
    <jsp:include page="pages/rawMaterialList.jsp"></jsp:include>
  </div>
</body>
</html>