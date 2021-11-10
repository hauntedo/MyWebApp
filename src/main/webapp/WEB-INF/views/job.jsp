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
<t:layout title="Работы">
    <script src="<c:url value="/js/getUserId.js"/>"></script>
<div class="change-jobs">
    <form method="get">
    <button type="submit" class="btn btn-primary btn-secondary" name="action" value="add">Объявить вакансию
    </button>
    </form>
</div>
<div class="vacancy-serp">
    <div class="vwrap"><div class="vmove">
        <c:forEach items="${jobs}" var="job">
        <div class="vitem">
            <span class="title">${job.title}</span>
            <span class="description">${job.description}</span>
        </div>
        <form method="get">
            <div class="item-control">
                <button type="submit" class="btn-respond" id="btn-respond" name="action" value="respond">
                    Откликнуться
                </button>
                <input name=id value="${job.employerId}" hidden>
            </div>
        </form>
        </c:forEach>
    </div></div>
</div>
</t:layout>
