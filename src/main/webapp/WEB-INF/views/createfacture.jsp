<%@page contentType="text/html" pageEncoding="UTF-8" %>
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


<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <form action="/clients/addclient" method="get">
        <div class="form-row">
            <div class="col">
                <p>Klient:</p>
                <input id="clientid" type="hidden" name="clientid" value="-1">
                <input id="clientname" type="text" class="form-control" placeholder="Nazwa klienta" name="name"
                       required="true" onchange="resetId();">
                <textarea id="clientdata" name="additionalData" class="form-control" placeholder="Dodadtkowe informacje"
                          required="true" onchange="resetId();"></textarea>
                <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#clientModal">
                    Wybierz klienta z bazy
                </button>


            </div>
            <div class="col">
                <p>Dane faktury:</p>
                <input id="invoiceNumber" type="text" class="form-control" placeholder="Numer faktury" name="name"
                       required="true">
            </div>
        </div>
        <div class="form-row">
            <input type="submit" value="dodaj" class="btn btn-primary btn-md" style="height: 100%;">
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
                                            onclick="selectClient(${client.id});" data-dismiss="modal" data-target="#clientModal"
                                    >wybierz
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%--<form action="/firms/updatefirm/${firm.id}" method="post">--%>
                <%--<div class="form-row">--%>
                <%--<div class="form-group col-md-6">--%>
                <%--<input type="email" class="form-control" placeholder="Email" name="email" required="true">--%>
                <%--</div>--%>
                <%--<div class="form-group col-md-6">--%>
                <%--<input type="text" class="form-control" placeholder="Nazwa" name="name" required="true">--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-row">--%>
                <%--<div class="form-group col-md-6">--%>
                <%--<input class="form-control" placeholder="Nip" name="nip" type="text" pattern="^((\d{3}-\d{3}-\d{2}-\d{2})|(\d{3}-\d{2}-\d{2}-\d{3}))$"  title="Wymagany format to: xxx-xxx-xx-xx lub xxx-xx-xx-xxx" required="true">--%>
                <%--</div>--%>
                <%--<div class="form-group col-md-6">--%>
                <%--<input class="form-control" placeholder="Telefon" name="phone" type="text" pattern="^(?:\(?\+?48)?(?:[-\.\(\)\s]*(\d)){9}\)?$" title="Przykładowe numery telefonów w poprawnym formacie: 123123123; 123 123 123; 123-123-123; 12 123 12 12; 12-123-12-12; +48 123 123 123; +48-123-123-123; +48123123123" required="true">--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<input type="text" class="form-control" placeholder="Lokalizacja" name="place" required="true">--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>--%>
                <%--<button type="submit" class="btn btn-primary" >Edytuj</button>--%>
                <%--</div>--%>
                <%--</form>--%>
                <%----%>
            </div>

        </div>
    </div>
</div>

<jsp:include page="includes/bootstrap.jsp"/>

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
        var selectedName = document.getElementById("clientname"+id).innerHTML;
        var selectedData = document.getElementById("clientdata"+id).innerHTML;
        alert(id+selectedName+selectedData);

        document.getElementById("clientname").value = selectedName;
        document.getElementById("clientdata").value = selectedData;
        document.getElementById("clientid").setAttribute("value",id);

    }
    function resetId() {
        document.getElementById("clientid").setAttribute("value",-1);

    }
</script>
</body>
</html>