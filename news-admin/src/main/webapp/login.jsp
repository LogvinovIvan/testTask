<%--
  Created by IntelliJ IDEA.
  User: Ivan_Lohvinau
  Date: 11/2/2016
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spting" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:url var="authUrl" value="/static/j_spring_security_check"/>
<form method="post" action="${authUrl}">
    <input name="username" type="text">
    <input name="password" type="password">
    <input name="remember_me" type="checkbox" id="remember_me">
    <input name="commit" type="submit">
</form>
</body>
</html>
