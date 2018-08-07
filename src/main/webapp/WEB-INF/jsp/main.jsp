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
  	
  	
  	
  	<div id="headerwrap">
		<div class="container">
			<div class="row centered">
				<div class="col-lg-8 col-lg-offset-2"></br></br></br>
				<h1>Слоган <b>номер один</b></h1>
				<h2>Слоган номер два</h2>
				</div>
			</div><!-- row -->
		</div><!-- container -->
	</div><!-- headerwrap -->
	

	<div class="container w">
		<div class="row centered">
			<h4>НОВОСТИ</h4>
			<br><br>
			<div class="col-lg-4">
				<i class="fa fa-heart"></i>
				<h4>DESIGN/ЗАГОЛОВОК</h4>
				<p>2018-07-07/ДАТА НОВОСТИ &nbsp;<b>Автор</b></p>				
				<p>There are many variations of passages of Lorem Ipsum available, 
				but the majority have suffered alteration in some form, by injected 
				humour, or randomised words which don't look even believable...<a>Подробнее</a></p>
				<p><a>#англ язык/ теги</a> <a>#олимпиада/ теги</a></p>
			</div><!-- col-lg-4 -->

			<div class="col-lg-4">
				<i class="fa fa-laptop"></i>
				<h4>BOOTSTRAP</h4>
				<p>2018-07-07/ДАТА НОВОСТИ &nbsp;<b>Автор</b></p>				
				<p>There are many variations of passages of Lorem Ipsum available, 
				but the majority have suffered alteration in some form, by injected 
				humour, or randomised words which don't look even believable...<a>Подробнее</a></p>
				<p><a>#англ язык/ теги</a> <a>#олимпиада/ теги</a></p>
			</div><!-- col-lg-4 -->

			<div class="col-lg-4">
				<i class="fa fa-trophy"></i>
				<h4>SUPPORT</h4>
				<p>2018-07-07/ДАТА НОВОСТИ &nbsp;<b>Автор</b></p>				
				<p>There are many variations of passages of Lorem Ipsum available, 
				but the majority have suffered alteration in some form, by injected 
				humour, or randomised words which don't look even believable...<a>Подробнее</a></p>
				<p><a>#англ язык/ теги</a> <a>#олимпиада/ теги</a></p>
			</div><!-- col-lg-4 -->
		</div><!-- row -->
		<br>
		<br>
	</div><!-- container -->


	<!-- SECTION -->
	<div id="dg">
		<div class="container">
			<div class="row centered">
				<h4>ЛУЧШИЕ УЧЕНИКИ</h4>
				<br>
				<div class="col-lg-4">
					<div class="tilt">
					<a href="#"><img src="assets/img/pic.jpg" alt=""></a>
					</div>
					<h4>Frank Lampard</h4>
					<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				</div>

				<div class="col-lg-4">
					<div class="tilt">
					<a href="#"><img src="assets/img/pic.jpg" alt=""></a>
					</div>
					<h4>Frank Lampard</h4>
					<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>
				</div>

				<div class="col-lg-4">
					<div class="tilt">
					<a href="#"><img src="assets/img/pic.jpg" alt=""></a>
					</div>
					<h4>Frank Lampard</h4>
					<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</p>					
				</div>
								
			</div><!-- row -->
		</div><!-- container -->
	</div><!-- DG -->


  	
  	
  	<jsp:include page="include/footer.jsp"></jsp:include>
  	 	
  	
  	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>
