<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <title>Invoices</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/logged">Twoje Faktury</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/createfacture">Stwórz fakturę </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/products">Twoje produkty</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/clients">Twoi klienci</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/bankaccounts">Twoje konta bankowe</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/firms">Zarządzaj firmami</a>
            </li>
        </ul>
    </div>
    <div class="nick" style="padding-right: 10px; color: white">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h4>You are logged as: ${pageContext.request.userPrincipal.name}</h4>
        </c:if>
    </div>
    <!-- naval with buttons -->
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/logout'">Wyloguj się</button>
    </div>
</nav>

<div>
    <table class="table" border="1">
        <thead class="thead-dark" align="center">
        <div>
            <th>Email</th>
            <th>Nazwa</th>
            <th>NIP</th>
            <th>Telefon</th>
            <th>Lokalizacja</th>
            <th></th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="firm" items="${firms}">
            <tr>
                <td align="center">${firm.email}</td>
                <td align="center">${firm.name}</td>
                <td align="center">${firm.nip}</td>
                <td align="center">${firm.phone}</td>
                <td align="center">${firm.place}</td>
                <td align="center">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        Edytuj
                    </button>
                </td>
                <td align="center">
                    <form action="/firms/deletefirm/${firm.id}" method="post">
                        <button type="submit" class="btn btn-primary">Usuń</button>
                    </form>
                </td>
            </tr>



            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <%----%>
                            <form action="/firms/updatefirm/${firm.id}" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="email" class="form-control" placeholder="Email" name="email">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" placeholder="Nazwa" name="name">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" placeholder="Nip" name="nip">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" placeholder="Telefon" name="phone">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Lokalizacja" name="place">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                                    <button type="submit" class="btn btn-primary" >Edytuj</button>
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


<div>
    <form action="/firms/addfirm" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Email" name="email">
            </div>
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Nazwa" name="name">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Nip" name="nip">
            </div>
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Telefon" name="phone">
            </div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Lokalizacja" name="place">
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">Dodaj</button>
        </div>
    </form>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


<%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
<%--<h2>Welcome : ${pageContext.request.userPrincipal.emial}</h2>--%>
<%--</c:if>--%>

</body>
</html>
