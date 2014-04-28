<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 20.04.14
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<head>
    <title>Money on your account</title>
</head>
<body>
<hr/>
<h1 style="color: white">
    <fmt:message key="MONEY"/><c:out value=" : ${money}"/>
</h1>
<hr/>
<form action="com.bionic.bardakov.web.controller">
    <input type="hidden" name="command" value="back to account"/>
    <input type="submit" value="<fmt:message key="BACK"/>"/>
</form>
</body>
</html>