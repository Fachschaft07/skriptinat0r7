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
        TODO: is pw locked?
    </tiles:putAttribute>
</tiles:insertDefinition>
