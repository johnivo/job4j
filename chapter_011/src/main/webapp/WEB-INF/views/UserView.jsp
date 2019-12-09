
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User page</title>
</head>
<body>

<table style="border: 1px solid black" cellpadding="10" cellspacing="0" border="1">
    <caption><h3>User page</h3></caption>
    <tr>
        <th>id</th>
        <th>photo</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>action</th>
    </tr>

    <tr>
        <td>${user.id}</td>
        <td>
            <c:choose>
                <%--<c:when test="${user.photoId != '' && user.photoId != null}">--%>
                <c:when test="${not empty user.photoId}">
                    <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                         width="100px" height="100px"/></br>
                    <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a>
                </c:when>
                <c:otherwise>no image</c:otherwise>
            </c:choose>
        </td>
        <td>${user.name}</td>
        <td>${user.login}</td>
        <td>${user.email}</td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="update"/>
            </form>
        </td>
    </tr>

</table>
<br>
<form action="${pageContext.servletContext.contextPath}/signout" method="get">
    <input type="submit" value="Exit" style="background-color: darkgrey"/>
</form>

</body>
</html>
