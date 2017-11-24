<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
Welcome ${username}
your password is:
${password}
<br>
<br>

 <table border="1">
                <th>N#</th>
                <th>Author</th>
                <th>Title</th>
                <c:forEach var="book" items="${books}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${book.author}</td>
                    <td>${book.title}</td>
                </tr>
                </c:forEach>
            </table>
</body>