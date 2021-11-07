<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 02.11.2021
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<form action="/signIn" method="post">

    <div class="container">
        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Введите email" name="email" id="email" required>

        <label for="psw"><b>Пароль</b></label>
        <input type="password" placeholder="Введите пароль" name="psw" id="psw" required>

        <button type="submit">Войти</button>
    </div>

    <div>
        Нет аккаунта?
        <a class="registration" href="<c:url value="/signUp"/>">
            Регистрация
        </a>
    </div>
</form>
</body>
</html>