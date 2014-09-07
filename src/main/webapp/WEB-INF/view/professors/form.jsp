<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form:form action="" modelAttribute="professor" method="POST" cssClass="form-horizontal">
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">eMail</label>
        <div class="col-sm-6">
            <form:input id="email" path="email" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="firstName" class="col-sm-2 control-label">Vorname</label>
        <div class="col-sm-6">
            <form:input id="firstName" path="firstName" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">Nachname</label>
        <div class="col-sm-6">
            <form:input id="lastName" path="lastName" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">Titel</label>
        <div class="col-sm-6">
            <form:input id="title" path="title" cssClass="form-control" />
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary">Speichern</button>
        </div>
    </div>
    
</form:form>
