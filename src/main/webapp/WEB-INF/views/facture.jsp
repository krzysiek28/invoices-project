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

<div class="container">

<h3>${facture.number}</h3>


<div class="row">
    <div class="col-8"></div>
    <div class="col-4">
        <p>Data wystawienia:    ${facture.issueDate}</p>
        <p>Miejsce wystawienia: ${facture.place}</p>
    </div>
</div>

<div class="row">
    <div class="col">
        <p>Sprzedawca:</p>
        <p>${firm.name}</p>
        <p>${firm.additionalData}</p>

    </div>
    <div class="col">
        <p>Nabywca:</p>
        <p>${client.name}</p>
        <p>${client.additionalData}</p>
    </div>
</div>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Lp</th>
        <th scope="col">Nazwa towaru i usługi</th>
        <th scope="col">Ilość</th>
        <th scope="col">Cena netto</th>
        <th scope="col">Stawka Vat</th>
        <th scope="col">Wartość Netto</th>
        <th scope="col">Wartość VAT</th>
        <th scope="col">Wartość Brutto</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row"></th>
        <td>${productEntry.product.name}</td>
        <td>${productEntry.quantity}</td>
        <td>${productEntry.product.netUnitPrice}</td>
        <td>${productEntry.product.vatRate}</td>
        <td>obliczyć</td>
        <td>obliczyć</td>
        <td>obliczyć</td>
    </tr>
    </tbody>
</table>

    <div class="row">
        <div class="col">
            <p>Forma zapłaty: przelew</p>
            <p>Termin zapłaty: ${facture.paymentDate}</p>
            <p>Bank:</p>
            <p>Nr konta: ${bankAccount.bankAccount}</p>

        </div>
        <div class="col">
            <p>Razem: tutaj cena</p>
            <p>Do zapłaty: </p>
        </div>
    </div>

</div>

<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
