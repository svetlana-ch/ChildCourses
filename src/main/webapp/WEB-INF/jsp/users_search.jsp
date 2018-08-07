<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style type="text/css">
.menu1 li {
display: inline;
}


.table_blur {
  background: #f5ffff;
  border-collapse: collapse;
  text-align: left;
  border: "10";
}
.table_blur th {
  border-top: 1px solid #777777;	
  border-bottom: 1px solid #777777; 
  box-shadow: inset 0 1px 0 #999999, inset 0 -1px 0 #999999;
  background: linear-gradient(#9595b6, #5a567f);
  color: white;
  padding: 10px 15px;
  position: relative;
}
.table_blur th:after {
  content: "";
  display: block;
  position: absolute;
  left: 0;
  top: 25%;
  height: 25%;
  width: 100%;
  background: linear-gradient(rgba(255, 255, 255, 0), rgba(255,255,255,.08));
}
.table_blur tr:nth-child(odd) input{
  background: #ebf3f9;
}
.table_blur th:first-child {
  border-left: 1px solid #777777;	
  border-bottom:  1px solid #777777;
  box-shadow: inset 1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur th:last-child {
  border-right: 1px solid #777777;
  border-bottom:  1px solid #777777;
  box-shadow: inset -1px 1px 0 #999999, inset 0 -1px 0 #999999;
}
.table_blur th {
  border: 1px solid #e3eef7;
  padding: 10px 15px;
  position: relative;
  transition: all 0.5s ease;
}
.table_blur tbody:hover th {
  color: transparent;
  text-shadow: 0 0 3px #a09f9d;
}
.table_blur tbody:hover tr:hover th {
  color: #444444;
  text-shadow: none;
}




a.button9 {
  position: relative;
  display: inline-block;
  color: #777674;
  font-weight: bold;
  text-decoration: none;
  text-shadow: rgba(255,255,255,.5) 1px 1px, rgba(100,100,100,.3) 3px 7px 3px;
  user-select: none;
  padding: 1em 2em;
  outline: none;
  border-radius: 3px / 100%;  
  background-image:
   linear-gradient(45deg, rgba(255,255,255,.0) 30%, rgba(255,255,255,.8), rgba(255,255,255,.0) 70%),
   linear-gradient(to right, rgba(255,255,255,1), rgba(255,255,255,0) 20%, rgba(255,255,255,0) 90%, rgba(255,255,255,.3)),
   linear-gradient(to right, rgba(125,125,125,1), rgba(255,255,255,.9) 45%, rgba(125,125,125,.5)),
   linear-gradient(to right, rgba(125,125,125,1), rgba(255,255,255,.9) 45%, rgba(125,125,125,.5)),
   linear-gradient(to right, rgba(223,190,170,1), rgba(255,255,255,.9) 45%, rgba(223,190,170,.5)),
   linear-gradient(to right, rgba(223,190,170,1), rgba(255,255,255,.9) 45%, rgba(223,190,170,.5));
  background-repeat: no-repeat;
  background-size: 200% 100%, auto, 100% 2px, 100% 2px, 100% 1px, 100% 1px;
  background-position: 200% 0, 0 0, 0 0, 0 100%, 0 4px, 0 calc(100% - 4px);
  box-shadow: rgba(0,0,0,.5) 3px 10px 10px -10px;
  background-color: #5a567f;
}
a.button9:hover {
  transition: .9s linear;
  background-position: -200% 0, 0 0, 0 0, 0 100%, 0 4px, 0 calc(100% - 4px);
}
a.button9:active {
  top: 4px;


</style>

</head>
<body>

<div class="header" >
          <ul class="menu1">
          	<li><a href=Controller?command=main_page class="button9">На главную</a></li>
            <li><a href=Controller?command=users_edit class="button9">Пользователи</a></li>
            <li><a href=Controller?command=main_page class="button9">Предметы</a></li>
            <li><a href=Controller?command=main_page class="button9">Учителя</a></li>
            <li><a href=Controller?command=main_page class="button9">Родители</a></li>
            <li><a href=Controller?command=main_page class="button9">Заявки</a></li>
            <li><a href=Controller?command=main_page class="button9">Отчеты</a></li>
          </ul>
          <ul class="">
            <c:if test="${userActive == null}">
            <li><a href=Controller?command=sign_in>Вход</a></li>
            </c:if>
            <c:if test="${userActive != null}">
            <p class=""><b>${userActive.login} (${userActive.nameRole})</b></p>            
            <li><a href=do?command=Logout>Профиль</a></li>
            <li><a href=do?command=Logout>Выход</a></li>
            </c:if>
          </ul>
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
					<td><select name="role" class="form-control">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}" role=${role.id} ${role.id==user.fk_Role?"selected":""}>
                                 ${role.role}
                            </option>
                        </c:forEach>
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
  
</body>
</html>