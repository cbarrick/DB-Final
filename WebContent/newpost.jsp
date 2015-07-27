<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
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
					<h1>New Post</h1>
				</header>
				<form action="<s:url action='createpost'/>" method="post">
					<div class="form-group">
						<label for="title-input">Title</label>
						<input id="title-input" class="form-control" type="text" name="title">
					</div>
					<div class="form-group">
						<label for="text-input">Body</label>
						<textarea id="text-input" class="form-control" type="text" name="text" rows="10"></textarea>
					</div>
					<button class="btn btn-default">Submit</button>
				</form>
			</section>
		</main>

	</body>
</html>
