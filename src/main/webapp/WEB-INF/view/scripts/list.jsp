<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<h2>Skriptindex</h2>
		<c:choose>
			<c:when test="${ ! scripts.isEmpty()}">
				<table class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Autor</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${scripts}" var="script">
							<tr>
								<td>${script.name}</td>
								<td>Todo</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p>Es existieren bisher keine Skripte.</p>
			</c:otherwise>
		</c:choose>
	</tiles:putAttribute>
</tiles:insertDefinition>
