<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${authservice.getFirmId() < 0}">
    <c:redirect url="/chooseFirm?error=Najpierw wybierz firme"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Klienci"/>
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

<%--Main form--%>
<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <h3>Wystawianie faktury - Krok 1 - Podstawowe dane</h3>
    <form action="/createfacture/step2" method="post">
        <div class="form-row">
            <div class="col" >
                <div class="row card" style="margin: 2px;">

                <p>Klient:
                    <input id="clientid" type="hidden" name="clientid" value="-1">
                    <input id="clientname" type="text" class="form-control" placeholder="Nazwa klienta" name="clientname"
                           required="true" onchange="resetId();" style="margin: 2px; width: 95%;">
                    <textarea id="clientdata" name="clientdata" class="form-control"
                              placeholder="Dodadtkowe informacje" style="overflow-x:hidden; margin: 2px; width: 95%;"
                              required="true" onchange="resetId();"></textarea><br/>
                    <button type="button" class="btn btn-primary btn-block" data-toggle="modal"
                            data-target="#clientModal" style="margin: 2px; width: 95%;">
                        Wybierz klienta z bazy
                    </button>
                </p>

                </div>
                <div class="row card" style="margin: 2px;">

                    <p>Konto bankowe:
                        <input id="accountnumber" type="text" class="form-control" placeholder="Numer konta" name="accountnumber"
                               required="true" style="margin: 2px; width: 95%;" readonly="true">
                        <textarea id="accountdata" name="accountdata" class="form-control"
                                  placeholder="Dodadtkowe dane konta" style="overflow-x:hidden; margin: 2px; width: 95%;"
                                  required="true" readonly="true"></textarea><br/>
                        <button type="button" class="btn btn-primary btn-block" data-toggle="modal"
                                data-target="#accountModal" style="margin: 2px; width: 95%;">
                            Wybierz konto bankowe
                        </button>
                    </p>

                </div>
            </div>
            <div class="col card" style="margin: 2px;">
                <p>Dane faktury:
                    <input id="invoiceNumber" type="text" class="form-control" placeholder="Numer faktury"
                           name="invoicenumber"
                           required="true">
                    <input id="invoicePlace" type="text" class="form-control" placeholder="Miejsce wystawienia faktury"
                           name="invoiceplace"
                           required="true">
                <p>Data wystawienia:
                    <input id="invoiceIssueDate" type="date" class="form-control" name="invoiceissuedate"
                           required="true" onchange="checkDates();"></p>
                </p>
                <p>Data płatności:
                    <input id="invoicePaymentDate" type="date" class="form-control" name="invoicepaymentdate"
                           required="true" onchange="checkDates();"></p>
                </p>
            </div>
        </div>
        <div class="form-row card" style="margin: 5px;">
                <input type="submit" value="Przejdź do kolejnego kroku" class="btn btn-primary btn-block" style= width: 98%; height: 98%;">
        </div>
    </form>
</div>


<!-- Clients modal -->
<div class="modal fade" id="clientModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Wybierz klienta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" id="searchPhrase" onkeyup="searchClients()" placeholder="Szukaj klientów">
                <div class="pre-scrollable">
                    <%----%>
                    <table class="table">
                        <thead>
                        <th style="width: 300px">nazwa klienta</th>
                        <th> dodatkowe dane</th>
                        <th></th>

                        </thead>
                        <tbody id="searchTable">
                        <c:forEach var="client" items="${clients}">
                            <tr>
                                <td id="clientname${client.id}">${client.name}</td>
                                <td id="clientdata${client.id}">${client.additionalData}</td>
                                <td>
                                    <button id="clientbutton${client.id}" type="button" class="btn btn-primary btn-xs"
                                            onclick="selectClient(${client.id});" data-dismiss="modal"
                                            data-target="#clientModal"
                                    >wybierz
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Bank accounts modal -->
<div class="modal fade" id="accountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Wybierz konto bankowe</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="pre-scrollable">
                    <%----%>
                    <table class="table">
                        <thead>
                        <th style="width: 300px">numer konta bankowego</th>
                        <th>dodatkowe dane</th>
                        <th></th>

                        </thead>
                        <tbody>
                        <c:forEach var="account" items="${accounts}">
                            <tr>
                                <td id="account${account.bankAccount}">${account.bankAccount}</td>
                                <td id="accountdata${account.bankAccount}">${account.additionalData}</td>
                                <td>
                                    <button id="accountbutton${account.bankAccount}" type="button" class="btn btn-primary btn-xs"
                                            onclick="selectAccount(${account.bankAccount});" data-dismiss="modal"
                                            data-target="#accountModal"
                                    >wybierz
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="includes/bootstrap.jsp"/>

<%--javascript controller--%>
<script type="text/javascript" charset="utf-8">
    function searchClients() {
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

    function selectClient(id) {
        var selectedName = document.getElementById("clientname" + id).innerHTML;
        var selectedData = document.getElementById("clientdata" + id).innerHTML;
//        alert(id + selectedName + selectedData);

        document.getElementById("clientname").value = selectedName;
        document.getElementById("clientdata").value = selectedData;
        document.getElementById("clientid").setAttribute("value", id);

    }

    function resetId() {
        document.getElementById("clientid").setAttribute("value", -1);

    }

    function checkDates() {
        var issueDate = Date.parse(document.getElementById("invoiceIssueDate").value);
        var paymentDate = Date.parse(document.getElementById("invoicePaymentDate").value);

        if (paymentDate < issueDate) {
            alert('Nieprawidłowy termin płatności');
            document.getElementById("invoicePaymentDate").value = document.getElementById("invoiceIssueDate").value
        }
    }

    function selectAccount(id) {
        var selectedName = document.getElementById("account" + id).innerHTML;
        var selectedData = document.getElementById("accountdata" + id).innerHTML;
//        alert(id + selectedName + selectedData);

        document.getElementById("accountnumber").value = selectedName;
        document.getElementById("accountdata").value = selectedData;

    }
</script>
</body>
</html>