<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 19.04.14
  Time: 2:07
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<head>
    <title style="color: white">Make payment</title>
</head>
<body>
<h3 style="color: white"><fmt:message key="MONEY_PAY"/></h3>
<hr/>
<form style="color: white" name="paymentForm" method="POST" action="com.bionic.bardakov.web.controller">
    <input type="hidden" name="command" value="make payment">
    <fmt:message key="MONEY"/>: <br/>
    <input type="text" name="moneySum" value=""><br/>
    <input type="submit" value="<fmt:message key="ENTER"/>">
</form>
</body>
</html>