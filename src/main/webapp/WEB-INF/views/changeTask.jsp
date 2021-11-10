<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 10.11.2021
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Обновить задачу">
    <form method="post" class="task-form">
        <div class="row">
            <div class="col">
                <input type="text" name="title" class="form-control" placeholder="Название" value="${task.getTitle()}" disabled>
            </div>
            <div class="col">
                <input type="text" name="desc" class="form-control" placeholder="Описание"
                       value="${task.getDescription()}" disabled>
            </div>
        </div>
        <div class="row">
            <select name="states" id="states">
                <option value="OPEN">Open</option>
                <option value="IN_PROGRESS">In progress</option>
                <option value="RESOLVED">Resolved</option>
                <option value="COMPLETED">Completed</option>
            </select>
        </div>
        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-primary btn-success" name="action" value="save">Сохранить</button>
            <button type="submit" class="btn btn-primary btn-dark" name="action" value="cancel">Отмена</button>
        </div>
    </form>
</t:layout>
