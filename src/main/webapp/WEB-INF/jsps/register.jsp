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
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="C:\Users\andro\IdeaProjects\mysite_0\src\mysite_0\resources\textBundle" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Title</title>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="simple">
            <input type="text" name="login" placeholder="Логин" required>
            <input type="password" name="password" placeholder="Пароль" required>
            <input type="hidden" name="WhatToDo" value="Registration" />
            <button name="submit" type="submit">Зарегистрироваться</button>
            <p class="message">Зарегистрированы?</p>
        </form>
        <form action="simple" method="post">
            <input type="hidden" name="WhatTodo" value="GoToLogin" />
            <button>Страница входа</button>
        </form>
    </div>
</div>
</body>
</html>
