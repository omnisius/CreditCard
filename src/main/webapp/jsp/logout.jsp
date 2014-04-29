<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 18.04.14
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
<head>
    <title>logout</title>
</head>
<body style="background-color: #ec9d0d;">
<h3><fmt:message key="GOODBYE"/></h3>
<hr/>
<a href="process"> <fmt:message key="TO_LOGIN"/></a>
</body>
</html>