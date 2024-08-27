<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inventory System</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom CSS -->
  <link rel="stylesheet" href="css/styles.css">
  <link rel="icon" type="image/x-icon" href="images/favicon.ico">
</head>
<body>
  <!-- Header -->
  <jsp:include page="pages/header.jsp"></jsp:include>
  
  <!-- Main Content -->
  <div class="container my-4">
    <div class="row">
      <div class="col-md-3">
        <!-- Side Menu -->
        <div id="divMenu" class="list-group">
          <!-- Side menu content here -->
        </div>
      </div>
      <div class="col-md-12">
        <!-- Main Content Area -->
        <div id="divContent">
          <!--<jsp:include page="pages/login.jsp"></jsp:include>-->
          <jsp:include page="pages/dispatching.jsp"></jsp:include>
          <%-- <jsp:include page="pages/inventory.jsp"></jsp:include> --%>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Footer -->
  <footer class="bg-light py-3">
    <div class="container">
      <jsp:include page="pages/footer.jsp"></jsp:include>
    </div>
  </footer>

  <!-- jQuery and Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="js/common.js"></script>
</body>
</html>
