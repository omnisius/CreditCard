<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 22.04.2014
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/WEB-INF/tlds/error.tld" prefix="errortag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Card Error</title>
</head>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<body style="background: #ff562d">
<h3><fmt:message key="ERROR"/></h3>
<hr/>
<errortag:error/>
<hr/>
<form action="com.bionic.bardakov.web.controller" >
    <input type = "hidden" name = "command" value = "get new card"/>
    <input type="submit" value="<fmt:message key="GET_CARD"/>"/>
</form>
</body>
</html>
