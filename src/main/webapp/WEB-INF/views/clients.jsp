    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Klienci" />
</jsp:include>
<body>
<jsp:include page="includes/navigation.jsp" />
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
        deletebutton.setAttribute("class","btn btn-primary disabled");
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
            var params = "name="+newName+"&additionalData="+newData+"&id="+id;
            xhr.send(params);
            nameElement.innerHTML = newName;
            dataElement.innerHTML = newData;
            button.innerHTML = "edytuj";
            button.setAttribute("onclick",buttonOnclick);
            deletebutton.setAttribute("class","btn btn-primary");
//            history.go(0);
//            setTimeout(function(){
//                window.location.reload(true);
//            });


        }
    }
</script>

<c:if test="${param.error != null}">
    <div  style="width:900px; margin:0 auto; margin-top: 10px;"class="alert alert-danger">
        <p><c:out value="${param.error}"/></p>
    </div>
</c:if>

<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <form action="/clients/addclient" method="post">
        <div class="form-row">
            <div class="col">
                <input style="width: 800px"type="text" class="form-control" placeholder="Nazwa klienta" name="name" required="true">
                <textarea name="additionalData" class="form-control" placeholder="Dodadtkowe informacje" required="true"></textarea>

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
    <table class="table" border="1">
        <thead class="thead-dark">
            <th style="width: 300px">dane klienta</th>
            <th>dodatkowe informacje</th>
            <th style="width: 158px"></th>

        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <form>
                <td id="clientname${client.id}">${client.name}</td>
                <td id="clientdata${client.id}" style="white-space: pre-line">${client.additionalData}</td>
                <td>
                    <a id="deletebutton${client.id}" href="/clients/deleteclient/${client.id}" class="btn btn-primary" role="button">usuń</a>
                    <button id="clientbutton${client.id}" type="button" class="btn btn-primary"  onclick="showEdit(${client.id});">edytuj</button>
                </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br><br>
</div>



<jsp:include page="includes/bootstrap.jsp" />
</body>
</html>
