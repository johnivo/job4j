
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
    <input type="text" name="name" value="${user.name}"/><br>
    Login:<br>
    <input type="text" name="login" value="${user.login}"/><br>
    Email:<br>
    <input type="text" name="email" value="${user.email}"/><br>
    <br>
    <input type="submit" value="Update this user"/>
</form>
<%--<form action="${pageContext.servletContext.contextPath}/update" method="post">--%>
<%--<input type="hidden" name="id" value="${param.id}"/>--%>
<%--Name:<br>--%>
<%--<input type="text" name="name" value="${param.name}"/><br>--%>
<%--Login:<br>--%>
<%--<input type="text" name="login" value="${param.login}"/><br>--%>
<%--Email:<br>--%>
<%--<input type="text" name="email" value="${param.email}"/><br>--%>
<%--<br>--%>
<%--<input type="submit" value="Update this user"/>--%>
<%--</form>--%>

</body>
</html>
