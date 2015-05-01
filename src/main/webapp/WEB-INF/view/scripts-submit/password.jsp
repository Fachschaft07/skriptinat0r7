<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Passwörter - Schritt 3</h2>
        <form action="${pageContext.request.contextPath}/scripts-submit/password/${id}" method="post">
            <jsp:include page="/WEB-INF/partials/script-documents-passwords.jsp" />
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>
