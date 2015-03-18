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
                <form:form action="script-documents/unlock" method="POST" cssClass="form-horizontal">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Dateiname</th>
                                <th>Passwort</th>
                                <th>Gr��e</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${documents}" var="document">
                                <tr>
                                    <td><input type="checkbox" name="script[]" value="${document.hashvalue}" /></td>
                                    <td><a href="${pageContext.request.contextPath}/script-documents/download/${document.hashvalue}">${document.filename}</a></td>
                                    <td>${document.password}</td>
                                    <td>${document.fileSizeFormatted}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button class="btn btn-success" name="action" value="accept">Freischalten</button>
                    <button class="btn btn-danger" name="action" value="decline">Ablehnen</button>
                </form:form>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Skriptdokumente.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
