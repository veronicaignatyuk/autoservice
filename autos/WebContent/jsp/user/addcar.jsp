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
<body>
	<form action="controller" method="POST">
		<script type="text/javascript">
			var er = $(error);
			if (er != "") {
				alert("$(error)");
			}
		</script>
		<div id="wrapper">
			<div id="header">

				<input id="log_out" type="submit" value="Logout" name="command">
				<div id="nav">
					<ul>
						<li><input class="input_nav" type="submit" value="Main"
							name="command"></li>
						<li><input
							style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
							class="input_nav" type="submit" value="Add_Car" name="command">
						</li>
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

					<table id="add_car" align="center">
						<tr>
							<td>Brand:</td>
							<td><input type="text" name="brand"></td>
						<tr />
						<tr>
							<td>Model:</td>
							<td><input type="text" name="model"></td>
						<tr />

						<tr>
							<td colspan="2" align="center"><input id="input_submit"
								type="submit" value="Add_Cars" name="command"></td>
						</tr>
					</table>
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