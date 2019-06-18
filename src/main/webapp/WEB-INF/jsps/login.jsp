<%--
  Created by IntelliJ IDEA.
  User: andro
  Date: 30.05.2019
  Time: 1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="TextBundle"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title><fmt:message key="Login" /></title>
    <style>
        <%@include file="../css/enter.css"%>
    </style>
</head>
<body>
<div class="login-page">
    <c:out value="${errors}" default=""/>
    <div class="form">
        <form class="login-form" method="Post" action="simple">
            <input type="text" name="login" placeholder="<fmt:message key="Login" />" required>
            <input type="password" name="password" placeholder="<fmt:message key="Password" />" required>
            <input type="hidden" name="WhatToDo" value="Login" />
            <button name="submit" type="submit"><fmt:message key="Enter"/> </button>
            <p class="message"><fmt:message key="Not_registered"/> </p>
        </form>
        <form action="simple" method="post">
        <input type="hidden" name="GoTo" value="WEB-INF/jsps/register.jsp" />
        <button><fmt:message key="Create_account"/> </button>
        </form>
    </div>
</div>
</body>
</html>
