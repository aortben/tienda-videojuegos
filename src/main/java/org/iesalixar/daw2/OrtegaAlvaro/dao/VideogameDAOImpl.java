package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.Videogame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideogameDAOImpl implements VideogameDAO {

    @Override
    public List<Videogame> listAllGames() throws SQLException {
        List<Videogame> games = new ArrayList<>();
        String sql = "SELECT * FROM videogames";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                games.add(new Videogame(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("platform"),
                        rs.getDouble("price")
                ));
            }
        }
        return games;
    }

    @Override
    public void insertGame(Videogame game) throws SQLException {
        String sql = "INSERT INTO videogames (title, genre, platform, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getGenre());
            ps.setString(3, game.getPlatform());
            ps.setDouble(4, game.getPrice());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateGame(Videogame game) throws SQLException {
        String sql = "UPDATE videogames SET title=?, genre=?, platform=?, price=? WHERE id=?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getGenre());
            ps.setString(3, game.getPlatform());
            ps.setDouble(4, game.getPrice());
            ps.setInt(5, game.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteGame(int id) throws SQLException {
        String sql = "DELETE FROM videogames WHERE id=?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Videogame getGameById(int id) throws SQLException {
        String sql = "SELECT * FROM videogames WHERE id=?";
        Videogame game = null;

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    game = new Videogame(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("platform"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return game;
    }

    @Override
    public boolean existsGameByTitle(String title) throws SQLException {
        String sql = "SELECT COUNT(*) FROM videogames WHERE title=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    @Override
    public boolean existsGameByTitleAndNotId(String title, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM videogames WHERE title=? AND id<>?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }
}

