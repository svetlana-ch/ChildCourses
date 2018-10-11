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
	
	
	<fmt:message bundle="${loc}" key="local.subjectedit.subjectedit.error.text" var="messageAddSubjectError" />
	
	<fmt:message bundle="${loc}" key="locale.subjectedit.addsubject.text" var="addSubject" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.namesubject.text" var="nameSubject" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.agechildfrom.text" var="ageChildFrom" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.agechildto.text" var="ageChildTo" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.sexchild.text" var="sexChild" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.timespending.text" var="timeSpending" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.numberperweek.text" var="numberPerWeek" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.isnew.text" var="isNew" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.teacher.text" var="teacher" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.cost.text" var="cost" />
	
	<fmt:message bundle="${loc}" key="locale.subjectedit.any.text" var="any" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.boy.text" var="boy" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.girl.text" var="girl" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.morning.text" var="morning" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.noon.text" var="noon" />
	<fmt:message bundle="${loc}" key="locale.subjectedit.evening.text" var="evening" />		
	
	<fmt:message bundle="${loc}" key="locale.subjectedit.button.addsubject.text" var="buttonAddSubject" />		
	<fmt:message bundle="${loc}" key="locale.subjectedit.button.clear.text" var="buttonClear" />			
			
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhere.text" var="searchWhere" />	
	<fmt:message bundle="${loc}" key="locale.useredit.table.searchwhat.text" var="searchWhat" />
	<fmt:message bundle="${loc}" key="locale.useredit.table.search.text" var="search" />
	<fmt:message bundle="${loc}" key="locale.useredit.table.role.text" var="role" />
		
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
				<legend>${addSubject}</legend>
				
				<input type="hidden" name="command" value="subjects_edit" />	
					
				<div>
					<label for="Name">${nameSubject}</label>
					<input type="text" name="name_subject" required />
				</div>
								
				<div>
					<label for="AgeChildFrom">${ageChildFrom}</label>
					<select style="width: 40px;" name="age_child_from">							
							<option value="3" selected>3
							<option value="4" >4
							<option value="5" >5
							<option value="6" >6
							<option value="7" >7
							<option value="8" >8
							<option value="9" >9
							<option value="10" >10
							<option value="11" >11
							<option value="12" >12
							<option value="13" >13
							<option value="14" >14
					</select>
				</div>
				
				<div>
					<label for="AgeChildTo">${ageChildTo}</label>
					<select style="width: 40px;" name="age_child_to">							
							<option value="3" selected>3
							<option value="4" >4
							<option value="5" >5
							<option value="6" >6
							<option value="7" >7
							<option value="8" >8
							<option value="9" >9
							<option value="10" >10
							<option value="11" >11
							<option value="12" >12
							<option value="13" >13
							<option value="14" >14
					</select></div>
				
				<div>
					<label for="SexChild">${sexChild}</label>
					<select name="sex_child">
								<option value="любой" selected>любой
								<option value="мальчик" >мальчик
								<option value="девочка" >девочка								
					</select> 					
				</div>					
				
				<div>
					<label for="TimeSpending">${timeSpending}</label>
					<select name="time_spending">
								<option value="утро" selected>утро
								<option value="день" >день
								<option value="вечер" >вечер
					</select> 	
					
				</div>
				
				<div>
					<label for="NumberPerWeek">${numberPerWeek}</label>
					<select name="number_per_week">
								<option value="1" selected>1
								<option value="2" >2
								<option value="3" >3
								<option value="4" >4								
					</select> 
					
				</div>	
								
				<div>
					<label for="Cost">${cost}</label>
					<input type="number" min="0" max="1000" step="0.01" name="cost" value="" />
				</div>					
							
				<input type="submit" name="create" value="${buttonAddSubject}" />
				<div><input type="reset" value="${buttonClear}"></div>
				
			</fieldset>
		</form>
	</div>	
	
	
	<table class="table_blur" border="3">
		<tr>
			<th style="width: 20px;">ID</th>
			<th>${nameSubject}</th>
			<th>${ageChildFrom}</th>
			<th>${ageChildTo}</th>
			<th>${sexChild}</th>
			<th>${timeSpending}</th>
			<th>${numberPerWeek}</th>
					
			<th>${cost}</th>
			<th>${edit}</th>
			<th>${delete}</th>
			
		</tr>

		<c:forEach items="${subjects}" var="subject">
			<tr>

				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="subjects_edit" />	
							
					<td><input style="width: 40px;" name="id" value="${subject.id}" /></td>								
					<td><input type="text" name="name_subject" value="${subject.subjectName}" /></td>						
					<td><select style="width: 40px;" type="text" name="age_child_from">
							<option selected value="${subject.ageChildFrom}">${subject.ageChildFrom}</option>
							<option value="3" >3
							<option value="4" >4
							<option value="5" >5
							<option value="6" >6
							<option value="7" >7
							<option value="8" >8
							<option value="9" >9
							<option value="10" >10
							<option value="11" >11
							<option value="12" >12
							<option value="13" >13
							<option value="14" >14
					</select></td>
					<td><select style="width: 40px;" type="text" name="age_child_to" value="${subject.ageChildTo}">
							<option selected value="${subject.ageChildTo}">${subject.ageChildTo}</option>
							<option value="3" >3
							<option value="4" >4
							<option value="5" >5
							<option value="6" >6
							<option value="7" >7
							<option value="8" >8
							<option value="9" >9
							<option value="10" >10
							<option value="11" >11
							<option value="12" >12
							<option value="13" >13
							<option value="14" >14
					</select></td>									
					<td><select name="sex_child">
								<option selected value="${subject.sexChild}">${subject.sexChild}</option>
								<option value="любой" >любой
								<option value="мальчик" >мальчик
								<option value="девочка" >девочка								
					</select></td> 
					<td><select name="time_spending">
								<option value="${subject.timeSpending}" selected>${subject.timeSpending}
								<option value="утро" >утро
								<option value="день" >день
								<option value="вечер" >вечер
					</select> </td>					           
                    <td><select name="number_per_week">
								<option value="${subject.numberPerWeek}" selected>${subject.numberPerWeek}
								<option value="1" >1
								<option value="2" >2
								<option value="3" >3
								<option value="4" >4								
						</select> 
                    </td>
                   
									
					<td><input style="width: 40px;" type="number" min="0" max="1000" step="0.01" name="cost" value="${subject.cost}" /></td>									
					
               		 <td>
						<input type="submit" name="update" value="${buttonEdit}"/>
						
					</td>
					
					<td>
						<button type="submit" name="delete" value="delete" 
						onclick="if (!(confirm('${delSubject}'))) return false">${buttonDelete}</button>
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