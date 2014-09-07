<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Professor anlegen</h2>
        <jsp:include page="form.jsp" />
    </tiles:putAttribute>
</tiles:insertDefinition>
