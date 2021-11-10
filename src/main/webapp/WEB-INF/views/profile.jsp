<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 02.11.2021
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Профиль">
    <form class="account-form">
        <div class="row">
            <div class="col">
                <input type="text" class="form-control" placeholder="Имя" value="${user.getFirstName()}" disabled>
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Фамилия" value="${user.getLastName()}" disabled>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <input type="email" class="form-control" placeholder="Email" value="${user.getEmail()}" disabled>
            </div>
        </div>
        <div class="d-grid gap-2">
            <form method="get">
                <button type="submit" class="btn btn-primary btn-secondary" name="action" value="change">Изменить
                    данные
                </button>
                <button type="submit" class="btn btn-primary btn-danger" name="action" value="quit">Выйти</button>
            </form>
        </div>
    </form>
</t:layout>
