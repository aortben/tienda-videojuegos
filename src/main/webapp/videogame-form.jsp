<%@ include file="header.jsp" %>
<!-- 🔹 Incluye el encabezado común de la aplicación (estructura HTML, CSS, idioma, etc.)
     Esto no ejecuta ninguna acción, solo importa el diseño general de la página. -->

<h1>
    <c:choose>
        <c:when test="${game == null}">
            <fmt:message key="msg.videogame-form.add" />
        </c:when>
        <c:otherwise>
            <fmt:message key="msg.videogame-form.edit" />
        </c:otherwise>
    </c:choose>
</h1>
<!-- 🔸 Si 'game' es null → significa que el servlet no ha enviado ningún objeto videojuego.
     ➤ Estamos en modo “añadir videojuego”.
     🔸 Si 'game' contiene datos → venimos desde “editar” en la lista,
     y el servlet cargó ese juego en el request con setAttribute("game", game). -->

<c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
</c:if>
<!-- 🔹 Si el servlet detecta un error (por ejemplo, campos vacíos o título duplicado),
     le pasa un atributo "errorMessage" para que se muestre aquí. -->

<form action="videogames" method="post">
<!-- 🔹 Este formulario se enviará al servlet con URL mapping "/videogames".
     🔹 Usa método POST, por lo que el servlet ejecutará doPost(). -->

    <input type="hidden" name="id" value="${game != null ? game.id : ''}" />
    <input type="hidden" name="action" value="${game == null ? 'insert' : 'update'}" />
    <!-- 🔹 Campos ocultos para indicar al servlet qué acción realizar.
         - Si game == null → se trata de un nuevo videojuego → action = 'insert'
         - Si game existe → se trata de una edición → action = 'update'
         El servlet usará este valor en un switch o if(action.equals("insert")) para decidir. -->

    <label for="title"><fmt:message key='msg.videogame-form.title' />:</label>
    <input type="text" name="title" id="title" value="${game != null ? game.title : ''}" required />
    <!-- 🔸 Campo del título del videojuego.
         - Si estamos editando, se muestra el valor actual del videojuego.
         - Si estamos creando, el valor estará vacío. -->

    <label for="genre"><fmt:message key='msg.videogame-form.genre' />:</label>
    <input type="text" name="genre" id="genre" value="${game != null ? game.genre : ''}" required />

    <label for="platform"><fmt:message key='msg.videogame-form.platform' />:</label>
    <input type="text" name="platform" id="platform" value="${game != null ? game.platform : ''}" required />

    <label for="price"><fmt:message key='msg.videogame-form.price' />:</label>
    <input type="number" step="0.01" name="price" id="price" value="${game != null ? game.price : ''}" required />
    <!-- 🔹 Los demás campos funcionan igual: si game != null, muestran los datos del juego. -->

    <hr>
    <h3><fmt:message key="msg.videogame-form.adddlc" /></h3>
    <!-- 🔹 Sección adicional para agregar o editar información de un DLC relacionado con el videojuego. -->

    <label for="dlcName"><fmt:message key="msg.dlc.name" />:</label>
    <input type="text" name="dlcName" id="dlcName" value="${dlc != null ? dlc.name : ''}" />

    <label for="dlcDescription"><fmt:message key="msg.dlc.description" />:</label>
    <input type="text" name="dlcDescription" id="dlcDescription" value="${dlc != null ? dlc.description : ''}" />

    <label for="dlcPrice"><fmt:message key="msg.dlc.price" />:</label>
    <input type="number" step="0.01" name="dlcPrice" id="dlcPrice" value="${dlc != null ? dlc.price : ''}" />
    <!-- 🔸 Si el servlet ha cargado un objeto DLC (por ejemplo, al editar),
         los valores se muestran. Si no, estarán vacíos. -->

    <c:choose>
        <c:when test="${game == null}">
            <input type="submit" value="<fmt:message key='msg.videogame-form.create' />" />
        </c:when>
        <c:otherwise>
            <input type="submit" value="<fmt:message key='msg.videogame-form.update' />" />
        </c:otherwise>
    </c:choose>
    <!-- 🔹 Según si se trata de un nuevo videojuego o de una edición,
         el botón cambia su texto entre “Crear” o “Actualizar”.
         🔹 En ambos casos, al pulsar el botón se hace POST al servlet.
         🔹 En el servlet:
             - Si action="insert" → llama dao.insertGame()
             - Si action="update" → llama dao.updateGame() -->
</form>

<a href="videogames"><fmt:message key="msg.videogame-form.returnback" /></a>
<!-- 🔹 Este enlace lleva al usuario de vuelta a la lista principal.
     🔹 Hace una petición GET al servlet "/videogames".
     🔹 El servlet ejecuta doGet(), carga todos los juegos con dao.listAllGames()
         y reenvía a videogames.jsp para mostrarlos. -->

<%@ include file="footer.jsp" %>
<!-- 🔹 Incluye el pie de página común del sitio (cierre de HTML, scripts, etc.) -->



