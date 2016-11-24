<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AutoService</title>
<link href="login/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="scriptLogIn.js"></script>

</head>
<body>
	<script type="text/javascript">
		var er = '${error}';
		if (er != "") {
			alert("${error}");
		}
	</script>
	<script type="text/javascript">
		function show(state) {

			document.getElementById('window').style.display = state;
			document.getElementById('signup').style.display = state;
		}
	</script>
	<div onclick="show('none')" id="signup"></div>
	<div id="window">
		<form action="controller" method="POST">
			<img src="jsp/img/close.png" class="close" onclick="show('none')" />
			<scope>
			<center>Sign Up</center>
			</scope>
			<div id="login-content">
				<fieldset id="inputsSign">
					<div id="inputs">

						<center>
							<input id="login" type="email" name="email1"
								placeholder="Enter email" required>
						</center>

						<center>
							<input id="passwordfirst" type="password" name="passwordF"
								placeholder="Password" required>
						</center>

						<center>
							<input id="passwordsec" type="password" name="passwordS"
								placeholder="Repeat your Password" required>
						</center>
					</div>
				</fieldset>

				<fieldset id="actionBut">
					<input type="submit" id="submit" value="SignUp" name="command">

				</fieldset>
			</div>
		</form>
	</div>
	<div id="wrapper">

		<div id="header">

			<div id="nav">
				<ul>

					<div id="profile">
						<div id="login-nav">
							<div id="login-ul">
								<li id="login"><a id="login-trigger" href="#"> <span>Log
											in &#9660;</span>
								</a>
									<div id="login-content">
										<form action="controller" method="POST">
											<fieldset id="inputs">
												<input id="username" type="email" name="email" value="email"
													placeholder="Your email address" required> <input
													id="password" type="password" name="password"
													value="password" placeholder="Password" required>
											</fieldset>
											<fieldset id="actions">
												<input type="submit" id="submit" value="SignIn"
													name="command" >
											</fieldset>
										</form>
									</div></li>
								<li id="signMeUp">
									<center>
										<button class="myButton" onclick="show('block')">
											<a>Sign Up</a>
										</button>
									</center>
								</li>
							</div>
						</div>
					</div>

				</ul>
			</div>
		</div>
		<div id="content">
			<div id="content-left"></div>
			<div id="content-right"></div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>