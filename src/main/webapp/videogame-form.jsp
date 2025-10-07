<%@ include file="header.jsp" %>
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

<c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
</c:if>

<form action="videogames" method="post">
    <input type="hidden" name="id" value="${game != null ? game.id : ''}" />
    <input type="hidden" name="action" value="${game == null ? 'insert' : 'update'}" />

    <label for="title"><fmt:message key='msg.videogame-form.title' />:</label>
    <input type="text" name="title" id="title" value="${game != null ? game.title : ''}" required />

    <label for="genre"><fmt:message key='msg.videogame-form.genre' />:</label>
    <input type="text" name="genre" id="genre" value="${game != null ? game.genre : ''}" required />

    <label for="platform"><fmt:message key='msg.videogame-form.platform' />:</label>
    <input type="text" name="platform" id="platform" value="${game != null ? game.platform : ''}" required />

    <label for="price"><fmt:message key='msg.videogame-form.price' />:</label>
    <input type="number" step="0.01" name="price" id="price" value="${game != null ? game.price : ''}" required />

    <hr>
    <h3><fmt:message key="msg.videogame-form.adddlc" /></h3>

    <label for="dlcName"><fmt:message key="msg.dlc.name" />:</label>
    <input type="text" name="dlcName" id="dlcName" value="${dlc != null ? dlc.name : ''}" />

    <label for="dlcDescription"><fmt:message key="msg.dlc.description" />:</label>
    <input type="text" name="dlcDescription" id="dlcDescription" value="${dlc != null ? dlc.description : ''}" />

    <label for="dlcPrice"><fmt:message key="msg.dlc.price" />:</label>
    <input type="number" step="0.01" name="dlcPrice" id="dlcPrice" value="${dlc != null ? dlc.price : ''}" />

    <c:choose>
        <c:when test="${game == null}">
            <input type="submit" value="<fmt:message key='msg.videogame-form.create' />" />
        </c:when>
        <c:otherwise>
            <input type="submit" value="<fmt:message key='msg.videogame-form.update' />" />
        </c:otherwise>
    </c:choose>
</form>

<a href="videogames"><fmt:message key="msg.videogame-form.returnback" /></a>
<%@ include file="footer.jsp" %>

