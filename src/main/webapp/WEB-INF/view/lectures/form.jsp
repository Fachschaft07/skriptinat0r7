<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form:form action="" modelAttribute="lecture" method="POST" cssClass="form-horizontal">

    <t:input path="name" required="true" label="Name"></t:input>
    
    <div class="form-group">
        <label for="readingProfessor" class="col-sm-2 control-label">Professor</label>
        <div class="col-sm-5">
            <form:select id="readingProfessor" path="readingProfessor" cssClass="form-control">
                <form:options itemValue="id" itemLabel="fullName" items="${professors}" />
            </form:select>
        </div>
        <div class="col-sm-5">
            <p class="text-danger"><form:errors path="readingProfessor" /></p>
        </div>
    </div>
    
    <div class="form-group">
        <label for="studyProgram" class="col-sm-2 control-label">Studiengang</label>
        <div class="col-sm-5">
            <form:select id="studyProgram" path="studyProgram" cssClass="form-control">
                <form:options itemLabel="name" />
            </form:select>
        </div>
        <div class="col-sm-5">
            <p class="text-danger"><form:errors path="studyProgram" /></p>
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary">Speichern</button>
            <c:if test="${lecture.id != null}">
                <a data-confirm href="${pageContext.request.contextPath}/lectures/delete/${lecture.id}" class="btn btn-danger">Löschen</a>
            </c:if>
        </div>
    </div>
    
</form:form>
