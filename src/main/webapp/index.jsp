<!--
Created by IntelliJ IDEA.
User: БОГДАН
Date: 14.04.14
Time: 15:49
To change this template use File | Settings | File Templates.
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link href="style.css" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title>elcome</title>
</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
    <form name="localeForm" method="POST" action="process">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="images/ua.png" name="local" value="uk_UA">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
    <form name="localeForm" method="POST" action="process">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="images/us.png" name="local" value="en_US">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
    <form name="localeForm" method="POST" action="process">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="images/ru.png" name="local" value="ru_RU">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<div id="wrap">
    <div id="header">
        <h1><a href="#"><fmt:message key="WELCOME_MESSAGE"/> </a></h1>

        <div id="topnav">
            <ul>
                <li><a href="process"><fmt:message key="LOGIN"/></a></li>
                <li>
                    <form name="registrationForm" method="POST" action="process">
                        <input type="hidden" name="command" value="registration"/>
                        <input type="submit" value="<fmt:message key="REGISTRATION"/>">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>


