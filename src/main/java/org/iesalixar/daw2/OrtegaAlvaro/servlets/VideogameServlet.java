package org.iesalixar.daw2.OrtegaAlvaro.servlets;

import org.iesalixar.daw2.OrtegaAlvaro.dao.VideogameDAO;
import org.iesalixar.daw2.OrtegaAlvaro.dao.VideogameDAOImpl;
import org.iesalixar.daw2.OrtegaAlvaro.dao.DLCDAO;
import org.iesalixar.daw2.OrtegaAlvaro.dao.DLCDAOImpl;
import org.iesalixar.daw2.OrtegaAlvaro.entity.Videogame;
import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/videogames")
public class VideogameServlet extends HttpServlet {

    private VideogameDAO gameDAO;
    private DLCDAO dlcDAO;

    @Override
    public void init() throws ServletException {
        gameDAO = new VideogameDAOImpl();
        dlcDAO = new DLCDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    listGames(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertGame(request, response);
                    break;
                case "update":
                    updateGame(request, response);
                    break;
                case "delete":
                    deleteGame(request, response);
                    break;
                default:
                    listGames(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listGames(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Videogame> games = gameDAO.listAllGames();
        request.setAttribute("listGames", games);
        request.getRequestDispatcher("videogame.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("videogame-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Videogame existing = gameDAO.getGameById(id);
        request.setAttribute("game", existing);

        // Obtener DLCs del juego
        request.setAttribute("dlcList", dlcDAO.getDLCsByGameId(id));

        request.getRequestDispatcher("videogame-form.jsp").forward(request, response);
    }

    private void insertGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String title = request.getParameter("title").trim();
        String genre = request.getParameter("genre").trim();
        String platform = request.getParameter("platform").trim();
        double price = Double.parseDouble(request.getParameter("price"));

        if (gameDAO.existsGameByTitle(title)) {
            request.setAttribute("errorMessage", "Ya existe un juego con ese título.");
            request.getRequestDispatcher("videogame-form.jsp").forward(request, response);
            return;
        }

        Videogame newGame = new Videogame(title, genre, platform, price);
        gameDAO.insertGame(newGame);

        // Procesar DLC opcional
        String dlcName = request.getParameter("dlcName");
        String dlcPriceStr = request.getParameter("dlcPrice");

        if (dlcName != null && !dlcName.isBlank() && dlcPriceStr != null && !dlcPriceStr.isBlank()) {
            double dlcPrice = Double.parseDouble(dlcPriceStr);

            Videogame saved = gameDAO.getGameById(
                    gameDAO.listAllGames().getLast().getId()
            );
            if (saved != null) {
                DLC dlc = new DLC(dlcName, dlcPrice, saved.getId());
                dlcDAO.insertDLC(dlc);
            }
        }

        response.sendRedirect("videogames");
    }

    private void updateGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title").trim();
        String genre = request.getParameter("genre").trim();
        String platform = request.getParameter("platform").trim();
        double price = Double.parseDouble(request.getParameter("price"));

        if (gameDAO.existsGameByTitleAndNotId(title, id)) {
            request.setAttribute("errorMessage", "Ya existe otro juego con ese título.");
            request.getRequestDispatcher("videogame-form.jsp").forward(request, response);
            return;
        }

        Videogame updatedGame = new Videogame(id, title, genre, platform, price);
        gameDAO.updateGame(updatedGame);

        // Procesar DLC opcional al actualizar
        String dlcName = request.getParameter("dlcName");
        String dlcPriceStr = request.getParameter("dlcPrice");

        if (dlcName != null && !dlcName.isBlank() && dlcPriceStr != null && !dlcPriceStr.isBlank()) {
            double dlcPrice = Double.parseDouble(dlcPriceStr);
            // crear DLC asociado al juego existente (id conocido = id)
            DLC dlc = new DLC(dlcName, dlcPrice, id);
            dlcDAO.insertDLC(dlc); // o insertOrUpdateDLC si quieres actualizar si ya existe
        }

        response.sendRedirect("videogames");
    }

    private void deleteGame(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        gameDAO.deleteGame(id);
        // eliminar DLCs asociados si tienes ese método
        try {
            dlcDAO.deleteDLCsByGameId(id);
        } catch (Exception ignored) {}
        response.sendRedirect("videogames");
    }
}
