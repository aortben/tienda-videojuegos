package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DLCDAOImpl implements DLCDAO {

    @Override
    public List<DLC> getDLCsByGameId(int videogameId) throws SQLException {
        List<DLC> dlcs = new ArrayList<>();
        String sql = "SELECT dlc.id, dlc.name, dlc.price, dlc.videogame_id, v.title AS videogame_title " +
                "FROM dlc " +
                "JOIN videogames v ON dlc.videogame_id = v.id " +
                "WHERE dlc.videogame_id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, videogameId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DLC dlc = new DLC(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("videogame_id")
                    );
                    dlc.setVideogameTitle(rs.getString("videogame_title"));
                    dlcs.add(dlc);
                }
            }
        }
        return dlcs;
    }

    @Override
    public List<DLC> getAllDLCs() throws SQLException {
        List<DLC> dlcs = new ArrayList<>();
        String sql = "SELECT dlc.id, dlc.name, dlc.price, dlc.videogame_id, v.title AS videogame_title " +
                "FROM dlc " +
                "JOIN videogames v ON dlc.videogame_id = v.id";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DLC dlc = new DLC(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("videogame_id")
                );
                dlc.setVideogameTitle(rs.getString("videogame_title"));
                dlcs.add(dlc);
            }
        }
        return dlcs;
    }

    @Override
    public void insertDLC(DLC dlc) throws SQLException {
        String sql = "INSERT INTO dlc (name, price, videogame_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dlc.getName());
            ps.setDouble(2, dlc.getPrice());
            ps.setInt(3, dlc.getVideogameId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateDLC(DLC dlc) throws SQLException {
        String sql = "UPDATE dlc SET name=?, price=? WHERE id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dlc.getName());
            ps.setDouble(2, dlc.getPrice());
            ps.setInt(3, dlc.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteDLC(int id) throws SQLException {
        String sql = "DELETE FROM dlc WHERE id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteDLCsByGameId(int videogameId) throws SQLException {
        String sql = "DELETE FROM dlc WHERE videogame_id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, videogameId);
            ps.executeUpdate();
        }
    }

    @Override
    public DLC getDLCById(int id) throws SQLException {
        String sql = "SELECT dlc.id, dlc.name, dlc.price, dlc.videogame_id, v.title AS videogame_title " +
                "FROM dlc " +
                "JOIN videogames v ON dlc.videogame_id = v.id " +
                "WHERE dlc.id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DLC dlc = new DLC(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("videogame_id")
                    );
                    dlc.setVideogameTitle(rs.getString("videogame_title"));
                    return dlc;
                }
            }
        }
        return null;
    }
}
