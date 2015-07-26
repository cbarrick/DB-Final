<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
	<head>
		<meta charset="utf-8"/>
		<title>{{title}}</title>

		<!-- CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

		<!-- JavaScript -->
		<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>

		<jsp:include page="navbar.jsp"></jsp:include>

		<main class="container">
			<section>
				<header class="page-header">
					<h1>Signup</h1>
				</header>
				<form action="<s:url action='signupaction'/>" method="post">
					<input class="form-control" type="hidden" name="Role" value="Guest">
					<div class="form-group">
						<label for="email-input">Email</label>
						<input id="email-input" class="form-control" type="text" name="email">
					</div>
					<div class="form-group">
						<label for="password-input">Password</label>
						<input id="password-input" class="form-control" type="password" name="password">
					</div>
					<button class="btn btn-default">Submit</button>
				</form>
			</section>
		</main>

	</body>
</html>
