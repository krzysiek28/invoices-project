<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Produkty" />
</jsp:include>
<body>

<jsp:include page="includes/navigation.jsp" />


<div align="left">
    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>Nazwa produktu</th>
            <th>Cena jednostkowa netto</th>
            <th>Jednostka</th>
            <th>Vat</th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.netUnitPrice}</td>
                <td>${product.unit}</td>
                <td>${product.vatRate}</td>
                <td><a href="/products/deleteproduct/${product.id}">delete</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>
<div align="left">
    <form action="/products/addproduct" method="post">
        <div class="form-row">
            <div class="col">
                <input type="text" class="form-control" placeholder="Nazwa produktu" name="name">
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Cena jednostkowa netto" name="netUnitPrice">
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Jednostka" name="unit">
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Vat" name="vatRate">
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
