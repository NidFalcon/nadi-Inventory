<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- third party CDN's -->
  <link rel="icon" type="image/x-icon" href="images/icon.png">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link href="https://unpkg.com/tabulator-tables@6.2.5/dist/css/tabulator.min.css" rel="stylesheet">
  <script type="text/javascript" src="https://unpkg.com/tabulator-tables@6.2.5/dist/js/tabulator.min.js"></script>
  <script type="text/javascript" src="https://oss.sheetjs.com/sheetjs/xlsx.full.min.js"></script>
<!-- -->
	<meta charset="ISO-8859-1">
	<title>Inventory System</title>
	<script src="js/jquery-3.7.1.min.js"></script>
	<script src="js/common.js"></script>
	<script src="js/main.js"></script>
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/table.css">
</head>
<body>
	<div id="divMenu"></div>
	<div id="divAlert"></div>
	<div id="divContent" class="container-fluid my-3 divContent">
		<jsp:include page="pages/login.jsp"></jsp:include>
  	</div>
</body>
</html>