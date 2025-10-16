<%@ include file="header.jsp" %>
<!-- 🔹 Incluye el encabezado general de la aplicación (HTML, CSS, idioma, menú, etc.)
     No ejecuta ninguna acción, solo muestra el diseño base. -->

<h1><fmt:message key="msg.videogame.title" /></h1>
<!-- 🔹 Título principal de la página, internacionalizado con las etiquetas fmt:message.
     Por ejemplo, puede mostrar “Videojuegos” o “Videogames” según el idioma. -->

<a href="videogames?action=new"><fmt:message key="msg.videogame.add" /></a>
<!-- 🔹 Enlace para crear un nuevo videojuego.
     🔸 Hace una petición GET al servlet /videogames con el parámetro action=new.
     🔸 En el servlet (VideogameServlet.doGet):
         if (action.equals("new")) → reenvía al formulario videogame-form.jsp vacío.
     Es decir, abre el formulario para crear un nuevo videojuego. -->

<table border="1">
    <thead>
        <tr>
            <th><fmt:message key="msg.videogame.id" /></th>
            <th><fmt:message key="msg.videogame.title" /></th>
            <th><fmt:message key="msg.videogame.genre" /></th>
            <th><fmt:message key="msg.videogame.platform" /></th>
            <th><fmt:message key="msg.videogame.price" /></th>
            <th><fmt:message key="msg.videogame.actions" /></th>
        </tr>
    </thead>
    <!-- 🔹 Cabecera de la tabla, donde se muestran los nombres de las columnas.
         Las etiquetas <fmt:message> cargan los textos según el idioma configurado. -->

    <tbody>
        <c:forEach var="game" items="${listGames}">
        <!-- 🔹 Bucle que recorre la lista de videojuegos (listGames)
             enviada por el servlet con request.setAttribute("listGames", dao.listAllGames()).
             Cada iteración muestra un videojuego en una fila de la tabla. -->

            <tr>
                <td>${game.id}</td>
                <td>${game.title}</td>
                <td>${game.genre}</td>
                <td>${game.platform}</td>
                <td>${game.price}</td>
                <!-- 🔹 Muestra los valores de cada videojuego accediendo a los getters de la entidad.
                     Por ejemplo, game.getTitle() → ${game.title}. -->

                <td>
                    <a href="videogames?action=edit&id=${game.id}">
                        <fmt:message key="msg.videogame.edit" />
                    </a>
                    <!-- 🔸 Enlace para editar un videojuego concreto.
                         - Hace una petición GET a /videogames?action=edit&id=ID_DEL_JUEGO.
                         - El servlet (doGet):
                               if (action.equals("edit")) {
                                   Videogame game = dao.getGameById(id);
                                   request.setAttribute("game", game);
                                   request.getRequestDispatcher("videogame-form.jsp").forward(request, response);
                               }
                         - Resultado: se abre el formulario cargado con los datos del videojuego. -->

                    <form action="videogames" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${game.id}"/>
                        <input type="submit" value="<fmt:message key='msg.videogame.delete' />"
                               onclick="return confirm('<fmt:message key='msg.videogame.confirm' />')"/>
                    </form>
                    <!-- 🔸 Formulario para eliminar un videojuego.
                         - Al hacer clic en el botón “Eliminar”, se ejecuta POST a /videogames.
                         - Envia:
                               action = delete
                               id = id del videojuego
                         - El servlet (doPost):
                               if (action.equals("delete")) {
                                   dao.deleteGame(id);
                                   response.sendRedirect("videogames");
                               }
                         - Antes de enviar, aparece un confirm() en JavaScript para preguntar si estás seguro. -->
                </td>
            </tr>
        </c:forEach>
        <!-- 🔹 Termina el bucle que genera las filas de la tabla. -->
    </tbody>
</table>

<%@ include file="footer.jsp" %>
<!-- 🔹 Incluye el pie de página (scripts, cierre de etiquetas HTML, etc.). -->

