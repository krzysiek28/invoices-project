<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Produkty"/>
</jsp:include>
<body>

<jsp:include page="includes/navigation.jsp"/>

<div style="width:900px; margin:0 auto; margin-top: 40px;">
    <form action="/products/addproduct" method="post">
        <div class="form-row">

                    <div class="col-md-3"><input type="text" class="form-control"
                                                 placeholder="Nazwa produktu" name="name"/></div>
                    <div class="col-md-3"><input type="text" class="form-control"
                                                 placeholder="Cena jednostkowa netto"
                                                 name="netUnitPrice"/></div>
                    <div class="col-md-3"><input type="text" class="form-control"
                                                 placeholder="Jednostka" name="unit"/>
                    </div>
                    <div class="col-md-3"><input type="text" class="form-control" placeholder="Vat"
                                                 name="vatRate"/>
                    </div>
        </div>
        <div class="form-row">
                <div class="row" style="width: 100%; margin: 5px;">
                    <input class="btn btn-primary btn-block" type="submit" value="dodaj">
                </div>
            </div>

        </div>
</div>
</form>
</div>


<div style="width:900px; margin:0 auto; margin-top: 20px;">
    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>Nazwa produktu</th>
            <th>Cena jednostkowa netto</th>
            <th>Jednostka</th>
            <th>% VAT</th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.netUnitPrice}</td>
                <td>${product.unit}</td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${product.vatRate*100}"/>
                </td>
                <td><a href="/products/deleteproduct/${product.id}">delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>


<jsp:include page="includes/bootstrap.jsp"/>

</body>
</html>
