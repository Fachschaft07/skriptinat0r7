<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>About:Scriptinat0r7</h2>
        <p>&copy; ${year} Fachschaft der Fakultät 07 an der Hochschule
            München.</p>
        <p>Mitwirkende:</p>
        <ul class="list-unstyled">
            <c:forEach var="contributor" items="${contributors}">
                <li>${contributor}</li>
            </c:forEach>
        </ul>
    </tiles:putAttribute>
</tiles:insertDefinition>
