<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 14.04.14
  Time: 15:49
  To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<head>
    <title>Account update</title>
</head>
<body>
<h3 style="color: white"><fmt:message key="ADD_MONEY"/></h3>
<hr/>
<form style="color: white" name="accountUpForm" method="POST" action="com.bionic.bardakov.web.controller">
    <input type="hidden" name="command" value="update account">
    <fmt:message key="MONEY"/><br/>
    <input type="text" name="money" value=""><br/>
    <input type="submit" value="<fmt:message key="ENTER"/>">
</form>
</body>
</html>