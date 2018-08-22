<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.08.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/catalog" prefix="catalog" %>


<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
    <%-- <fmt:setBundle basename="message" var="msg"/>--%>
</head>
<body>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>

<div class="head">
    <h6> Ut enim ad minim veniam 1962 </h6>
    <%--<h1> <fmt:message key="periodicals" bundle="${msg}"/></h1>--%>
    <h1><fmt:message key="periodicals"/></h1>

    <div class="title">All periodicals</div>
</div>
<catalog:getCatalog/>
<div class="container">
    <ul>
        <c:forEach var="item" items="${periodicals}">
            <li>
                <a href="periodical?command=periodical&id=${item.getId()}">
                    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
                    <div class="mainHeader">${item.getTitle()}</div>
                    <img src="../../css/images/book.jpg" alt="image">
                    <p>Category: ${item.getPeriodicalCategory().getCategory()}</p>
                    <p>Price: ${item.getPrice()}</p>
                    <p>Period: ${item.getPeriod()}</p>
                    <p style="color: chocolate"> Details</p></a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>