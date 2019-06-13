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
<c:out value="${language}"></c:out>ооо
<fmt:message key="Login" />
<div class="login-page">
    <div class="form">
        ${requestScope.some};
        <form class="login-form" method="Post" action="simple">
            <input type="text" name="login" placeholder="Логин" required>
            <input type="password" name="password" placeholder="Пароль" required>
            <input type="hidden" name="WhatToDo" value="Login" />
            <button name="submit" type="submit">Войти</button>
            <p class="message">Не зарегистрированы?</p>
        </form>
        <form action="simple" method="post">
        <input type="hidden" name="WhatToDO" value="GoToRegistration" />
        <button>Cоздайте аккаунт</button>
        </form>
    </div>
</div>
</body>
</html>
