<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form:form action="" modelAttribute="lecture" method="POST" cssClass="form-horizontal">
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-6">
            <form:input id="name" path="name" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="readingProfessor" class="col-sm-2 control-label">Professor</label>
        <div class="col-sm-6">
            <form:select id="readingProfessor" path="readingProfessor" cssClass="form-control">
                <form:options itemValue="id" itemLabel="fullName" items="${professors}" />
            </form:select>
        </div>
    </div>
    
    <div class="form-group">
        <label for="semesterType" class="col-sm-2 control-label">Semestertyp</label>
        <div class="col-sm-6">
            <form:select id="semesterType" path="semesterType" cssClass="form-control">
                <form:options itemLabel="name" />
            </form:select>
        </div>
    </div>
    
    <div class="form-group">
        <label for="semesterYear" class="col-sm-2 control-label">Jahr</label>
        <div class="col-sm-6">
            <form:input id="semesterYear" path="semesterYear" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="studyProgram" class="col-sm-2 control-label">Studiengang</label>
        <div class="col-sm-6">
            <form:select id="studyProgram" path="studyProgram" cssClass="form-control">
                <form:options itemLabel="name" />
            </form:select>
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary">Speichern</button>
        </div>
    </div>
    
</form:form>
