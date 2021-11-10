<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 08.11.2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Изменить">
  <form method="post" class="account-form">
    <div class="row">
      <div class="col">
        <input type="text" name="fname" class="form-control" placeholder="Имя" value="${user.getFirstName()}">
      </div>
      <div class="col">
        <input type="text" name="lname" class="form-control" placeholder="Фамилия"
               value="${user.getLastName()}">
      </div>
    </div>
    <div class="row">
      <div class="col">
        <input type="password" name="psw" class="form-control" placeholder="Новый пароль">
      </div>
    </div>
    <div class="d-grid gap-2">
      <button type="submit" class="btn btn-primary btn-success" name="action" value="save">Сохранить</button>
      <button type="submit" class="btn btn-primary btn-dark" name="action" value="cancel">Отмена</button>
    </div>
  </form>
</t:layout>
