<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">

        <div class="alert alert-success">
            Du bist fast fertig!
            Bitte sortiere die Skriptdokumente per drag'n'drop, sofern es eine sinnvolle Reihenfolge gibt.
            Schick es ab, sobald du dich dazu bereit fühlst.
        </div>

        <h2>Skript einschicken - Schritt 4. Zusammenfassung</h2>

        <div class="form-horizontal">
            <div class="form-group">
                <label for="category" class="col-sm-2 control-label">Kategorie</label>
                <div class="col-sm-3">
                    <select disabled class="form-control">
                        <option>${script.category.name}</option>
                    </select>
                </div>
            </div>
    
            <div class="form-group">
                <label for="lectures" class="col-sm-2 control-label">Vorlesung(en)</label>
                <div class="col-sm-3">
                    <select disabled class="form-control" multiple>
                        <c:forEach items="${lectures}" var="lecture">
                            <option>${lecture.lectureAndProfessor}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
    
            <form method="post" action="" style="display: inline;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Skript-Dokumente (bitte sortieren)</h3>
                    </div>
                    <div class="panel-body">
                        <div id="sort-scripts">
                            <c:forEach items="${scriptDocuments}" var="document">
                                <p class="sortable bg-info natural-padding">
                                    <strong>::</strong> ${document.filename} (${document.fileSizeFormatted})
                                    <input type="hidden" name="scriptDocumentSort[]" value="${document.hashvalue}" />
                                </p>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button id="submit" class="btn btn-primary btn-lg" type="submit">Abschicken</button>
            </form>
            <button class="btn btn-danger">Abbrechen TODO</button>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
