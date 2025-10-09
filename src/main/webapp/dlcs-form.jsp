<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Formulario DLC</title>
</head>
<body>
<h2><c:choose>
        <c:when test="${dlc != null}">Editar DLC</c:when>
        <c:otherwise>Nuevo DLC</c:otherwise>
    </c:choose>
</h2>

<form action="dlcs" method="post">
    <input type="hidden" name="videogameId" value="${videogameId}" />

    <c:choose>
        <c:when test="${dlc != null}">
            <input type="hidden" name="id" value="${dlc.id}" />
            <input type="hidden" name="action" value="update" />
        </c:when>
        <c:otherwise>
            <input type="hidden" name="action" value="insert" />
        </c:otherwise>
    </c:choose>

    <label>Nombre:</label>
    <input type="text" name="name" value="${dlc != null ? dlc.name : ''}" required /><br/>

    <label>Precio:</label>
    <input type="number" step="0.01" name="price" value="${dlc != null ? dlc.price : ''}" required /><br/>

    <input type="submit" value="Guardar" />
</form>

<a href="dlcs?videogameId=${videogameId}">Volver</a>
</body>
</html>
