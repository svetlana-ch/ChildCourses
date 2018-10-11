<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc" />
	
	<fmt:message bundle="${loc}" key="locale.registrationform.registration.text" var="registration" />
	<fmt:message bundle="${loc}" key="locale.registrationform.name.text" var="surnameName" />
	<fmt:message bundle="${loc}" key="locale.registrationform.login.text" var="login" />
	<fmt:message bundle="${loc}" key="locale.registrationform.password.text" var="password" />
	<fmt:message bundle="${loc}" key="locale.registrationform.repeatpassword.text" var="repeatPassword" />
	<fmt:message bundle="${loc}" key="locale.registrationform.button.registration.text" var="buttonRegistration" />		
	<fmt:message bundle="${loc}" key="locale.registrationform.button.clear.text" var="buttonClear" />
	
	<fmt:message bundle="${loc}" key="locale.registrationform.notfree.text" var="notFree" />
	<fmt:message bundle="${loc}" key="locale.registrationform.invalidlogin.text" var="invalidLogin" />		
	<fmt:message bundle="${loc}" key="locale.registrationform.invalidpassword.text" var="invalidPassword" />
	<fmt:message bundle="${loc}" key="locale.registrationform.invalidname.text" var="invalidName" />		
	<fmt:message bundle="${loc}" key="locale.registrationform.invalidemail.text" var="invalidEmail" />
	<fmt:message bundle="${loc}" key="locale.registrationform.placeholdername.text" var="placeholderName" />
	<fmt:message bundle="${loc}" key="locale.registrationform.placeholderlogin.text" var="placeholderLogin" />
	<fmt:message bundle="${loc}" key="locale.registrationform.placeholderpassword.text" var="placeholderPassword" />
	
	
	
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



		<br><br><br><br><br><br><br>
		
		  
	<div class="form signup">

		<form class="registerForm" action="Controller" method='post' >
			<fieldset>				
				<legend>${registration}</legend>
				
				<input type="hidden" name="command" value="sign_up" />	
					
				<div>
					<label for="Name">${surnameName}</label>
					<input type="text" name="name"  />
					<span style="color: #ff0000;">
					 	<c:if	test="${requestScope.errorMessage.contains('Invalid_name')}">
							<c:out value="${invalidName}" />
						</c:if>
					</span>					
				</div>					
					
				<div>
					<label for="Login">${login}</label>					
					<input type="text" name="login"  required />
					<span style="color: #ff0000;">
					 	<c:if	test="${requestScope.errorMessage.contains('Login_is_not_free')}">
							<c:out value="${notFree}" />
						</c:if>
					</span>
					<span style="color: #ff0000;">
					 	<c:if	test="${requestScope.errorMessage.contains('Invalid_login')}">
							<c:out value="${invalidLogin}" />
						</c:if>
					</span>
				</div>
				
				<div>
					<label for="e-mail">E-mail</label>
					<input type="email" id="email" name="e-mail" />
					<span style="color: #ff0000;">
					 	<c:if	test="${requestScope.errorMessage.contains('Invalid_email')}">
							<c:out value="${invalidEmail}" />
						</c:if>
					</span>
				</div>
				
				<div>				
					<label for="Password">${password}</label>					
					<input type="password" id="inputPassword" name="password"  />
					<span style="color: #ff0000;">
					 	<c:if	test="${requestScope.errorMessage.contains('Invalid_password')}">
							<c:out value="${invalidPassword}" />
						</c:if>
					</span>
				</div>				
									
				<div><input type="submit" value="${buttonRegistration}" /></div>
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
	
 