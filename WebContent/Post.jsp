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

		<jsp:include page="navbar.jsp"></jsp:include>

		<main class="container">
			<article>
				<header class="page-header">
					<h1><s:property value="post.postTitle"/></h1>
					<time><s:property value="post.timestamp"/></time>
				</header>
				<div class="post">
					<p><s:property value="post.postText"/></p>
				</div>
			</article>
		</main>

		<section id="comments" class="container">
			<div class="panel panel-default">
				<header class="panel-heading">
					<h3 class="panel-title">Comments</h3>
				</header>

				<ol class="list-group">
					<s:iterator value="comments">
						<li class="panel-body list-group-item">
							<s:property value="commentText"/>
						</li>
					</s:iterator>
				</ol>

				<s:if test="%{getLoggedIn()}">
					<form class="panel-body" action="{{new-comment-url}}" method="post">
						<input type="hidden" name="pid" value="<s:property value="post.postID"/>">
						<div class="form-group">
							<label for="new-comment-text">New Comment</label>
							<textarea id="new-comment-text" class="form-control" name="text" placeholder="Comment"></textarea>
						</div>
						<button id="new-comment-submit" class="btn btn-default">Submit</button>
					</form>
				</s:if>
			</div>
		</section>

	</body>
</html>
