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


<html>
<head>
    <title>Main</title>
    <%--<fmt:setBundle basename="message" scope="session" var="msg"/>--%>
</head>
<body>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="message"/>


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
