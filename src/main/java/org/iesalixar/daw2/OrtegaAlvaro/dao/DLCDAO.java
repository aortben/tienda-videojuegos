package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;
import java.sql.SQLException;
import java.util.List;

public interface DLCDAO {
    List<DLC> listAllByGame(int videogameId) throws SQLException;
    void insertDLC(DLC dlc) throws SQLException;
    void updateDLC(DLC dlc) throws SQLException;
    void deleteDLC(int id) throws SQLException;
    DLC getDLCById(int id) throws SQLException;
    public List<DLC> getAllDLCs();
    public List<DLC> getDLCsByGameId(int gameId);
}

