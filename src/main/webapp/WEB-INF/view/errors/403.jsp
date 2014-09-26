<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>403 - Nicht autorisiert</h2>
        <p>Sie haben nicht die notwendigen Rechte, diese Seite zu betreten. Wenn Sie dies für einen Fehler halten, kontaktieren Sie uns über "About" in der Navigation.</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
