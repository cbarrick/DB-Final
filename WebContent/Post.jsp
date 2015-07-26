<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8"/>
		<title><s:property value="title"/></title>

		<!-- CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

		<!-- JavaScript -->
		<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>

		<nav class="navbar navbar-default container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Blog</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#">Link</a></li>
					<li><a href="#">Link</a></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="username">
						<input type="password" class="form-control" placeholder="password">
					</div>
					<button id="login-btn" class="btn btn-default">Login</button>
					<button id="signup-btn" class="btn btn-default">Signup</button>
				</form>
			</div>
		</nav>

		<main class="container">
			<article>
				<header>
					<h1>{{title}}</h1>
					<time><s:property value="postTime"/></time>
				</header>
				<div class="post">
					<s:property value="text"/>"
				</div>
			</article>
		</main>

		<section id="comments" class="container">
			<div class="panel panel-default">
				<header class="panel-heading">
					<h3 class="panel-title">Comments</h3>
				</header>

				<div class="list-group">
					<div class="list-group-item">
						{{comment-text}}
					</div>
					<div class="list-group-item">
						{{comment-text}}
					</div>
					<div class="list-group-item">
						{{comment-text}}
					</div>
				</div>

				<form class="panel-body">
					<div class="form-group">
						<label for="new-comment-text">New Comment</label>
						<textarea id="new-comment-text" class="form-control" placeholder="Comment"></textarea>
					</div>
					<button id="new-comment-submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</section>

	</body>
</html>
