<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<div id="feedback">
		<div id="feedback-content"></div>
		<div id="feedback-mark1">
			<a href="#"><img src="jsp/img/feedback.png" /></a>
		</div>
		<div id="feedback-mark">
			<a href="#"><img src="jsp/img/feedback.png" /></a>
		</div>
	</div>
	<div id="wrapper">
		<div id="header">
			<input id="log_out" type="submit" value="Logout" name="command">
			<div id="nav">
				<ul>
					<li><input class="input_nav" type="submit" value="Main"
						name="command"></li>
					<li><input class="input_nav" type="submit" value="Add_Car"
						name="command"></li>
					<li><input
						style="color: #FFDEAD; background: rgba(43, 43, 43, .7);"
						class="input_nav" type="submit" value="Add_Service" name="command">
					</li>
					<li><input class="input_nav" type="submit" value="Contacts"
						name="command"></li>
					<div id="profile">
						<div id="prof_photo">
							<img src="login\jsp\img\ava.png" alt="photo">
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
						<td>Type service:</td>
						<td><select size="1">
								<option selected></option>
								<option value="example 1">example 1</option>
								<option value="example 2">example 2</option>
								<option value="example 3">example 3</option>
								<option value="example 4">example 4</option>
						</select></td>
					<tr />
					<tr>
						<td>Brand:</td>
						<td><select size="1">
								<option selected></option>
								<option value="BMW">BMW</option>
								<option value="Audi">Audi</option>
								<option value="Nissan">Nissan</option>
								<option value="Volkswagen">Volkswagen</option>
						</select></td>
					<tr />
					<tr>
						<td>Model:</td>
						<td><select size="1">
								<option selected></option>
								<option value="A8">A8</option>
								<option value="M6">M6</option>
								<option value="GT R-35">GT R-35</option>
								<option value="Sorento">Sorento</option>
						</select></td>
					<tr />
					<tr>
						<td>Date:</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input id="input_submit"
							type="submit" value="Add"></td>
					</tr>
				</table>
			</div>
			<div id="content-right"></div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
