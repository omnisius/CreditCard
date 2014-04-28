<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 21.04.2014
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/error.tld" prefix="errortag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Account Error</title>
    </head>
    <body style="background: #ff2700">
    <fmt:setLocale value="${local}" scope="session"/>
    <fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
    <h3><fmt:message key="ERROR"/></h3>
    <hr/>
    <errortag:error/>
    <hr/>
    <form action="com.bionic.bardakov.web.controller" >
        <input type = "hidden" name = "command" value = "back to account"/>
        <input type="submit" value="<fmt:message key="BACK"/>"/>
    </form>
    </body>
</html>