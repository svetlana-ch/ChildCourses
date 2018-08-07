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
				<h4>LEARN MORE ABOUT US</h4>
				<p>WE ARE COOL PEOPLE</p>
				</div>
			</div><!-- row -->
		</div><!-- container -->
	</div><!--  bluewrap -->


	<div class="container w">
		<div class="row centered">
			<br><br>
			<div class="col-lg-3">
				<img class="img-circle" src="assets/img/pic.jpg" width="110" height="110" alt="">
				<h4>Frank Lampard</h4>
				<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				<p><a href="#">@Frank_BlackTie</a></p>
			</div><!-- col-lg-3 -->

			<div class="col-lg-3">
				<img class="img-circle" src="assets/img/pic2.jpg" width="110" height="110" alt="">
				<h4>David Wright</h4>
				<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				<p><a href="#">@David_BlackTie</a></p>
			</div><!-- col-lg-3 -->

			<div class="col-lg-3">
				<img class="img-circle" src="assets/img/pic3.jpg" width="110" height="110" alt="">
				<h4>Mark Milestone</h4>
				<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				<p><a href="#">@Mark_BlackTie</a></p>
			</div><!-- col-lg-3 -->

			<div class="col-lg-3">
				<img class="img-circle" src="assets/img/pic4.jpg" width="110" height="110" alt="">
				<h4>Tania Tissen</h4>
				<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				<p><a href="#">@Tania_BlackTie</a></p>
			</div><!-- col-lg-3 -->

		</div><!-- row -->
		<br>
		<br>
	</div><!-- container -->



  	
  	<jsp:include page="include/footer.jsp"></jsp:include>
  	 	
  	
  	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>