<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<body>
<jsp:include page="includes/navigation.jsp"/>

<c:if test="${param.error != null}">
    <div style="width:900px; margin:0 auto; margin-top: 10px;" class="alert alert-danger">
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
    <h3>Wystawianie faktury - Krok 2 - Dodawanie produktów i usług</h3>

</div>

<div style="width:900px; margin:0 auto; margin-top: 20px;">
    <input type="text" id="searchPhrase" onkeyup="searchProducts()" placeholder="Szukaj produktów">

    <table class="table" border="1">
        <thead class="thead-dark">
        <div>
            <th>Nazwa produktu/usługi</th>
            <th style="width: 10%">Cena jedn. netto</th>
            <th style="width: 10%">Jednostka</th>
            <th style="width: 10%">% VAT</th>
            <th style="width: 10%">Waluta</th>
            <th style="width: 12%">Liczba</th>

            <th></th>
        </div>

        </thead>
        <tbody id="searchTable">
        <c:forEach var="product" items="${products}">
            <tr id="tr${product.id}">
                <td >${product.name}</td>
                <td>${product.netUnitPrice}</td>
                <td>${product.unit}</td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${product.vatRate*100}"/>
                </td>
                <td>${product.currency}</td>
                <td>

                        <input id="number${product.id}" type="number" step="0.0001" min="0" class="form-control"
                               placeholder="Liczba"
                               name="quantity" required="true"/>


                </td>
                <td>
                    <button id="button${product.id}" class="btn btn-primary" onclick="addProduct(${product.id});">
                        dodaj
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
    <a href="/createfacture3" class="btn btn-primary btn-block">Przejdź do kolejnego kroku</a>

</div>

<script>
    function addProduct(id) {
        console.log(id);
        var button = document.getElementById("button" + id);
        var number = document.getElementById("number" + id);
        var row = document.getElementById("tr" + id);
        if (number.value <= 0 || number.value == null ) {
            alert("Niepoprawna ilość!");
            return false;
        }

        var req = new XMLHttpRequest();
        req.open('POST', '/createfacture/addproduct', true); /* Argument trzeci, wartość true, określa, że żądanie ma być asynchroniczne */
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "productId=" + id + "&quantity=" + number.value;
        req.onreadystatechange = function (aEvt) {
            if (req.readyState == 4) {
                if(req.status == 200) {
                    button.innerHTML = "usuń";
                    row.setAttribute("style","background-color: #e4ffc9; font-weight: bold;")
                    button.setAttribute("onclick","deleteProduct("+id+");");
                    number.setAttribute("disabled",true);
                } else {
                    alert("Błąd podczas dodawania");
                }
            }
        };
        req.send(params);
    }

    function deleteProduct(id) {
        console.log(id);
        var button = document.getElementById("button" + id);
        var number = document.getElementById("number" + id).value = null;
        var row = document.getElementById("tr" + id);

        var req = new XMLHttpRequest();
        req.open('POST', '/createfacture/deleteproduct', true); /* Argument trzeci, wartość true, określa, że żądanie ma być asynchroniczne */
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "productId=" + id;
        req.onreadystatechange = function (aEvt) {
            if (req.readyState == 4) {
                if(req.status == 200) {
                    button.innerHTML = "usuń";
                    row.setAttribute("style","")
                    button.setAttribute("onclick","addProduct("+id+");");
                } else {
                    alert("Błąd podczas usuwania");
                }
            }
        };
        req.send(params);
    }
</script>
<jsp:include page="includes/bootstrap.jsp"/>

</body>
</html>
