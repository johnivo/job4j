
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/update" method="post">
    <input type="hidden" name="id" value="${param.id}"/>
    Name: <input type="text" name="name" value="${param.name}"/><br/>
    Login: <input type="text" name="login" value="${param.login}"/><br/>
    Email: <input type="text" name="email" value="${param.email}"/><br/>
    <input type="submit" value="Update this user"/>
</form>

</body>
</html>
