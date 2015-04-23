<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

        <div class="clearfix">
            <h2 class="pull-left">Skript ${script.name}</h2>
            <sec:authorize access="hasRole('ROLE_FACHSCHAFTLER')">
                <a class="btn btn-primary hidden-xs pull-right" href="${pageContext.request.contextPath}/scripts/${script.id}/edit">
                    <span class="glyphicon glyphicon-pencil"></span> Bearbeiten
                </a>
            </sec:authorize>
        </div>

        <p>Folgende Dateien sind Teil dieses Skripts.</p>
        <table class="table">
            <thead>
                <tr>
                    <th><input type="checkbox" class="check-all" data-target=".orderable-script" /></th>
                    <th class="col-md-6">Dateiname</th>
                    <th class="col-md-6">Sonstiges</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="document" items="${documents}">
                    <tr>
                        <c:if test="${document.isPublic()}">
                            <tr class="success">
                                <td>
                                    <input type="checkbox" class="orderable-script" name="script_document[]" form="order-single" value="${document.hashvalue}" />
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/script-documents/download/${document.hashvalue}">
                                        <span class="glyphicon glyphicon-download-alt"></span>
                                        ${document.filename}
                                    </a>
                                </td>
                                <td>
                                    <c:if test="${document.hasPassword()}">Passwortgeschützt</c:if>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${!document.isPublic()}">
                            <tr class="danger">
                                <td>
                                    <input type="checkbox" readonly />
                                </td>
                                <td>
                                    ${document.filename}
                                </td>
                                <td>
                                    Gesperrt
                                </td>
                            </tr>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <small>Legende: <span class="text-success">bestellbar</span>, <span class="text-danger">gesperrt</span></small>

        <div>
            <button form="order-all" class="btn btn-large btn-primary">Alle bestellbaren Skripte bestellen</button>
            <button form="order-single" class="btn btn-large btn-primary">Nur markierte bestellen</button>
            <form id="order-all" action="${pageContext.request.contextPath}/orders/order-script" method="post">
                <c:forEach var="document" items="${documents}">
                    <c:if test="${document.isPublic()}">
                        <input type="hidden" name="script_document[]" value="${document.hashvalue}" />
                    </c:if>
                </c:forEach>
            </form>
            <form id="order-single" action="${pageContext.request.contextPath}/orders/order-script" method="post" />
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
