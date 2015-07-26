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
					<h1>{{title}}</h1>
					<time datetime="{{date-rfc3339}}">{{date-human}}</time>
				</header>
				<div class="post">
					<p>{{post-text}}</p>
					<p>Lorem ipsum dolor sit amet, usu ad diam facilisi disputationi, nec expetenda ocurreret principes ea, no volumus expetenda pri. Et inani erant consetetur ius, ius eu audire viderer lucilius, per oratio utinam salutatus id. Te illum saepe mei, est ex eius tibique. Aeque maiestatis usu at, oportere ocurreret eum at.</p>
					<p>Quo dicta scripserit at. No vel nobis persius liberavisse. Insolens incorrupte reprehendunt eum no, eos tibique vituperatoribus ea. Cu est magna adolescens, ad nec dicat facilisi signiferumque. Sed id idque deleniti suavitate, usu id mucius ullamcorper.</p>
					<p>Nec ei solet nominati necessitatibus, solum neglegentur sed te. Vidisse vocibus maluisset vel ne, at mea quis praesent, partem option consequuntur id usu. Cu eos ceteros salutandi, no nullam assueverit mea. Vix id percipitur necessitatibus, id duo persius eripuit.</p>
					<p>Ne his soleat persius tincidunt, case solet lobortis in eum. Molestie disputando eu est, quis dolores partiendo in eos. Ad purto adipisci nam, cibo gubergren ne ius. Sed etiam minimum percipit an, nulla tamquam ancillae cu vel.</p>
					<p>Sit erant platonem salutatus ex. Usu quis erant oportere no. Dicant facete fastidii at pro, illud bonorum quo et. Harum urbanitas suscipiantur quo ne, at ipsum vituperatoribus vim. Ad nam malis debet detraxit, quo an postea graecis, aliquam euripidis vis ne. Euismod utroque oportere pro et, id sanctus minimum maiestatis has. In lucilius dissentias mei, feugait omittam vix ne.</p>
				</div>
			</article>
		</main>

		<section id="comments" class="container">
			<div class="panel panel-default">
				<header class="panel-heading">
					<h3 class="panel-title">Comments</h3>
				</header>

				<ol class="list-group">
					<li class="panel-body list-group-item">
						{{comment-text}}
					</li>
					<li class="panel-body list-group-item">
						{{comment-text}}
					</li>
					<li class="panel-body list-group-item">
						{{comment-text}}
					</li>
				</ol>

				<form class="panel-body" action="{{new-comment-url}}" method="post">
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
