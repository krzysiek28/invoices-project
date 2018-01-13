<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Produkty"/>
</jsp:include>
<body>
<jsp:include page="includes/navigation.jsp"/>


<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <c:if test="${param.error != null}">
        <div  style="width:900px; margin:0 auto; margin-top: 10px; margin-bottom: 10px;"class="alert alert-danger">
            <p><c:out value="${param.error}"/></p>
        </div>
    </c:if>
    <table class="table" border="1">
        <thead class="thead-dark" align="center">
        <div>
            <th>Email</th>
            <th>Nazwa</th>
            <th>NIP</th>
            <th>Telefon</th>
            <th>Lokalizacja</th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="firm" items="${firms}">
            <tr>
                <td align="center" style="width: 20%">${firm.email}</td>
                <td align="center">${firm.name}</td>
                <td align="center" style="width: 10%">${firm.nip}</td>
                <td align="center" style="width: 10%">${firm.phone}</td>
                <td align="center" style="width: 10%; white-space: pre-line">${firm.place}</td>
                <td style="width: 159px">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        edytuj
                    </button>
                    <a href="/firms/deletefirm/${firm.id}" class="btn btn-primary">usuń</a>
                </td>
            </tr>



            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Edytuj dane firmy</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <%----%>
                            <form action="/firms/updatefirm/${firm.id}" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="email" class="form-control" placeholder="Email" name="email" value="${firm.email}" required="true">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" placeholder="Nazwa" name="name" value="${firm.name}" required="true">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input class="form-control" placeholder="Nip" name="nip" type="text" pattern="^((\d{3}-\d{3}-\d{2}-\d{2})|(\d{3}-\d{2}-\d{2}-\d{3}))$"  title="Wymagany format to: xxx-xxx-xx-xx lub xxx-xx-xx-xxx" value="${firm.nip}" required="true">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input class="form-control" placeholder="Telefon" name="phone" type="text" pattern="^(?:\(?\+?48)?(?:[-\.\(\)\s]*(\d)){9}\)?$" title="Przykładowe numery telefonów w poprawnym formacie: 123123123; 123 123 123; 123-123-123; 12 123 12 12; 12-123-12-12; +48 123 123 123; +48-123-123-123; +48123123123" value="${firm.phone}" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <textarea name="place" class="form-control" placeholder="Lokalizacja"
                                              required="true">${firm.place}</textarea>
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


<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <form action="/firms/addfirm" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <input class="form-control" placeholder="Email" name="email" type="email" required="true">
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" placeholder="Nazwa" name="name" type="text" required="true">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <input class="form-control" placeholder="Nip" name="nip" type="text" pattern="^((\d{3}-\d{3}-\d{2}-\d{2})|(\d{3}-\d{2}-\d{2}-\d{3}))$"  title="Wymagany format to: xxx-xxx-xx-xx lub xxx-xx-xx-xxx" required="true">
            </div>
            <div class="form-group col-md-6">
                <input class="form-control" placeholder="Telefon" name="phone" type="text" pattern="^(?:\(?\+?48)?(?:[-\.\(\)\s]*(\d)){9}\)?$" title="Przykładowe numery telefonów w poprawnym formacie: 123123123; 123 123 123; 123-123-123; 12 123 12 12; 12-123-12-12; +48 123 123 123; +48-123-123-123; +48123123123" required="true">
            </div>
        </div>
        <div class="form-group">
            <textarea name="place" class="form-control" placeholder="Lokalizacja"
                      required="true"></textarea>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary btn-block">Dodaj</button>
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
