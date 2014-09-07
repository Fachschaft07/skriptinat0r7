<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${successMessage != null}">
    <div class="alert alert-success" role="alert">
        ${successMessage}
    </div>
</c:if>
