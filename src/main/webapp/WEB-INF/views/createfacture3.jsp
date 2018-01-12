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

<body>
<jsp:include page="includes/navigation.jsp"/>

<c:if test="${param.error != null}">
    <div style="width:900px; margin:0 auto; margin-top: 10px;" class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>

<div style="width:900px; margin:0 auto; margin-top: 40px;">
    <h3>Wystawianie faktury - Krok 3 - Płatność</h3>

</div>

<div style="width:900px; margin:0 auto; margin-top: 20px;">
    <div class="row">
        <div class="col">
            <p>Lączna wartość brutto wszystkich pozycji:
                <fmt:formatNumber type="number" maxFractionDigits="2" value="${facture.total}"/> ${facture.currency}
            </p>
            <form action="/createfacture/add" method="post">
                <label for="sel1">Meteoda płatności:</label>
                <select class="form-control" id="sel1" name="paymentmethod">
                    <option>gotówka</option>
                    <option>przelew</option>
                </select>
                <label for="sel1">Zapłacono ${facture.currency}:</label>
                <input id="invoicePaid" type="number" class="form-control" placeholder="Ile zapłacono" min="0"
                       step="0.001" name="paid" value="0" max="${facture.total}" required="true">
                <input type="submit" value="Wystaw fakturę" class="btn btn-primary btn-block" style="margin-top: 10px"/>

            </form>

        </div>
    </div>
</div>

<script>
    function addProduct(id) {
        console.log(id);
        var button = document.getElementById("button" + id);
        var number = document.getElementById("number" + id);
        var row = document.getElementById("tr" + id);
        if (number.value <= 0 || number.value == null) {
            alert("Niepoprawna ilość!");
            return false;
        }

        var req = new XMLHttpRequest();
        req.open('POST', '/createfacture/addproduct', true);
        /* Argument trzeci, wartość true, określa, że żądanie ma być asynchroniczne */
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "productId=" + id + "&quantity=" + number.value;
        req.onreadystatechange = function (aEvt) {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    button.innerHTML = "usuń";
                    row.setAttribute("style", "background-color: #e4ffc9; font-weight: bold;")
                    button.setAttribute("onclick", "deleteProduct(" + id + ");");
                    number.setAttribute("disabled", true);
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
        req.open('POST', '/createfacture/deleteproduct', true);
        /* Argument trzeci, wartość true, określa, że żądanie ma być asynchroniczne */
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "productId=" + id;
        req.onreadystatechange = function (aEvt) {
            if (req.readyState == 4) {
                if (req.status == 200) {
                    button.innerHTML = "usuń";
                    row.setAttribute("style", "")
                    button.setAttribute("onclick", "addProduct(" + id + ");");
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
