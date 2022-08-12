package com.master.LevelSystem.Levels;

import com.eu.habbo.Emulator;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelManager {
    private THashMap<Integer,Level> levels = new THashMap<>();
    public LevelManager() throws SQLException {
    }

    public void updateLevels() throws SQLException {
        this.levels.clear();
        try(Connection conn = Emulator.getDatabase().getDataSource().getConnection()){
            try(PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM habron_levelsystem_levels")){
                ResultSet set = preparedStatement.executeQuery();
                while (set.next()){
                    this.levels.put(set.getInt("level"),new Level(set));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
    public  THashMap<Integer, Level> Levels(){
        return this.levels;
    }
}
