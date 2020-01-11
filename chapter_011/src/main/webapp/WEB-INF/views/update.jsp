
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        //также для валидации полей формы можно использовать атрибут required.
        //required устанавливает поле формы обязательным для заполнения перед отправкой формы на сервер.
        //Если обязательное поле пустое, браузер выведет сообщение, а форма отправлена не будет.
        function validate() {
            var result = true;
            var name = $('#name');
            var login = $('#login');
            var password = $('#password');
            var email = $('#email');
            var message = '';

            if (name.val() === '') {
                message += name.attr('placeholder') + '\n';
                result = false;
            }
            if (login.val() === '') {
                message += login.attr('placeholder') + '\n';
                result = false;
            }
            if (password.val() === '') {
                message += password.attr('placeholder') + '\n';
                result = false;
            }
            if (email.val() === '') {
                message += email.attr('placeholder') + '\n';
                result = false;
            }
            if (!result) {
                alert(message);
            }
            return result;
        }

        $(document).ready(function () {
            var country = "<c:out value="${user.country}"/>";
            $.ajax({
                url: "./city",
                method: "get",
                complete: function (data) {
                    var result = "<option></option>";
                    var countries = JSON.parse(data.responseText);
                    for (var i = 0; i < countries.length; i++) {
                        var selected = country === countries[i] ? "selected" : "";
                        result += "<option " + selected +" value=" + countries[i] + ">" + countries[i] + "</option>"
                    }
                    document.getElementById("country").innerHTML = result;
                }
            });
            fillCity();
        });
        function fillCity() {
            var country = "<c:out value="${user.country}"/>";
            var city = "<c:out value="${user.city}"/>";
            $.ajax({
                url: "./city",
                method: "post",
                data: {"country" : country },
                complete: function (data) {
                    var result = "<option></option>";
                    var cities = JSON.parse(data.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        var selected = city === cities[i] ? "selected" : "";
                        result += "<option " + selected + " value=" + cities[i] + ">" + cities[i] + "</option>";
                    }
                    document.getElementById("city").innerHTML = result;
                }
            });
        }
        function getCityByCountry() {
            $.ajax({
                url: "./city",
                method: "post",
                data: {"country" : $("#country").val()},
                complete: function (data) {
                    var result = "<option></option>";
                    var cities = JSON.parse(data.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        result += "<option value="+ cities[i] + ">" + cities[i] + "</option>";
                    }
                    document.getElementById("city").innerHTML = result;
                }
            });
        }

    </script>

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
    <div class="form-group">
        <hr>
        <div class="form-group">
            <c:choose>
                <c:when test="${not empty user.photoId}">
                    <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}"
                         width="100px" height="100px"/></br>
                </c:when>
                <c:otherwise>no image</c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <form action="${pageContext.servletContext.contextPath}/upload" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${user.id}"/>
                <%--<input type="file" class="form-control-file" name="file">--%>
                <%--<button type="submit" class="btn btn-primary">Upload</button>--%>

                <div class="form-group">
                    <input type="file" class="form-control-file" name="file">
                </div>
                <div class="col-sm-offset-0">
                    <button type="submit" class="btn btn-primary">Upload photo</button>
                </div>
            </form>
        </div>
        <hr>
    </div>

    <div class="form-group">
        <form action="${pageContext.servletContext.contextPath}/update" method="post">
            <input type="hidden" name="id" value="${user.id}"/>

            <div class="form-group row">
                <label class="control-label col-sm-1" for="name">Name:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="name" id="name" value="${user.name}" required>
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="login">Login:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="login" id="login" value="${user.login}" required>
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="password">Password:</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" maxlength="16" name="password" id="password" value="${user.password}" required>
                </div>
            </div>
            <div class="form-group row">
                <c:if test="${sessionScope.role == 'admin'}">
                    <label class="control-label col-sm-1" for="role">Role:</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="role" id="role" required>
                            <option value="user">user</option>
                            <option value="admin">admin</option>
                        </select>
                    </div>
                </c:if>
                <c:if test="${sessionScope.role != 'admin'}">
                    <input type="hidden" name="role" value="user">
                </c:if>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="email">Email:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="email" id=email value="${user.email}">
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-1" for="country">Country:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="country" name="country" onchange="getCityByCountry()" required></select>
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="city">City:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="city" name="city" required></select>
                </div>
            </div>

            <div class="col-sm-offset-0">
                <button type="submit" class="btn btn-primary">Update this user</button>
            </div>
        </form>
    </div>

    <div class="form-group">
        <c:choose>
            <c:when test="${sessionScope.role == 'admin'}">
                <form action="${pageContext.servletContext.contextPath}/UsersView" method="get">
                    <div class="col-sm-offset-0">
                        <button type="submit" class="btn btn-info">Back to list</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.servletContext.contextPath}/UserView" method="get">
                    <div class="col-sm-offset-0">
                        <button type="submit" class="btn btn-info">Back to user page</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
