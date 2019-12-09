
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

<hr>
<div>
    <c:choose>
        <c:when test="${not empty user.photoId}">
            <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                 width="100px" height="100px"/></br>
        </c:when>
        <c:otherwise>no image</c:otherwise>
    </c:choose>
</div>
</br>
<form action="${pageContext.servletContext.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="file" name="file"></br>
    </br>
    <button type="submit" class="btn btn-default">upload</button>
</form>
</br>
<hr>

<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    Name:<br>
    <%--<input type="text" name="name" value="${param.name}"/><br>--%>
    <input type="text" name="name" value="${user.name}" required/><br>
    Login:<br>
    <input type="text" name="login" value="${user.login}" required/><br>
    Password:<br>
    <input type="text" maxlength="16" name="password" value="${user.password}" required/><br>

    <%--<c:if test="${user.role  == 'admin'}">--%>
    <c:if test="${sessionScope.role == 'admin'}">
        Role:<br>
        <select name="role" required>
            <option value="user">user</option>
            <option value="admin">admin</option>
        </select></br>
    </c:if>
    <c:if test="${sessionScope.role != 'admin'}">
        <input type="hidden" name="role" value="user"/>
    </c:if>

    Email:<br>
    <input type="text" name="email" value="${user.email}" required/><br>
    <br>
    <input type="submit" value="Update this user"/>
</form>

<c:choose>
    <c:when test="${sessionScope.role == 'admin'}">
        <form action="${pageContext.servletContext.contextPath}/UsersView" method="get">
            <input type="submit" value="Back to list" style="background-color: darkgrey"/>
        </form>
    </c:when>
    <c:otherwise>
        <form action="${pageContext.servletContext.contextPath}/UserView" method="get">
            <input type="submit" value="back to user page" style="background-color: darkgrey"/>
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>
