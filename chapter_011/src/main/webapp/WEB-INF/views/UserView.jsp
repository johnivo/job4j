
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #F8F8FF;
        }
        thead {
            background-color: gainsboro;
        }
        h2 {
            color: #008080;
            text-align: left;
        }
        p {
            font-family: Verdana,serif;
            color: #008B8B;
            text-align: left;
            font-size: 15px;
        }
        label {
            color: #008B8B;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>User page</h2>
    <p>Your personal data</p>

    <table class="table table-hover" id="table">
        <thead>
        <tr>
            <th>id</th>
            <th>photo</th>
            <th>name</th>
            <th>login</th>
            <th>email</th>
            <th>country</th>
            <th>city</th>
            <th>action</th>
        </tr>
        </thead>
        <%--<tbody id="datatable" onload="getPersons()">--%>
        <tbody id="datatable">
        <tr>
            <td>${user.id}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty user.photoId}">
                        <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                             width="100px" height="100px"/></br>
                        <br>
                        <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                           class="btn btn-info btn-xs">download</a>
                    </c:when>
                    <c:otherwise>no image</c:otherwise>
                </c:choose>
            </td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.country}</td>
            <td>${user.city}</td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <button type="submit" class="btn btn-info btn-sm">update</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="form-group">
        <form action="${pageContext.servletContext.contextPath}/signout" method="get">
            <%--<button type="submit" class="btn btn-primary" onclick="return validate()">Exit</button>--%>
            <button type="submit" class="btn btn-primary">Exit</button>
        </form>
    </div>
</div>

</body>
</html>
