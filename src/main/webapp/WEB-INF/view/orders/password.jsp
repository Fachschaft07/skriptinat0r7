<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Skriptbestellung - Passworteingabe</h2>
        <p>Du bist fast fertig! Leider sind manche Skripte zugriffsgeschützt.</p> 
        <form action="${pageContext.request.contextPath}/orders" method="post">
            <jsp:include page="/WEB-INF/partials/script-documents-passwords.jsp" />
            <c:forEach items="${documents}" var="document">
                <input type="hidden" name="script_document[]" value="${document.hashvalue}" />
            </c:forEach>
            <input type="hidden" name="script" value="${script.id}" />
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>
