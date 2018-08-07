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

</head>
<body>

		<jsp:include page="include/header.jsp"></jsp:include>




		<br><br><br><br><br>


	<div class="form signup">

		<form action="Controller" method='post'>
			<fieldset>				
				<legend>Регистрация</legend>
				
				<input type="hidden" name="command" value="sign_up" />	
					
				<div>
					<label for="Name">Фамилия Имя</label>
					<input type="text" name="name" value="" />
				</div>
					
				<div>
					<label for="Login">Логин</label>
					<input type="text" name="login" value="" />
				</div>
				
				<div>
					<label for="e-mail">E-mail</label>
					<input type="text" name="e-mail" value="" />
				</div>
				
				<div>				
					<label for="Password">Пароль</label>
					<input type="password" name="password" value="" />
				</div>			
							
				<input type="submit" value="Зарегистрироваться" />
				
			</fieldset>
		</form>

  	<jsp:include page="include/footer.jsp"></jsp:include>
  	 	
  	
  	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
  </body>
</html>	
	
  
</body>
</html>