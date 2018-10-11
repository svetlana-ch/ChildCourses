<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="assets/ico/favicon.png">

    <title>My Baby</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">        

    <!-- Custom styles for this template -->      
   <link rel="stylesheet" href="css/admin_header.css" type="text/css" />
    <link href="assets/css/main.css" rel="stylesheet">
    
    
    <fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc" />	
	
	<fmt:message bundle="${loc}" key="local.error.title.text" var="messageLoginError" />
	<fmt:message bundle="${loc}" key="local.error.massage" var="messageLoginError" />

</head>

<body>

	<jsp:include page="../include/header.jsp"></jsp:include>

	<br><br><br><br><br>      
	           
            <h3><fmt:message key="${requestScope.errorAccessMessage}"/></h3> 
             <h3><fmt:message key="${requestScope.errorMessage}"/></h3>        


	<jsp:include page="../include/footer.jsp"></jsp:include>

			<!-- Bootstrap core JavaScript
    ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
			<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
			<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>	
