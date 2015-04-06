<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Dateien hochladen - Schritt 2</h2>
        <form:form action="${pageContext.request.contextPath}/scripts/submit/files/${id}?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input name="files[]" type="file" multiple />
            </div>
            <div class="form-group">
                <button class="btn btn-primary">Hochladen</button>
            </div>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>
