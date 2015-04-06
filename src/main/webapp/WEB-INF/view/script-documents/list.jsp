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
                                <th><input type="checkbox" class="check-all" data-target="input[name='script[]']" /></th>
                                <th>Dateiname</th>
                                <th>Passwort</th>
                                <th>Größe</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${documents}" var="document">
                                <tr>
                                    <td><input type="checkbox" name="script[]" value="${document.hashvalue}" /></td>
                                    <td><a href="${pageContext.request.contextPath}/script-documents/download/${document.hashvalue}" target="_blank">${document.filename}</a></td>
                                    <td>${document.password}</td>
                                    <td>${document.fileSizeFormatted}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${document.reviewState == 'LOCKED'}">
                                                <span class="label label-warning">Ungeprüft</span>
                                            </c:when>
                                            <c:when test="${document.reviewState == 'DELETED'}">
                                                <span class="label label-danger">Gesperrt</span>
                                            </c:when>
                                            <c:when test="${document.reviewState == 'FACHSCHAFTLERAPPROVED' || document.reviewState == 'PROFESSORAPPROVED'}">
                                                <span class="label label-success">Freigegeben</span>
                                            </c:when>
                                            <c:otherwise>
                                                ${document.reviewState}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button class="btn btn-success" name="action" value="accept">Freischalten</button>
                    <button class="btn btn-danger" name="action" value="decline" data-confirm>Ablehnen</button>
                </form:form>
            </c:when>
            <c:otherwise>
                <p>Es existieren bisher keine Skriptdokumente.</p>
            </c:otherwise>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>
