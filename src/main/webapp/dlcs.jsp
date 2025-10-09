<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DLC List</title>
</head>
<body>
<h2>DLCs del videojuego</h2>
<a href="dlcs?action=new&videogameId=${videogameId}">Añadir DLC</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre DLC</th>
        <th>Precio</th>
        <th>Juego</th>
    </tr>
    <c:forEach var="dlc" items="${dlcList}">
        <tr>
            <td>${dlc.id}</td>
            <td>${dlc.name}</td>
            <td>${dlc.price}</td>
            <td>${dlc.videogameTitle}</td> <!-- Mostramos nombre del juego -->
            <td>
                <a href="dlcs?action=edit&id=${dlc.id}">Editar</a>
                <a href="dlcs?action=delete&id=${dlc.id}&videogameId=${dlc.videogameId}" onclick="return confirm('¿Seguro que quieres borrar este DLC?');">Borrar</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="videogames">Volver a videojuegos</a>
</body>
</html>
