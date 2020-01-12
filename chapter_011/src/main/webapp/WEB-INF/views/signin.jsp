
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var login = $('#login');
            var password = $('#password');
            var message = '';

            if (login.val() === '') {
                message += login.attr('placeholder') + '\n';
                result = false;
            }
            if (password.val() === '') {
                message += password.attr('placeholder') + '\n';
                result = false;
            }
            if (!result) {
                alert(message);
            }
            return result;
        }
    </script>
    <style>
        body {
            background-color: #F8F8FF;
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

<div class="container-fluid">
    <h2>Welcome!</h2>
    <p>Log in, please.</p>

    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="form-group">
            <c:if test="${error != ''}">
                <div style="background-color: red">
                    <c:out value="${error}"/>
                </div>
            </c:if>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="login" id="login" placeholder="Enter login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" maxlength="16" name="password" id="password" placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary" onclick="return validate()">Sign in</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
