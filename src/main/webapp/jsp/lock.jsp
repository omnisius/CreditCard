<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 21.04.2014
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<head>
    <title>Account Locked</title>
</head>
<body style="background: #ff8211">
<h3></h3>
<hr/>
<fmt:message key="LOCK"/>
<hr/>
<form action="com.bionic.bardakov.web.controller" >
    <input type = "hidden" name = "command" value = "show your cards"/>
    <input type="submit" value="<fmt:message key="BACK"/>"/>
</form>
</body>
</html>
