<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <div class="clearfix">
            <h2 class="pull-left">Skripte zur Vorlesung ${lecture.name} bei ${lecture.readingProfessor.fullName}</h2>
            <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
                <a class="btn btn-primary hidden-xs pull-right" href="${pageContext.request.contextPath}/lectures/edit/${lecture.id}">
                    <span class="glyphicon glyphicon-pencil"></span> Bearbeiten
                </a>
            </sec:authorize>
        </div>
        
        <p>Zu dieser Vorlesung existieren folgende Skripte:</p>
        <table class="table clickable table-hover">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Anzahl Dokumente</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${scripts}" var="script">
                    <tr data-href="${pageContext.request.contextPath}/scripts/${script.id}">
                        <td>${script.name}</td>
                        <td>${script.scriptDocuments.size()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>
