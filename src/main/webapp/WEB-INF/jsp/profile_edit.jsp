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
	
	
	<fmt:message bundle="${loc}" key="local.useredit.regform.error.text" var="messageLoginError" />
	
	<fmt:message bundle="${loc}" key="locale.registrationform.editprofile.text" var="editProfile" />
	<fmt:message bundle="${loc}" key="locale.registrationform.name.text" var="surnameName" />
	<fmt:message bundle="${loc}" key="locale.registrationform.login.text" var="login" />
	<fmt:message bundle="${loc}" key="locale.registrationform.password.text" var="password" />
	<fmt:message bundle="${loc}" key="locale.registrationform.button.editprofile.text" var="buttonEditProfile" />		
	<fmt:message bundle="${loc}" key="locale.registrationform.button.clear.text" var="buttonClear" />

	<fmt:message bundle="${loc}" key="locale.useredit.table.role.text" var="role" />

	
	<fmt:message bundle="${loc}" key="locale.useredit.deluser.text" var="delUser" />	
		


</head>
<body>


		<jsp:include page="include/header.jsp"></jsp:include>

		<br><br><br><br><br><br><br>


		<div class="form signup">

		<form action="Controller" method='post'>
			<fieldset>				
				<legend>${editProfile}</legend>
				
				<input type="hidden" name="command" value="profile_edit" />	
				<input type="hidden" name="id" value="${user.id}"  />
				<input type="hidden" name="role" value="${user.role}"  />		
					
				<div>
					<label for="Name">${surnameName}</label>
					<input type="text" name="name" value="${user.name}" />
				</div>
					
				<div>
					<label for="Login">${login}</label>
					<input type="text" name="login" value="${user.login}" />
				</div>
				
				<div>
					<label for="e-mail">E-mail</label>
					<input type="text" name="e-mail" value="${user.email}" />
				</div>
				
				<div>				
					<label for="Password">${password}</label>
					<input type="password" name="password" value="${user.password}" />
				</div>	
					
							
				<div><input type="submit" value="${buttonEditProfile}" /></div>
				<div><input type="reset" value="${buttonClear}"></div>          
				
			</fieldset>
		</form>
	</div>

	<jsp:include page="include/footer.jsp"></jsp:include>


			<!-- Bootstrap core JavaScript
    ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
			<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
			<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>	
