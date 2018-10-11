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
	
	
	<fmt:message bundle="${loc}" key="local.lessonsedit.error.text" var="messageAddLessonError" />
	
	
	<fmt:message bundle="${loc}" key="locale.lessonedit.addlesson.text" var="addLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.datelesson.text" var="dateLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.subjectlesson.text" var="subjectLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.teacherlesson.text" var="teacherLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.grouplesson.text" var="groupLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.periodstart.text" var="periodStart" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.periodend.text" var="periodEnd" />
		
	
	<fmt:message bundle="${loc}" key="locale.lessonedit.button.addlesson.text" var="buttonAddLesson" />		
	<fmt:message bundle="${loc}" key="locale.lessonedit.button.clear.text" var="buttonClear" />			
			
	<fmt:message bundle="${loc}" key="locale.lessonedit.table.searchwhere.text" var="searchWhere" />	
	<fmt:message bundle="${loc}" key="locale.lessonedit.table.searchwhat.text" var="searchWhat" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.table.search.text" var="search" />
				
	<fmt:message bundle="${loc}" key="locale.table.button.edit.text" var="buttonEdit" />
	<fmt:message bundle="${loc}" key="locale.table.button.delete.text" var="buttonDelete" />	
		
	<fmt:message bundle="${loc}" key="locale.table.edit.text" var="edit" />
	<fmt:message bundle="${loc}" key="locale.table.delete.text" var="delete" />
	
	<fmt:message bundle="${loc}" key="locale.lessonedit.dellesson.text" var="delLesson" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.selectsubject.text" var="selectSubject" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.selectteacher.text" var="selectTeacher" />
	<fmt:message bundle="${loc}" key="locale.lessonedit.selectgroup.text" var="selectGroup" />
	

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
					<select name="subject_id" >
						<option selected disabled >${selectSubject}</option>
							<c:forEach items="${subjects}" var="subject">
								<option value="${subject.id}"}>
									${subject.subjectName}</option>
							</c:forEach>
					</select>				
				</div>	
				
				<div>
					<label for="Teacher">${teacherLesson}</label>
					<select name="teacher_id" >
						<option selected disabled >${selectTeacher}</option>
							<c:forEach items="${teachers}" var="teacher">
								<option value="${teacher.id}">
									${teacher.name}</option>
							</c:forEach>
					</select> 					
				</div>	
				
				<div>
					<label for="group">${groupLesson}</label>
					<select name="group_id">
						<option selected disabled >${selectGroup}</option>
								<option value="1" >1
								<option value="2" >2
								<option value="3" >3
					</select> 	
					
				</div>	
							
							
				<input type="submit" name="create" value="${buttonAddLesson}" />
				<div><input type="reset" value="${buttonClear}"></div>
				
			</fieldset>
		</form>
	</div>	
	
	
	
 
	
				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="lessons_edit" />
				
					${searchWhere}: <select name="searchtype">
								<option value="subjects.name_subject" selected>${subjectLesson}
								<option value="date_lesson" >${dateLesson}
								<option value="users.name" >${teacherLesson}							
								<option value="child" >${child}
							
							</select> 
					${searchWhat}: <input name="searchterm" value=""> 
					
					<fieldset>						
					<label for="date_start">${periodStart}</label>
					<input name="date_start" type="date" >
					<label for="date_end">${periodEnd}</label>
					<input name="date_end" type="date">								
					</fieldset>
					
					<input type="submit" name="search" value="${search}">
				</form>	
	
	<table class="table_blur" border="3">
		<tr>
			<th style="width: 20px;">ID</th>
			<th>${dateLesson}</th>
			<th>${subjectLesson}</th>
			<th>${teacherLesson}</th>
			<th>${groupLesson}</th>
			<th>${buttonEdit}</th>
			<th>${buttonDelete}</th>
			
		</tr>

		<c:forEach items="${lessons}" var="lesson">
			<tr>
				<form action="Controller" method='post'>				
				<input type="hidden" name="command" value="lessons_edit" />	
							
					<td><input style="width: 40px;" name="id" value="${lesson.id}" /></td>	
					<td><input type="date" name="date_lesson" value="${lesson.date}" /></td>				
					
					<td><select name="subject_id" >
							<c:forEach items="${subjects}" var="subject">
								<option value="${subject.id}"
									${subject.id == lesson.subjectID?"selected":""}>
									${subject.subjectName}</option>
							</c:forEach>
					</select></td>
					
					<td><select name="teacher_id" >
							<c:forEach items="${teachers}" var="teacher">
								<option value="${teacher.id}"
									${teacher.id == lesson.teacherID?"selected":""}>
									${teacher.name}</option>
							</c:forEach>
					</select></td>
					
					
				
					
					<td><select name="group_id">
								<option selected value="${lesson.groupID}">${lesson.groupID}</option>
								<option value="1" >1
								<option value="2" >2
								<option value="3" >3								
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