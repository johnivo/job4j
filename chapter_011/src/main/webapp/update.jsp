
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/list/update" method="post">
    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>"/>
    Name: <input type="text" name="name" value="<%=request.getAttribute("name")%>"/><br/>
    Login: <input type="text" name="login" value="<%=request.getAttribute("login")%>"/><br/>
    Email: <input type="text" name="email" value="<%=request.getAttribute("email")%>"/><br/>
    <br>
    <input type="submit" value="Update this user"/>
</form>

</body>
</html>
