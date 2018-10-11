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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/ico/favicon.png">

    <title>My Baby</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">



</head>
  <body>
    
  	<jsp:include page="include/header.jsp"></jsp:include>


	<div id="blue">
		<div class="container">
			<div class="row centered">
				<div class="col-lg-8 col-lg-offset-2">
				<h4>WE CAN DO A LOT OF THINGS</b></h4>
				<p>JUST TAKE A LOOK & CONTACT US</p>
				</div>
			</div><!-- row -->
		</div><!-- container -->
	</div><!-- blue wrap -->


	<div class="container w">
		<div class="row centered">
			<br><br>
			
			<c:forEach items="${subjects}" var="subject">
			<div class="col-lg-4">
				<i class="fa fa-desktop"></i>
				<h4>${subject.subjectName}</h4>				
				<h3>${subject.cost} рублей</h3>
				<h3>от ${subject.ageChildFrom}</h3>
				<h3>до ${subject.ageChildTo} лет</h3>
				<br><br>				
			</div><!-- col-lg-4 -->
			</c:forEach>			
		</div><!-- row -->
	</div><!-- container -->


  	<jsp:include page="include/footer.jsp"></jsp:include>
  	 	
  	
  	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>