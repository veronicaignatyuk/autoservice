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

			<div id="header">
				<input id="log_out" type="submit" value="Logout" name="command">
				<div id="nav">
					<ul>
						<li><input
							style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
							class="input_nav" type="submit" value="Main" name="command">
						</li>
						<li><input class="input_nav" type="submit" value="Add_Car"
							name="command"></li>
						<li><input class="input_nav" type="submit"
							value="Order_Service" name="command"></li>

						<div id="profile">
							<div id="prof_photo">
								<img src="jsp\img\ava.png" alt="photo">
							</div>
							<div id="nickname">
								<input type="hidden" name="Username" placeholder="Enter email"
									value="${Username}" required> <a href="#">${Username}</a>
							</div>
						</div>
					</ul>
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
									<td>${service1.status}</td>
									<td>${service1.title}</td>
									<td>${service1.cost}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div id="content-right">
					<div class="scroll_block">
						<table id="Cars" align="center">
							<tr>
								<td colspan="4"><span class="table_title">Cars</span></td>
							</tr>
							<tr>
								<td class="cars_table_title">Id</td>
								<td class="cars_table_title">Brand name</td>
								<td class="cars_table_title">Model</td>
							</tr>
							<c:forEach var="cars1" items="${cars1}">
								<tr>
									<td>${cars1.id}</td>
									<td>${cars1.brand}</td>
									<td>${cars1.model}</td>
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
