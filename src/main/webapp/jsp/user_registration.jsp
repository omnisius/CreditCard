<!--
Created by IntelliJ IDEA.
User: БОГДАН
Date: 16.04.14
Time: 16:25
To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<link href="style.css" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<%@ include file="local.jsp" %>
<h3 style="color: white"><fmt:message key="REGISTRATION"/></h3>
<hr/>
<form style="color: white" name="registrationForm" method="POST" action="process">
    <input type="hidden" name="command" value="add new user">
    <fmt:message key="СODE"/>:<br/>
    <input type="text" name="id" value=""><br/>
    <fmt:message key="NAME"/>:<br/>
    <input type="text" name="fio" value=""><br/>
    <fmt:message key="LOGIN"/>:<br/>
    <input type="text" name="login" value=""><br/>
    <fmt:message key="PASSWORD"/>:<br/>
    <input type="password" name="password" value=""><br/>
    <input type="submit" value="<fmt:message key="ENTER"/>">
</form>
</body>
</html>