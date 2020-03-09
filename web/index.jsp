<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/page/basic/header.jsp"/>
<jsp:include page="WEB-INF/page/basic/menu.jsp"/>

<div id="main">
    <div class="subtitle">
        <a href="${pageContext.request.contextPath}/controller?action=showPersons">Consulta nuestro catálogo de personas</a>
    </div>
</div>

<jsp:include page="WEB-INF/page/basic/footer.jsp"/>