    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Klienci" />
</jsp:include>
<body>
<jsp:include page="includes/navigation.jsp" />

<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <form action="/clients/addclient" method="post">
        <div class="form-row">
            <div class="col">
                <input style="width: 800px"type="text" class="form-control" placeholder="Nazwa klienta" name="name">
                <textarea name="additionalData" class="form-control" placeholder="Dodadtkowe informacje"></textarea>

            </div>
            <div class="col">
            </div>
            <div class="col">
                <input type="submit" value="dodaj" class="btn btn-info btn-md" style="height: 100%;">
            </div>
        </div>
    </form>
</div>

<div style="width:900px; margin:0 auto; margin-top: 40px;">
    <table class="table" border="1">
        <thead class="thead-dark">
            <th style="width: 300px">dane klienta</th>
            <th>dodatkowe informacje</th>
            <th style="width: 50px"></th>

        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.name}</td>
                <td style="white-space: pre-line">${client.additionalData}</td>
                <td><a href="/clients/deleteclient/${client.id}" class="btn btn-info" role="button">usuń</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>


<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
