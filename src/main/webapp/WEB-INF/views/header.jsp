<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 11.08.2018
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/user" prefix="userInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css"/>
<%--
    <fmt:setBundle basename = "message" var="msg"/>
--%>
</head>
<body>
<div class="header">
    <a href="/" class="logo">Periodical Editions</a>
    <div class="header-right">
        <a class="active" href="/">Home</a>
        <c:if test="${not access}">
            <a href="login">Login</a>
            <a href="register">Registration</a>
        </c:if>
        <c:if test="${access}">
            <%-- <a href="logout">Logout</a>--%>
            <a>
                <form name="logoutForm" action="logout" method="get">
                        <%--<a href="logout">Logout</a>--%>
                    <input type="submit" ` name="command" value="logout">
                </form>
            </a>
        </c:if>

    </div>

    <%--  <form name="localeForm" action="locale" method="post">--%>
  <%--  <input type="hidden" name="command" value="locale">--%>
    <div class="locale">
        <%-- <button name="locale" value="en"><img src="../../css/images/en.png"></button>
         <button name="locale" value="ua"><img src="../../css/images/ua.png"></button>
         <button name="locale" value="ru"><img src="../../css/images/ru.png"></button>--%>
        <a href="Controller?command=locale&locale=en"><img src="../../css/images/en.png"></a>
        <a href="Controller?command=locale&locale=ua"><img src="../../css/images/ua.png"></a>
        <a href="Controller?command=locale&locale=ru"><img src="../../css/images/ru.png"></a>

        <%--<input type="hidden" name="src" value="/index.jsp">--%>
    </div>
    <%--  </form>--%>


</div>
<div style="padding-left:20px; color: #555;">
    <h4><userInfo:getinfo/></h4>
</div>
</body>
</html>
