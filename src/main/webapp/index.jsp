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
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title>JSP Page</title>
</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<ul>
    <form name="localeForm" method="POST" action="com.bionic.bardakov.web.controller">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="jsp/ramblingsoul9/images/ua.png" name="local" value="uk_UA">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
</ul>
<ul>
    <form name="localeForm" method="POST" action="com.bionic.bardakov.web.controller">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="jsp/ramblingsoul9/images/us.png" name="local" value="en_US">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
</ul>
<ul>
    <form name="localeForm" method="POST" action="com.bionic.bardakov.web.controller">
        <input type="hidden" name="command" value="local"/>
        <input type="image" src="jsp/ramblingsoul9/images/ru.png" name="local" value="ru_RU">
        <input type="hidden" name="page" value="INDEX_PAGE_PATH">
    </form>
</ul>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<div id="wrap">
    <div id="header">
        <h1><a href="#">CCWA.com </a><span class="desc"><fmt:message key="WELCOME_MESSAGE"/><br/></span></h1>

        <div id="topnav">
            <ul>
                <li><a href="com.bionic.bardakov.web.controller"><fmt:message key="LOGIN"/></a></li>
                <li>
                    <form name="registrationForm" method="POST" action="com.bionic.bardakov.web.controller">
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


