<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<nav class="navbar navbar-default container-fluid">
			<div class="navbar-header">
		<a class="navbar-brand" href="<s:url action='archive'/>?p=1">Blog</a>
	</div>
	<div class="collapse navbar-collapse">
		<div class="navbar-left">
			<form class="navbar-form" role="search" action="<s:url action='search'/>" method="get">
				<div class="form-group">
					<input type="text" class="form-control" name="q" placeholder="Search">
				</div>
			</form>
		</div>
		<div class="navbar-right">
			<s:if test="%{!getLoggedIn()}">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<form id="login-form" action="<s:url action='loginaction'/>" method="post"></form>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Login <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><input type="text" class="form-control" form="login-form" name="user" placeholder="email"></li>
							<li><input type="password" class="form-control" form="login-form" name="password" placeholder="password"></li>
							<li><button class="btn btn-default" form="login-form">Login</button></li>
						</ul>
					</li>
					<li><a href="<s:url action='signup' />">Signup</a></li>
				</ul>
			</s:if>
			<s:else>
				<p class="navbar-text">Signed in as <s:property value="currentUser"/></p>
				<a class="btn btn-default navbar-btn" href="<s:url action='logout'/>">Logout</a>
			</s:else>
		</div>
		<s:if test="%{getLoggedIn()}">
			<a class="btn btn-default navbar-btn" href="<s:url action='newpost'/>">New Post</a>
		</s:if>
	</div>
</nav>