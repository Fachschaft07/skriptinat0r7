<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Starter Template for Bootstrap</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="container body">
			<div class="row" style="height: 100%">
				<div class="col-sm-3 box">
					<div class="menu box-shadow">
						<tiles:insertAttribute name="menu" />
					</div>
				</div>
				<div class="col-sm-9 box">
					<div class="content box-shadow">
						<tiles:insertAttribute name="content" />
					</div>
				</div>
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>