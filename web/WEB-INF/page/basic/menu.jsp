<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="nav">
    <div class="right">
        ${ user != null ? "Bienvenido " : "" }
    </div>
    <div class="left">
        <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
    </div>
    <c:if test="${user != null}">
        <a href="${pageContext.request.contextPath}/controller?action=exit">Salir</a>
    </c:if>
</div>