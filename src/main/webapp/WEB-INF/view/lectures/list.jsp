<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <div class="clearfix">
            <h2 class="pull-left">Vorlesungen</h2>
            <a href="${pageContext.request.contextPath}/lectures/add" class="pull-right btn btn-default hidden-xs">
                <span class="glyphicon glyphicon-plus"></span> Vorlesung anlegen
            </a>
        </div>
        <c:choose>
            <c:when test="${ ! lectures.isEmpty()}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Studiengruppe</th>
                            <th>Professor</th>
                            <th>Semester</th>
                            <th>Anzahl Skripten</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lectures}" var="lecture">
                            <tr>
                                <td>${lecture.name}</td>
                                <td>${lecture.studyProgram.name}</td>
                                <td>${lecture.readingProfessor.fullName}</td>
                                <td>${lecture.semesterType}${lecture.semesterYear}</td>
                                <td>todo</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Vorlesungen.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
