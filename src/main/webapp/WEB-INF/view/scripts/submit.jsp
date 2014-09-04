<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<h2>Skript einschicken</h2>
		<c:if test="${submitted}">
			<p>Ihr Skript wurde erfolgreich hochgeladen. Wir werden es reviewen und gegebenenfalls zum Druck bereit stellen.</p>
		</c:if>
		<form:form action="" modelAttribute="script" method="POST">
			<form:input path="name" cssClass="input" />
			<input type="submit" />
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
