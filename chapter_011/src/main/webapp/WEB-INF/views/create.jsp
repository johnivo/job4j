
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
    <input type="text" name="name" title="name" required/><br>
    Login:<br>
    <input type="text" name="login" title="login" required/><br>
    Password:<br>
    <input type="password" maxlength="16" name="password" title="password" required/><br>
    Role:<br>
    <select name="role" required>
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select></br>
    Email:<br>
    <input type="text" name="email" title="email" required/><br>
    Photo:<br>
    <input type="file" name="file"/><br>
    <br>
    <input type="submit" value="Create new user"/>
</form>
<form action="${pageContext.servletContext.contextPath}/UsersView" method="get">
    <input type="submit" value="Back to list" style="background-color: darkgrey"/>
</form>

</body>
</html>
