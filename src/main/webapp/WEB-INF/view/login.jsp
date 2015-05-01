<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="bare">
    <tiles:putAttribute name="content">
        <div class="container body col-sm-4 col-sm-offset-4">
            <div class="box content box-shadow">
                <h1 class="no-margin-top">Skriptenbestellsystem</h1>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        Ungültige Benutzerdaten. Haben Sie sich vertippt?
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        Sie wurden erfolgreich ausgeloggt.
                    </div>
                </c:if>
            
                <form action="<c:url value="/login.do"/>" method="post" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-4 control-label">Benutzername</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="username" id="username" value="ifw20000" placeholder="Benutzername">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-4 control-label">Passwort</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="password" value="ifw20000" id="passwort" placeholder="Passwort">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-10">
                            <input type="hidden"
                                name="${_csrf.parameterName}"
                                value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">Anmelden</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
