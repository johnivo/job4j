
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
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
            $.ajax({
                url: "./city",
                method: "get",
                complete: function (data) {
                    var result = "<option></option>";
                    var countries = JSON.parse(data.responseText);
                    for (var i = 0; i < countries.length; i++) {
                        result += "<option value=\"" + countries[i] + "\">" + countries[i] + "</option>";
                    }
                    document.getElementById("country").innerHTML = result;
                }
            });
        });

        function getCity() {
            $.ajax({
                url: "./city",
                method: "post",
                data: {"country" : $("#country").val()},
                complete: function (data) {
                    var result = "<option></option>";
                    var cities = JSON.parse(data.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        result += "<option value=\"" + cities[i] + "\">" + cities[i] + "</option>";
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
    <h2>Hello!</h2>
    <p>Enter personal details, please!</p>
    <div class="form-group">
        <form action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label class="control-label col-sm-1" for="name">Name:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter name">
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="login">Login:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="login" id="login" placeholder="Enter login">
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="password">Password:</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" maxlength="16" name="password" id="password" placeholder="Enter password">
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="role">Role:</label>
                <div class="col-sm-4">
                    <select class="form-control" name="role" id="role">
                        <option value="user">user</option>
                        <option value="admin">admin</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="email">Email:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="email" id=email placeholder="Enter email">
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-1" for="country">Country:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="country" name="country" onchange="getCity()" required></select>
                </div>
            </div>
            <div class="form-group row">
                <label class="control-label col-sm-1" for="city">City:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="city" name="city" required></select>
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-1" for="email">Photo:</label>
                <div class="col-sm-4">
                    <input type="file" class="form-control-file" name="file">
                </div>
            </div>
            <div class="col-sm-offset-0">
                <button type="submit" class="btn btn-primary" onclick="return validate()">Create new user</button>
            </div>
        </form>
    </div>
    <div class="form-group">
        <form action="${pageContext.servletContext.contextPath}/UsersView" method="get">
            <div class="col-sm-offset-0">
                <button type="submit" class="btn btn-info">Back to list</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
