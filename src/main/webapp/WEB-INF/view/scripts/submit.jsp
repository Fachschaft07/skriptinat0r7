<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<h2>Skript einschicken</h2>
		<c:if test="${submitted}">
			<p>Ihr Skript wurde erfolgreich hochgeladen. Wir werden es reviewen und gegebenenfalls zum Druck bereit stellen.</p>
		</c:if>
		<form:form action="" modelAttribute="script" method="POST" cssClass="form-horizontal">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name des Skripts</label>
				<div class="col-sm-6">
					<form:input id="name" path="name" cssClass="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="category" class="col-sm-2 control-label">Kategorie</label>
				<div class="col-sm-6">
					<form:select id="category" path="category" cssClass="form-control">
						<form:options itemLabel="name" />
					</form:select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="authors" class="col-sm-2 control-label">Autor(en)</label>
				<div class="col-sm-6">
					<input id="authors" disabled value="TODO" class="form-control" />
					<%-- <form:input id="name" path="name" cssClass="form-control" /> --%>
				</div>
			</div>
			
			<div class="form-group">
				<label for="lectures" class="col-sm-2 control-label">Vorlesung(en)</label>
				<div class="col-sm-6">
					<form:select id="lectures" path="lectures" cssClass="form-control">
						<form:options itemValue="id" itemLabel="name" items="${lectures}" />
					</form:select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-primary">Senden</button>
				</div>
			</div>
			
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
