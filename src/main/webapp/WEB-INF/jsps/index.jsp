<%--
  Created by IntelliJ IDEA.
  User: andro
  Date: 30.05.2019
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="TextBundle"/>
<fmt:message key="Repair_agency" var="Repair_ag"/>
<fmt:message key="This_is_service" var="This_is"/>
<fmt:message key="On_this_site" var="On_this"/>
<fmt:message key="Also_if_you_have" var="Also_if"/>
<fmt:message key="Text" var="Txt"/>
<fmt:message key="Information" var="information" scope="session"/>
<fmt:message key="Work_with_body" var="Work_with_body" scope="session"/>
<fmt:message key="Work_with_motor" var="Work_with_motor" scope="session"/>
<fmt:message key="Work_with_electrical" var="Work_with_electrical" scope="session"/>
<fmt:message key="Work_with_transmission" var="Work_with_transmission" scope="session"/>
<fmt:message key="Work_with_tires" var="Work_with_tires" scope="session"/>
<fmt:message key="Order_nuber" var="Order_nuber" scope="session"/>
<fmt:message key="Order_by" var="Order_by" scope="session"/>
<fmt:message key="Taken_by" var="Taken_by" scope="session"/>
<fmt:message key="When_done" var="When_done" scope="session"/>
<fmt:message key="Work_kind" var="Work_kind" scope="session"/>
<fmt:message key="Phone" var="Phone" scope="session"/>
<c:set var="Main_text" scope="session">
    <c:out value="${errors}" default=""/>
    <h2 class="post-title"><b>${Repair_ag}</b></h2>
    <b>${This_is}</b><br><br>
    ${On_this}<br>
    ${Also_if}<br><br>
    <h4><b>${information}</b></h4><br>
    ${Txt}
    <div class="post-footer">
        <a class="more-link" href="https://kakui.krutoi.proect/"><fmt:message key="Continue_reading"/></a>
    </div>
</c:set>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>H-F-A-S</title>
<style>
    <%@include file="../css/header.css"%>
    <%@include file="../css/aside.css"%>
    <%@include file="../css/foot.css"%>
    <%@include file="../css/main_contains.css"%>
    <%@include file="../css/media.css"%>
    <%@include file="../css/style.css"%>
</style>


    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,400italic,600,600italic,700,700italic|Playfair+Display:400,700&subset=latin,cyrillic">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="responsiveslides.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <script>
        $(function(){
            $('.link').click(function(e){
                e.preventDefault();
                var id = $(this).attr('href');

                $.ajax({
                    url: 'simple',
                    type:'post',
                    data: 'WhatToDo='+id,
                    success:function(responce){
                        $('#aticle').html(responce);
                    }
                })
            })

        });</script>
</head>
<body>
<header>
    <nav class="container">
        <a class="logo" href="">
            <span>H</span>
            <span>F</span>
            <span>A</span>
            <span>S</span>
        </a>
        <div class="nav-toggle"><span></span></div>
        <nav>
            <ul id="menu">
                <li><a class="link" href="1"><fmt:message key="Main" /></a></li>
                <li><a class="link" href="2"><fmt:message key="Price"/> </a></li>
                <li><a class="link" href="3-1"><fmt:message key="Oder"/></a></li>
                <li><a class="link" href="4-0"><fmt:message key="Cabinet"/></a></li>
            </ul>
        </nav>
    </nav>
</header>
<div class="container">
    <div class="posts-list">
        <article id="post-1" class="post">
            <div class="post-image">
            </div>
            <div class="post-content">
                <c:out value="${requestScope.errors}" default=""/>
                <div id="aticle" class="aticle">
                   ${Main_text}
                </div>

            </div>
        </article>
        <article id="post-2" class="post">
        </article>
    </div>
    <aside>
        <div class="widget">
            <h3 class="widget-title"><fmt:message key="Perhaps_interesting"/></h3>
            <ul class="widget-posts-list">
                <li>
                    <div class="post-image-small">
                        <a href=""></a>
                    </div>
                    <h4 class="widget-post-title">Botnet</h4>
                </li>
                    <div class="post-image-small">
                        <a href=""></a>
                    </div>
                    <h4 class="widget-post-title">Криптор</h4>
                </li>
            </ul>
        </div>
    </aside>
</div>
<footer>
    <div class="container">
        <div class="footer-col"><span>by zuk coprght© 2019</span></div>
        <div class="footer-col">
            <div class="social-bar-wrap">

            </div>
        </div>
        <div class="footer-col">
            <fmt:message key="Contact"/> <i class="fas fa-arrow-circle-right"></i>
            <a title="Telegram" href="tg://resolve?domain=z_u_k" target="_blank"><i class="fab fa-telegram-plane"></i></a>
        </div>
    </div>
</footer>
<script>
    $('.nav-toggle').on('click', function(){
        $('#menu').toggleClass('active');
    });
</script>
</body>
</html>
