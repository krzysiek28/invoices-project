<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Faktury" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<jsp:include page="includes/navigation.jsp" />




<jsp:include page="includes/bootstrap.jsp" />


<div style="width:900px; margin:0 auto; margin-top: 10px;">
    <c:if test="${param.error != null}">
        <div  style="width:900px; margin:0 auto; margin-top: 10px; margin-bottom: 10px;"class="alert alert-danger">
            <p><c:out value="${param.error}"/></p>
        </div>
    </c:if>
    <table class="table" border="1">
        <thead class="thead-dark" align="center">
        <div>
            <th>Konto bankowe</th>
            <th>Dodatkowe informacje</th>
            <th></th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="bankAccount" items="${bankAccounts}">
            <tr>
                <td align="center" style="width: 20%">${bankAccount.bankAccount}</td>
                <td align="center" style="width: 20%">${bankAccount.additionalData}</td>
                <td style="width: 159px">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        edytuj
                    </button>
                    <a href="/bankAccounts/deleteBankAccount/${bankAccount.bankAccount}" class="btn btn-primary">usuń</a>
                </td>
            </tr>



            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Edytuj dane konta bankowego</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <%----%>
                            <form action="/bankAccounts/updateBankAccount/${bankAccount.bankAccount}" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" placeholder="Dodatkowe dane" name="additionalData" required="true">
                                    </div>
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
    <form action="/bankAccounts/addBankAccount" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Konto bankowe" name="bankAccount" pattern="^(([a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16})|([0-9]{26}))$" title="Podaj polski numer konta bankowego (26 cyfr) lub międzynarodowy numer IBAN" required="true">
            </div>
            <div class="form-group col-md-6">
                <input type="text" class="form-control" placeholder="Dodatkowe dane" name="additionalData" required="true">
            </div>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary btn-block">Dodaj</button>
        </div>
    </form>
</div>

</body>
</html>
