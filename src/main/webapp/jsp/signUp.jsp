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
<form action="/signUp" method="post">
    <div class="container">
        <hr>
        <p>Пожалуйста, заполните эту форму, чтобы создать учетную запись.</p>
        <hr>

        <label for="fname"><b>Имя</b></label>
        <input type="text" placeholder="Введите ваше имя" name="fname" id="fname" required>

        <label for="lname"><b>Фамилия</b></label>
        <input type="text" placeholder="Введите вашу фамилию" name="lname" id="lname" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Введите вашу почту" name="email" id="email" required>

        <label for="psw"><b>Пароль</b></label>
        <input type="password" placeholder="Введите пароль" name="psw" id="psw" required>

        <label for="psw-repeat"><b>Повторить пароль</b></label>
        <input type="password" placeholder="Повторите пароль" name="psw-repeat" id="psw-repeat" required>

        <div class="clearfix">
            <button type="submit" class="signupbtn">Регистрация</button>
        </div>
    </div>
</form>
</body>
</html>
