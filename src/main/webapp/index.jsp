<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Main</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<c:set var="user" scope="session" value="${user}"/>
<c:choose>
    <c:when test="${user.getUserRole().getRole()=='admin'}">
        <jsp:include page="WEB-INF/views/header.jsp"/>
        <%--<div style="padding-left:20px; color: #555;">
            <h4><userInfo:getInfo/></h4>
        </div>--%>
        <jsp:include page="WEB-INF/views/content.jsp"/>
    </c:when>
    <c:when test="${user.getUserRole().getRole()=='reader'}">
        <jsp:include page="WEB-INF/views/header.jsp"/>
        <%--<div style="padding-left:20px; color: #555;">
            <h4><userInfo:getInfo/></h4>
        </div>--%>
        <jsp:include page="WEB-INF/views/content.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="WEB-INF/views/header.jsp"/>
        <jsp:include page="WEB-INF/views/content.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
