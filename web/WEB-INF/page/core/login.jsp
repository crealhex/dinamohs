<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/page/basic/header.jsp"/>
<jsp:include page="/WEB-INF/page/basic/menu.jsp"/>

<div id="main">
    <div class="subtitle">
        Iniciar Sesión
    </div>
    <div class="form">
        <form action="${pageContext.request.contextPath}/controller" id="form_one" name="form_one" method="post">
            <input type="hidden" name="action" value="validateUser">
            <table class="element" align="center">
                <tr>
                    <td>Usuario:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password"></td>
                </tr>
            </table>
            <button>Enviar</button>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/page/basic/footer.jsp"/>