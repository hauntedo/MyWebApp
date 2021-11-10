<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 09.11.2021
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Объявить вакансию">
<form method="post" class="jobs-create">
    <div class="container" data-id="${userId}">
        <label for="title"><b>Название</b></label>
        <input type="text" placeholder="Придумайте название" name="title" id="title" required>

        <label for="desc"><b>Описание</b></label>
        <input type="text" placeholder="Опишите" name="desc" id="desc" required>

        <button type="submit" class="btn btn-primary btn-success" name="action" value="save">Сохранить</button>
        <button type="submit" class="btn btn-primary btn-dark" name="action" value="cancel">Отмена</button>
    </div>
</form>
</t:layout>