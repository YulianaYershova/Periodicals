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
<form name="registrationForm" action="/register" method="post">
    <jsp:include page="../views/header.jsp"/>
    <input type="hidden" name="command" value="registration">
    Registration:
    <br>
    *Name
    <input type="text" name="name" value="" required>
    <br>
    *Login
    <input type="text" name="login" value="" required>
    <br>
    *Password:
    <input type="password" name="password" value="" required>
    <br>
    *Confirm password:
    <input type="password" name="confirmPassword" value="" required>
    <br>
    <input type="submit" value="Enter">
    ${error}
    <jsp:include page="../views/footer.jsp"></jsp:include>
</form>

<form name="registrationForm" action="register" method="post">
    <input type="hidden" name="command" value="registration">
    <div class="loginContainer">
        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Username" name="name" required>

        <label><b>Login</b></label>
        <input type="password" placeholder="Enter Login" name="login" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <div class="loginButton">
            <button type="submit">Create account</button>
        </div>
        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
    </div>
</form>
</body>
</html>
