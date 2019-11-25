
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/list/create" method="post">
    Name: <input type="text" name="name" title="Name"/><br/>
    Login: <input type="text" name="login" title="Login"/><br/>
    Email: <input type="text" name="email" title="Email"/><br/>
    <br>
    <input type="submit" value="Create new user"/>
</form>

</body>
</html>
