<?xml version="1.0" encoding="UTF-8"?>
<!--
Created by IntelliJ IDEA.
User: БОГДАН
Date: 14.04.14
Time: 15:49
To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/tlds/error.tld" prefix="errortag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body style="background: #ff3710">
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<h3><fmt:message key="ERROR"/></h3>
<hr/>
<errortag:error/>
<hr/>
<form action="process" >
    <input type = "hidden" name = "command" value = "registration"/>
    <input type="submit" value="<fmt:message key="REGISTRATION"/>"/>
</form>
</body>
</html>



