
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>

<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login:<br>
    <input type="text" name="login"></br>
    Password:<br>
    <input type="password" maxlength="16" name="password"></br>
    </br>
    <input type="submit" value="Sign in">
</form>

</body>
</html>
