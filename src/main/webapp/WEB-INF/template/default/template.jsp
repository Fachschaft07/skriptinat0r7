<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="header-nav" />
<div class="container body">
	<div class="row" style="height: 100%">
		<div class="col-sm-3 box">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="col-sm-9 box">
			<div class="content box-shadow">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
</div>
<tiles:insertAttribute name="footer" />
