<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AutoService</title>
<link href="login/css/style.css" rel="stylesheet" type="text/css" />
<link href="login/css/style-1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="scriptLogIn.js"></script>
</head>
<body>
	<form action="controller" method="POST">
		<script type="text/javascript">
			var er = $(error);
			if (er != "") {
				alert("$(error)");
			}
		</script>
		<script type="text/javascript">
			function show(state) {

				document.getElementById('window').style.display = state;
				document.getElementById('signup').style.display = state;
			}
		</script>

		<div id="wrapper">

			<div id="header">
				<input id="log_out" type="submit" value="Logout" name="command">
				<div id="nav">
					<ul>
						<li><input class="input_nav" type="submit" value="Main"
							name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Add_Service" name="command"></li>
						<li><input class="input_nav" type="submit" value="Add_User"
							name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Update_User" name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Delete_User" name="command"></li>
						<li><input
							style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
							class="input_nav" type="submit" value="Change_Master"
							name="command"></li>
					</ul>
					<div id="profile">
						<div id="prof_photo">
							<img src="jsp\img\ava.png" alt="photo">
						</div>
						<div id="nickname">
							<input type="hidden" name="Username" value="${Username}">
							<a href="#">${Username}</a>
						</div>

					</div>
				</div>
			</div>
			<div id="content">
				<div id="content-left">
					<div class="scroll_block">
						<table id="Services" align="center">
							<tr>
								<td colspan="7"><span class="table_title">Orders</span></td>
							</tr>
							<tr>
								<td class="cars_table_title">Id</td>
								<td class="cars_table_title">Login</td>
								<td class="cars_table_title">Model</td>
								<td class="cars_table_title">Brand</td>
								<td class="cars_table_title">Date</td>
								<td class="cars_table_title">Master</td>
								<td class="cars_table_title">Status</td>
								<td class="cars_table_title">title</td>
								<td class="cars_table_title">cost</td>
							</tr>
							<c:forEach var="service1" items="${service1}">
								<tr>
									<td>${service1.id}</td>
									<td>${service1.login}</td>
									<td>${service1.model}</td>
									<td>${service1.brand}</td>
									<td>${service1.date}</td>
									<td>${service1.master}</td>
									<td>${service1.status}</td>
									<td>${service1.title}</td>
									<td>${service1.cost}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div style="margin: 30px auto 0 auto; width: 800px;">
						<select type="text" name="Id_Serv" class="input" placeholder="ID">
							<option selected>Id</option>
							<c:forEach var="service1" items="${service1}">
								<option value=${service1.id}>${service1.id}</option>
							</c:forEach>
						</select> <select type="text" name="Master" class="input"
							placeholder="MASTER">
							<option selected>Master</option>
							<c:forEach var="master1" items="${master1}">
								<option value=${master1.login}>${master1.login}</option>
							</c:forEach>
						</select>
					</div>
					<div style="margin: 5px auto; width: 350px;">
						<input id="input_submit" type="submit" value="Appoint"
							name="command" title="Choice id, master">
					</div>
				</div>
				<div id="content-right"></div>
			</div>
			<div id="footer"></div>
		</div>
	</form>
</body>
</html>
