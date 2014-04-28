<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 19.04.14
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<head>
    <title>
        Account
    </title>
</head>
<body style="background-color: #ECE5B6;" WIDTH="50%">
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<h3 align="center" style="color: white"> Admin <fmt:message key="ACCOUNT"/>
<c:out value="${accountNumber} ${smth}"/>
</h3>
<form align="center" action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "lock account"/>
    <input type="submit" value="<fmt:message key="LOCK_ACCOUNT"/>"/>
</form>
<form align="center" action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "go to unlock account"/>
    <input type="submit" value="<fmt:message key="UNLOCK_ACCOUNT"/>"/>
</form>
<form align="center" action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "go to update account"/>
    <input type="submit" value="<fmt:message key="PUT_MONEY"/>"/>
</form>
<form align="center" action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "go to payment"/>
    <input type="submit" value="<fmt:message key="MAKE_PAYMENT"/>"/>
</form>
<form  align="center"action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "money"/>
    <input type="submit" value="<fmt:message key="VIEW_MONEY"/>"/>
</form>
<form  align="center" action="com.bionic.bardakov.web.controller" >
    <input type = "hidden" name = "command" value = "show your cards"/>
    <input type="submit" value="<fmt:message key="RETURN_TO_CARDS"/>"/>
</form>
<form align="center"  action="com.bionic.bardakov.web.controller">
    <input type = "hidden" name = "command" value = "logout"/>
    <input type="submit" value="<fmt:message key="LOGOUT"/>"/>
</form>
</body>
</html>