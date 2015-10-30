<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="searchcode is a free source code and documentation search engine. API documentation, code snippets and open source (free sofware) repositories are indexed and searchable.">
<link rel="shortcut icon" href="/static/favicon.ico" type="image/x-icon" />
<title>Search for undo redo texteditor | source code search
	engine</title>
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


	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h5>About 112 results</h5>
				<div id="documentation-results"></div>
				<div class="code-result" data-id="12191000">
					<div>
						<h5>
							<a href="/codesearch/view/12191000/">TextEditor.java in
								textmash</a> <small>
								http://textmash.googlecode.com/svn/trunk/ | 1271 lines | Java</small> <small><a
								href="#" class="view-similar-link hidden"></a></small>
						</h5>
					</div>
					<ol class="code-result">
						<li value="42"></li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<div class="common enterprise">
		<h2>Java</h2>
		<pre>
			
<%
				if (request.getAttribute("code") == null)
					return;
String[] list = (String[]) request.getAttribute("code");
for (String code : list) {
%>
<code class="java"> <% out.print(code); %> </code>
<%
}
%>
		</pre>
	</div>


</body>
</html>