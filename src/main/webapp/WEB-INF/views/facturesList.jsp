<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Faktury" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="includes/navigation.jsp" />
<c:if test="${param.error != null}">
    <div  style="width:900px; margin:0 auto; margin-top: 10px;"class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>



<div class="list-group">
<c:forEach var="facture" items="${factures}">
    <a href="/factures/${facture.id}" class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">${facture.number}</h5>
            <small class="text-muted">${facture.issueDate}</small>
        </div>
        <p class="mb-1">Tu można coś napisać</p>
        <small class="text-muted">Tu też ...</small>
    </a>
</c:forEach>
</div>


<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
