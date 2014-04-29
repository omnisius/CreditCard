<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Богдан
  Date: 26.04.2014
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<fmt:setLocale value="${local}" scope="session"/>
<fmt:setBundle basename="com.bionic.bardakov.web.messages.messages"/>
    <form align="right" name="localeForm" method="POST" action="process">
        <input type = "hidden" name = "command" value = "local"/>
        <input type="image" src="images/ua.png" name="local" value="uk_UA" >
        <input type = "hidden" name = "page" value = "${page}">
    </form>
    <form align="right" name="localeForm" method="POST" action="process">
        <input type = "hidden" name = "command" value = "local"/>
        <input type="image" src="images/us.png" name="local" value="en_US" >
        <input type = "hidden" name = "page" value = "${page}">
    </form>
    <form align="right" name="localeForm" method="POST" action="process">
        <input type = "hidden" name = "command" value = "local"/>
        <input type="image" src="images/ru.png" name="local" value="ru_RU" >
        <input type = "hidden" name = "page" value = "${page}">
    </form>
</body>
</html>
