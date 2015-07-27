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
					<h1><s:property value="pageTitle"/></h1>
				</header>
				
				<div class="list-group">				
					<s:iterator value="posts">
						<a class="list-group-item" href="<s:url action='post'/>?id=<s:property value="postID"/>">
							<h2><s:property value="postTitle"/> <small><s:property value="timestamp"/></small></h2>
						</a>
					</s:iterator>
				</div>
			</section>
			<nav>
				<ul class="pager">
					<li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> Previous</a></li>
					<li class="next disabled"><a href="#">Next <span aria-hidden="true">&rarr;</span></a></li>
				</ul>
			</nav>
		</main>

	</body>
</html>
