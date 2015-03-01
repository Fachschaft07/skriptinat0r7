<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <div class="clearfix">
            <h2 class="pull-left">Skriptdokumente verwalten</h2>
        </div>
        <c:choose>
            <c:when test="${ ! documents.isEmpty()}">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Dateiname</th>
                            <th>Größe</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${documents}" var="document">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/script-documents/download/${document.hashvalue}">${document.filename}</a></td>
                                <td>${document.fileSizeFormatted}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Skriptdokumente.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
