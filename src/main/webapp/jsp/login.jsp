<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 15.08.2018
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Login</h3>
<br>
<form name="loginForm" action="/Controller" method="post">
    <input type="hidden" name="command" value="login">
    Login:
    <br>
    <input type="text" name="login" value="">
    <br>
    Password: <br>
    <input type="password" name="password" value="">
    <br>
    <input type="submit" value="Enter">
</form>
</body>
</html>
