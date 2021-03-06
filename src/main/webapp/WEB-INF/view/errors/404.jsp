<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>404 - Nicht gefunden</h2>
        <p>Die gesuchte Seite existiert nicht. Wenn Sie dies für einen Fehler halten, kontaktieren Sie uns über "About" in der Navigation.</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
