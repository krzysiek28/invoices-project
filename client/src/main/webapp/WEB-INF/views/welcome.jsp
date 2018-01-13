<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
Welcome ${email}
your password is:
${password}
<br>
<br>

 <table border="1">
                <th>N#</th>
                <th>Author</th>
                <th>Title</th>
                <c:forEach var="user" items="${users}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                </tr>
                </c:forEach>
            </table>
</body>