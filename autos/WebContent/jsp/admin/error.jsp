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
			<div id="content">
				<div id="content-left">
					<input type="hidden" name="Error" value="${Error}">
				</div>
				<div id="content-right"></div>
			</div>
			<div id="footer"></div>
		</div>
	</form>
</body>
</html>
