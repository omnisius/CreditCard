<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<head>
    <title>
        Credit Card Web Application
    </title>
</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<div id="wrap">
    <div id="header">
        <h1><a href="#"><c:out value="${user}, ${smth}"/> </a><span class="desc"><fmt:message key="ACTIONS"/> </span>
        </h1>

        <div id="topnav">
            <ul>
                <li>
                    <form action="com.bionic.bardakov.web.controller">
                        <input type="hidden" name="command" value="go to change password"/>
                        <input type="submit" value="<fmt:message key="PASSWORD_CHANGE"/>"/>
                    </form>
                </li>
                <li>
                    <form align="center" action="com.bionic.bardakov.web.controller">
                        <input type="hidden" name="command" value="show your cards"/>
                        <input type="submit" value="<fmt:message key="CARDS"/>"/>
                    </form>
                </li>
                <li>
                    <form align="center" action="com.bionic.bardakov.web.controller">
                        <input type="hidden" name="command" value="get new card"/>
                        <input type="submit" value="<fmt:message key="GET_CARD"/>"/>
                    </form>
                </li>
                <li>
                    <form align="center" action="com.bionic.bardakov.web.controller">
                        <input type="hidden" name="command" value="logout"/>
                        <input type="submit" value="<fmt:message key="LOGOUT"/>"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>


