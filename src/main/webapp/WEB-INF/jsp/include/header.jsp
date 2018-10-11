<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="loc" />
	
	<fmt:message bundle="${loc}" key="locale.header.loginform.login.text" var="login" />
	<fmt:message bundle="${loc}" key="locale.header.loginform.password.text" var="password" />
	<fmt:message bundle="${loc}" key="locale.header.loginform.signin.text" var="signIn" />
	<fmt:message bundle="${loc}" key="locale.header.loginform.signup.text" var="signUp" />
	<fmt:message bundle="${loc}" key="locale.header.loginform.errorsignin.text" var="messageSigninError" />	
	
	<fmt:message bundle="${loc}" key="locale.header.button.home.text" var="buttonHome" />	
	<fmt:message bundle="${loc}" key="locale.header.button.teachers.text" var="buttonTeachers" />
	<fmt:message bundle="${loc}" key="locale.header.button.subjects.text"	var="buttonSubjects" />	
	<fmt:message bundle="${loc}" key="locale.header.button.services.text" var="buttonServices" />	
	
	<fmt:message bundle="${loc}" key="locale.header.button.ru.text"	var="buttonRu" />
	<fmt:message bundle="${loc}" key="locale.header.button.en.text"	var="buttonEn" />	
	
	<fmt:message bundle="${loc}" key="locale.header.button.usersedit.text" var="buttonUsersEdit" />
	<fmt:message bundle="${loc}" key="locale.header.button.teachersedit.text" var="buttonTeachersEdit" />
	<fmt:message bundle="${loc}" key="locale.header.button.subjectsedit.text" var="buttonSubjectsEdit" />	
	<fmt:message bundle="${loc}" key="locale.header.button.servicesedit.text" var="buttonServicesEdit" />
	<fmt:message bundle="${loc}" key="locale.header.button.childrenedit.text" var="buttonChildrenEdit" />
	<fmt:message bundle="${loc}" key="locale.header.button.lessonsedit.text" var="buttonLessonsEdit" />
	<fmt:message bundle="${loc}" key="locale.header.button.reports.text" var="buttonReports" />	
	
	<fmt:message bundle="${loc}" key="locale.header.profile.text" var="profile" />
	<fmt:message bundle="${loc}" key="locale.header.logout.text" var="logout" />
	
	<fmt:message bundle="${loc}" key="locale.header.contactUs.text" var="contactUs" />
	<fmt:message bundle="${loc}" key="locale.header.contacts.text" var="ourContacts" />
	<fmt:message bundle="${loc}" key="locale.header.address.text" var="address" />
	<fmt:message bundle="${loc}" key="locale.header.town.text" var="town" />
	

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top">         
      <div class="container">
      
      
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>                  
          
          <a class="navbar-brand" href=Controller?command=main_page>My<i class="fa fa-circle"></i>Baby</a>
        </div>


	
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				 <c:if test="${sessionScope.user.role == 'USER'}">
				<li class="active"><a href=Controller?command=main_page>${buttonHome}</a></li>
				<li><a href=Controller?command=teachers_page>${buttonTeachers}</a></li>
				<li><a href=Controller?command=subjects_page>${buttonSubjects}</a></li>
												
				<li><a data-toggle="modal" data-target="#myModal"
					href="#myModal"><i class="fa fa-envelope-o"></i></a></li>
				</c:if>	

				<li>
					<form class="loc_button" action="Controller" method="post">
						<input type="hidden" name="command" value="localization" /> <input
							type="hidden" name="local" value="en" /> <input
							class="loc_button" type="submit" value="${buttonEn}" />
					</form>

					<form action="Controller" method="post">
						<input type="hidden" name="command" value="localization" /> <input
							type="hidden" name="local" value="ru" /> <input
							class="loc_button" type="submit" value="${buttonRu}" />
					</form>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->

   <c:if test="${sessionScope.user.role == 'ADMIN'}">
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href=Controller?command=users_edit>${buttonUsersEdit}</a></li>				
				<li><a href=Controller?command=subjects_edit>${buttonSubjectsEdit}</a></li>				
				<li><a href=Controller?command=children_edit>${buttonChildrenEdit}</a></li>
				<li><a href=Controller?command=lessons_edit>${buttonLessonsEdit}</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</c:if>
	
	<c:if test="${sessionScope.user.role == 'TEACHER'}">
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">							
				<li><a href=Controller?command=children_edit>${buttonChildrenEdit}</a></li>
				<li><a href=Controller?command=lessons_edit>${buttonLessonsEdit}</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</c:if>
	
	

	 <c:if test="${sessionScope.user != null}">
		<div class="navbar-collapse collapse">
			<div class="">
				<ul class="nav navbar-nav navbar-right">
					<li><a href=Controller?command=profile_edit_page>${profile}&nbsp;
						<c:out value="${sessionScope.user.role}"></c:out>
						<c:out value="${sessionScope.user.name}"></c:out></a></li>
					<li><a href=Controller?command=sign_out>${logout}</a></li>
				</ul>
			</div>
		</div>
		<!--/.nav-collapse -->
	</c:if>

	<c:if test="${sessionScope.user == null}">
		<div class="enter">
			<div>
				<a href=Controller?command=sign_up_page><c:out value="${signUp}" /></a>
			</div>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="sign_in" />
				<c:out value="${login}" />
				<input type="text" name="login" value="" /> <br />
				<c:out value="${password}" />
				<input type="password" name="password" value="" /> <br /> 
				<input type="submit" value="${signIn}" />
			</form>
			<span style="color: #ff0000;">
				<c:if test="${not empty requestScope.errorSigninMessage}">
					<c:out value="${messageSigninError}" />
				</c:if>
			</span>
		</div>
		<!--enter -->
	</c:if>
		

		</div>
    </div>


	<!-- MODAL FOR CONTACT -->
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">${contactUs}</h4>
	      </div>
	      <div class="modal-body">
		        <div class="row centered">
		        	<p>${ourContacts}</p>
		        	<p>
		        		${address}<br/>
						${town}<br/>
						+375 25 999 88 77<br/>
						
						info@mybaby.by
		        	</p>
		        	<div id="mapwrap">
		        	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2451.6328701641714!2d23.702099915626512!3d52.08641277973448!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47210c09f708e29b%3A0xad4c58320bcf835a!2z0LHRg9C7LiDQqNC10LLRh9C10L3QutC-IDQsINCR0YDQtdGB0YI!5e0!3m2!1sru!2sby!4v1533116442382" width="400" height="300" frameborder="0" style="border:0" allowfullscreen></iframe>
		
					</div>	
		        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


   
