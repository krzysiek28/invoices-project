<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Faktury"/>
</jsp:include>
<script>
    function pdf(id) {
        var token = "${authservice.rawToken}";
        let xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8090/pdf/get/'+id, true);
        xhr.setRequestHeader("Authorization", "Bearer "+token);

        xhr.responseType = 'blob';
        let formData = new FormData(this);
        xhr.send(formData);

        xhr.onload = function(e) {
            if (this.status == 200) {
                // Create a new Blob object using the
                //response data of the onload object
                var blob = new Blob([this.response], {type: 'image/pdf'});
                //Create a link element, hide it, direct
                //it towards the blob, and then 'click' it programatically
                let a = document.createElement("a");
                a.style = "display: none";
                document.body.appendChild(a);
                //Create a DOMString representing the blob
                //and point the link element towards it
                let url = window.URL.createObjectURL(blob);
                a.href = url;
                a.download = 'invoice_${facture.number}.pdf';
                //programatically click the link to trigger the download
                a.click();
                //release the reference to the file by revoking the Object URL
                window.URL.revokeObjectURL(url);
            }else{
                alert(this.status);
            }
        };
    }
</script>
<body>
<jsp:include page="includes/navigation.jsp"/>
<c:if test="${param.error != null}">
    <div style="width:900px; margin:0 auto; margin-top: 10px;" class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>


<div class="container">
    <div class="row">
        <div class="col">
            Faktura nr
            <h3>${facture.number}</h3>
        </div>
        <div class="row" style="margin-top: 5px;">
            <div class="col">
                <input type="submit" value="generuj PDF" class="btn btn-primary"  onclick="pdf(${facture.id})" >

            </div>
            <div class="col">
                <a href="/deletefacture/${facture.id}" class="btn btn-primary" >usuń</a>

            </div>
        </div>

    </div>



<div class="row">
    <div class="col-8"></div>
    <div class="col-4">
        <p>Data wystawienia: ${facture.issueDate}<br/>
            Miejsce wystawienia: ${facture.place}</p>
    </div>
</div>

<div class="row">
    <div class="col">
        <p style="font-weight: bold;">Sprzedawca:</p>
        <p>${firm.name}<br/>
            <p style="white-space: pre-line; margin: 0px">${firm.place}<p><br/>
            NIP ${firm.nip}<br/>
            ${firm.phone}<br/>
            ${firm.email}</p>


    </div>
    <div class="col">
        <p style="font-weight: bold;">Nabywca:</p>
        <p>${client.name}<br/>
            ${client.additionalData}</p>
    </div>
</div>
<br/><br/>
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

<c:forEach var="productEntry" items="${productentries}">
<tr>
    <th scope="row">${productEntry.no}</th>
    <td>${productEntry.product.name}</td>
    <td> <fmt:formatNumber type="number" maxFractionDigits="4" value="${productEntry.quantity}"/></td>
    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${productEntry.product.netUnitPrice}"/> ${productEntry.product.currency}</td>
    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${productEntry.product.vatRate}"/></td>
    <td><fmt:formatNumber type="number" maxFractionDigits="4" value="${productEntry.netprice}"/> ${productEntry.product.currency}</td>
    <td><fmt:formatNumber type="number" maxFractionDigits="4" value="${productEntry.vat}"/> ${productEntry.product.currency}</td>
    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${productEntry.grossprice}"/> ${productEntry.product.currency}</td>
</tr>
</c:forEach>

    </tbody>
    </table>

    <div class="row">
    <div class="col">
    <p>Forma zapłaty: ${facture.paymentMethod}<br/>
    Termin zapłaty: ${facture.paymentDate}<br/>
    Zapłacono: <fmt:formatNumber type="number" maxFractionDigits="2" value="${facture.paid}"/> ${facture.currency}<br/>
    <p>Dane płatności:<br />
    ${bankaccount.bankAccount}<br/>
    ${bankaccount.additionalData}</p>

    </div>
    <div class="col">
    <p>Razem: <fmt:formatNumber type="number" maxFractionDigits="2" value="${facture.total}"/> ${facture.currency}</p>
    <p>Do zapłaty:  <fmt:formatNumber type="number" maxFractionDigits="2" value="${facture.toPay}"/> ${facture.currency}</p>
    </div>
    </div>

    </div>

    <jsp:include page="includes/bootstrap.jsp"/>
    </body>
    </html>
