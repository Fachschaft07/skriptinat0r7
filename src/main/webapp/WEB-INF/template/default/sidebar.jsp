<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.Arrays" %>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="${controller == 'IndexController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}">Home</a></li>
        <li class="${controller == 'ScriptsSubmitController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts-submit">Skript einschicken</a></li>
        <li class="${controller == 'ScriptsController' && method != 'showScriptSubmissions' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts">Vorlesungen</a></li>
        <li class="${controller == 'SearchController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/search/advanced">Erweiterte Suche</a></li>
        <li class="${controller == 'AboutController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/about">About</a></li>
    </ul>
    <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
        <hr class="divider" />
        <ul class="nav nav-sidebar">
            <li class="${controller == 'LecturesController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/lectures">Vorlesungen verwalten</a></li>
            <li class="${controller == 'ProfessorsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/professors">Lehrende verwalten</a></li>
            <li class="${controller == 'ScriptsController' && method == 'showScriptSubmissions' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/scripts/show-submissions">Skripteinsendungen verwalten</a></li>
            <li class="${controller == 'ScriptDocumentsController' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/script-documents">Skriptdokumente verwalten</a></li>
            <li class="${controller == 'OrdersManagementController' ? 'active' : ''}">
                <a href="${pageContext.request.contextPath}/manage-orders">
                    Bestellungen verwalten <span class="badge">${GLOBAL_ordersNotTransmittedToCopyShopCount}</span>
                </a>
            </li>
        </ul>
    </sec:authorize>
</div>
