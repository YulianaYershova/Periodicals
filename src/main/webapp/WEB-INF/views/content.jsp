<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 18.08.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>

<div class="head">
    <h6> Ut enim ad minim veniam 1962 </h6>
    <h1> Periodicals</h1>

    <div class="title">All periodicals</div>
</div>
<div class="container">
    <ul>
        <c:forEach var="item" items="${periodicals}">
            <li>
                <a href="">
                    <div class="type"><c:out value="${item.getPeriodicalType().getType()}"/></div>
                    <div class="mainHeader">${item.getTitle()}</div>
                    <img src="../../css/images/image1.jpg" alt="image">
                    <p>Category: ${item.getPeriodicalCategory().getCategory()}</p>
                    <p>Price: ${item.getPrice()}</p>
                    <p>Period: ${item.getPeriod()}</p>
                    <p>
                        <span class="dropcaps">D</span>dropcaps
                        <span class="red">scription:</span> ${item.getDescription()}
                        <span class="red">Commodo consequa t -></span>
                    </p>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
