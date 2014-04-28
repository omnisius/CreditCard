
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
<head>CARDS LIST:</head>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<strong style="color: white"><mytag:body><fmt:message key="SELECT_CARD"/></mytag:body></strong>
<br><br>
    <form style="color: white" WIDTH="50%" name="cardsForm" method="POST" action="com.bionic.bardakov.web.controller" >
        <mytag:body size="${size}">
            <c:forEach var="cardNumber" items="${cardNum}">
                <input type="radio" name="cardNumber" value="${cardNumber}">${cardNumber}<br/>
           </c:forEach>
            <input style="color: rgba(80, 23, 255, 0.03)" type="submit" name = "command" value="to account" >
        </mytag:body>
        <form action="controller" >
            <input type = "hidden" name = "command" value = "back to main"/>
            <input type="submit" value="<fmt:message key="BACK"/>"/>
        </form>
    </form>
</body>
</html>