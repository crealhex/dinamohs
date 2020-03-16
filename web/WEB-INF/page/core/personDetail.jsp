<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/page/basic/header.jsp"/>
<jsp:include page="/WEB-INF/page/basic/menu.jsp"/>
<jsp:include page="/WEB-INF/page/basic/message.jsp"/>

<div id="main">
    <div class="subtitle">
        Datos de la Persona
    </div>
    <div class="form">
        <form action="${pageContext.request.contextPath}/controller" id="form_one" method="post">
            <input type="hidden" name="action" id="action" value="savePerson">
            <input type="hidden" name="context" id="context" value="${pageContext.request.contextPath}">
            <input type="hidden" name="idPerson" value="${person.idPerson}">
            <table align="center" class="element">
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="firstName" value="${person.firstName}"></td>
                    <td><input type="text" name="lastName" value="${person.lastName}"></td>
                </tr>
            </table>
            <input type="submit" value="Guardar">
            <input type="submit" value="Cancelar" onclick="cancel()">
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/page/basic/footer.jsp"/>