<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>500 - Interner Serverfehler</h2>
        <p>
            Ups, das h�tte nicht passieren d�rfen. Der Fehler wurde aufgezeichnet und wird untersucht werden.
            Kontaktieren Sie uns �ber "About" in der Navigation.
        </p>
    </tiles:putAttribute>
</tiles:insertDefinition>
