<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form:form action="" modelAttribute="professor" method="POST" cssClass="form-horizontal">

    <t:input path="email" label="eMail" />
    <t:input path="firstName" label="Vorname" />
    <t:input path="lastName" label="Nachname" />
    <t:input path="title" label="Titel" />

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary">Speichern</button>
            <c:if test="${professor.id != null}">
                <a data-confirm href="${pageContext.request.contextPath}/professors/delete/${professor.id}" class="btn btn-danger">Löschen</a>
            </c:if>
        </div>
    </div>

</form:form>
