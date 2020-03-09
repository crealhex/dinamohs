<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Dinamo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css">
    <script src="${pageContext.request.contextPath}/static/scripts.js"></script>
</head>
<body>
    <div id="wrap">
        <div id="header">
            <div class="title">
                Dinamo <br> High School
            </div>
        </div>