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
				<legend>Добавление занятия</legend>
				
				<input type="hidden" name="command" value="subjects_edit" />	
					
				<div>
					<label for="Name">Наименование занятия</label>
					<input type="text" name="name_subject" value="" />
				</div>
				
				<div>
					<label for="Cathegory">Категория</label>
					<input type="text" name="" value="Пока не нужно" />
				</div>
				
				<div>
					<label for="AgeChild">Возраст ребенка от</label>
					<input type="text" name="age_child" value="" />
				</div>
				
				<div>
					<label for="">Возраст ребенка до</label>
					<input type="text" name="" value="Пока не нужно" />
				</div>
				
				<div>
					<label for="SexChild">Пол ребенка</label>
					<select name="sex_child">
								<option value="Any" selected>Любой
								<option value="Boy" >Мальчик
								<option value="Girl" >Девочка								
					</select> 					
				</div>					
				
				<div>
					<label for="TimeSpending">Время проведения</label>
					<select name="time_spending">
								<option value="Morning" selected>Morning
								<option value="Noon" >Noon
								<option value="Evening" >Evening
					</select> 	
					
				</div>
				
				<div>
					<label for="NumberPerWeek">Количество в неделю</label>
					<select name="number_per_week">
								<option value="1" selected>1
								<option value="2" >2
								<option value="3" >3
								<option value="4" >4								
					</select> 
					
				</div>	
				
				<div>
					<label for="IsNew">Новинка?</label>
					<input type="checkbox"  name="is_new" value="" />
				</div>
				
				<div>
					<label for="Teacher">Преподаватель</label>
					<input type="text" name="" value="Пока нет" />
				</div>
				
				<div>
					<label for="Cost">Стоимость</label>
					<input type="text" name="cost" value="" />
				</div>					
							
				<input type="submit" name="create" value="Добавить" />
				
			</fieldset>
		</form>
	</div>	
	
	
				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="users_search" />
				
					Ищем по:<select name="searchtype">
								<option value="xxxx" selected>Категории
								<option value="id" >Наименованию
								<option value="xxxx" >Препадавателю
								
								<option value="login" >Возрасту ребенка
								<option value="role" >Времени проведения
								
							</select> 
					Что ищем:<input name="searchterm" value=""> 
					<input type="submit" value="Поиск">
				</form>
	
	
	
	<table class="table_blur" border="3">
		<tr>
			<th>ID</th>
			<th>Наименование предмета</th>
			<th>Возраст ребенка</th>
			<th>Пол ребенка</th>
			<th>Время проведения</th>
			<th>Количество в неделю</th>
			<th>Новинка</th>
			<th>Стоимость</th>
			<th>Редактировать</th>
			<th>Удалить</th>
			
		</tr>

		<c:forEach items="${subjects}" var="subject">
			<tr>

				<form action="Controller" method='post'>
				
				<input type="hidden" name="command" value="users_edit" />
		
		
				
					<td>${subject.id}</td>
					<td>${subject.name}</td>
					<td>${subject.ageChildFrom}</td>
					<td>${subject.sexChild}</td>
					<td>${subject.timeSpending}</td>
					<td>${subject.numberPerWeek}</td>
					<td>${subject.isNew}</td>
					<td>${subject.cost}</td>
										
					<td>
						<input type="submit" name="update" value="Редактировать" class="button9"/>
						
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