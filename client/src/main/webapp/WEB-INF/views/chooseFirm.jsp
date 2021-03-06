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

<%--list of firm--%>
<div class="dropdown show" align="center" style="padding: 20px">
    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Wybierz firmę
    </a>

    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <c:forEach var="firm" items="${firms}">
            <a class="dropdown-item" href="/chooseFirm/${firm.id}">${firm.name}</a>
        </c:forEach>
    </div>
</div>
<div>


<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
