
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users list</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Create new user" />
</form>

<br>

<table style="border: 1px solid black" cellpadding="10" cellspacing="0" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>created date</th>
        <th>action</th>
        <th>action</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
    <tr>
        <%--<td><c:out value="${user.id}"/></td>--%>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>${user.createDate}</td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="name" value="${user.name}"/>
                <input type="hidden" name="login" value="${user.login}"/>
                <input type="hidden" name="email" value="${user.email}"/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="delete"/>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
