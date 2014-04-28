<!--
Created by IntelliJ IDEA.
User: БОГДАН
Date: 14.04.14
Time: 15:49
To change this template use File | Settings | File Templates.
-->

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<head>
    <title>login</title>
</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<%@ include file="local.jsp" %>
<h2 align="center" style="color: white"><fmt:message key="LOGIN"/></h2>
<h3>
    <form align="center" style="color: white" name="loginForm" method="POST" action="com.bionic.bardakov.web.controller">
        <input type="hidden" name="command" value="login">
        <fmt:message key="LOGIN"/>:<br/>
        <input type="text" name="login" value=""><br/>
        <fmt:message key="PASSWORD"/>:<br/>
        <input type="password" name="password" value=""><br/>
        <input type="submit" value="<fmt:message key="ENTER"/>">
    </form>
</h3>
</body>
</html>
