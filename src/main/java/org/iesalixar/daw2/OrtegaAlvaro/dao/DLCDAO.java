package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.DLC;
import java.sql.SQLException;
import java.util.List;

public interface DLCDAO {
    List<DLC> getDLCsByGameId(int videogameId) throws SQLException;
    void insertDLC(DLC dlc) throws SQLException;
    void updateDLC(DLC dlc) throws SQLException;
    void deleteDLC(int id) throws SQLException;
    DLC getDLCById(int id) throws SQLException;
    void deleteDLCsByGameId(int videogameId) throws SQLException;
    List<DLC> getAllDLCs() throws SQLException;
}


