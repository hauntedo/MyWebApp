<%@tag description="basic layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true" %>
<html>
    <head>
        <title>${title}</title>
        <t:head/>
    </head>
    <body class="body">
        <t:header/>
        <jsp:doBody/>
        <t:footer/>
    </body>
</html>