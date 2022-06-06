<div class="fixed-bottom alert alert-danger alert-dismissible">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    <strong>Error! </strong>
    <c:forEach var="error" items="${errors}">
        <span>${error} </span>
    </c:forEach>
</div>