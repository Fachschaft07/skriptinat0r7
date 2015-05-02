<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="content">
        <h2>Bestellungen</h2>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" />
                    </th>
                    <th>Besteller</th>
                    <th>Skriptdokumente</th>
                    <th>Bestelldatum</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ordersNotAtCopyShop}" var="order">
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td>${order.orderer.facultyID}</td>
                        <td>${order.scriptDocuments}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>
