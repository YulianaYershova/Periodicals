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
<jsp:include page="../views/header.jsp"/>
<form name="loginForm" action="login" method="post">
    <input type="hidden" name="command" value="login">
    <div class="formContainer">
        <label><b>Login</b></label>
        <input type="text" placeholder="Enter Username" name="login" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <div class="submitButton">
            <button type="submit">Login</button>
        </div>

        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
        <label style="color: brown">
        ${error}
        </label>
    </div>
</form>
</body>

</html>
