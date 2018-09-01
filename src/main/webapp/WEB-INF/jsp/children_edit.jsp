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
	
	
	<fmt:message bundle="${loc}" key="local.childrenedit.error.text" var="messageAddChildError" />
	
	<fmt:message bundle="${loc}" key="locale.childrenedit.addchild.text" var="addChild" />	
	<fmt:message bundle="${loc}" key="locale.childrenedit.namechild.text" var="nameChild" />
	<fmt:message bundle="${loc}" key="locale.childrenedit.surnamechild.text" var="surnameChild" />
	<fmt:message bundle="${loc}" key="locale.childrenedit.birthchild.text" var="birthChild" />
	<fmt:message bundle="${loc}" key="locale.childrenedit.parentchild.text" var="parentChild" />		
	
	<fmt:message bundle="${loc}" key="locale.childrenedit.button.addchild.text" var="buttonAddChild" />		
	<fmt:message bundle="${loc}" key="locale.subjectedit.button.clear.text" var="buttonClear" />			
			
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhere.text" var="searchWhere" />	
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhat.text" var="searchWhat" />
	<fmt:message bundle="${loc}" key="locale.useredit.table.search.text" var="search" />
			
	<fmt:message bundle="${loc}" key="locale.table.button.edit.text" var="buttonEdit" />
	<fmt:message bundle="${loc}" key="locale.table.button.delete.text" var="buttonDelete" />	
		
	<fmt:message bundle="${loc}" key="locale.table.edit.text" var="edit" />
	<fmt:message bundle="${loc}" key="locale.table.delete.text" var="delete" />

</head>
<body>

		<jsp:include page="include/header.jsp"></jsp:include>
		<br><br><br><br><br><br>

	<div class="form">	
		<form action="Controller" method='post'>
			<fieldset>				
				<legend>${addChild}</legend>
				
				<input type="hidden" name="command" value="children_edit" />	
					
				<div>
					<label for="Name">${nameChild}</label>
					<input type="text" name="name_child" value="" />
				</div>
								
				<div>
					<label for="surname">${surnameChild}</label>
					<input type="text" name="surname_child" value="" />
				</div>
				
				<div>
					<label for="BirthChild">${birthChild}</label>
					<input type="date" name="birth_child" />
				</div>
				
				<div>
					<label for="Parent">${parentChild}</label>
					<select name="parent_child">
								<option value="1" selected>1
								<option value="4" >4
								<option value="6" >6
					</select> 	
					
				</div>					
							
							
				<input type="submit" name="create" value="${buttonAddChild}" />
				<div><input type="reset" value="${buttonClear}"></div>
				
			</fieldset>
		</form>
	</div>	
	
	
	
 
	
				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="children_search" />
				
					Ищем по:<select name="searchtype">
								<option value="xxxx" selected>Имени
								<option value="id" >Фамилии
								<option value="xxxx" >Дате рождения							
								<option value="login" >Родителю
							
							</select> 
					Что ищем:<input name="searchterm" value=""> 
					<input type="submit" value="Поиск">
				</form>	
	
	<table class="table_blur" border="3">
		<tr>
			<th style="width: 20px;">ID</th>
			<th>${nameChild}</th>
			<th>${surnameChild}</th>			
			<th>${birthChild}</th>
			<th>${parentChild}</th>
			<th>${edit}</th>
			<th>${delete}</th>
			
		</tr>

		<c:forEach items="${children}" var="child">
			<tr>
				<form action="Controller" method='post'>				
				<input type="hidden" name="command" value="children_edit" />	
							
					<td><input style="width: 40px;" name="id" value="${child.id}" /></td>								
					<td><input type="text" name="name_child" value="${child.name}" /></td>
					<td><input type="text" name="surname_child" value="${child.surname}" /></td>	
					<td><input type="date" name="birth_child" value="${child.dateOfBirth}" /></td>														
					<td><select name="parent_child">
								<option selected value="${child.parentID}">${child.parentID}</option>
								<option value="Any">${any}
								<option value="Boy" >${boy}
								<option value="Girl" >${girl}								
					</select></td> 
									
					
               		 <td>
						<input type="submit" name="update" value="${buttonEdit}"/>
						
					</td>
					
					<td>
						<button type="submit" name="delete" value="delete" 
						onclick="if (!(confirm('${delChild}'))) return false">${buttonDelete}</button>
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