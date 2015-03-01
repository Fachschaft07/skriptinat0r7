<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Skriptindex</h2>
        <c:choose>
            <c:when test="${ ! lectures.isEmpty()}">
                <table class="table table-hover clickable">
                    <thead>
                        <tr>
                            <th>Fach</th>
                            <th>Professor</th>
                            <th>Anzahl Skripte</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lectures}" var="lecture">
                            <tr data-href="${pageContext.request.contextPath}/scripts/lecture/${lecture.id}">
                                <td>${lecture.name}</td>
                                <td>${lecture.readingProfessor.fullName}</td>
                                <td>${lecture.usedScripts.size()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Skripte.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
