<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/page/basic/header.jsp"/>
<jsp:include page="/WEB-INF/page/basic/menu.jsp"/>
<jsp:include page="/WEB-INF/page/basic/message.jsp"/>

<div id="main">
    <form action="${pageContext.request.contextPath}/controller" id="form_one" name="form" method="post">
        <input type="hidden" name="action" id="action">
        <div class="subtitle">
            Listado de Personas
        </div>
        <div class="table">
            <table align="center" class="element">
                <tr>
                    <th><input type="checkbox" name="checkBoxController" onclick="selectAllCheckBoxes(this)"></th>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                </tr>
                <%--@elvariable id="personList" type="java.util.List"--%>
                <c:forEach var="person" items="${personList}" varStatus="row">
                    <tr class="${ (row.count % 2) == 0 ? "row_one" : "row_two"}">
                        <td align="center"><input type="checkbox" name="person" id="persons" value="${person.idPerson}"></td>
                        <td onclick="editRegister(${row.count})">${person.idPerson}</td>
                        <td onclick="editRegister(${row.count})">${person.firstName}</td>
                        <td onclick="editRegister(${row.count})">${person.lastName}</td>
                    </tr>
                </c:forEach>
            </table>
            <button id="btnAdd" onclick="validateFormPersonList(this)" value="Agregar">Agregar</button>
            <button id="btnEdit" onclick="validateFormPersonList(this)" value="Editar">Editar</button>
            <button id="btnDelete" onclick="validateFormPersonList(this)" value="Eliminar">Eliminar</button>
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/page/basic/footer.jsp"/>