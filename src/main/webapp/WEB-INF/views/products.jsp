<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>
<c:if test="${authservice.getFirmId() < 0}">
    <c:redirect url="/chooseFirm?error=Najpierw wybierz firme"/>
</c:if>
<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Produkty"/>
</jsp:include>
<style>
    #searchPhrase {
        background-position: 10px 12px; /* Position the search icon */
        background-repeat: no-repeat; /* Do not repeat the icon image */
        width: 100%; /* Full-width */
        font-size: 16px; /* Increase font-size */
        padding: 12px 20px 12px 40px; /* Add some padding */
        border: 1px solid #ddd; /* Add a grey border */
        margin-bottom: 12px; /* Add some space below the input */
    }

    #searchTable {
        border-collapse: collapse; /* Collapse borders */
        width: 100%; /* Full-width */
        border: 1px solid #ddd; /* Add a grey border */
        font-size: 18px; /* Increase font-size */
    }

    #searchTable th, #searchTable td {
        text-align: left; /* Left-align text */
        padding: 12px; /* Add padding */
    }

    #searchTable tr {
        /* Add a bottom border to all table rows */
        border-bottom: 1px solid #ddd;
    }

    #searchTable tr.header, #searchTable tr:hover {
        /* Add a grey background color to the table header and on hover */
        background-color: #f1f1f1;
    }
</style>
<body>
<jsp:include page="includes/navigation.jsp"/>

<c:if test="${param.error != null}">
    <div  style="width:900px; margin:0 auto; margin-top: 10px;"class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>

<script type="text/javascript" charset="utf-8">
    function searchProducts() {
        // Declare variables
        var input, filter, table, tr, td, i;
        input = document.getElementById("searchPhrase");
        filter = input.value.toUpperCase();
        table = document.getElementById("searchTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>

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
    <input type="text" id="searchPhrase" onkeyup="searchProducts()" placeholder="Szukaj produktów">

    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>Nazwa produktu/usługi</th>
            <th>Cena jednostkowa netto</th>
            <th>Jednostka</th>
            <th>% VAT</th>
            <th style="width: 170px"></th>
        </div>

        </thead>
        <tbody id="searchTable">
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
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${product.id}">
                        edytuj
                    </button>
                </td>
            </tr>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal${product.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
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
