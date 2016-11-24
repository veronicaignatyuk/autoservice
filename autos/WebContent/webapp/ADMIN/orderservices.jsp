
<html>
<head>
<link href="../login/css/style.css" rel="stylesheet" type="text/css" />
<link href="../login/css/style-1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../scriptLogIn.js"></script>
</head>
<body>
	<div id="feedback">
		<div id="feedback-content"></div>
		<div id="feedback-mark1">
			<a href="#"><img src="../login/jsp/img/feedback.png" /></a>
		</div>
		<div id="feedback-mark">
			<a href="#"><img src="../login/jsp/img/feedback.png" /></a>
		</div>


	</div>
	<script type="text/javascript">

					//Функция показа
			function show(state){

					document.getElementById('window').style.display = state;			
					document.getElementById('signup').style.display = state; 			
			}
			
		</script>
	<!-- Задний прозрачный фон-->
	<div onclick="show('none')" id="signup"></div>

	<!-- Само окно-->
	<div id="window">
		<img src="../login/jsp/img/close.png" class="close"
			onclick="show('none')" />
		<scope>
		<center>Sign Up</center>
		</scope>
		<div id="login-content">
			<fieldset id="inputsSign">
				<div id="inputs">
					<center>
						<input id="login" type="email" name="Email"
							placeholder="Enter email" required>
					</center>

					<center>
						<input id="passwordfirst" type="password" name="Passwordf"
							placeholder="Password" required>
					</center>

					<center>
						<input id="passwordsec" type="password" name="Passwordsec"
							placeholder="Repeat your Password" required>
					</center>
				</div>
			</fieldset>

			<fieldset id="actionBut">
				<input type="submit" id="submit" value="Log in">

			</fieldset>
		</div>
	</div>


	<div id="wrapper">

		<div id="header">

			<div id="nav">
				<ul>
					<li><a href="admin_page.jsp">Main</a></li>
					<li><a href="addcar.jsp">Add car</a></li>
					<li><a href="orderservices.jsp" class="active"><span
							style="color: #FFDEAD">Order service</span></a></li>
					<li><a href="addservices.jsp">Add service</a></li>
					<li><a href="index.html">Contacts</a></li>
					<div id="profile">
						<!--<div id="login-nav">
			<div id="login-ul">
			 <li id="login">
				  <a id="login-trigger" href="#">
					<span>Log in &#9660;</span>
				  </a>
				  <div id="login-content">
					<form action="controller" method="POST">
					  <fieldset id="inputs"  >
						<input id="username" type="email" name="email" value="email" placeholder="Your email address" required>   
						<input id="password" type="password" name="password" value="password" placeholder="Password" required>
					  </fieldset>
					  <fieldset id="actions">
						<input  type="submit" id="submit" value="SignIn" name="command">
						<label><input type="checkbox" checked="checked"> <span style="color: #FFDEAD">Keep me signed in</span></label>
					  </fieldset>
					</form>
				  </div>                     
				</li>
				<li id="signMeUp">
				<center><button class="myButton" onclick="show('block')"><a>Sign Up</a></button></center>		
				</li>
				</div>
			</div>-->

						<!-- Пользователь -->

						<div id="prof_photo">
							<img src="../login\jsp\img\ava.png" alt="photo">
						</div>
						<div id="nickname">
							<a href="#">Admin</a>
						</div>
						<a href="#" id="log_out">Log out</a>
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
