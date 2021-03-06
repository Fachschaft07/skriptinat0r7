<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="header-nav" />
<div class="container-fluid body">
    <div class="row" style="height: 100%">
        <div class="col-xs-3">
            <tiles:insertAttribute name="sidebar" />
        </div>
        <div class="col-xs-9">
            <div class="content box-shadow">
                <jsp:include page="flash-messages.jsp" />
                <tiles:insertAttribute name="content" />
            </div>
        </div>
    </div>
</div>
<tiles:insertAttribute name="footer" />
