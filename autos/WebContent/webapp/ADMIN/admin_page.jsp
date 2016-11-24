
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
					<li><a href="admin_page.jsp" class="active"><span
							style="color: #FFDEAD">Main</span></a></li>
					<li><a href="addcar.jsp">Add car</a></li>
					<li><a href="orderservices.jsp">Order service</a></li>
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
				<div class="scroll_block">
					<table id="Users" align="center">
						<tr>
							<td colspan="7"><span class="table_title">Users</span></td>
						</tr>
						<tr>
							<td class="cars_table_title">ID</td>
							<td class="cars_table_title">Username</td>
							<td class="cars_table_title">Password</td>
							<td class="cars_table_title">ID Role</td>
							<td class="cars_table_title">Role</td>
						</tr>
						<tr>
							<td>BMW</td>
							<td>M6</td>
							<td>qwerty</td>
							<td>qwerty</td>
							<td>qwerty</td>
						</tr>
						<tr>
							<td>Volkswagen</td>
							<td>Passat</td>
							<td>qwerty</td>
							<td>qwerty</td>
							<td>qwerty</td>
						</tr>
						<tr>
							<td>Nissan</td>
							<td>GT R-35</td>
							<td>qwerty</td>
							<td>qwerty</td>
							<td>qwerty</td>
						</tr>
						<tr>
							<td>Kia</td>
							<td>Sorento</td>
							<td>qwerty</td>
							<td>qwerty</td>
							<td>qwerty</td>
						</tr>
						<tr>
							<td>Toyota</td>
							<td>Tundra</td>
							<td>qwerty</td>
							<td>qwerty</td>
							<td>qwerty</td>
						</tr>
					</table>

				</div>

				<div style="margin: 30px auto 0 auto; width: 800px;">
					<input type="text" class="input" placeholder="ID"> <input
						type="text" class="input" placeholder="Login"> <input
						type="text" class="input" placeholder="Password"> <select
						size="1">
						<option selected>ID Role</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select> <select size="1">
						<option selected>Role</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				</div>
				<div style="margin: 5px auto; width: 350px;">
					<input id="input_submit" type="submit" value="Add"> <input
						id="input_submit" type="submit" value="Edit"> <input
						id="input_submit" type="submit" value="Delete">
				</div>

				<div class="scroll_block">
					<table id="Services" align="center">
						<tr>
							<td colspan="7"><span class="table_title">Services</span></td>
						</tr>
						<tr>
							<td class="cars_table_title">Brand name</td>
							<td class="cars_table_title">Model</td>
							<td class="cars_table_title">About</td>
							<td class="cars_table_title">Account number</td>
							<td class="cars_table_title">Type of service</td>
							<td class="cars_table_title">Price ($)</td>
							<td class="cars_table_title">Date</td>
						</tr>
						<tr>
							<td>BMW</td>
							<td>M6</td>
							<td>qwerty</td>
							<td>10102015</td>
							<td>example 1</td>
							<td>200</td>
							<td>20.02.2015</td>
						</tr>
						<tr>
							<td>Volkswagen</td>
							<td>Passat</td>
							<td>qwerty</td>
							<td>55606616</td>
							<td>example 2</td>
							<td>500</td>
							<td>02.07.2015</td>
						</tr>
						<tr>
							<td>Nissan</td>
							<td>GT R-35</td>
							<td>qwerty</td>
							<td>56514864</td>
							<td>example 3</td>
							<td>1500</td>
							<td>05.09.2015</td>
						</tr>
						<tr>
							<td>Kia</td>
							<td>Sorento</td>
							<td>qwerty</td>
							<td>10102015</td>
							<td>example 4</td>
							<td>2000</td>
							<td>13.10.2015</td>
						</tr>
						<tr>
							<td>Toyota</td>
							<td>Tundra</td>
							<td>qwerty</td>
							<td>55606616</td>
							<td>example 5</td>
							<td>2500</td>
							<td>25.12.2015</td>
						</tr>
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
							<td class="cars_table_title">Brand name</td>
							<td class="cars_table_title">Model</td>
						</tr>
						<tr>
							<td>BMW</td>
							<td>M6</td>
						</tr>
						<tr>
							<td>Volkswagen</td>
							<td>Passat</td>
						</tr>
						<tr>
							<td>Nissan</td>
							<td>GT R-35</td>
						</tr>
					</table>
				</div>


			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
