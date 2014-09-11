<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Skript einschicken - Schritt 2. Alle Dateien auswählen und hochladen</h2>
        <form:form action="${pageContext.request.contextPath}/scripts/submit/files/${id}?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
            <input name="files[]" type="file" multiple />
            <button class="btn btn-primary">Hochladen</button>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>
