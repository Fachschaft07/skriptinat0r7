<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
    
        <div class="clearfix">
            <h2 class="pull-left">Skript ${script.name}</h2>
            <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
                <a class="btn btn-primary hidden-xs pull-right" href="${pageContext.request.contextPath}/scripts/${script.id}/edit">
                    <span class="glyphicon glyphicon-pencil"></span> Bearbeiten
                </a>
            </sec:authorize>
        </div>
    
        <p>Folgende Dateien sind Teil dieses Skripts.</p>
        <ul class="list-group">
            <c:forEach var="document" items="${documents}">
                <c:if test="${document.isPublic()}">
                    <li class="list-group-item list-group-item-success">${document.filename}</li>
                </c:if>
                <c:if test="${!document.isPublic()}">
                    <li class="list-group-item list-group-item-danger">${document.filename}</li>
                </c:if>
            </c:forEach>
        </ul>
        <small>Legende: <span class="text-success">bestellbar</span>, <span class="text-danger">gesperrt</span></small>
    </tiles:putAttribute>
</tiles:insertDefinition>
