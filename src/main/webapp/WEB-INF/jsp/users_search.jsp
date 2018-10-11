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
	
	<fmt:message bundle="${loc}" key="locale.registrationform.adduser.text" var="addUser" />
	<fmt:message bundle="${loc}" key="locale.registrationform.name.text" var="surnameName" />
	<fmt:message bundle="${loc}" key="locale.registrationform.login.text" var="login" />
	<fmt:message bundle="${loc}" key="locale.registrationform.password.text" var="password" />
	<fmt:message bundle="${loc}" key="locale.registrationform.button.adduser.text" var="buttonAddUser" />		
	<fmt:message bundle="${loc}" key="locale.registrationform.button.clear.text" var="buttonClear" />
	
	
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhere.text" var="searchWhere" />	
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhat.text" var="searchWhat" />
	<fmt:message bundle="${loc}" key="locale.useredit.table.search.text" var="search" />
	<fmt:message bundle="${loc}" key="locale.useredit.table.role.text" var="role" />
	
	<fmt:message bundle="${loc}" key="locale.table.button.edit.text" var="buttonEdit" />
	<fmt:message bundle="${loc}" key="locale.table.button.delete.text" var="buttonDelete" />
	
	<fmt:message bundle="${loc}" key="locale.table.edit.text" var="edit" />
	<fmt:message bundle="${loc}" key="locale.table.delete.text" var="delete" />
	
	<fmt:message bundle="${loc}" key="locale.useredit.deluser.text" var="delUser" />	


</head>
<body>


		<jsp:include page="include/header.jsp"></jsp:include>
		
		<br><br><br><br><br><br><br>
	
				<form action="Controller" method='post'>				
				<input type="hidden" name="command" value="users_search" />				
					${searchWhere}:<select name="searchtype">
								<option value="name" selected>${surnameName}
								<option value="email" >E-mail
								<option value="login" >${login}
								<option value="role" >${role}
								<option value="id" >Id
							</select> 
					${searchWhat}:<input name="searchterm" value=""> 
					<input type="submit" value="${search}">
				</form>
	
	
	
	<table class="table_blur" border="3">
		<tr>
			<th width="30px">ID</th>
			<th>${surnameName}</th>
			<th>e-mail</th>
			<th>${role}</th>
			<th>${login }</th>
			<th>${password}</th>
			<th>${edit}</th>
			<th>${delete}</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>

				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="users_edit" />
				<input type="hidden"  name="id" value="${user.id}"  />	
		
				
					<td>${user.id}</td>								
					<td><input type="text" name="name" value="${user.name}" /></td>
					<td><input type="text" name="e-mail" value="${user.email}" /></td>
					                      
                    <td><select name="role">                        
                            <option selected value="${user.role}">${user.role}</option>
                            <option value="TEACHER">TEACHER</option>
                            <option value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>                       
                    </select>
                    </td>
                    </td>
					<td><input type="text" name="login" value="${user.login}" /></td>
					<td><input type="text" name="password" value="${user.password}" /></td>
					
					

					
					<td>
						<input type="submit" name="update" value="${buttonEdit}""/>
						
					</td>
					
					<td>
						<button type="submit" name="delete" value="delete" 
						onclick="if (!(confirm('${delUser}'))) return false">${buttonDelete}</button>
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