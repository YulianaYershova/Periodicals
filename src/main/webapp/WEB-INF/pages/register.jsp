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
<jsp:include page="../views/header.jsp"/>
<form name="registrationForm" action="register" method="post">
    <input type="hidden" name="command" value="register">
    <div class="formContainer">
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="name" required>

        <label><b>Login</b></label>
        <input type="text" placeholder="Enter Username" name="login" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <label><b>Confirm password</b></label>
        <input type="password" placeholder="Confirm password" name="confirmPassword" required>

        <div class="submitButton">
            <button type="submit">Create account</button>
        </div>

        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
        <label style="color: brown">
            ${error}
        </label>
    </div>
</form>
</html>
