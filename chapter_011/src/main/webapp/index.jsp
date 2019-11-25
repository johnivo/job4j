<%@ page import="ru.job4j.crud.datamodel.User" %>
<%@ page import="ru.job4j.crud.logic.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users list</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/list/create" method="get">
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
    <% for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/list/update" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list/delete" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="delete"/>
            </form>
        </td>
    </tr>
    <%} %>
</table>

</body>
</html>
