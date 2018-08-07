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
	
	<fmt:message bundle="${loc}" key="locale.useredit.regform.login.text" var="login" />
	
	
	<fmt:message bundle="${loc}" key="locale.useredit.regform.password.text" var="password" />
	<fmt:message bundle="${loc}" key="locale.useredit.regform.signin.text" var="signIn" />
	<fmt:message bundle="${loc}" key="locale.useredit.regform..signup.text" var="signUp" />
	<fmt:message bundle="${loc}" key="local.useredit.regform.error.text" var="messageLoginError" />	
	
	


</head>
<body>


		<jsp:include page="include/header.jsp"></jsp:include>




		<br><br><br><br><br>





	<div class="form signup">
	
		<form action="Controller" method='post'>
			<fieldset>				
				<legend>Добавление пользователя</legend>
				
				<input type="hidden" name="command" value="sign_up" />	
					
				<div>
					<label for="Name">Фамилия Имя</label>
					<input type="text" name="name" value="" />
				</div>
				
				<div>
					<label for="e-mail">E-mail</label>
					<input type="text" name="e-mail" value="" />
				</div>					
				
				<div>
					<label for="Login">Логин</label>
					<input type="text" name="login" value="" />
				</div>				
								
				<div>				
					<label for="Password">Пароль</label>
					<input type="password" name="password" value="" />
				</div>			
							
				<input type="submit" value="Добавить" />
				
			</fieldset>
		</form>
	</div>	
	
	
				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="users_search" />
				
					Ищем по:<select name="searchtype">
								<option value="name" selected>Фамилии Имени
								<option value="email" >E-mail
								<option value="login" > Логину
								<option value="role" >Роли
								<option value="id" >Id
							</select> 
					Что ищем:<input name="searchterm" value=""> 
					<input type="submit" value="Поиск">
				</form>
	
	
	
	<table class="table_blur" border="3">
		<tr>
			<th width="auto">ID</th>
			<th>Name</th>
			<th>e-mail</th>
			<th>Role</th>
			<th>Login</th>
			<th>Password</th>
			<th>Edit</th>
			<th>Del</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>

				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="users_edit" />
		
		
				
					<td><input name="id" value="${user.id}" /></td>								
					<td><input type="text" name="name" value="${user.name}" /></td>
					<td><input type="text" name="e-mail" value="${user.email}" /></td>
					<td><input name="role" value="${user.role}">                        
                            
                    </select>
                    </td>
					<td><input type="text" name="login" value="${user.login}" /></td>
					<td><input type="text" name="password" value="${user.password}" /></td>
					
					

					
					<td>
						<input type="submit" name="update" value="Обновить" class="button9"/>
						
					</td>
					
					<td>
						<button type="submit" name="delete" value="delete" class="button9">Удалить</button>
               		 </td>
				
				</form>
			</tr>
		</c:forEach>
	</table>
	
	
	
	
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