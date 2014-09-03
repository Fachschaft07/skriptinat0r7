<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="${controller == 'IndexController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}">Home</a></li>
		<li class="${controller == 'ScriptsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts">Scripts</a></li>
		<li class="${controller == 'ProfessorsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/professors">Professoren / Lehrbeauftragte</a></li>
		<li class="${controller == 'AboutController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/about">About</a></li>
	</ul>
</div>
