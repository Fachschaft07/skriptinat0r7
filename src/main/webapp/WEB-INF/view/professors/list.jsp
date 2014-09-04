<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Professoren</h2>
        <c:choose>
            <c:when test="${ ! professors.isEmpty()}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Vorlesungen</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${professors}" var="professor">
                            <tr>
                                <td>${professor.fullName}</td>
                                <td>Todo</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Professoren.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
