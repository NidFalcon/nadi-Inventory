<c:forEach var="item" items="${rs}">
    <option value="${item.branchId}">${item.branchName}</option>
</c:forEach>