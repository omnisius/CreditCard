
<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 16.04.14
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/WEB-INF/tlds/cardtag.tld" prefix="mytag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link href="jsp/ramblingsoul9/style.css" rel="stylesheet" type="text/css"/>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<head/>
<body>
<strong style = "color:white" ><mytag:body><fmt:message key="SELECT_UNLOCK"/></mytag:body></strong>
<br><br>
<form style = "color:white" name="accountUnlockForm" method="POST" action="com.bionic.bardakov.web.controller" >
    <mytag:body size="${size}">
        <c:forEach var="userAccountNumber" items="${accountNum}">
           <input type="radio" name="userAccountNumber" value="${userAccountNumber}">${userAccountNumber}<br/>
        </c:forEach>
        <input style="color: rgba(100, 255, 20, 0.04)" type="submit" name = "command" value="unlock account">
    </mytag:body>
</form>
</body>
</html>