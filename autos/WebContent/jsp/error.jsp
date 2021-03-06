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
								placeholder="Enter email" required
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" maxlength=15
								minlength=5
								title="Email must be in the following order: characters@characters.domain, max length = 15">
						</center>

						<center>
							<input id="passwordfirst" type="password" name="passwordF"
								placeholder="Password" pattern=".{5,15}" title="5 to 15 symbols"
								required>
						</center>

						<center>
							<input id="passwordsec" type="password" name="passwordS"
								placeholder="Repeat your Password" pattern=".{5,15}"
								title="5 to 15 symbols" required>
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
													placeholder="Your email address"
													pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
													maxlength=15 minlength=5
													title="Email must be in the following order: characters@characters.domain, max length = 15"
													required> <input id="password" type="password"
													name="password" value="password" placeholder="Password"
													pattern=".{3,15}" title="5 to 15 symbols" required>
											</fieldset>
											<fieldset id="actions">
												<input type="submit" id="submit" value="SignIn"
													name="command"> <label><input
													type="checkbox" checked="checked"> <span
													style="color: #FFDEAD">Keep me signed in</span></label>
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
			<div id="content-left">
				<a>Enter the data correctly!!!</a>

			</div>
			<div id="content-right"></div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>