package org.iesalixar.daw2.OrtegaAlvaro.dao;

import org.iesalixar.daw2.OrtegaAlvaro.entity.Videogame;
import java.sql.SQLException;
import java.util.List;

public interface VideogameDAO {
    List<Videogame> listAllGames() throws SQLException;
    void insertGame(Videogame game) throws SQLException;
    void updateGame(Videogame game) throws SQLException;
    void deleteGame(int id) throws SQLException;
    Videogame getGameById(int id) throws SQLException;
    boolean existsGameByTitle(String title) throws SQLException;
    boolean existsGameByTitleAndNotId(String title, int id) throws SQLException;
}
