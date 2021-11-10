<%--
  Created by IntelliJ IDEA.
  User: Rustem
  Date: 06.11.2021
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout title="Мои задачи">
    <div class="create-task">
        <form method="get">
            <button type="submit" class="btn btn-primary btn-secondary" name="action" value="add">Создать задачу
            </button>
        </form>
    </div>
    <div class="task-list">
        <div class="twrap"><div class="tmove">
            <c:forEach items="${tasks}" var="task">
                <div class="titem">
                    <span class="title">Название: ${task.title}</span>
                    <span class="description">Описание: ${task.description}</span>
                </div>
                <form method="get">
                    <div class="row">
                        <select name="state" id="state">
                            <option selected="taskState">${task.taskState}</option>
                            <option value="OPEN">Open</option>
                            <option value="IN_PROGRESS">In progress</option>
                            <option value="RESOLVED">Resolved</option>
                            <option value="COMPLETED">Completed</option>
                        </select>
                    </div>
                    <div class="update-task">
                        <button type="submit" class="btn-task-update" name="action" value="update">Обновить
                            задачу
                        </button>
                        <input name="id" value="${task.id}" hidden>
                    </div>
                </form>
            </c:forEach>
        </div></div>
    </div>
</t:layout>
