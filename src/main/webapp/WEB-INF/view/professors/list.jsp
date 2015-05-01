<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <div class="clearfix">
            <h2 class="pull-left">Professoren</h2>
            <a href="${pageContext.request.contextPath}/professors/add" class="pull-right btn btn-default hidden-xs">
                <span class="glyphicon glyphicon-plus"></span> Professor anlegen
            </a>
        </div>
        <c:choose>
            <c:when test="${ ! professors.isEmpty()}">
                <table class="table table-hover clickable">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Vorlesungen</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${professorsAndLectures}" var="professorAndLecture">
                            <tr data-href="${pageContext.request.contextPath}/professors/edit/${professorAndLecture['professor'].id}">
                                <td>${professorAndLecture['professor'].fullName}</td>
                                <td>${professorAndLecture['lectures']}</td>
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
