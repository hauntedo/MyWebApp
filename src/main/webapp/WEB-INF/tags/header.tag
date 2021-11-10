<%@tag description="header" pageEncoding="UTF-16" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="<c:url value="/profile"/>" class="nav-link active" aria-current="page">Мой профиль</a></li>
            <li class="nav-item"><a href="<c:url value="/tasks"/>" class="nav-link">Мои задачи</a></li>
            <li class="nav-item"><a href="<c:url value="/jobs"/>" class="nav-link">Вакансии</a></li>
            <li class="nav-item"><a href="#" class="nav-link">About</a></li>
        </ul>
    </header>
</div>