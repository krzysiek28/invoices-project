<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a class="nav-link" href="/bankAccounts">Twoje konta bankowe</a>
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