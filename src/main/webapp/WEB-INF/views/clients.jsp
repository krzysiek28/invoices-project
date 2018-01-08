<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Klienci" />
</jsp:include>
<body>
<jsp:include page="includes/navigation.jsp" />

<div>
    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>dane klienta</th>
            <th>dodatkowe informacje</th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.name}</td>
                <td>${client.additionalData}</td>
                <td><a href="/clients/deleteclient/${client.id}">delete</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>
<div>
    <form action="/clients/addclient" method="post">
        <div class="form-row">
            <div class="col">
                <input type="text" class="form-control" placeholder="Dane klienta" name="name">
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Dodatkowe informacje" name="additionalData">
            </div>
            <div class="col">
                <input type="submit" value="dodaj">
            </div>
        </div>
    </form>
</div>

<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
