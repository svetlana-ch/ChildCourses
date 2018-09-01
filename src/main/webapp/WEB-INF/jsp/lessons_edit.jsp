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
				<legend>${addLesson}</legend>
				
				<input type="hidden" name="command" value="lessons_edit" />	
					
				<div>
					<label for="Date">${dateLesson}</label>
					<input type="date" name="date_lesson" value="" />
				</div>
								
								
				<div>
					<label for="Subject">${subjectLesson}</label>
					<select name="subject_lesson">
								<option value="${morning}" selected>${morning}
								<option value="${noon}" >${noon}
								<option value="${evening}" >${evening}
					</select> 	
					
				</div>	
				
				<div>
					<label for="Teacher">${teachertLesson}</label>
					<select name="teacherLesson">
								<option value="${morning}" selected>${morning}
								<option value="${noon}" >${noon}
								<option value="${evening}" >${evening}
					</select> 	
					
				</div>	
				
				<div>
					<label for="group">${groupLesson}</label>
					<select name="group_lesson">
								<option value="${morning}" selected>${morning}
								<option value="${noon}" >${noon}
								<option value="${evening}" >${evening}
					</select> 	
					
				</div>	
							
							
				<input type="submit" name="create" value="${buttonAddLesson}" />
				<div><input type="reset" value="${buttonClear}"></div>
				
			</fieldset>
		</form>
	</div>	
	
	
	
 
	
				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="lessons_search" />
				
					Ищем по:<select name="searchtype">
								<option value="xxxx" selected>Предмету
								<option value="id" >Учителю
								<option value="xxxx" >Дате							
								<option value="login" >Ребенку
							
							</select> 
					Что ищем:<input name="searchterm" value=""> 
					<input type="submit" value="Поиск">
				</form>	
	
	<table class="table_blur" border="3">
		<tr>
			<th style="width: 20px;">ID</th>
			<th>${dataLesson}</th>
			<th>${subjectLesson}</th>
			<th>${teacherLesson}</th>
			<th>${groupLesson}</th>
			
		</tr>

		<c:forEach items="${lesson}" var="lessons">
			<tr>
				<form action="Controller" method='post'>				
				<input type="hidden" name="command" value="lessons_edit" />	
							
					<td><input style="width: 40px;" name="id" value="${lesson.id}" /></td>	
					<td><input type="date" name="date_lesson" value="${lesson.date}" /></td>
					
					<td><select name="subject_lesson">
								<option selected value="${{lesson.subject}">${{lesson.subject}</option>
								<option value="Any">${any}
								<option value="Boy" >${boy}
								<option value="Girl" >${girl}								
					</select></td> 
					
					<td><select name="teacher_lesson">
								<option selected value="${{lesson.teacher}">${{lesson.teacher}</option>
								<option value="Any">${any}
								<option value="Boy" >${boy}
								<option value="Girl" >${girl}								
					</select></td> 
					
					<td><select name="group_lesson">
								<option selected value="${{lesson.group}">${child.group}</option>
								<option value="Any">${any}
								<option value="Boy" >${boy}
								<option value="Girl" >${girl}								
					</select></td> 
									
					
               		 <td>
						<input type="submit" name="update" value="${buttonEdit}"/>
						
					</td>
					
					<td>
						<button type="submit" name="delete" value="delete" 
						onclick="if (!(confirm('${delLesson}'))) return false">${buttonDelete}</button>
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