<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.Arrays" %>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="${controller == 'IndexController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}">Home</a></li>
        <li class="${controller == 'ScriptsController' && method.startsWith('addScript') ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts/submit">Skript einschicken</a></li>
        <li class="${controller == 'ScriptsController' && ('showScriptsByLectures' == method || 'showLectureDetail' == method) ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts">Skripte</a></li>
        <li class="${controller == 'SearchController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/search/advanced">Erweiterte Suche</a></li>
        <li class="${controller == 'AboutController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/about">About</a></li>
    </ul>
    <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
        <hr class="divider" />
        <ul class="nav nav-sidebar">
            <li class="${controller == 'LecturesController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/lectures">Vorlesungen verwalten</a></li>
            <li class="${controller == 'ProfessorsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/professors">Lehrende verwalten</a></li>
            <li class="${controller == 'ScriptsController' && method == 'showScriptSubmissions' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts/show-submissions">Skripteinsendungen verwalten</a></li>
        </ul>
    </sec:authorize>
</div>
