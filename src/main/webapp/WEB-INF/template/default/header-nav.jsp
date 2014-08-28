<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}">Scriptinat<span class="cool-number">0</span>r<span class="cool-number">7</span></a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">trampusc@hm.edu <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Einstellungen</a></li>
						<li><a href="${pageContext.request.contextPath}/logout">Abmelden</a></li>
					</ul>
				</li>
			</ul>
			<form class="navbar-form navbar-right" role="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Suche">
					<span class="input-group-btn">
						<button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Erweiterte Suche</a></li>
						</ul>
					</span>
				</div>
			</form>
		</div>
	</div>
</div>