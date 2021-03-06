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
	</form>
	<script type="text/javascript">
		function show(state) {

			document.getElementById('window').style.display = state;
			document.getElementById('signup').style.display = state;
		}
	</script>

	<div id="wrapper">

		<div id="header">
			<form action="controller" method="POST">
				<input id="log_out" type="submit" value="Logout" name="command">
				<div id="nav">
					<ul>
						<li><input class="input_nav" type="submit" value="Main"
							name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Add_Service" name="command"></li>
						<li><input
							style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
							class="input_nav" type="submit" value="Add_User" name="command"></li>
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
			</form>
		</div>
		<div id="content">
			<form action="controller" method="POST">
				<div id="content-left">
					<div class="scroll_block">
						<table id="Users" align="center">
							<tr>
								<td colspan="7"><span class="table_title">Users</span></td>
							</tr>
							<tr>
								<td class="cars_table_title">ID</td>
								<td class="cars_table_title">Username</td>
								<td class="cars_table_title">Password</td>
							</tr>
							<c:forEach var="users1" items="${users1}">
								<tr>
									<td>${users1.id}</td>
									<td>${users1.login}</td>
									<td>${users1.password}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div style="margin: 30px auto 0 auto; width: 800px;">
						<input type="email" name="Log" class="input" placeholder="Login"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" maxlength=15
							minlength=5
							title="Email must be in the following order: characters@characters.domain, max length = 15"
							required> <input type="password" name="Pas" class="input"
							placeholder="Password" pattern=".{3,15}" maxlength=15 minlength=3
							title="5 to 15 symbols" required> <select type="text"
							name="Role" class="input" placeholder="MASTER">
							<option selected>Role</option>
							<c:forEach var="role1" items="${role1}">
								<option value=${role1.id}>${role1.role}</option>
							</c:forEach>
						</select>
					</div>
					<div style="margin: 5px auto; width: 350px;">
						<input id="input_submit" type="submit" value="Add" name="command"
							title="Enter Login, Password, Role">
					</div>
				</div>
				<div id="content-right"></div>
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
