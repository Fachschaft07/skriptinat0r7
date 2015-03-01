<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Passwörter - Schritt 3</h2>
        <form action="${pageContext.request.contextPath}/scripts/submit/password/${id}" method="post">
            <p>Folgenden Dokumente sind verschlüsselt und benötigen ein Passwort:</p>
            <ul>
                <c:forEach items="${documentsWithMissingPassword}" var="document">
                    <li class="text-warning">${document.filename}</li>
                </c:forEach>
            </ul>
            
            <p>Folgende Dokumente konnten wir entschlüsseln / benötigten kein Passwort:</p>
            <ul>
                <c:forEach items="${documentsWithKnownPassword}" var="document">
                    <li class="text-success">${document.filename}</li>
                </c:forEach>
            </ul>
            
            <p>Bitte gib in folgendes Formular die notwendigen Passwörter ein. Eines pro Zeile, die Reihenfolge ist egal.</p>
            <div class="form-group">
                <textarea name="passwords" class="form-control" rows="${documentsWithMissingPassword.size()}"></textarea>
            </div>
            
            <div class="form-group">
                <button class="btn btn-primary" ><span class="glyphicon glyphicon-lock"></span> Entschlüsseln</button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>
