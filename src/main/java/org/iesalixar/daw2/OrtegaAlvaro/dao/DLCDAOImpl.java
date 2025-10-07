package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DLCDAOImpl implements DLCDAO {
    private Connection conn;

    public DLCDAOImpl() {
        conn = DatabaseConnectionManager.getConnection(); // Asumiendo que tienes un Database.getConnection()
    }

    @Override
    public List<DLC> listAllByGame(int videogameId) throws SQLException {
        List<DLC> dlcs = new ArrayList<>();
        String sql = "SELECT * FROM dlc WHERE videogame_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, videogameId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            dlcs.add(new DLC(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("videogame_id")
            ));
        }
        return dlcs;
    }

    @Override
    public void insertDLC(DLC dlc) throws SQLException {
        String sql = "INSERT INTO dlc (name, price, videogame_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dlc.getName());
        ps.setDouble(2, dlc.getPrice());
        ps.setInt(3, dlc.getVideogameId());
        ps.executeUpdate();
    }

    @Override
    public void updateDLC(DLC dlc) throws SQLException {
        String sql = "UPDATE dlc SET name=?, price=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dlc.getName());
        ps.setDouble(2, dlc.getPrice());
        ps.setInt(3, dlc.getId());
        ps.executeUpdate();
    }

    @Override
    public void deleteDLC(int id) throws SQLException {
        String sql = "DELETE FROM dlc WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public DLC getDLCById(int id) throws SQLException {
        String sql = "SELECT * FROM dlc WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new DLC(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("videogame_id")
            );
        }
        return null;
    }

    @Override
    public List<DLC> getAllDLCs() {
        return List.of();
    }

    @Override
    public List<DLC> getDLCsByGameId(int gameId) {
        return List.of();
    }
}

