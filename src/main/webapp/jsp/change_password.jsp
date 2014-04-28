<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 17.04.14
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <title>Change Password</title>
</head>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<body>
<h3 style="color: white"><fmt:message key="CHANGE_PASSWORD"/></h3>
<hr/>
<form style="color: white" name="loginForm" method="POST" action="com.bionic.bardakov.web.controller">
    <input type="hidden" name="command" value="change password">
    <fmt:message key="PASSWORD"/>:<br/>
    <input type="password" name="password" value=""><br/>
    <input type="submit" value="<fmt:message key="ENTER"/>"/>
</form>
</body>
</html>