<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 02.11.2021
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

</form>
</body>
</html>
