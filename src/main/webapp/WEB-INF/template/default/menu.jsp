<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="${controller == 'IndexController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}">Home</a></li>
        <li class="${controller == 'ScriptsController' && method.startsWith('submit') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts/submit">Skript einschicken</a></li>
        <li class="${controller == 'ScriptsController' && method == 'getAllScripts' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts">Skripte</a></li>
        <li class="${controller == 'ProfessorsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/professors">Professoren / Lehrbeauftragte</a></li>
        <li class="${controller == 'SearchController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/search/advanced">Erweiterte Suche</a></li>
        <li class="${controller == 'AboutController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/about">About</a></li>
    </ul>
    <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
        <hr class="divider" />
        TODO: admin stuff here
    </sec:authorize>
</div>
