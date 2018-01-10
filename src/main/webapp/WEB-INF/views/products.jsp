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

<c:if test="${param.error != null}">
    <div  style="width:900px; margin:0 auto; margin-top: 10px;"class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>

<div style="width:900px; margin:0 auto; margin-top: 40px;">
    <form action="/products/addproduct" method="post">
        <div class="form-row">

            <div class="col-md-3"><input type="text" class="form-control"
                                         placeholder="Nazwa produktu" name="name" required="true"/></div>
            <div class="col-md-3"><input type="number" step="0.001" min="0" class="form-control"
                                         placeholder="Cena jednostkowa netto"
                                         name="netUnitPrice" required="true"/></div>
            <div class="col-md-3"><input type="text" class="form-control"
                                         placeholder="Jednostka" name="unit" required="true"/>
            </div>
            <div class="col-md-3"><input type="number" min="0" max="100" class="form-control" placeholder="Vat"
                                         name="vatRate" required="true"/>
            </div>
        </div>
        <div class="form-row">
            <div class="row" style="width: 100%; margin: 5px;">
                <input class="btn btn-primary btn-block" type="submit" value="dodaj">
            </div>
        </div>

    </form>
</div>


<div style="width:900px; margin:0 auto; margin-top: 20px;">
    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>Nazwa produktu/usługi</th>
            <th>Cena jednostkowa netto</th>
            <th>Jednostka</th>
            <th>% VAT</th>
            <th style="width: 158px"></th>
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
                <td>
                    <a href="/products/deleteproduct/${product.id}" class="btn btn-primary">usuń</a>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        edytuj
                    </button>
                </td>
            </tr>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Edytuj produkt</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <%----%>
                            <form action="/products/updateproduct/${product.id}" method="post">
                                <div class="form-row">

                                    <div class="col-md-6"><input type="text" class="form-control"
                                                                 placeholder="Nazwa produktu" name="name"
                                                                 value="${product.name}" required="true"/></div>
                                    <div class="col-md-6"><input type="number" step="0.001" min="0" class="form-control"
                                                                 placeholder="Cena jednostkowa netto"
                                                                 value="${product.netUnitPrice}" name="netUnitPrice" required="true"/></div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-6"><input type="text" class="form-control"
                                                                 placeholder="Jednostka" name="unit" value="${product.unit}" required="true"/>
                                    </div>
                                    <div class="col-md-6"><input type="number" min="0" max="100" class="form-control"
                                                                 placeholder="Vat"
                                                                 name="vatRate" value="<fmt:formatNumber type="number" maxFractionDigits="2" value="${product.vatRate*100}"/>" required="true"/>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij
                                    </button>
                                    <button type="submit" class="btn btn-primary">Edytuj</button>
                                </div>
                            </form>
                                <%----%>
                        </div>

                    </div>
                </div>
            </div>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>


<jsp:include page="includes/bootstrap.jsp"/>

</body>
</html>
