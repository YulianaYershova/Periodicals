<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 21.08.2018
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Periodical</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<jsp:include page="../views/header.jsp"/>
<div class="container">
    <ul>
        <li>
            <c:set var="item" scope="session" value="${periodical}"/>
            <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
            <div class="mainHeader">${item.getTitle()}</div>
            <img src="../../css/images/book.jpg" alt="image">
            <p>Category: ${item.getPeriodicalCategory().getCategory()}</p>
            <p>Price: ${item.getPrice()}</p>
            <p>Period: ${item.getPeriod()}</p>
            <p>
               <%-- <span class="dropcaps">D</span>--%>
                <span class="red">Description:</span> ${item.getDescription()}
            </p>
            <form name="subscribeForm" action="subscribe" method="post">
                <input type="hidden" name="command" value="subscribe" required>
                <div class="submitButton">
                    <button type="submit">Subscribe</button>
                </div>

            </form>
        </li>
    </ul>
</div>
</body>
</html>
