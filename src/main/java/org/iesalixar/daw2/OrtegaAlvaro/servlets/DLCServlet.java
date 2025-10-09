package org.iesalixar.daw2.OrtegaAlvaro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesalixar.daw2.OrtegaAlvaro.dao.DLCDAO;
import org.iesalixar.daw2.OrtegaAlvaro.dao.DLCDAOImpl;
import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dlcs")
public class DLCServlet extends HttpServlet {

    private DLCDAO dlcDAO;

    @Override
    public void init() throws ServletException {
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
                    listDLCs(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertDLC(request, response);
                    break;
                case "update":
                    updateDLC(request, response);
                    break;
                case "delete":
                    deleteDLC(request, response);
                    break;
                default:
                    listDLCs(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    private void listDLCs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String idParam = request.getParameter("videogameId");
        List<DLC> dlcs = new ArrayList<>();

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int gameId = Integer.parseInt(idParam);
                dlcs = dlcDAO.getDLCsByGameId(gameId);
            } catch (NumberFormatException e) {
                dlcs = dlcDAO.getAllDLCs();
            }
        } else {
            dlcs = dlcDAO.getAllDLCs();
        }

        request.setAttribute("dlcs", dlcs);
        request.getRequestDispatcher("dlcs.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("videogameId");
        if (idParam != null && !idParam.isEmpty()) {
            request.setAttribute("videogameId", Integer.parseInt(idParam));
        }
        request.getRequestDispatcher("dlc-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DLC existing = dlcDAO.getDLCById(id);
        request.setAttribute("dlc", existing);
        request.setAttribute("videogameId", existing.getVideogameId());
        request.getRequestDispatcher("dlc-form.jsp").forward(request, response);
    }

    private void insertDLC(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name").trim();
        double price = Double.parseDouble(request.getParameter("price"));
        int videogameId = Integer.parseInt(request.getParameter("videogameId"));

        DLC dlc = new DLC(name, price, videogameId);
        dlcDAO.insertDLC(dlc);
        response.sendRedirect("dlcs?videogameId=" + videogameId);
    }

    private void updateDLC(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name").trim();
        double price = Double.parseDouble(request.getParameter("price"));
        int videogameId = Integer.parseInt(request.getParameter("videogameId"));

        DLC dlc = new DLC(id, name, price, videogameId);
        dlcDAO.updateDLC(dlc);
        response.sendRedirect("dlcs?videogameId=" + videogameId);
    }

    private void deleteDLC(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int videogameId = Integer.parseInt(request.getParameter("videogameId"));
        dlcDAO.deleteDLC(id);
        response.sendRedirect("dlcs?videogameId=" + videogameId);
    }
}

