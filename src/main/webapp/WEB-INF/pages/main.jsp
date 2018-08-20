<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 15.08.2018
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/user" prefix="userTag" %>


<html>
<head>
    <title>Main</title>
<%--
    <link rel="stylesheet" href="../../css/style.css"/>
--%>
</head>
<body>
<%--<c:set var="lang" scope="session" value="${lang}"/>
<c:choose>
    <c:when test="${lang.equals('en')}">
        <fmt:setLocale value="EN" scope="session"/>
        <fmt:setBundle basename="EN" scope="session"/>
    </c:when>
    <c:when test="${lang.equals('ru')}">
        <fmt:setLocale value="RU" scope="session"/>
        <fmt:setBundle basename="RU" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="EN" scope="session"/>
        <fmt:setBundle basename="EN" scope="session"/>
    </c:otherwise>
</c:choose>--%>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="localization" scope="session"/>

<c:set var="role" scope="session" value="${role}"/>
<c:choose>
    <c:when test="${role=='admin'}">
        <jsp:include page="../views/header.jsp"/>
        <jsp:include page="../views/content.jsp"/>
    </c:when>
    <c:when test="${role=='reader'}">
        <jsp:include page="../views/header.jsp"/>
        <jsp:include page="../views/content.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../views/header.jsp"/>
        <jsp:include page="../views/content.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
