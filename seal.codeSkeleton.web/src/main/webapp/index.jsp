<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="searchcode is a free source code and documentation search engine. API documentation, code snippets and open source (free sofware) repositories are indexed and searchable.">
<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
<title>Structured Code Search Engine</title>
<link rel="stylesheet" href="css/default.min.css">
<script src="js/highlight.min.js"></script>
<script>
	hljs.initHighlightingOnLoad();
</script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/1.css" rel="stylesheet" />
<link href="css/jquery.nouislider.css" rel="stylesheet" />
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/0.js"></script>
<script src="js/jquery.nouislider.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/typeahead.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<form class="navbar-form navbar-left" role="search" id="top-search"
			action="userQuery" method="POST">
			<div class="input-group">
				<input name="query" type="text" class="form-control" value="">
				<span class="input-group-btn">
					<button type="submit" class="btn btn-primary">search</button>
				</span>
			</div>
		</form>

	</nav>

	<%
		if (request.getAttribute("code") == null)
			return;
		//String[] examples = (String[]) request.getAttribute("code");
			String[][] examples = (String[][]) request.getAttribute("code");
			for (int i = 0; i < examples[0].length; i++) {
	%>
	<div style="height: 1000px; overflow-y: scroll;">
		<h2>
			Cluster
			<%
			out.print(i+1);
		%>
		</h2>
		<div class="container" style="float: left; width: 700px;">
			<%
				out.print(examples[0][i]);
			%>
		</div>
		<div class="container" style="float: right; width: 700px;">
			<%
			out.print(examples[1][i]);
			%>

		</div>
	</div>

	<%
		}
	%>
</body>
</html>