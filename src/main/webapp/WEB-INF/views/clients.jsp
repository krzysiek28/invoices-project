<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<script type="text/javascript" charset="utf-8">
    function showEdit(id) {
        var clientId = id;
        var nameElement = document.getElementById("clientname" + id);
        var nameElementHTML = nameElement.innerHTML;
        var dataElement = document.getElementById("clientdata" + id);
        var dataElementHTML = dataElement.innerHTML;
        var name = nameElement.innerHTML;
        var data = dataElement.innerHTML;
        nameElement.innerHTML = "<input id=\"i" + id + "\" style=\"width: 270px\"type=\"text\" class=\"form-control\" value=\"" + name + "\" name=\"name\" required>"
        dataElement.innerHTML = "<textarea id=\"t" + id + "\"name=\"additionalData\" class=\"form-control\" required=\"true\">" + data + "</textarea>";
        var button = document.getElementById("clientbutton" + id);
        var deletebutton = document.getElementById("deletebutton" + id);
        deletebutton.setAttribute("class", "btn btn-primary disabled");
        var buttonOnclick = button.getAttribute("onclick");
        button.innerHTML = "zapisz";

        button.onclick = function () {
            var newName = document.getElementById("i" + id).value;
            var newData = document.getElementById("t" + id).value;
            if (newName == "")
                newName = name;
            if (newData == "")
                newData = data;
//            alert(newName);
//            alert(newData);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/clients/editclient", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            var params = "name=" + newName + "&additionalData=" + newData + "&id=" + id;
            xhr.send(params);
            nameElement.innerHTML = newName;
            dataElement.innerHTML = newData;
            button.innerHTML = "edytuj";
            button.setAttribute("onclick", buttonOnclick);
            deletebutton.setAttribute("class", "btn btn-primary");
//            history.go(0);
//            setTimeout(function(){
//                window.location.reload(true);
//            });


        }
    }

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
</script>

<c:if test="${param.error != null}">
    <div style="width:900px; margin:0 auto; margin-top: 10px;" class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>

<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <form action="/clients/addclient" method="post">
        <div class="form-row">
            <div class="col">
                <input style="width: 800px" type="text" class="form-control" placeholder="Nazwa klienta" name="name"
                       required="true">
                <textarea name="additionalData" class="form-control" placeholder="Dodadtkowe informacje"
                          required="true"></textarea>

            </div>
            <div class="col">
            </div>
            <div class="col">
                <input type="submit" value="dodaj" class="btn btn-primary btn-md" style="height: 100%;">
            </div>
        </div>
    </form>
</div>

<div style="width:900px; margin:0 auto; margin-top: 40px;">
    <input type="text" id="searchPhrase" onkeyup="searchClients()" placeholder="Szukaj klientów">
    <table class="table" border="1">
        <thead class="thead-dark">
        <th style="width: 300px">dane klienta</th>
        <th>dodatkowe informacje</th>
        <th style="width: 170px"></th>

        </thead>
        <tbody id="searchTable">
        <c:forEach var="client" items="${clients}">
            <tr>
                <form>
                    <td id="clientname${client.id}">${client.name}</td>
                    <td id="clientdata${client.id}" style="white-space: pre-line">${client.additionalData}</td>
                    <td>
                        <a id="deletebutton${client.id}" href="/clients/deleteclient/${client.id}"
                           class="btn btn-primary" role="button">usuń</a>
                        <button id="clientbutton${client.id}" type="button" class="btn btn-primary"
                                onclick="showEdit(${client.id});">edytuj
                        </button>
                    </td>
                </form>
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
