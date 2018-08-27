<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 11.08.2018
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/user" prefix="userInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css"/>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<div class="header">
    <a href="/" class="logo"><fmt:message key="periodical_editions"/></a>
    <div class="header-right">
        <a class="active" href="/"><fmt:message key="home"/> </a>
        <c:if test="${empty user}">
            <a href="login"><fmt:message key="sign_in"/> </a>
            <a href="register"><fmt:message key="registration"/> </a>
        </c:if>
        <c:if test="${not empty user}">
            <%--    <a>
                    <form name="logoutForm" action="logout" method="get">
                        <input type="submit" name="command" value="logout">
                    </form>
                </a>--%>
            <a href="Controller?command=logout"><fmt:message key="sign_out"/> </a>
            <a href="Controller?command=userPeriodicals">My periodicals</a>
        </c:if>

    </div>

    <div class="locale">
        <a href="Controller?command=locale&locale=en"><img src="../../css/images/en.png"></a>
        <a href="Controller?command=locale&locale=ua"><img src="../../css/images/ua.png"></a>
        <a href="Controller?command=locale&locale=ru"><img src="../../css/images/ru.png"></a>
    </div>
</div>
</body>
</html>
