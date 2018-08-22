<%--
  Created by IntelliJ IDEA.
  User: Julia
  Date: 15.08.2018
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%--<h3>Error</h3>--%>
<%--
<br>
<jsp:expression>(request.getAttribute("error")!=null)?
    (String) request.getAttribute("error") : "unknown error"</jsp:expression>
<br>
<a href="Controller">Return to login page</a>
--%>
<jsp:include page="/login"/>

</body>
</html>
