<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 16.08.2018
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="registrationForm" action="/Controller" method="post">
    <input type="hidden" name="command" value="login">
    Registration:
    <br>
    *Name
    <input type="text" name="name" value="" required>
    <br>
    *Login
    <input type="text" name="login" value="" required>
    *Password: <br>
    <input type="password" name="password" value="" required>
    <br>
    *Confirm password: <br>
    <input type="password" name="confirmPassword" value="" required>
    <br>
    <input type="submit" value="Enter">
    ${RegistrationMistake}
</form>
</body>
</html>
