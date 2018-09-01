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
		
		  
               <!--       <c:if test="${requestScope.get('errorMessage')!=null}">
                        <h4 class="red text-center"><c:out value="${requestScope.get('errorMessage')}"/></h4>
                        <c:remove var="errorMessage" scope="request"/>
                    </c:if>
                    
-->

	<div class="form signup">

		<form class="registerForm" action="Controller" method='post' onsubmit="return validateForm();">
			<fieldset>				
				<legend>${registration}</legend>
				
				<input type="hidden" name="command" value="sign_up" />	
					
				<div>
					<label for="Name">${surnameName}</label>
					<input type="text" name="name"  />
				</div>
					
					
				<div>
					<label for="Login">${login}</label>
					<input type="text" id="inputLogin" name="login" required />
				</div>
				
				<div>
					<label for="e-mail">E-mail</label>
					<input type="email" id="email" name="e-mail" />
				</div>
				
				<div>				
					<label for="Password">${password}</label>
					<input type="password" id="inputPassword" name="password" required/>
				</div>	
				
				<div>				
					<label for="Password">${repeatPassword}</label>
					<input type="password" id="inputPassword2" name="password2я" required/>
				</div>
				
				<!--  
    <c:if test="${isLoginTaken == true}">
        <p style="color: #FF0000;" align="center">${login_error}</p>
    </c:if>

    <c:if test="${isLoginTaken == false}">
        <p style="color: #FF0000;" align="center">${error}</p>
    </c:if>
				-->		
							
				<div><input type="submit" value="${buttonRegistration}" /></div>
				<div><input type="reset" value="${buttonClear}"></div>          
				
			</fieldset>
		</form>
	</div>


	<c:if test="${errorMessage != null && !errorMessage.isEmpty()}">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">
				&times;</a> ${errorMessage}
		</div>
	</c:if>
	<c:if test="${successMessage != null && !successMessage.isEmpty()}">
		<div class="alert alert-success fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">
				&times;</a> ${successMessage}
		</div>
	</c:if>


	<!--	
    <c:if test="${requestScope.errorCreateJourney != null && requestScope.createJourneyDTO == null}">
        <div class="w3-modal" style="display: block">
            <div class="w3-modal-content w3-animate-zoom">
                <div class="w3-panel <c:if test="${requestScope.errorCreateJourney.get('Good!') != null}">w3-green</c:if><c:if test="${requestScope.errorCreateJourney.get('Danger!') != null}">w3-redd</c:if>  w3-display-container">
                <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                      class="w3-button <c:if test="${requestScope.errorCreateJourney.get('Good!') != null}">w3-green</c:if><c:if test="${requestScope.errorCreateJourney.get('Danger!') != null}">w3-redd</c:if> w3-large w3-display-topright">&times;</span>
                    <c:forEach var="error" items="${errorCreateJourney}">
                        <h3>${error.key}</h3>
                        <p>${error.value}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    
    
<c:if test="${requestScope.errorCarJourney != null}">
    <div id="driverCars" class="w3-modal" style="display: block">
        <div class="w3-modal-content">
            <c:if test="${requestScope.errorCarJourney != ''}">
                <div class="w3-panel w3-red w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-red w3-large w3-display-topright">&times;</span>
                    <h3>Danger!</h3>
                    <p>${requestScope.errorCarJourney}</p>
                </div>
            </c:if>
            <c:if test="${requestScope.errorCarJourney == ''}">
                <div class="w3-panel w3-teal w3-display-container">
                    <span onclick="this.parentElement.parentElement.parentElement.style.display='none'"
                          class="w3-button w3-teal w3-large w3-display-topright">&times;</span>
                    <h3>Good!</h3>
                    <p>Car added to journey</p>
                </div>
            </c:if>
        </div>
    </div>
</c:if>
    
    -->

  	<jsp:include page="include/footer.jsp"></jsp:include>
  	 	
  	
  	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script  language="javascript">
    function validateForm() {
        var login, email, psw, psw2;
        var unameText, emailText, pswText;
        var result = true;

        login = document.forms["registerForm"]["login"].value;
        email = document.forms["registerForm"]["email"].value;
        psw = document.forms["registerForm"]["inputPassword"].value;
        psw2 = document.forms["registerForm"]["inputPassword2"].value;

        var loginPattern = /[a-zA-Z_0-9]{3,16}/;
        if (!loginPattern.test(uname)) {
            loginText = "Login should contain only latin symbols, digits and _";
            document.getElementById("loginDemo").innerHTML = loginText;
            result = false;
        } else if (uname.length < 3) {
            loginText = "Login should be at least 3 symbols.";
            document.getElementById("loginDemo").innerHTML = unameText;
            result = false;
        } else if (login.length > 16) {
            loginText = "Login should be less then 17 symbols.";
            document.getElementById("loginDemo").innerHTML = unameText;
            result = false;
        }  else {
            loginText = "";
            document.getElementById("loginDemo").innerHTML = unameText;
        }

        var emailPattern = /[a-zA-Z0-9_]+@[A-Za-z0-9].+/;
        if (!emailPattern.test(email)) {
            emailText = "Email should contain latin symbols, @, digits, . and _";
            document.getElementById("emailDemo").innerHTML = emailText;
            return false;
        } else {
            unameText = "";
            document.getElementById("emailDemo").innerHTML = unameText;
        }

        var passPattern = /[a-zA-Z0-9_]{6,32}/;
        if (psw.length < 6) {
            pswText = "Password should be at least 6 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (!passPattern.test(psw)) {
            pswText = "Password should contain only latin symbols, digits and _";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (psw.length > 32) {
            pswText = "Password should be less then 32 symbols";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else if (psw !== psw2) {
            pswText = "Passwords should be the same";
            document.getElementById("pswDemo").innerHTML = pswText;
            result = false;
        } else {
            unameText = "";
            document.getElementById("pswDemo").innerHTML = unameText;
        }

        return result;
    }
</script>
  </body>
</html>	
	
  
</body>
</html>