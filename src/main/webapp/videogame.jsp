<%@ include file="header.jsp" %>
<h1><fmt:message key="msg.videogame.title" /></h1>
<a href="videogames?action=new"><fmt:message key="msg.videogame.add" /></a>
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
    <tbody>
        <c:forEach var="game" items="${listGames}">
            <tr>
                <td>${game.id}</td>
                <td>${game.title}</td>
                <td>${game.genre}</td>
                <td>${game.platform}</td>
                <td>${game.price}</td>
                <td>
                    <a href="videogames?action=edit&id=${game.id}">
                        <fmt:message key="msg.videogame.edit" />
                    </a>
                    <form action="videogames" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${game.id}"/>
                        <input type="submit" value="<fmt:message key='msg.videogame.delete' />"
                               onclick="return confirm('<fmt:message key='msg.videogame.confirm' />')"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="footer.jsp" %>
