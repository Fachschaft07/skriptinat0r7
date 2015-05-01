<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Skript einschicken - Schritt 1</h2>
        <form:form action="" modelAttribute="script" method="POST" cssClass="form-horizontal">
            
            <t:input path="name" label="Name des Skripts" required="true" />
            
            <div class="form-group">
                <label for="category" class="col-sm-2 control-label">Kategorie</label>
                <div class="col-sm-5">
                    <form:select id="category" path="category" cssClass="form-control">
                        <form:options itemLabel="name" />
                    </form:select>
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static text-danger"><form:errors path="category" /></p>
                </div>
            </div>
            
            <div class="form-group">
                <label for="lectures" class="col-sm-2 control-label">Vorlesung(en) *</label>
                <div class="col-sm-5">
                    <form:select id="lectures" path="lectures" cssClass="form-control">
                        <form:options itemValue="id" itemLabel="lectureAndProfessor" items="${lectures}" />
                    </form:select>
                </div>
                <div class="col-sm-5">
                    <p class="form-control-static text-danger"><form:errors path="lectures" /></p>
                </div>
            </div>
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-primary">Weiter</button>
                </div>
            </div>
            
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>
