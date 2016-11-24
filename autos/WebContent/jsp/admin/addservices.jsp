<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html >
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
		<form action="controller" method="POST">
			<div id="header">
				<input id="log_out" type="submit" value="Logout" name="command">
				<div id="nav">
					<ul>
						<li><input class="input_nav" type="submit" value="Main"
							name="command"></li>
						<li><input
							style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
							class="input_nav" type="submit" value="Add_Service"
							name="command"></li>
						<li><input class="input_nav" type="submit" value="Add_User"
							name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Update_User" name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Delete_User" name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Change_Master" name="command"></li>
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
			</form>
			<div id="content">
				<div id="content-left">
				<form action="controller" method="POST">
					<table id="add_car" align="center">
						<tr>
							<td>Type service:</td>
							<td><input type="text" name="Type" class="input"
								placeholder="Type" required ></td>
						<tr />

						<tr>
							<td>Cost:</td>
							<td><input type="text" name="Cost" class="input"
								placeholder="Cost" required></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input id="input_submit"
								type="submit" value="Ok" name="command"></td>
						</tr>
					</table>
					</form>
				</div>
				<div id="content-right">
					<div class="scroll_block">
						<table id="ServicesType" align="center">
							<tr>
								<td colspan="4"><span class="table_title">Services</span></td>
							</tr>
							<tr>
								<td class="cars_table_title">Id</td>
								<td class="cars_table_title">Title</td>
								<td class="cars_table_title">Cost</td>
							</tr>
							<c:forEach var="services" items="${services}">
								<tr>
									<td>${services.id}</td>
									<td>${services.title}</td>
									<td>${services.cost}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div id="footer"></div>
		</div>
</form>
</body>
</html>
