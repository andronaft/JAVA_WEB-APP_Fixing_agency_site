<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: andro
  Date: 29.05.2019
  Time: 1:48
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
    <title><fmt:message key="Regiser"/> </title>
    <style>
        <%@include file="../css/enter.css"%>
    </style>
</head>
<body>
<div class="login-page">
    <c:out value="${errors}" default=""/>
    <div class="form">
        <form class="login-form" method="post" action="simple">
            <input type="text" name="login" placeholder="<fmt:message key="Login"/>" required>
            <input type="password" name="password" placeholder="<fmt:message key="Password"/>" required>
            <fmt:message key="Do_you_want_work"/>
            <input type="radio" name="role" value="master" >
            <input type="hidden" name="WhatToDo" value="Registration" />
            <button name="submit" type="submit"><fmt:message key="Regiser"/></button>
            <p class="message"><fmt:message key="Are_registered"/></p>
        </form>
        <form action="simple" method="post">
            <input type="hidden" name="GoTo" value="WEB-INF/jsps/login.jsp" />
            <button><fmt:message key="Login_page"/> </button>
        </form>
    </div>
</div>
</body>
</html>
