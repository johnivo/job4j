
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>

<strong>Please, enter your details!</strong><br>
<br>
<form action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
    Name:<br>
    <input type="text" name="name" title="Name"/><br>
    Login:<br>
    <input type="text" name="login" title="Login"/><br>
    Email:<br>
    <input type="text" name="email" title="Email"/><br>
    Photo:<br>
    <input type="file" name="file"/><br>
    <%--<input type="file" name="file" required><br>--%>
    <br>
    <input type="submit" value="Create new user"/>
</form>

</body>
</html>
